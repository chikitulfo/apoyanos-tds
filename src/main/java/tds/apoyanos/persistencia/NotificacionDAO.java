package tds.apoyanos.persistencia;


import tds.apoyanos.modelo.Notificacion;

public interface NotificacionDAO {
    public void registrar(Notificacion notificacion);
    public boolean borrar(Notificacion notificacion);
    public void actualizarNotificacion(Notificacion notificacion);
    public Notificacion recuperar(int id);
}
