package tds.apoyanos.controlador;


import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;
import tds.apoyanos.exceptions.InvalidArgumentException;
import tds.apoyanos.exceptions.InvalidStateException;
import tds.apoyanos.modelo.*;
import tds.apoyanos.vista.RecompensaVista;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Tests de integración del modelo de la aplicación
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ControladorTest  {


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void test10RegistrarUsuario() {
        // Usuario no registrado no se encuentra en el catálogo
        assertFalse(CatalogoUsuarios.getUnicaInstancia().esRegistrado("a"));
        // Se puede registrar un usuario nuevo
        assertTrue(Controlador.getUnicaInstancia().registrarUsuario("a", "a", "a", "a", "a", "a"));
        // Usuario registrado está en catálogo
        assertTrue(CatalogoUsuarios.getUnicaInstancia().esRegistrado("a"));
        // No se puede registrar un usuario ya registrado
        assertFalse(Controlador.getUnicaInstancia().registrarUsuario("a", "a", "a", "a", "a", "a"));

    }

    @Test
    public void test20Login(){
        // Login falso
        assertFalse(Controlador.getUnicaInstancia().login("falso", "falso"));
        // Login ok, password erróneo
        assertFalse(Controlador.getUnicaInstancia().login("a","b"));
        // Login ok
        assertTrue(Controlador.getUnicaInstancia().login("a","a"));
        // Logout ok
        assertTrue(Controlador.getUnicaInstancia().logout());
        // Logout erróneo
        assertFalse(Controlador.getUnicaInstancia().logout());

    }

    @Test
    public void test30CrearProyecto() throws InvalidArgumentException {

        //Registramos un nuevo usuario
        Controlador.getUnicaInstancia().registrarUsuario("Gran", "Luser", "123456A", "lala@lala.com", "luser", "luser");
        Controlador.getUnicaInstancia().login("luser", "luser");

        LinkedList<RecompensaVista> recompensas = new LinkedList<>();
        recompensas.add(new RecompensaVista("R1", "Me pagas una parte pequeña, y yo te lo agradezco", 10, 0));
        recompensas.add(new RecompensaVista("R2", "Tu nombre en mi bici como sponsor", 200, 10));
        recompensas.add(new RecompensaVista("R3", "Pinto la bici como tú quieras", 500, 1));
        //Registramos el proyecto
        Controlador.getUnicaInstancia().crearProyecto("El mejor proyecto", "Quiero una bici", 4000,
                new GregorianCalendar(2015,05,12), "Otros", recompensas);
        assertTrue(Controlador.getUnicaInstancia().esCreado("El mejor proyecto"));

        //Registramos un proyecto sin recompensas
        try {
            Controlador.getUnicaInstancia().crearProyecto("p", "a", 20, new GregorianCalendar(2015, 07, 12), "Otros", null);
        } catch (InvalidArgumentException e) {}
        assertFalse(Controlador.getUnicaInstancia().esCreado("p"));

        //Registramos un proyecto con categoria errónea
        try {
            Controlador.getUnicaInstancia().crearProyecto("p","a", 20, new GregorianCalendar(2015,07,12), "CATEGORIAFALSA", recompensas);
        } catch (InvalidArgumentException e) {}
        assertFalse(Controlador.getUnicaInstancia().esCreado("p"));

        //Registramos un proyecto sin fecha
        try {
            Controlador.getUnicaInstancia().crearProyecto("p","a", 20, null, "Cine", recompensas);
        } catch (InvalidArgumentException e) {}
        assertFalse(Controlador.getUnicaInstancia().esCreado("p"));

        //Registramos un proyecto con fecha en el pasado
        try {
            Controlador.getUnicaInstancia().crearProyecto("Pasado","Proyecto pasado", 20, new GregorianCalendar(2014,07,12), "Software", recompensas);
        } catch (InvalidArgumentException e) {}
        assertFalse(Controlador.getUnicaInstancia().esCreado("Pasado"));

        Controlador.getUnicaInstancia().logout();
    }

    @Test
    public void test40VotarProyecto() throws InvalidStateException, InvalidArgumentException {
        Controlador.getUnicaInstancia().login("a", "a");
        assertTrue(Controlador.getUnicaInstancia().esCreado("El mejor proyecto"));
        // El usuario no ha votado
        assertFalse(Controlador.getUnicaInstancia().isVotadoProyecto("El mejor proyecto"));
        // El proyecto no tiene votos
        assertEquals(Controlador.getUnicaInstancia().getProyecto("El mejor proyecto").getNumvotos(), 0);

        Controlador.getUnicaInstancia().votarProyecto("El mejor proyecto");

        // El usuario ha votado
        assertTrue(Controlador.getUnicaInstancia().isVotadoProyecto("El mejor proyecto"));
        // El proyecto tiene un voto
        assertEquals(Controlador.getUnicaInstancia().getProyecto("El mejor proyecto").getNumvotos(), 1);

        Controlador.getUnicaInstancia().votarProyecto("El mejor proyecto");

        // El proyecto sigue teniendo solo un voto
        assertEquals(Controlador.getUnicaInstancia().getProyecto("El mejor proyecto").getNumvotos(), 1);

        Controlador.getUnicaInstancia().logout();
    }

    @Test
    public void test50VotosAProyecto() throws InvalidStateException, InvalidArgumentException {
        Proyecto p = Controlador.getUnicaInstancia().getProyecto("El mejor proyecto");
        //No está en financiacion
        assertFalse(p.estaEnFinanciacion());
        //Está en votacion
        assertTrue(p.estaEnVotacion());
        //Registramos 50 usuarios que voten
        for (int i = 1; i<50; i++) {
            Controlador.getUnicaInstancia().registrarUsuario("u"+i,"u"+i,"u"+i,"u"+i,"u"+i,"u"+i);
            Controlador.getUnicaInstancia().login("u"+i,"u"+i);
            Controlador.getUnicaInstancia().votarProyecto("El mejor proyecto");
            Controlador.getUnicaInstancia().logout();
        }
        //Está en financiación
        assertTrue(p.estaEnFinanciacion());
    }

    @Test
    public void test60ApoyarProyecto() throws InvalidStateException, InvalidArgumentException {
        Proyecto p = Controlador.getUnicaInstancia().getProyecto("El mejor proyecto");
        Controlador.getUnicaInstancia().login("a", "a");

        assertTrue(p.getCantidadRecaudada() == 0);
        // Apoyo con menor cantidad en esa recompensa
        try {
            Controlador.getUnicaInstancia().apoyarProyecto("El mejor proyecto", "R3", 20, "");
        } catch (InvalidArgumentException e) {}
        assertTrue(p.getCantidadRecaudada() == 0);

        // Apoyo correcto
        Controlador.getUnicaInstancia().apoyarProyecto("El mejor proyecto", "R3", 500, "");

        assertTrue(p.getCantidadRecaudada() == 500);

        // No se puede un segundo apoyo en esta recompensa
        try {
            Controlador.getUnicaInstancia().apoyarProyecto("El mejor proyecto", "R3", 500, "");
        } catch (InvalidStateException e) {}
    }

    @Test
    public void test60FinanciarProyectoCompleto() throws InvalidStateException, InvalidArgumentException {
        Proyecto p = Controlador.getUnicaInstancia().getProyecto("El mejor proyecto");
        assertTrue(p.estaEnFinanciacion());
        for (int i = 1; i<11; i++) {
            Controlador.getUnicaInstancia().login("u"+i,"u"+i);
            Controlador.getUnicaInstancia().apoyarProyecto("El mejor proyecto", "R2", 250, "");
            Controlador.getUnicaInstancia().logout();
        }
        for (int i = 11; i<31; i++) {
            Controlador.getUnicaInstancia().login("u"+i,"u"+i);
            Controlador.getUnicaInstancia().apoyarProyecto("El mejor proyecto", "R1", 50, "");
            Controlador.getUnicaInstancia().logout();
        }
        assertTrue(p.estaEnFinanciacion());
        assertTrue(p.esFinanciado());

    }

    @Test
    public void test70Notificaciones(){
        Proyecto p = Controlador.getUnicaInstancia().getProyecto("El mejor proyecto");
        assertTrue(p.esFinanciado());
        assertTrue(p.getCreador().getNotificaciones().size() == 1);

        for (Recompensa r : p.getRecompensas()){
            for (Usuario u : r.getMecenas()) {
                assertTrue(u.getNotificaciones().size() == 1);
            }
        }
    }

    @Test
    public void test80HacerPregunta() throws InvalidArgumentException {
        Controlador.getUnicaInstancia().login("a","a");
        Usuario emisor = Controlador.getUnicaInstancia().getUsuario();
        Usuario receptor = Controlador.getUnicaInstancia().getProyecto("El mejor proyecto").getCreador();

        assertTrue(emisor.getPreguntasEmitidas().isEmpty());
        assertTrue(receptor.getPreguntasRecibidas().isEmpty());
        Controlador.getUnicaInstancia().hacerPregunta("El mejor proyecto", "Tu proyecto está chido wey", "JAJA");
        assertFalse(emisor.getPreguntasEmitidas().isEmpty());
        assertFalse(receptor.getPreguntasRecibidas().isEmpty());

        assertEquals(((ArrayList<Pregunta>)receptor.getPreguntasRecibidas()).get(0).getAsunto(),
                "Tu proyecto está chido wey");
    }

    @Test
    public void test90ComprobarPlazoFinalizacion(){
        Proyecto p = Controlador.getUnicaInstancia().getProyecto("El mejor proyecto");
        assertTrue(p.estaEnFinanciacion());
        assertFalse(p.esCompletado());
        Controlador.getUnicaInstancia().adelantarRelojUnDia();
        Controlador.getUnicaInstancia().comprobarPlazoFinalizacionProyectos();
        assertTrue(p.esCompletado());

    }
}
