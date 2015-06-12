package tds.apoyanos.persistencia;


import beans.Entidad;
import beans.Propiedad;
import tds.apoyanos.modelo.Notificacion;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class H2NotificacionDAO implements NotificacionDAO {
    private final ServicioPersistencia servPersistencia;
    private HashMap<Integer, Notificacion> pool;
    private final FactoriaDAO factoriaDAO;

    public H2NotificacionDAO(FactoriaDAO factoria) {
        pool = new HashMap<Integer, Notificacion>();
        servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
        factoriaDAO = factoria;
    }

    private Entidad notificacion_a_entidad(Notificacion notificacion) {
		/* Crear entidad */
        Entidad  eNotificacion = new Entidad();
        eNotificacion.setNombre("notificacion"); /*"tipo" de entidad */
		/* crear propiedades y rellenar datos */
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String tiempo = sdf.format(notificacion.getTiempo().getTime());
        String leida = Boolean.valueOf(notificacion.isLeida()).toString();
        String proyecto = Integer.toString(notificacion.getProyecto().getId());
        eNotificacion.setPropiedades(
                new ArrayList<Propiedad>(Arrays.asList(
                        new Propiedad("descripcion", notificacion.getDescripcion()),
                        new Propiedad("tiempo", tiempo),
                        new Propiedad("leida", leida),
                        new Propiedad("proyecto", proyecto)
                ))
        );
        return eNotificacion;
    }

    @Override
    public void registrar(Notificacion notificacion) {
        Entidad eNotificacion = this.notificacion_a_entidad(notificacion);
        eNotificacion = servPersistencia.registrarEntidad(eNotificacion);
        notificacion.setId(eNotificacion.getId());
        pool.put(eNotificacion.getId(), notificacion);
    }

    @Override
    public boolean borrar(Notificacion notificacion) {
        Entidad eNotificacion= servPersistencia.recuperarEntidad(notificacion.getId());
        return servPersistencia.borrarEntidad(eNotificacion);
    }

    @Override
    public void actualizarNotificacion(Notificacion notificacion) {
        Entidad eNotificacion = servPersistencia.recuperarEntidad(notificacion.getId());
        Entidad newENotificacion = notificacion_a_entidad(notificacion);
        eNotificacion.setPropiedades(newENotificacion.getPropiedades());
        servPersistencia.modificarEntidad(eNotificacion);
    }

    @Override
    public Notificacion recuperar(int id) {
        // Recuperamos del pool
        Notificacion notificacion = pool.get(id);
        if (notificacion == null) { //No está registrado en el pool de notificaciones, hay que ir a la bd
            Entidad eNotificacion = servPersistencia.recuperarEntidad(id);
			/* Se recuperan primero aquellos atributos que no dependen de recuperar otra entidad */
            String descripcion = servPersistencia.recuperarPropiedadEntidad(eNotificacion, "descripcion");
            String leida = servPersistencia.recuperarPropiedadEntidad(eNotificacion, "leida");

            String stiempo = servPersistencia.recuperarPropiedadEntidad(eNotificacion, "tiempo");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            GregorianCalendar tiempo = new GregorianCalendar() ;
            try {
                tiempo.setTime(sdf.parse(stiempo));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // Se crea la notificacion con los atributos actuales
            notificacion = new Notificacion(null, descripcion);
            notificacion.setId(id);
            notificacion.setTiempo(tiempo);
            notificacion.setLeida(Boolean.parseBoolean(leida));
            // Se introduce en el pool para evitar ciclos de llamadas recursivas
            pool.put(id, notificacion);

            //Se recupera el resto de atributos
            ProyectoDAO proyectoDAO = factoriaDAO.getProyectoDAO();
            String idProyecto = servPersistencia.recuperarPropiedadEntidad(eNotificacion, "proyecto");
            notificacion.setProyecto(proyectoDAO.recuperar(Integer.parseInt(idProyecto)));
        }
        return notificacion;
    }

}
