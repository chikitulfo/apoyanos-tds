package tds.apoyanos.controlador;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import tds.apoyanos.modelo.CatalogoProyectos;
import tds.apoyanos.modelo.CatalogoUsuarios;
import tds.apoyanos.modelo.Proyecto;
import tds.apoyanos.modelo.Usuario;
import tds.apoyanos.vista.RecompensaVista;

import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.LinkedList;

public class ControladorTest extends TestCase {

    public ControladorTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( ControladorTest.class );
    }


    public void testRegistrarUsuario()
    {
        // Usuario no registrado no se encuentra en el catálogo
        assertFalse(CatalogoUsuarios.getUnicaInstancia().esRegistrado("a"));
        // Se puede registrar un usuario nuevo
        assertTrue(Controlador.getUnicaInstancia().registrarUsuario("a", "a", "a", "a", "a", "a"));
        // Usuario registrado está en catálogo
        assertTrue(CatalogoUsuarios.getUnicaInstancia().esRegistrado("a"));
        // No se puede registrar un usuario ya registrado
        assertFalse(Controlador.getUnicaInstancia().registrarUsuario("a", "a", "a", "a", "a", "a"));

    }

    public Usuario registrarUsuarioA(){
        if (!CatalogoUsuarios.getUnicaInstancia().esRegistrado("a")) {
            Controlador.getUnicaInstancia().registrarUsuario("a", "a", "a", "a", "a", "a");
        }
        return CatalogoUsuarios.getUnicaInstancia().getUsuario("a");
    }

    public void  testCrearProyecto(){
        Usuario usuario = registrarUsuarioA();
        Controlador.getUnicaInstancia().login(usuario.getLogin(), usuario.getPassword());

        Collection<RecompensaVista> recompensas = new LinkedList<RecompensaVista>();
        recompensas.add(new RecompensaVista("R1", "Recompensa 1", 10, 100));
        assertTrue(Controlador.getUnicaInstancia().crearProyecto("p", "p", 100, new GregorianCalendar(2015, 6, 1), "SOCIAL", recompensas));

        assertTrue(CatalogoProyectos.getUnicaInstancia().esRegistrado("p"));

        assertFalse(Controlador.getUnicaInstancia().crearProyecto("p", "p", 100, new GregorianCalendar(2015, 6, 1), "SOCIAL", recompensas));

        Proyecto p = CatalogoProyectos.getUnicaInstancia().getProyecto("p");
        assertTrue(usuario.getProyectosCreados().contains(p));

    }

}
