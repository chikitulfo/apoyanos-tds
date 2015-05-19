package tds.apoyanos.persistencia;


import beans.Entidad;
import beans.Propiedad;
import tds.apoyanos.modelo.Pregunta;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class H2PreguntaDAO implements PreguntaDAO {
    private final ServicioPersistencia servPersistencia;
    private HashMap<Integer, Pregunta> pool;
    private final FactoriaDAO factoriaDAO;

    public H2PreguntaDAO(FactoriaDAO factoria) {
        pool = new HashMap<Integer, Pregunta>();
        servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
        factoriaDAO = factoria;
    }

    private Entidad pregunta_a_entidad(Pregunta pregunta) {
		/* Crear entidad */
        Entidad  ePregunta = new Entidad();
        ePregunta.setNombre("pregunta"); /*"tipo" de entidad */
		/* crear propiedades y rellenar datos */
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = sdf.format(pregunta.getFecha().getTime());

        String emisor = new Integer(pregunta.getEmisor().getId()).toString();
        String receptor = new Integer(pregunta.getReceptor().getId()).toString();
        String proyecto = new Integer (pregunta.getProyecto().getId()).toString();
        ePregunta.setPropiedades(
                new ArrayList<Propiedad>(Arrays.asList(
                        new Propiedad("asunto", pregunta.getAsunto()),
                        new Propiedad("cuerpo", pregunta.getCuerpo()),
                        new Propiedad("fecha", fecha),
                        new Propiedad("emisor", emisor),
                        new Propiedad("receptor", receptor),
                        new Propiedad("proyecto", proyecto),
                        new Propiedad("respuesta", pregunta.getRespuesta())
                        ))
        );
        return ePregunta;
    }

    @Override
    public void registrar(Pregunta pregunta) {
        Entidad ePregunta = this.pregunta_a_entidad(pregunta);
        ePregunta = servPersistencia.registrarEntidad(ePregunta);
        pregunta.setId(ePregunta.getId());
        pool.put(ePregunta.getId(), pregunta);
    }

    @Override
    public boolean borrar(Pregunta pregunta) {
        Entidad ePregunta= servPersistencia.recuperarEntidad(pregunta.getId());
        return servPersistencia.borrarEntidad(ePregunta);
    }

    @Override
    public void actualizarPregunta(Pregunta pregunta) {
        Entidad ePregunta = servPersistencia.recuperarEntidad(pregunta.getId());
        Entidad newEPregunta = pregunta_a_entidad(pregunta);
        ePregunta.setPropiedades(newEPregunta.getPropiedades());
        servPersistencia.modificarEntidad(ePregunta);
    }

    @Override
    public Pregunta recuperar(int id) {
        // Recuperamos del pool
        Pregunta pregunta = pool.get(id);
        if (pregunta == null) { //No está registrado en el pool de preguntas, hay que ir a la bd
            Entidad ePregunta = servPersistencia.recuperarEntidad(id);
			/* Se recuperan primero aquellos atributos que no dependen de recuperar otra entidad */
            String asunto = servPersistencia.recuperarPropiedadEntidad(ePregunta, "asunto");
            String cuerpo = servPersistencia.recuperarPropiedadEntidad(ePregunta, "cuerpo");
            String respuesta = servPersistencia.recuperarPropiedadEntidad(ePregunta, "respuesta");
            // Pasar fecha de String a Calendar
            String sfecha = servPersistencia.recuperarPropiedadEntidad(ePregunta, "fecha");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            GregorianCalendar fecha = new GregorianCalendar() ;
            try {
                fecha.setTime(sdf.parse(sfecha));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // Se crea la pregunta con los atributos actuales
            pregunta = new Pregunta(null, null, asunto, cuerpo, null);
            pregunta.setId(id);
            pregunta.setFecha(fecha);
            pregunta.setRespuesta(respuesta);

            // Se introduce en el pool para evitar ciclos de llamadas recursivas
            pool.put(id, pregunta);

            //Se recupera el resto de atributos
            UsuarioDAO usuarioDAO = factoriaDAO.getUsuarioDAO();
            String idUsuario = servPersistencia.recuperarPropiedadEntidad(ePregunta, "emisor");
            pregunta.setEmisor(usuarioDAO.recuperar(Integer.parseInt(idUsuario)));
            idUsuario = servPersistencia.recuperarPropiedadEntidad(ePregunta, "receptor");
            pregunta.setReceptor(usuarioDAO.recuperar(Integer.parseInt(idUsuario)));

            ProyectoDAO proyectoDAO = factoriaDAO.getProyectoDAO();
            String idProyecto = servPersistencia.recuperarPropiedadEntidad(ePregunta, "proyecto");
            pregunta.setProyecto(proyectoDAO.recuperar(Integer.parseInt(idProyecto)));
        }
        return pregunta;
    }

}
