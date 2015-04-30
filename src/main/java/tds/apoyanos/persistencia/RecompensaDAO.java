package tds.apoyanos.persistencia;


import tds.apoyanos.modelo.Recompensa;

public interface RecompensaDAO {
    public void registrar(Recompensa recompensa);
    public boolean borrar(Recompensa recompensa);
    public void actualizarRecompensa(Recompensa recompensa);
    public Recompensa recuperar(int id);
}
