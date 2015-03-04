package tds.apoyanos.controlador;

import tds.apoyanos.modelo.CatalogoUsuarios;
import tds.apoyanos.modelo.Categoria;
import tds.apoyanos.modelo.Proyecto;
import tds.apoyanos.modelo.Usuario;
import tds.apoyanos.vista.RecompensaVista;

import java.util.Collection;
import java.util.Date;

public final class Controlador {
    private static Controlador unicaInstancia = new Controlador();

    private Usuario usuario = null;

	private Controlador() {	}

	public static Controlador getUnicaInstancia() {
		return unicaInstancia;
	}
	
	/**
	 * Comprueba si un usuario está registrado en el sistema
	 * (comprueba si está en el catálogo)
	 */
	public boolean esRegistrado(String login) {
		return CatalogoUsuarios.getUnicaInstancia().esRegistrado(login);
	}
	
	public boolean registrarUsuario(String nombre,
									String apellidos, 
									String dni,
									String email,
									String login,
									String password) {

			if (CatalogoUsuarios.getUnicaInstancia().esRegistrado(login)) return false;

			Usuario usuario = new Usuario(nombre,apellidos,dni,email,login,password);
			CatalogoUsuarios.getUnicaInstancia().addUsuario(usuario);
			return true;
	}
	
	public boolean login(String nombreUsuario,String password) {
		Usuario usuario = CatalogoUsuarios.getUnicaInstancia().getUsuario(nombreUsuario);
		if (usuario != null) {
			if (usuario.getPassword().equals(password)) {
				this.usuario = usuario;
				return true;
			}
		}
		return false;
	}

    public boolean crearProyecto (String nombre, String descripcion, int cantidadMinima, Date plazoFinanciacion, String categoria, Collection<RecompensaVista> recompensas) {
        Proyecto proyec = new Proyecto(nombre, descripcion, usuario, cantidadMinima, plazoFinanciacion, Categoria.valueOf(categoria));
        for (RecompensaVista r : recompensas) {
            proyec.addRecompensa(r.getNombre(), r.getDescripcion(), r.getCantidadMinima(), r.getMaximoParticipantes());
        }
        return proyec.validarProyecto();
    }
	
}
	