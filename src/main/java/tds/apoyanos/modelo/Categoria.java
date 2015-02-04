package tds.apoyanos.modelo;


public enum Categoria {
    MUSICA("Música"),
    LIBROS("Libros"),
    CINE("Social"),
    SOFTWARE("Software"),
    DEPORTES("Deportes");

    private final String nombre;

    private Categoria (String nombre){
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
