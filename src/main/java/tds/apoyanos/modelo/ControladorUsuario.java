package umu.tds.controlador;

import umu.tds.modelo.CatalogoUsuarios;
import umu.tds.modelo.Usuario;

public final class ControladorUsuario {
		
	private Usuario usuario = null;
	private static ControladorUsuario unicaInstancia = new ControladorUsuario();
	
	private ControladorUsuario() {	}
	
	public static ControladorUsuario getUnicaInstancia() {
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
	
}
	