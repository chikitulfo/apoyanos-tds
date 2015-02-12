package tds.apoyanos.modelo;

public class CatalogoProyectos {
    private static CatalogoProyectos ourInstance = new CatalogoProyectos();

    public static CatalogoProyectos getInstance() {
        return ourInstance;
    }

    private CatalogoProyectos() {
    }
}
