package tds.apoyanos.persistencia;


import beans.Entidad;
import beans.Propiedad;
import tds.apoyanos.modelo.Apoyo;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class H2ApoyoDAO implements ApoyoDAO {
    private final ServicioPersistencia servPersistencia;
    private HashMap<Integer, Apoyo> pool;
    private final FactoriaDAO factoriaDAO;

    public H2ApoyoDAO(FactoriaDAO factoria) {
        pool = new HashMap<Integer, Apoyo>();
        servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
        factoriaDAO = factoria;
    }

    private Entidad apoyo_a_entidad(Apoyo apoyo) {
		/* Crear entidad */
        Entidad  eApoyo = new Entidad();
        eApoyo.setNombre("apoyo"); /*"tipo" de entidad */
		/* crear propiedades y rellenar datos */
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = sdf.format(apoyo.getFecha().getTime());
        String cantidad = Double.toString(apoyo.getCantidad());
        String usuario = Integer.toString(apoyo.getUsuario().getId());
        String recompensa = Integer.toString(apoyo.getRecompensa().getId());
        eApoyo.setPropiedades(
                new ArrayList<Propiedad>(Arrays.asList(
                        new Propiedad("comentario", apoyo.getComentario()),
                        new Propiedad("fecha", fecha),
                        new Propiedad("cantidad", cantidad),
                        new Propiedad("usuario", usuario),
                        new Propiedad("recompensa", recompensa)
                ))
        );
        return eApoyo;
    }

    @Override
    public void registrar(Apoyo apoyo) {
        Entidad eApoyo = this.apoyo_a_entidad(apoyo);
        eApoyo = servPersistencia.registrarEntidad(eApoyo);
        apoyo.setId(eApoyo.getId());
        pool.put(eApoyo.getId(),apoyo);
    }

    @Override
    public boolean borrar(Apoyo apoyo) {
        Entidad eApoyo= servPersistencia.recuperarEntidad(apoyo.getId());
        return servPersistencia.borrarEntidad(eApoyo);
    }

    @Override
    public void actualizarApoyo(Apoyo apoyo) {
        Entidad eApoyo = servPersistencia.recuperarEntidad(apoyo.getId());
        Entidad newEApoyo = apoyo_a_entidad(apoyo);
        eApoyo.setPropiedades(newEApoyo.getPropiedades());
        servPersistencia.modificarEntidad(eApoyo);
    }

    @Override
    public Apoyo recuperar(int id) {
        // Recuperamos del pool
        Apoyo apoyo = pool.get(id);
        if (apoyo == null) { //No está registrado en el pool de apoyos, hay que ir a la bd
            Entidad eApoyo = servPersistencia.recuperarEntidad(id);
			/* Se recuperan primero aquellos atributos que no dependen de recuperar otra entidad */
            String comentario = servPersistencia.recuperarPropiedadEntidad(eApoyo, "comentario");
            String cantidad = servPersistencia.recuperarPropiedadEntidad(eApoyo, "cantidad");

            String sfecha = servPersistencia.recuperarPropiedadEntidad(eApoyo, "fecha");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            GregorianCalendar fecha = new GregorianCalendar();
            try {
                fecha.setTime(sdf.parse(sfecha));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // Se crea la apoyo con los atributos actuales
            apoyo = new Apoyo(comentario, null, Double.parseDouble(cantidad));
            apoyo.setId(id);
            apoyo.setFecha(fecha);
            // Se introduce en el pool para evitar ciclos de llamadas recursivas
            pool.put(id, apoyo);

            //Se recupera el resto de atributos
            UsuarioDAO usuarioDAO = factoriaDAO.getUsuarioDAO();
            String idUsuario = servPersistencia.recuperarPropiedadEntidad(eApoyo, "usuario");
            apoyo.setUsuario(usuarioDAO.recuperar(Integer.parseInt(idUsuario)));

            RecompensaDAO recompensaDAO = factoriaDAO.getRecompensaDAO();
            String idRecompensa = servPersistencia.recuperarPropiedadEntidad(eApoyo, "recompensa");
            apoyo.setRecompensa(recompensaDAO.recuperar(Integer.parseInt(idRecompensa)));

        }
        return apoyo;
    }
}
