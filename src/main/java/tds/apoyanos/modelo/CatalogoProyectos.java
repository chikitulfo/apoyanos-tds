package tds.apoyanos.modelo;

import java.util.HashMap;

public class CatalogoProyectos {
    private static CatalogoProyectos ourInstance = new CatalogoProyectos();

    private HashMap<Integer, Proyecto> proyectos_id;
    private HashMap<String, Proyecto> proyectos_nombre;

    public static CatalogoProyectos getUnicaInstancia() {
        return ourInstance;
    }

    private CatalogoProyectos() {
        proyectos_id = new HashMap<Integer, Proyecto>();
        proyectos_nombre = new HashMap<String, Proyecto>();
    }

    public void addProyecto (Proyecto proyecto) {
        proyectos_id.put(proyecto.getId(),proyecto);
        proyectos_nombre.put(proyecto.getNombre(),proyecto);
    }

    public Proyecto getProyecto(String nombre) {
        return proyectos_id.get(nombre);
    }

    public void removeProyecto(Proyecto proyecto) {
        proyectos_id.remove(proyecto.getId());
        proyectos_nombre.remove(proyecto.getNombre());
    }

    public boolean esRegistrado(String nombre) {
        return proyectos_nombre.containsKey(nombre);
    }
}
