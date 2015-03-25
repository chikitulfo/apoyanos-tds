package tds.apoyanos.exceptions;

/**
 * Excepción que se lanzará cuando el objeto al que se intenta
 * acceder no está preparado para esa operación.
 */
public class InvalidStateException extends Exception {

    public InvalidStateException(){
        super();
    }

    public InvalidStateException(String s){
        super(s);
    }
}
