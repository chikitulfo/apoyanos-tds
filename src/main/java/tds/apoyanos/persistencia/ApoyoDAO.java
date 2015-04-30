package tds.apoyanos.persistencia;


import tds.apoyanos.modelo.Apoyo;

public interface ApoyoDAO {
    public void registrar(Apoyo apoyo);
    public boolean borrar(Apoyo apoyo);
    public void actualizarApoyo(Apoyo apoyo);
    public Apoyo recuperar(int id);
}
