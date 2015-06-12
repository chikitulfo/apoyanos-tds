package tds.apoyanos.modelo;

import tds.apoyanos.Config;
import tds.apoyanos.persistencia.DAOException;
import tds.apoyanos.persistencia.FactoriaDAO;

import java.util.*;

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
        return proyectos_id.get(id);
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

    public List<Proyecto> getAllProyectosVotacion() {
        List<Proyecto> lista = new LinkedList<>();
        for (Proyecto p : proyectos_id.values()) {
            if (p.estaEnVotacion())
                lista.add(p);
        }
        return lista;
    }

    public List<Proyecto> getProyectosVotacion(Categoria cat) {
        List<Proyecto> lista = new LinkedList<>();
        for (Proyecto p : proyectos_id.values()) {
            if (p.estaEnVotacion() && p.getCategoria().equals(cat))
                lista.add(p);
        }
        return lista;
    }

    public List<Proyecto> getAllProyectosFinanciacion() {
        List<Proyecto> lista = new LinkedList<>();
        for (Proyecto p : proyectos_id.values()) {
            if (p.estaEnFinanciacion())
                lista.add(p);
        }
        return lista;
    }

    public List<Proyecto> getProyectosFinanciacion(Categoria cat) {
        List<Proyecto> lista = new LinkedList<>();
        for (Proyecto p : proyectos_id.values()) {
            if (p.estaEnFinanciacion() && p.getCategoria().equals(cat))
                lista.add(p);
        }
        return lista;
    }
}
