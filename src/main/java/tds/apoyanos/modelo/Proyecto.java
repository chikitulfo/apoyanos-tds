package tds.apoyanos.modelo;

import tds.apoyanos.Config;
import tds.apoyanos.exceptions.InvalidArgumentException;
import tds.apoyanos.exceptions.InvalidStateException;
import tds.apoyanos.persistencia.DAOException;
import tds.apoyanos.persistencia.FactoriaDAO;

import java.util.*;

public class Proyecto {

    // Se utiliza una clase interna para representar el estado mediante un enum
    public enum Estado {
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
        this.id = 0;

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

    public void addVoto() throws InvalidStateException {
        if (estado == Estado.VOTACION) {
            this.numvotos++;
            if (numvotos >= Config.VOTOS_NECESARIOS) {
                estado = Estado.FINANCIACION;
            }
        } else {
            throw new InvalidStateException("El proyecto no está en fase de votación");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean setCreador(Usuario creador) {
        if (this.creador == null) {
            this.creador = creador;
            return true;
        }
        else return false;
    }

    public void setRecompensas(LinkedList<Recompensa> recompensas) {
        this.recompensas = new LinkedList<Recompensa>(recompensas);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setCantidadRecaudada(double cantidadRecaudada) {
        this.cantidadRecaudada = cantidadRecaudada;
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

    public void setNumvotos(int numvotos) {
        this.numvotos = numvotos;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        if (this.estado == null) {
            this.estado = estado;
        }
    }

    public PoliticaComisiones getPoliticaComisiones() {
        return politicaComisiones;
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
     * Un proyecto se valida para que entre en estado de votación, tras haber
     * sido creado y añadidas recompensas.
     * También se registra en la persistencia al ser validado
     * @return True si el proyecto se ha podido validar
     */
    public boolean validarProyecto() {
        if (estado==null && !recompensas.isEmpty()) {
            estado = Estado.VOTACION;
            Collections.sort(recompensas);
            this.registrarPersistencia();
            for (Recompensa r : recompensas) {
                r.registrarPersistencia();}
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
                    Apoyo a =  r.apoyar(usuario, cantidad, comentario);
                    addFinanciacion(cantidad);
                    return a;
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
                this.actualizarPersistencia();
                notificarUsuarios("El proyecto " + nombre + " ha sido cancelado antes de alcanzar su meta de "
                        + cantidadMinima + "." +
                        "\nLo sentimos");
            } else if ( esFinanciado()) {
                notificarUsuarios("El proyecto "+nombre+" ha finalizado la campaña logrando recaudar un total de "
                        +cantidadRecaudada+" sobre un mínimo de "+cantidadMinima+"." +
                        "\n¡Fantásticas noticias!");
            }
        }
    }

    private void addFinanciacion (double cantidad){
        cantidadRecaudada += cantidad;
        if (cantidadRecaudada >= cantidadMinima) {
            estado = Estado.COMPLETADO;
            notificarUsuarios("El proyecto "+nombre+" acaba de alcanzar su objetivo de "+cantidadMinima+"." +
                    "\n¡Fantásticas noticias!" +
                    "\nLa campaña continúa hasta vencer el plazo.");
        }
        this.actualizarPersistencia();
    }

    private void notificarUsuarios(String Mensaje){
        for (Recompensa r : recompensas) {
            for ( Usuario u :r.getMecenas()) {
                Notificacion notificacion = new Notificacion(this,Mensaje);
                notificacion.registrarPersistencia();
                u.addNotificacion(notificacion);
            }
        }
    }

    public void registrarPersistencia(){
        try {
            FactoriaDAO.getFactoriaDAO(Config.TipoDAO).getProyectoDAO().registrar(this);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public void actualizarPersistencia(){
        try {
            FactoriaDAO.getFactoriaDAO(Config.TipoDAO).getProyectoDAO().actualizarProyecto(this);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
