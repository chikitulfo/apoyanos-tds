package tds.apoyanos.modelo;

import tds.apoyanos.Config;
import tds.apoyanos.exceptions.InvalidArgumentException;
import tds.apoyanos.exceptions.InvalidStateException;

import java.util.*;

public class Proyecto {
    // Se utiliza una clase interna para representar el estado mediante un enum
    private enum Estado {
        VOTACION, FINANCIACION, COMPLETADO, CANCELADO}

    private int id;

    private String nombre;
    private String descripcion;
    private Usuario creador;
    private double cantidadMinima;
    private double cantidadRecaudada;
    private GregorianCalendar plazoFinanciacion;
    private int numvotos;
    private Estado estado;
    private Categoria categoria;
    private PoliticaComisiones politicaComisiones;
    private List<Recompensa> recompensas;

    public Proyecto(String nombre, String descripcion, Usuario creador, double cantidadMinima, GregorianCalendar plazoFinanciacion,
                    Categoria categoria) {
        this.id = 0;  //FIXME: Temporal mientras no haya persistencia.

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creador = creador;
        this.cantidadMinima = cantidadMinima;
        this.plazoFinanciacion = plazoFinanciacion;
        this.categoria = categoria;
        this.cantidadRecaudada = 0;
        this.recompensas = new ArrayList<Recompensa>();

        // Creación de la política de comisiones adecuada
        if (categoria == Categoria.SOCIAL) {
            politicaComisiones = new ComisionSocial();
        } else if (categoria == Categoria.CINE && cantidadMinima >6000) {
            politicaComisiones = new ComisionCinePlus();
        } else {
            politicaComisiones = new ComisionStandard();
        }

    }

    public void addVoto(){
        if (estado == Estado.VOTACION) {
            this.numvotos++;
            if (numvotos >= Config.VOTOS_NECESARIOS) {
                estado = Estado.FINANCIACION;
            }
        }
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

    public double getCantidadRecaudada() {
        return cantidadRecaudada;
    }

    public double getCantidadMinima() {
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

    public boolean esCancelado() {
        return estado == Estado.CANCELADO;
    }

    public boolean addRecompensa(String nombre, String descripcion, double cantidadMinima, int maximoParticipantes){
        for (Recompensa r : recompensas) {
            if (r.getNombre().equals(nombre)) return false;
        }
        Recompensa r = new Recompensa(nombre, descripcion, this, cantidadMinima, maximoParticipantes);
        return recompensas.add(r);
    }

    public double calcularComision(){
        return politicaComisiones.calcular(cantidadRecaudada);
    }

    /**
     * Un proyecto se crea, se le añaden recompensas, y luego se valida para
     * que entre en estado de votación.
     * @return True si el proyecto se ha podido validar
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

    public Apoyo apoyar (Usuario usuario, String nombreRecompensa, double cantidad, String comentario)
            throws InvalidArgumentException, InvalidStateException {
        if (estado==Estado.FINANCIACION) {
            for (Recompensa r : recompensas) {
                if (r.getNombre().equals(nombreRecompensa)) {
                    return r.apoyar(usuario, cantidad, comentario);
                }
            }
            throw new InvalidArgumentException("La recompensa indicada no existe en el proyecto");
        }
        throw new InvalidStateException("El proyecto no está en fase de financiación");
    }

    public void comprobarPlazo() {
        if (plazoFinanciacion.before(new GregorianCalendar())) {
            if ( estaEnVotacion() || estaEnFinanciacion()) {
                estado = Estado.CANCELADO;
                // TODO Notificar creador y usuarios de cancelación
            } else if ( esFinanciado()) {
                //TODO Notificar creador y usuarios de finalización exitosa
            }
        }
    }
}
