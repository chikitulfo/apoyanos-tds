package tds.apoyanos.persistencia;


import beans.Entidad;
import beans.Propiedad;
import tds.apoyanos.modelo.Apoyo;
import tds.apoyanos.modelo.Recompensa;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class H2RecompensaDAO implements RecompensaDAO {

    private final ServicioPersistencia servPersistencia;
    private HashMap<Integer, Recompensa> pool;
    private final FactoriaDAO factoriaDAO;

    public H2RecompensaDAO(FactoriaDAO factoria) {
        pool = new HashMap<Integer, Recompensa>();
        servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
        factoriaDAO = factoria;
    }

    private Entidad recompensa_a_entidad(Recompensa recompensa) {
		/* Crear entidad */
        Entidad  eRecompensa = new Entidad();
        eRecompensa.setNombre("recompensa"); /*"tipo" de entidad */
		/* crear propiedades y rellenar datos */
        String cantMinima = new Double(recompensa.getCantidadMinima()).toString();
        String maxParticip = new Integer(recompensa.getMaximoParticipantes()).toString();
        String proyecto = new Integer(recompensa.getProyecto().getId()).toString();

        StringBuilder apoyos = new StringBuilder();
        for ( Apoyo a : recompensa.getApoyos()){
            apoyos.append(a.getId());
            apoyos.append(';');
        }
        eRecompensa.setPropiedades(
                new ArrayList<Propiedad>(Arrays.asList(
                        new Propiedad("nombre", recompensa.getNombre()),
                        new Propiedad("descripcion", recompensa.getDescripcion()),
                        new Propiedad("cantidadminima", cantMinima),
                        new Propiedad("maximoparticipantes", maxParticip),
                        new Propiedad("proyecto", proyecto),
                        new Propiedad("apoyos", apoyos.toString())
                ))
        );
        return eRecompensa;
    }

    @Override
    public void registrar(Recompensa recompensa) {
        Entidad eRecompensa = this.recompensa_a_entidad(recompensa);
        eRecompensa = servPersistencia.registrarEntidad(eRecompensa);
        recompensa.setId(eRecompensa.getId());
        pool.put(eRecompensa.getId(),recompensa);
    }

    @Override
    public boolean borrar(Recompensa recompensa) {
        return false;
    }

    @Override
    public void actualizarRecompensa(Recompensa recompensa) {

    }

    @Override
    public Recompensa recuperar(int id) {
// Recuperamos del pool
        Recompensa recompensa = pool.get(id);
        if (recompensa == null) { //No está registrado en el pool de recompensas, hay que ir a la bd
            Entidad eRecompensa = servPersistencia.recuperarEntidad(id);
			/* Se recuperan primero aquellos atributos que no dependen de recuperar otra entidad */
            String nombre = servPersistencia.recuperarPropiedadEntidad(eRecompensa, "nombre");
            String descripcion = servPersistencia.recuperarPropiedadEntidad(eRecompensa, "descripcion");
            String cantMinima = servPersistencia.recuperarPropiedadEntidad(eRecompensa, "cantidadminima");
            String maxPartic = servPersistencia.recuperarPropiedadEntidad(eRecompensa, "maximoparticipantes");

            // Se crea la recompensa con los atributos actuales
            recompensa = new Recompensa(nombre, descripcion, null,
                    Double.parseDouble(cantMinima),
                    Integer.parseInt(maxPartic));
            recompensa.setId(id);
            // Se introduce en el pool para evitar ciclos de llamadas recursivas
            pool.put(id, recompensa);

            //Se recupera el resto de atributos
            ProyectoDAO proyectoDAO = factoriaDAO.getProyectoDAO();
            String idProyecto = servPersistencia.recuperarPropiedadEntidad(eRecompensa, "proyecto");
            recompensa.setProyecto(proyectoDAO.recuperar(Integer.parseInt(idProyecto)));

            ApoyoDAO apoyoDAO = factoriaDAO.getApoyoDAO();
            String idapoyos = servPersistencia.recuperarPropiedadEntidad(eRecompensa, "apoyos");
            LinkedList<Apoyo> apoyos = new LinkedList<>();
            if (idapoyos.length() > 0) {
                for (String idRec : idapoyos.split(";")) {
                    apoyos.add(apoyoDAO.recuperar(Integer.parseInt(idRec)));
                }
            }
            recompensa.setApoyos(apoyos);
        }
        return recompensa;
    }
}
