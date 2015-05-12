package tds.apoyanos.modelo;


import java.util.HashMap;
import java.util.Map;

public enum Categoria {
    MUSICA("Música"),
    LIBROS("Libros"),
    CINE("Cine"),
    SOCIAL("Social"),
    SOFTWARE("Software"),
    DEPORTES("Deportes"),
    OTROS("Otros");

    private static Map<String,Categoria> map = null;
    private final String nombre;

    private Categoria (String nombre){
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }


    public static Categoria valueOfNombre(String nombre) {
        synchronized(Categoria.class) {
            if (map == null) {
                map = new HashMap();
                for (Categoria v : values()) {
                    map.put(v.nombre, v);
                }
            }
        }
        Categoria result = map.get(nombre);
        if (result == null) {
            throw new IllegalArgumentException(
                    "No enum const " + Categoria.class + "@nombre." + nombre);
        }
        return result;
    }
}
