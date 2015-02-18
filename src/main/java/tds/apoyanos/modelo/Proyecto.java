package tds.apoyanos.modelo;

import java.util.*;

public class Proyecto {
    // Se utiliza una clase interna para representar el estado mediante un enum
    private enum Estado {
        VOTACION, FINANCIACION, COMPLETADO, CANCELADO};

    private String nombre;
    private String descripcion;
    private Usuario creador;
    private int cantidadMinima;
    private int cantidadRecaudada;
    private Date plazoFinanciacion;
    private int numvotos;
    private Estado estado;
    private Categoria categoria;
    //private PoliticaComisiones politicaComisiones;
    private Collection<Recompensa> recompensas;

    public Proyecto(String nombre, String descripcion, Usuario creador, int cantidadMinima, Date plazoFinanciacion,
                    Categoria categoria, Collection<Recompensa> recompensas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creador = creador;
        this.cantidadMinima = cantidadMinima;
        this.plazoFinanciacion = plazoFinanciacion;
        this.categoria = categoria;
        this.recompensas = new ArrayList<Recompensa>(recompensas); //FIXME ¿se le pasan al proyecto ya creadas, o las crea él?
        this.estado = Estado.VOTACION;
        this.numvotos = 0;
    }

    public void addVoto(){
        this.numvotos++;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadRecaudada() {
        return cantidadRecaudada;
    }

    public int getCantidadMinima() {
        return cantidadMinima;
    }

    public int getNumvotos() {
        return numvotos;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Date getPlazoFinanciacion() {
        return plazoFinanciacion;
    }

    public Usuario getCreador() {
        return creador;
    }

    public Collection<Recompensa> getRecompensas() {
        return new LinkedList<Recompensa>(recompensas);
    }

    public boolean estaEnVotacion(){
        return estado == Estado.VOTACION;
    }

    public boolean estaEnFinanciacion(){
        return estado == Estado.FINANCIACION;
    }

    public boolean esFinanciado(){
        return estado == Estado.COMPLETADO;
    }

    public void addRecompensa(String nombre, String descripcion, int cantidadMinima, int maximoParticipantes){
        //FIXME No indexamos por el nombre ¿chapuza?
        boolean existeNombre=false;
        for (Recompensa r : recompensas) {
            if (r.getNombre().equals(nombre)) existeNombre=true;
        }
        if (!existeNombre){
            Recompensa r = new Recompensa(nombre, descripcion, this, cantidadMinima, maximoParticipantes);
            recompensas.add(r);
        }
    }

    public Apoyo apoyar (String nombreRecompensa, int cantidad, String comentario){
        for (Recompensa r : recompensas) {
            if (r.getNombre().equals(nombreRecompensa)){
                return r.apoyar(cantidad, comentario);
            }
            
        }
        //FIXME lanzar excepción porque recompensa no existe
        throw new
    }
}
