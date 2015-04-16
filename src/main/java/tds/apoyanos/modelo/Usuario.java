package tds.apoyanos.modelo;

import tds.apoyanos.exceptions.InvalidArgumentException;
import tds.apoyanos.exceptions.InvalidStateException;

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
        this.id = 0;  //FIXME: Temporal mientras no haya persistencia.

        this.votos=new LinkedList<Proyecto>();
        this.proyectosCreados = new LinkedList<Proyecto>();
        this.apoyos = new LinkedList<Apoyo>();
        this.notificaciones = new LinkedList<Notificacion>();

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

    public void addProyectoCreado(Proyecto proyecto) {
        if (!proyectosCreados.contains(proyecto)) {
            proyectosCreados.add(proyecto);
        }
    }

    public void votar(Proyecto p) throws InvalidStateException {
        if (!votos.contains(p)){
            p.addVoto();
            votos.add(p);
        }
    }

    public void apoyar(Proyecto p, String nRecompensa, double cantidad, String comentario)
            throws InvalidStateException, InvalidArgumentException {
        Apoyo apoyo = p.apoyar(this,nRecompensa,cantidad,comentario);
        apoyos.add(apoyo);
    }

    public void addPreguntaEmitida(Pregunta pregunta) throws InvalidArgumentException {
        if (pregunta.getEmisor() == this) {
            preguntasEmitidas.add(pregunta);
        }
        else {
            throw new InvalidArgumentException("La pregunta no pertenece a este usuario");
        }
    }

    public void addPreguntaRecibida(Pregunta pregunta) throws InvalidArgumentException {
        if (pregunta.getReceptor() == this) {
            preguntasRecibidas.add(pregunta);
        }
        else {
            throw new InvalidArgumentException("La pregunta no pertenece a este usuario");
        }
    }

    public void addNotificacion (Notificacion notificacion) {
        notificaciones.addFirst(notificacion);
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


}
