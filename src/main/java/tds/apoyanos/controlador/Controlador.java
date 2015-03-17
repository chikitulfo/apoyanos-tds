package tds.apoyanos.controlador;

import tds.apoyanos.modelo.*;
import tds.apoyanos.vista.RecompensaVista;

import java.util.Calendar;
import java.util.Collection;

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

    public boolean logout(){
        if (usuario != null){
            usuario = null;
            return true;
        } else {
            return false;
        }
    }

    public boolean crearProyecto (String nombre, String descripcion, int cantidadMinima, Calendar plazoFinanciacion, String categoria, Collection<RecompensaVista> recompensas) {
        if (CatalogoProyectos.getUnicaInstancia().esRegistrado(nombre)) return false;
        if (recompensas.isEmpty()) return false;

        Proyecto proyec = new Proyecto(nombre, descripcion, usuario, cantidadMinima, plazoFinanciacion, Categoria.valueOf(categoria));
        for (RecompensaVista r : recompensas) {
            proyec.addRecompensa(r.getNombre(), r.getDescripcion(), r.getCantidadMinima(), r.getMaximoParticipantes());
        }
        if (proyec.validarProyecto()) {
            usuario.addProyectoCreado(proyec);
            CatalogoProyectos.getUnicaInstancia().addProyecto(proyec);
            return true;
        }
        else return false;
    }


    public void votarProyecto (String nombreProyecto) {
        Proyecto p = CatalogoProyectos.getUnicaInstancia().getProyecto(nombreProyecto);
        usuario.votar(p);
    }

    public void apoyarProyecto(String nombreProyecto, String nRecompensa, int cantidad, String comentario){
        Proyecto p = CatalogoProyectos.getUnicaInstancia().getProyecto(nombreProyecto);
        usuario.apoyar(p,nRecompensa,cantidad, comentario);
    }
	
}
	