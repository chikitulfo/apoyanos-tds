package tds.apoyanos.exceptions;

/**
 * Excepción que se lanzará cuando el argumento pasado a un método no sea válido.
 */
public class InvalidArgumentException extends Exception {

    public InvalidArgumentException(){
        super();
    }

    public InvalidArgumentException(String s){
        super(s);
    }

}
