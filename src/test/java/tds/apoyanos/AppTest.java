package tds.apoyanos;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import tds.apoyanos.controlador.Controlador;
import tds.apoyanos.exceptions.InvalidArgumentException;
import tds.apoyanos.exceptions.InvalidStateException;
import tds.apoyanos.vista.RecompensaVista;

import java.util.GregorianCalendar;
import java.util.LinkedList;


public class AppTest 
    extends TestCase
{

    public AppTest( String testName )
    {
        super( testName );
    }


    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testApp() throws InvalidArgumentException, InvalidStateException {
        Controlador controlador = Controlador.getUnicaInstancia();
        controlador.registrarUsuario("Juanjo","Andreu","123455a3","email","jj","jj");
        controlador.login("jj","jj");

        LinkedList<RecompensaVista> recompensas = new LinkedList<>();
        recompensas.add(new RecompensaVista("Luna1", "Apoyas nuestro proyecto, y sales en los créditos", 10, 0));
        recompensas.add(new RecompensaVista("Luna3", "Dibujamos tu nombre en la luna con pipí", 500, 10));
        recompensas.add(new RecompensaVista("Astronauta", "Te vienes con nosotros", 5000, 1));
        recompensas.add(new RecompensaVista("Luna2", "Te invitamos a la fiesta que montaremos a la ida", 200, 100));
        //Registramos el proyecto
        GregorianCalendar manana= new GregorianCalendar();
        manana.add(GregorianCalendar.DAY_OF_YEAR, 5);
        controlador.crearProyecto("A la luna",
                "Vamos a la luna en un cohete, nos damos un paseo y nos volvemos.", 10000,
                manana, "Otros", recompensas);
        controlador.logout();

        //Votamos para pasarlo a financiación
        for (int i = 1; i<50; i++) {
            if (!controlador.esRegistrado("u"+i))
                controlador.registrarUsuario("u" + i, "u" + i, "u" + i, "u" + i, "u" + i, "u" + i);
            controlador.login("u" + i, "u" + i);
            controlador.votarProyecto("A la luna");
            controlador.logout();
        }
    }
}
