package tds.apoyanos.modelo;

import com.sun.istack.internal.NotNull;
import tds.apoyanos.Config;
import tds.apoyanos.exceptions.InvalidArgumentException;
import tds.apoyanos.exceptions.InvalidStateException;
import tds.apoyanos.persistencia.DAOException;
import tds.apoyanos.persistencia.FactoriaDAO;

import java.util.Collection;
import java.util.LinkedList;

public class Recompensa implements Comparable<Recompensa>{
    private int id;
    private String nombre;
    private String descripcion;
    private double cantidadMinima;
    private int maximoParticipantes;
    private Collection<Apoyo> apoyos;
    private Proyecto proyecto;

    public Recompensa (String nombre, String descripcion, Proyecto proyecto, double cantidadMinima){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.proyecto=proyecto;
        this.apoyos = new LinkedList<Apoyo>();
        this.cantidadMinima=cantidadMinima;
        this.maximoParticipantes=0;

    }

    public Recompensa (String nombre, String descripcion, Proyecto proyecto, double cantidadMinima, int maximoParticipantes){
        this(nombre, descripcion, proyecto, cantidadMinima);
        this.maximoParticipantes=maximoParticipantes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getCantidadMinima() {
        return cantidadMinima;
    }

    public int getMaximoParticipantes() {
        return maximoParticipantes;
    }

    public Collection<Apoyo> getApoyos() {
        return new LinkedList<Apoyo>(apoyos);
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setMaximoParticipantes(int maximoParticipantes) {
        this.maximoParticipantes = maximoParticipantes;
    }

    public void setCantidadMinima(int cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Usuario> getMecenas(){
        LinkedList<Usuario> mecenas= new LinkedList<Usuario>();
        for (Apoyo a : apoyos){
            mecenas.add(a.getUsuario());
        }
        return mecenas;
    }

    public Apoyo apoyar(Usuario usuario, double cantidad, String comentario)
            throws InvalidArgumentException, InvalidStateException {
        if (maximoParticipantes == 0 || apoyos.size() < maximoParticipantes) {
            if (cantidad >= cantidadMinima) {
                Apoyo apoyo = new Apoyo(usuario, comentario, this, cantidad);
                apoyo.registrarPersistencia();
                apoyos.add(apoyo);
                this.actualizarPersistencia();
                return apoyo;
            } else {
                throw new InvalidArgumentException("La cantidad es menor que la mínima para esta recompensa");
            }
        } else {
            throw new InvalidStateException("No se pueden añadir más apoyos a esta recompensa");
        }
    }

    /**
     * @param r es una Recompensa no nula.
     *
     * @throws NullPointerException si r es nulo.
     */
    @NotNull
    public int compareTo(Recompensa r) {
        if ( this.cantidadMinima < r.cantidadMinima) return -1;
        if ( this.cantidadMinima > r.cantidadMinima) return  1;
        return 0;
    }

    public void setProyecto(Proyecto proyecto) {
        if (this.proyecto == null) {
            this.proyecto = proyecto;
        }
    }

    public void setApoyos(LinkedList<Apoyo> apoyos) {
        this.apoyos = new LinkedList<>(apoyos);
    }

    public void registrarPersistencia(){
        try {
            FactoriaDAO.getFactoriaDAO(Config.TipoDAO).getRecompensaDAO().registrar(this);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public void actualizarPersistencia(){
        try {
            FactoriaDAO.getFactoriaDAO(Config.TipoDAO).getRecompensaDAO().actualizarRecompensa(this);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
