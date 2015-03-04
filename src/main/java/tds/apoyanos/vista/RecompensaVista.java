package tds.apoyanos.vista;

/**
 * Clase recompensa que se utiliza en la vista para enviarle las recompensas
 * al controlador, sin conocer el proyecto ni los apoyos, y sin acoplar la
 * vista al modelo.
 */
public class RecompensaVista {
    private String nombre;
    private String descripcion;
    private int cantidadMinima;
    private int maximoParticipantes;

    public RecompensaVista(String nombre, String descripcion, int cantidadMinima, int maximoParticipantes) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidadMinima = cantidadMinima;
        this.maximoParticipantes = maximoParticipantes;
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
}
