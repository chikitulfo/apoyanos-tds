package tds.apoyanos.modelo;

import tds.apoyanos.Config;
import tds.apoyanos.exceptions.InvalidArgumentException;
import tds.apoyanos.exceptions.InvalidStateException;
import tds.apoyanos.persistencia.DAOException;
import tds.apoyanos.persistencia.FactoriaDAO;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;

public class Usuario {
    private String nombre;
    private String apellidos;
    private String dni;
    private String email;
    private String password;
    private String login;
    private Collection<Proyecto> votos;
    private Collection<Proyecto> proyectosCreados;
    private Collection<Apoyo> apoyos;
    private Deque<Notificacion> notificaciones;
    private int id;
    private Collection<Pregunta> preguntasEmitidas;
    private Collection<Pregunta> preguntasRecibidas;


    public Usuario (String nombre, String apellidos, String dni, String email, String login, String password){
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.password = password;
        this.dni=dni;
        this.email=email;
        this.login=login;

        this.votos=new LinkedList<Proyecto>();
        this.proyectosCreados = new LinkedList<Proyecto>();
        this.apoyos = new LinkedList<Apoyo>();
        this.notificaciones = new LinkedList<Notificacion>();
        this.preguntasEmitidas = new LinkedList<Pregunta>();
        this.preguntasRecibidas = new LinkedList<Pregunta>();

    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Collection<Notificacion> getNotificaciones() {
        return new LinkedList<Notificacion>(notificaciones);
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Collection<Proyecto> getVotos() {
        return new LinkedList<Proyecto>(votos);
    }

    public Collection<Apoyo> getApoyos() {
        return new LinkedList<Apoyo>(apoyos);
    }

    public Collection<Proyecto> getProyectosCreados() {
        return new LinkedList<Proyecto>(proyectosCreados);
    }

    public Collection<Pregunta> getPreguntasEmitidas() {
        return new LinkedList<Pregunta>(preguntasEmitidas);
    }

    public Collection<Pregunta> getPreguntasRecibidas() {
        return new LinkedList<Pregunta>(preguntasRecibidas);
    }

    public void addProyectoCreado(Proyecto proyecto) {
        if (!proyectosCreados.contains(proyecto)) {
            proyectosCreados.add(proyecto);
            actualizarPersistencia();
        }
    }

    public void votar(Proyecto p) throws InvalidStateException {
        if (!votos.contains(p)){
            p.addVoto();
            p.actualizarPersistencia();
            votos.add(p);
            this.actualizarPersistencia();
        }
    }

    public void apoyar(Proyecto p, String nRecompensa, double cantidad, String comentario)
            throws InvalidStateException, InvalidArgumentException {
        Apoyo apoyo = p.apoyar(this,nRecompensa,cantidad,comentario);
        apoyos.add(apoyo);
        this.actualizarPersistencia();
    }

    public void addPreguntaEmitida(Pregunta pregunta) throws InvalidArgumentException {
        if (pregunta.getEmisor() == this) {
            preguntasEmitidas.add(pregunta);
            this.actualizarPersistencia();
        }
        else {
            throw new InvalidArgumentException("La pregunta no pertenece a este usuario");
        }
    }

    public void hacerPregunta(Proyecto proyecto, Usuario emisor, String asunto, String cuerpo) throws InvalidArgumentException {
        Pregunta pregunta = new Pregunta(emisor, this, asunto, cuerpo, proyecto);
        pregunta.registrarPersistencia();
        emisor.addPreguntaEmitida(pregunta);
        preguntasRecibidas.add(pregunta);
        this.actualizarPersistencia();
    }

    public void addNotificacion (Notificacion notificacion) {
        notificaciones.addFirst(notificacion);
        this.actualizarPersistencia();
    }

    public void responderPregunta(int idPregunta, String respuesta)
            throws InvalidArgumentException, InvalidStateException {
        for (Pregunta p: preguntasRecibidas) {
            if (p.getId() == idPregunta) {
                p.addRespuesta(respuesta);
                return;
            }
        }
        throw new InvalidArgumentException("La pregunta no se ha encontrado");
    }

    public void marcarNotificacionLeida( int idNotifiacion) throws InvalidArgumentException {
        for ( Notificacion n : notificaciones){
            if (n.getId() == idNotifiacion) {
                n.marcarLeida();
                return;
            }
        }
        throw new InvalidArgumentException("Notificación no encontrada");
    }

    public void setVotos(Collection<Proyecto> votos) {
        this.votos = new LinkedList<Proyecto>(votos);
    }

    public void setproyectosCreados(Collection<Proyecto> creados) {
        this.proyectosCreados = new LinkedList<Proyecto>(creados);
    }

    public void setApoyos(LinkedList<Apoyo> apoyos) {
        if (this.apoyos == null) {
            this.apoyos = apoyos;
        }
    }

    public void setNotificaciones(LinkedList<Notificacion> notificaciones) {
        this.notificaciones = new LinkedList<>(notificaciones);
    }

    public void setPreguntasEmitidas(LinkedList<Pregunta> preguntasEmitidas) {
        this.preguntasEmitidas = new LinkedList<>(preguntasEmitidas);
    }

    public void setPreguntasRecibidas(LinkedList<Pregunta> preguntasRecibidas) {
        this.preguntasRecibidas = new LinkedList<>(preguntasRecibidas);
    }

    public void registrarPersistencia(){
        try {
            FactoriaDAO.getFactoriaDAO(Config.TipoDAO).getUsuarioDAO().registrar(this);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public void actualizarPersistencia(){
        try {
            FactoriaDAO.getFactoriaDAO(Config.TipoDAO).getUsuarioDAO().actualizarUsuario(this);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public boolean isVotado(Proyecto p) {
        return this.votos.contains(p);
    }
}
