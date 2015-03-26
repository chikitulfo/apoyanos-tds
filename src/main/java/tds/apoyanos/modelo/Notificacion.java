package tds.apoyanos.modelo;


import java.util.GregorianCalendar;

public class Notificacion {
    private int id;
    private String descripcion;
    private GregorianCalendar tiempo;
    private Proyecto proyecto;
    private boolean leida;

    Notificacion (Proyecto proyecto, String descripcion) {
        this.descripcion=descripcion;
        this.proyecto=proyecto;
        this.tiempo= new GregorianCalendar();
        this.leida = false;
        this.id = 0;
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

    public int getId() {
        return id;
    }

    public boolean isLeida(){
        return leida;
    }

    public void marcarLeida(){
        this.leida = true;
    }



}
