package tds.apoyanos;

public class Config {
    public static final long PLAZO_PROYECTO = 2*7*24*60*1000;//2 semanas en milisegundos.
    public static final int VOTOS_NECESARIOS = 50;// Votos necesarios para poder empezar a financiar un proyecto.
    public static String TipoDAO="tds.apoyanos.persistencia.H2FactoriaDAO";
}
