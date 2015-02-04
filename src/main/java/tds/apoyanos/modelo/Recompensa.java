package tds.apoyanos.modelo;

public class Recompensa {
    private String nombre;
    private String descripcion;
    private int cantidadMinima;
    private int maximoParticipantes;
    private Collection<Apoyo> apoyos;
    private Proyecto proyecto;

    public Recompensa (String nombre, String descripcion, Proyecto proyecto){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.proyecto=proyecto;
        this.apoyos = new LinkedList<Apoyo>();
        this.cantidadMinima=0;
        this.maximoParticipantes=0;
    }

    public Recompensa (String nombre, String descripcion, Proyecto proyecto, int cantidadMinima){
        this(nombre, descripcion, proyecto);
        this.cantidadMinima=cantidadMinima;
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
        return apoyos;
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
}
