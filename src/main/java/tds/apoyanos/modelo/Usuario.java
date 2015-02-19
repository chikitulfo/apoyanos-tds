package tds.apoyanos.modelo;

import java.util.Collection;
import java.util.LinkedList;

public class Usuario {
    private static int nextID;

    private String nombre;
    private String apellidos;
    private String dni;
    private String email;
    private String password;
    private String login;
    private Collection<Proyecto> votos;
    private Collection<Proyecto> proyectosCreados;
    private Collection<Apoyo> apoyos;
    private Collection<Notificacion> notificaciones;
    private int id;
    //TODO: Mensajes


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
}
