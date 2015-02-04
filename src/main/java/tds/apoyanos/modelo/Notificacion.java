package tds.apoyanos.modelo;


import java.util.GregorianCalendar;

public class Notificacion {
    private String descripcion;
    private GregorianCalendar tiempo;
    private Proyecto proyecto;

    Notificacion (Proyecto proyecto, String descripcion) {
        this.descripcion=descripcion;
        this.proyecto=proyecto;
        this.tiempo= new GregorianCalendar();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public GregorianCalendar getTiempo() {
        return tiempo;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }
}
