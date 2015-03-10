package tds.apoyanos.modelo;

import java.util.*;

public class Proyecto {
    // Se utiliza una clase interna para representar el estado mediante un enum
    private enum Estado {
        VOTACION, FINANCIACION, COMPLETADO, CANCELADO}

    private int id;

    private String nombre;
    private String descripcion;
    private Usuario creador;
    private int cantidadMinima;
    private int cantidadRecaudada;
    private Calendar plazoFinanciacion;
    private int numvotos;
    private Estado estado;
    private Categoria categoria;
    //private PoliticaComisiones politicaComisiones;
    private List<Recompensa> recompensas;

    public Proyecto(String nombre, String descripcion, Usuario creador, int cantidadMinima, Calendar plazoFinanciacion,
                    Categoria categoria) {
        this.id = 0;  //FIXME: Temporal mientras no haya persistencia.

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creador = creador;
        this.cantidadMinima = cantidadMinima;
        this.plazoFinanciacion = plazoFinanciacion;
        this.categoria = categoria;

        this.recompensas = new ArrayList<Recompensa>();
    }

    public void addVoto(){
        this.numvotos++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Calendar getPlazoFinanciacion() {
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

    public boolean addRecompensa(String nombre, String descripcion, int cantidadMinima, int maximoParticipantes){
        for (Recompensa r : recompensas) {
            if (r.getNombre().equals(nombre)) return false;
        }
        Recompensa r = new Recompensa(nombre, descripcion, this, cantidadMinima, maximoParticipantes);
        return recompensas.add(r);
    }

    /**
     * Un proyecto se crea, se le añaden recompensas, y luego se valida para
     * que entre en estado de votación.
     * @return
     */
    public boolean validarProyecto() {
        if (estado==null && !recompensas.isEmpty()) {
            estado = Estado.VOTACION;
            Collections.sort(recompensas);
            return true;
        }
        else {
            return false;
        }
    }

    public Apoyo apoyar (Usuario usuario, String nombreRecompensa, int cantidad, String comentario){
        for (Recompensa r : recompensas) {
            if (r.getNombre().equals(nombreRecompensa)){
                return r.apoyar(usuario, cantidad, comentario);
            }
            
        }
        //FIXME lanzar excepción porque recompensa no existe
        return null;
    }
}
