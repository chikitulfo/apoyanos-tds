package tds.apoyanos.controlador;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import tds.apoyanos.exceptions.InvalidArgumentException;
import tds.apoyanos.exceptions.InvalidStateException;
import tds.apoyanos.modelo.*;
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

    private Usuario registrarUsuario(String u){
        if (!CatalogoUsuarios.getUnicaInstancia().esRegistrado(u)) {
            Controlador.getUnicaInstancia().registrarUsuario(u, u, u, u, u, u);
        }
        return CatalogoUsuarios.getUnicaInstancia().getUsuario(u);
    }

    public void  testCrearProyecto(){
        Usuario usuario = registrarUsuario("tCP");
        Controlador.getUnicaInstancia().login(usuario.getLogin(), usuario.getPassword());

        Collection<RecompensaVista> recompensas = new LinkedList<RecompensaVista>();
        recompensas.add(new RecompensaVista("R1", "Recompensa 1", 10, 100));
        assertTrue(Controlador.getUnicaInstancia().crearProyecto("p", "p", 100, new GregorianCalendar(2015, 6, 1), "SOCIAL", recompensas));

        assertTrue(CatalogoProyectos.getUnicaInstancia().esRegistrado("p"));

        assertFalse(Controlador.getUnicaInstancia().crearProyecto("p", "p", 100, new GregorianCalendar(2015, 6, 1), "SOCIAL", recompensas));

        Proyecto p = CatalogoProyectos.getUnicaInstancia().getProyecto("p");
        assertTrue(usuario.getProyectosCreados().contains(p));
    }

    private Proyecto registrarProyecto(String p) {
        if(!CatalogoProyectos.getUnicaInstancia().esRegistrado(p)) {
            Usuario usuario = registrarUsuario("rP");
            Controlador.getUnicaInstancia().login(usuario.getLogin(), usuario.getPassword());

            Collection<RecompensaVista> recompensas = new LinkedList<RecompensaVista>();
            recompensas.add(new RecompensaVista("R1", "Recompensa 1", 10, 100));
            Controlador.getUnicaInstancia().crearProyecto(p, p, 100, new GregorianCalendar(2015, 6, 1), "SOCIAL", recompensas);
        }
        return CatalogoProyectos.getUnicaInstancia().getProyecto(p);
    }

    public void testVotarProyecto() throws InvalidStateException {
        Proyecto p = registrarProyecto("tVP");
        assertTrue(p.estaEnVotacion());
        assertEquals(p.getNumvotos(), 0);
        Controlador.getUnicaInstancia().votarProyecto(p.getNombre());
        assertEquals(p.getNumvotos(),1);
        while (p.getNumvotos()<49){ p.addVoto(); }
        assertEquals(p.getNumvotos(),49);
        assertTrue(p.estaEnVotacion());
        p.addVoto();
        assertFalse(p.estaEnVotacion());
        assertTrue(p.estaEnFinanciacion());

    }

    public void testApoyarProyecto() throws InvalidStateException, InvalidArgumentException {
        Usuario u = registrarUsuario("UtAP");
        Proyecto p = registrarProyecto("tAP");
        Controlador.getUnicaInstancia().logout();
        Controlador.getUnicaInstancia().login(u.getLogin(), u.getPassword());
        while (!p.estaEnFinanciacion()) {p.addVoto();}
        assertTrue(u.getApoyos().isEmpty());
        Controlador.getUnicaInstancia().apoyarProyecto(p.getNombre(),"R1", 150,"Lalala");
        assertFalse(u.getApoyos().isEmpty());
        assertEquals( ((LinkedList<Apoyo>)u.getApoyos()).getFirst().getCantidad() , 150.0);
    }
}
