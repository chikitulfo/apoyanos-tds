package tds.apoyanos.controlador;

import tds.apoyanos.exceptions.InvalidArgumentException;
import tds.apoyanos.exceptions.InvalidStateException;
import tds.apoyanos.modelo.*;
import tds.apoyanos.vista.RecompensaVista;

import java.util.Collection;
import java.util.GregorianCalendar;

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

    public boolean crearProyecto (String nombre, String descripcion, double cantidadMinima, GregorianCalendar plazoFinanciacion, String categoria, Collection<RecompensaVista> recompensas) {
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


    public void votarProyecto (String nombreProyecto) throws InvalidStateException, InvalidArgumentException {
        Proyecto p = CatalogoProyectos.getUnicaInstancia().getProyecto(nombreProyecto);
        if (p==null) {
            throw new InvalidArgumentException("Proyecto inexistente");
        }
        usuario.votar(p);
    }

    public void apoyarProyecto(String nombreProyecto, String nRecompensa, double cantidad, String comentario)
            throws InvalidStateException, InvalidArgumentException {
        Proyecto p = CatalogoProyectos.getUnicaInstancia().getProyecto(nombreProyecto);
        if (p==null) {
            throw new InvalidArgumentException("Proyecto inexistente");
        }
        usuario.apoyar(p,nRecompensa,cantidad, comentario);
    }

    public void comprobarPlazoFinalizacionProyectos() {
        Collection<Proyecto> proyectos = CatalogoProyectos.getUnicaInstancia().getAllProyectos();
        for (Proyecto p : proyectos ) {
            p.comprobarPlazo();
        }
    }

    public void hacerPregunta(String nombreProyecto, String asunto, String cuerpo) throws InvalidArgumentException {
        Proyecto p = CatalogoProyectos.getUnicaInstancia().getProyecto(nombreProyecto);
        if (p==null) {
            throw new InvalidArgumentException("Proyecto inexistente");
        }
        p.hacerPregunta(usuario, asunto, cuerpo);
    }

    public void responderPregunta(String nombreProyecto, int idPregunta, String respuesta)
            throws InvalidArgumentException, InvalidStateException {
        Proyecto p = CatalogoProyectos.getUnicaInstancia().getProyecto(nombreProyecto);
        if (p==null) {
            throw new InvalidArgumentException("Proyecto inexistente");
        }
        p.responderPregunta(usuario, idPregunta, respuesta);
    }

    public void marcarNotificacionLeida (int idNotificacion) throws InvalidArgumentException {
        usuario.marcarNotificacionLeida(idNotificacion);
    }
	
}
	