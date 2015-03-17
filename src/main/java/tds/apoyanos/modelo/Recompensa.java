package tds.apoyanos.modelo;

import java.util.Collection;
import java.util.LinkedList;

public class Recompensa implements Comparable<Recompensa>{
    private String nombre;
    private String descripcion;
    private int cantidadMinima;
    private int maximoParticipantes;
    private Collection<Apoyo> apoyos;
    private Proyecto proyecto;

    public Recompensa (String nombre, String descripcion, Proyecto proyecto, int cantidadMinima){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.proyecto=proyecto;
        this.apoyos = new LinkedList<Apoyo>();
        this.cantidadMinima=cantidadMinima;
        this.maximoParticipantes=0;

    }

    public Recompensa (String nombre, String descripcion, Proyecto proyecto, int cantidadMinima, int maximoParticipantes){
        this(nombre, descripcion, proyecto, cantidadMinima);
        this.maximoParticipantes=maximoParticipantes;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidadMinima() {
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


    public Apoyo apoyar(Usuario usuario, int cantidad, String comentario) {
        if (maximoParticipantes == 0 || apoyos.size() < maximoParticipantes) {
            Apoyo apoyo = new Apoyo(usuario, comentario, this, cantidad);
            apoyos.add(apoyo);
            return apoyo;
        } else {
            return null; // FIXME Lanzar excepción, no caben más participantes
        }
    }

    /**
     * @param r es una Recompensa no nula.
     *
     * @throws NullPointerException si r es nulo.
     */
    public int compareTo(Recompensa r) {
        if ( this.cantidadMinima < r.cantidadMinima) return -1;
        if ( this.cantidadMinima > r.cantidadMinima) return  1;
        return 0;
    }
}
