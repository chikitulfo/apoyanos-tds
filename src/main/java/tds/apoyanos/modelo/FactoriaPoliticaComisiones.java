package tds.apoyanos.modelo;

/**
 * Factoría que encapsula la lógica y análisis de información necesarios
 * para elegir la Política de comisiones adecuada para el proyecto
 */
public class FactoriaPoliticaComisiones {
    public static PoliticaComisiones createPoliticaComisiones(Categoria categoria, double cantidadMinima ){
        // Creación de la política de comisiones adecuada
        if (categoria == Categoria.SOCIAL) {
            return new ComisionSocial();
        } else if (categoria == Categoria.CINE && cantidadMinima >6000) {
            return new ComisionCinePlus();
        } else {
            return new ComisionStandard();
        }
    }
}
