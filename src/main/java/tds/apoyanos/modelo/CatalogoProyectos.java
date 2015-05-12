package tds.apoyanos.modelo;

import tds.apoyanos.Config;
import tds.apoyanos.persistencia.DAOException;
import tds.apoyanos.persistencia.FactoriaDAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

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
        try {
            for (Proyecto proyecto : recuperarProyectos()) {
                proyectos_id.put(new Integer(proyecto.getId()), proyecto);
                proyectos_nombre.put(proyecto.getNombre(), proyecto);
            }
        } catch (DAOException eDAO) {
            eDAO.printStackTrace();
        }
    }

    private List<Proyecto> recuperarProyectos() throws DAOException {
        return FactoriaDAO.getFactoriaDAO(Config.TipoDAO).getProyectoDAO().getProyectos();
    }

    public void addProyecto (Proyecto proyecto) {
        if ( !esRegistrado(proyecto.getNombre())) {
            proyectos_id.put(proyecto.getId(), proyecto);
            proyectos_nombre.put(proyecto.getNombre(), proyecto);
        }
    }

    public Proyecto getProyecto(String nombre) {
        return proyectos_nombre.get(nombre);
    }

    public Proyecto getProyecto(int id) {
        return proyectos_nombre.get(id);
    }

    public boolean esRegistrado(String nombre) {
        return proyectos_nombre.containsKey(nombre);
    }

    public boolean esRegistrado(int id) {
        return proyectos_id.containsKey(id);
    }

    public void removeProyecto(Proyecto proyecto) {
        proyectos_id.remove(new Integer(proyecto.getId()));
        proyectos_nombre.remove(proyecto.getNombre());
    }

    public Collection<Proyecto> getAllProyectos() {
        return new ArrayList<Proyecto>(proyectos_id.values());
    }
}
