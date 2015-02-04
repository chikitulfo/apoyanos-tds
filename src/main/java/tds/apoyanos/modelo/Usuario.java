package tds.apoyanos.modelo;

// BLABALBALBLABLABLABLA
import java.util.Collection;
import java.util.LinkedList;

public class Usuario {
    private static int nextID;

    private final int id;
    private String nombre;
    private String apellidos;
    private String dni;
    private String email;
    private String contraseña;
    private Collection<Proyecto> votos;
    private Collection<Proyecto> proyectosCreados;
    private Collection<Apoyo> apoyos;
    private Collection<Notificacion> notificaciones;
    //TODO: Mensajes


    Usuario (String nombre, String apellidos, String dni, String email, String contraseña){
        this.id=nextID;
        nextID++;
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.contraseña=contraseña;
        this.dni=dni;
        this.email=email;

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

    public String getContraseña() {
        return contraseña;
    }

    public Collection<Notificacion> getNotificaciones() {
        return notificaciones;
    }


}
