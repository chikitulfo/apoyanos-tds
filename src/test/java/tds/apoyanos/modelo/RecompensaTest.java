package tds.apoyanos.modelo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tds.apoyanos.exceptions.InvalidArgumentException;
import tds.apoyanos.exceptions.InvalidStateException;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class RecompensaTest {
    Usuario usuario1, usuario2;
    Proyecto proyecto;
    Recompensa R1, R2;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        usuario1 = new Usuario("Alicia","Apellido","12345678A", "alicia@alice.com", "alicia", "alicia");
        usuario2 = new Usuario("Beatriz","Buñuel","12345678B", "b@tds.com", "bea", "bea");
        proyecto = new Proyecto("P1", "p1", usuario1, 1000, new GregorianCalendar(2015, 06,31),Categoria.DEPORTES);
        R1 = new Recompensa("R1", "Descripcion reshulona",proyecto, 20, 1);
        R2 = new Recompensa("R2", "Descripcion reshulona 2",proyecto, 25);
    }

    @Test
    public void testCreador(){
        assertEquals(R1.getMaximoParticipantes(), 1);
        assertEquals(R2.getMaximoParticipantes(),0);
    }
    @Test
    public void testApoyar() throws InvalidStateException, InvalidArgumentException {

        thrown.expect(InvalidArgumentException.class);
        R1.apoyar(usuario2,15,"");

        R1.apoyar(usuario2,20,"");
        assertTrue(R1.getMecenas().contains(usuario2));

        thrown.expect(InvalidStateException.class);
        Usuario usu3 = new Usuario("u3","u3","u3", "u3@tds.com", "u3", "u3");
        R1.apoyar(usu3,25,"");
    }

}
