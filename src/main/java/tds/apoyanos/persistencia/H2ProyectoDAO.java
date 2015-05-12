package tds.apoyanos.persistencia;


import beans.Entidad;
import beans.Propiedad;
import tds.apoyanos.modelo.Categoria;
import tds.apoyanos.modelo.Proyecto;
import tds.apoyanos.modelo.Recompensa;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class H2ProyectoDAO implements ProyectoDAO {

    private final ServicioPersistencia servPersistencia;
    private HashMap<Integer, Proyecto> pool;
    private final FactoriaDAO factoriaDAO;

    public H2ProyectoDAO(FactoriaDAO factoria) {
        pool = new HashMap<Integer, Proyecto>();
        servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
        factoriaDAO = factoria;
    }

    private Entidad proyecto_a_entidad(Proyecto proyecto) {
		/* Crear entidad */
        Entidad  eProyecto = new Entidad();
        eProyecto.setNombre("proyecto"); /*"tipo" de entidad */
		/* crear propiedades y rellenar datos */
        String creador = new Integer(proyecto.getCreador().getId()).toString();
        String cantMinima = new Double(proyecto.getCantidadMinima()).toString();
        String cantRecaudada = new Double(proyecto.getCantidadRecaudada()).toString();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar fecha = proyecto.getPlazoFinanciacion();
        String plazofinanciacion = sdf.format(fecha.getTime());

        String numVotos = new Integer(proyecto.getNumvotos()).toString();
        String estado = proyecto.getEstado().name();
        String categoria = proyecto.getCategoria().getNombre();

        StringBuilder recompensas = new StringBuilder();
        for ( Recompensa r : proyecto.getRecompensas()){
            recompensas.append(r.getId());
            recompensas.append(';');
        }
        eProyecto.setPropiedades(
                new ArrayList<Propiedad>(Arrays.asList(
                        new Propiedad("nombre", proyecto.getNombre()),
                        new Propiedad("descripcion", proyecto.getDescripcion()),
                        new Propiedad("creador", creador),
                        new Propiedad("cantidadminima", cantMinima),
                        new Propiedad("cantidadrecaudada", cantRecaudada),
                        new Propiedad("plazofinanciacion", plazofinanciacion ),
                        new Propiedad("numvotos", numVotos),
                        new Propiedad("estado", estado),
                        new Propiedad("categoria", categoria),
                        new Propiedad("recompensas", recompensas.toString())
                ))
        );
        return eProyecto;
    }

    @Override
    public void registrar(Proyecto proyecto) {
        Entidad eProyecto = this.proyecto_a_entidad(proyecto);
        eProyecto = servPersistencia.registrarEntidad(eProyecto);
        proyecto.setId(eProyecto.getId());
        pool.put(eProyecto.getId(),proyecto);
    }


    @Override
    public void actualizarProyecto(Proyecto proyecto) {
        Entidad eProyecto = servPersistencia.recuperarEntidad(proyecto.getId());
        Entidad newEProyecto = proyecto_a_entidad(proyecto);
        eProyecto.setPropiedades(newEProyecto.getPropiedades());
        servPersistencia.modificarEntidad(eProyecto);
    }


    @Override
    public boolean borrar(Proyecto proyecto) {
        Entidad eProyecto= servPersistencia.recuperarEntidad(proyecto.getId());
        return servPersistencia.borrarEntidad(eProyecto);
    }

    @Override
    public Proyecto recuperar(int id) {
        // Recuperamos del pool
        Proyecto proyecto = pool.get(id);
        if (proyecto == null) { //No está registrado en el pool de proyectos, hay que ir a la bd
            Entidad eProyecto = servPersistencia.recuperarEntidad(id);
			/* Se recuperan primero aquellos atributos que no dependen de recuperar otra entidad */
            String nombre = servPersistencia.recuperarPropiedadEntidad(eProyecto, "nombre");
            String descripcion = servPersistencia.recuperarPropiedadEntidad(eProyecto, "descripcion");
            String cantMinima = servPersistencia.recuperarPropiedadEntidad(eProyecto, "cantidadminima");
            String cantRecaudada = servPersistencia.recuperarPropiedadEntidad(eProyecto, "cantidadrecaudada");

            String plazo = servPersistencia.recuperarPropiedadEntidad(eProyecto, "plazofinanciacion");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            GregorianCalendar cplazo = new GregorianCalendar();
            try {
                cplazo.setTime(sdf.parse(plazo));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String numvotos = servPersistencia.recuperarPropiedadEntidad(eProyecto, "numvotos");
            String estado = servPersistencia.recuperarPropiedadEntidad(eProyecto, "estado");
            String categoria = servPersistencia.recuperarPropiedadEntidad(eProyecto, "categoria");
            // Se crea el proyecto con los atributos actuales
            proyecto = new Proyecto(nombre, descripcion, null, Double.parseDouble(cantMinima), cplazo, Categoria.valueOfNombre(categoria));
            proyecto.setId(id);
            proyecto.setEstado(Proyecto.Estado.valueOf(estado));
            proyecto.setNumvotos(Integer.parseInt(numvotos));
            proyecto.setCantidadRecaudada(Double.parseDouble(cantRecaudada));
            // Se introduce en el pool para evitar ciclos de llamadas recursivas
            pool.put(id, proyecto);

            //Se recupera el resto de atributos
            UsuarioDAO usuarioDAO = factoriaDAO.getUsuarioDAO();
            String idCreador = servPersistencia.recuperarPropiedadEntidad(eProyecto, "creador");
            proyecto.setCreador(usuarioDAO.recuperar(Integer.parseInt(idCreador)));

            RecompensaDAO recompensaDAO = factoriaDAO.getRecompensaDAO();
            String idRecompensas = servPersistencia.recuperarPropiedadEntidad(eProyecto, "recompensas");
            LinkedList<Recompensa> recompensas = new LinkedList<>();
            if (idRecompensas.length() > 0) {
                for (String idRec : idRecompensas.split(";")) {
                    recompensas.add(recompensaDAO.recuperar(Integer.parseInt(idRec)));
                }
            }
            proyecto.setRecompensas(recompensas);
        }
        return proyecto;
    }

    @Override
    public List<Proyecto> getProyectos() {
        List<Entidad> entidades = servPersistencia.recuperarEntidades("proyecto");
        List<Proyecto> proyectos  = new LinkedList<Proyecto>();

        for (Entidad eProyecto : entidades) {proyectos.add(recuperar(eProyecto.getId()));}
        return proyectos;
    }
}
