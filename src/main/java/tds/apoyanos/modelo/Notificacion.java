package tds.apoyanos.modelo;


import tds.apoyanos.Config;
import tds.apoyanos.persistencia.DAOException;
import tds.apoyanos.persistencia.FactoriaDAO;

import java.util.GregorianCalendar;

public class Notificacion {
    private int id;
    private String descripcion;
    private GregorianCalendar tiempo;
    private Proyecto proyecto;
    private boolean leida;

    public Notificacion (Proyecto proyecto, String descripcion) {
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
        if (!this.leida) {
            this.leida = true;
            this.actualizarPersistencia();
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLeida(boolean leida) {
        this.leida = leida;
    }

    public void setTiempo(GregorianCalendar tiempo) {
        this.tiempo = tiempo;
    }

    public void setProyecto(Proyecto proyecto) {
        if ( this.proyecto == null ) {
            this.proyecto = proyecto;
        }
    }

    public void registrarPersistencia(){
        try {
            FactoriaDAO.getFactoriaDAO(Config.TipoDAO).getNotificacionDAO().registrar(this);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public void actualizarPersistencia(){
        try {
            FactoriaDAO.getFactoriaDAO(Config.TipoDAO).getNotificacionDAO().actualizarNotificacion(this);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
