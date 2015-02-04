package tds.apoyanos.modelo;

/**
 * Created by Julio on 16/11/2014.
 */
public enum EstadoProyecto implements Comparable {
    EN_CREACION, EN_VOTACION, EN_FINANCIACION, FINANCIADO, NO_FINANCIADO;

    public String toString (){
        switch (this){
            case EN_CREACION: return "EN_CREACION";
            case EN_VOTACION: return "EN_VOTACION";
            case EN_FINANCIACION: return "EN_FINACIACION";
            case FINANCIADO: return "FINANCIADO";
            case NO_FINANCIADO: return "NO_FINANCIADO";
            default: return "";
        }
    }

    public boolean equals(EstadoProyecto other){
        if (other == null) return false;
        return other.toString().compareTo(this.toString()) == 0;
    }
}
