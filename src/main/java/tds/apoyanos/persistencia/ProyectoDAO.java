package tds.apoyanos.persistencia;


import tds.apoyanos.modelo.Proyecto;

import java.util.List;

public interface ProyectoDAO {
    public void registrar(Proyecto proyecto);
    public boolean borrar(Proyecto proyecto);
    public void actualizarProyecto(Proyecto proyecto);
    public Proyecto recuperar(int id);
    public List<Proyecto> getProyectos();
}
