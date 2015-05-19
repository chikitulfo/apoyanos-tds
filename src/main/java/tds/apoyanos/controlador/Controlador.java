package tds.apoyanos.controlador;

import tds.apoyanos.Config;
import tds.apoyanos.exceptions.InvalidArgumentException;
import tds.apoyanos.exceptions.InvalidStateException;
import tds.apoyanos.modelo.*;
import tds.apoyanos.persistencia.DAOException;
import tds.apoyanos.persistencia.FactoriaDAO;
import tds.apoyanos.vista.RecompensaVista;

import java.util.Collection;
import java.util.GregorianCalendar;

public final class Controlador {
    private static Controlador unicaInstancia = new Controlador();

    private Usuario usuario = null;
    private CatalogoUsuarios catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();
    private CatalogoProyectos catalogoProyectos = CatalogoProyectos.getUnicaInstancia();

	private Controlador() {	}

	public static Controlador getUnicaInstancia() {
		return unicaInstancia;
	}
	
	/**
	 * Comprueba si un usuario está registrado en el sistema
	 * (comprueba si está en el catálogo)
	 */
	public boolean esRegistrado(String login) {
		return catalogoUsuarios.esRegistrado(login);
	}
	
	public boolean registrarUsuario(String nombre,
									String apellidos, 
									String dni,
									String email,
                                    String login,
                                    String password) {
        if (catalogoUsuarios.esRegistrado(login)) return false;

        Usuario usuario = new Usuario(nombre,apellidos,dni,email,login,password);
        try {
            FactoriaDAO.getFactoriaDAO(Config.TipoDAO).getUsuarioDAO().registrar(usuario);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        catalogoUsuarios.addUsuario(usuario);
        return true;
	}
	
	public boolean login(String nombreUsuario,String password) {
		Usuario usuario = catalogoUsuarios.getUsuario(nombreUsuario);
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

    public boolean crearProyecto (String nombre, String descripcion, double cantidadMinima,
                                  GregorianCalendar plazoFinanciacion, String categoria,
                                  Collection<RecompensaVista> recompensas) throws InvalidArgumentException {
        // Si ya está registrado, o la colección de recompensas está vacía, se lanza una excepción
        if (catalogoProyectos.esRegistrado(nombre))
            throw new InvalidArgumentException("El nombre de proyecto debe de ser único");
        if ( recompensas == null || recompensas.isEmpty()) {
            throw new InvalidArgumentException("El proyecto necesita al menos una recompensa");
        }
        Proyecto proyec = new Proyecto(nombre, descripcion, usuario, cantidadMinima,
                plazoFinanciacion, Categoria.valueOfNombre(categoria));
        for (RecompensaVista r : recompensas) {
            proyec.addRecompensa(r.getNombre(), r.getDescripcion(), r.getCantidadMinima(),
                    r.getMaximoParticipantes());
        }
        if (proyec.validarProyecto()) {
            usuario.addProyectoCreado(proyec);
            catalogoProyectos.addProyecto(proyec);
            return true;
        }
        else return false;
    }

    /**
     * @param nombreProyecto
     * @return Si el proyecto ya ha sido votado o no
     * @throws InvalidArgumentException si el proyecto no existe
     */
    public boolean proyectoYaVotado(String nombreProyecto) throws InvalidArgumentException {
        Proyecto p = catalogoProyectos.getProyecto(nombreProyecto);
        if (p==null) {
            throw new InvalidArgumentException("Proyecto inexistente");
        }
        return usuario.isVotado(p);
    }


    public void votarProyecto (String nombreProyecto) throws InvalidStateException, InvalidArgumentException {
        Proyecto p = catalogoProyectos.getProyecto(nombreProyecto);
        if (p==null) {
            throw new InvalidArgumentException("Proyecto inexistente");
        }
        usuario.votar(p);

    }

    public void apoyarProyecto(String nombreProyecto, String nRecompensa, double cantidad, String comentario)
            throws InvalidStateException, InvalidArgumentException {
        Proyecto p = catalogoProyectos.getProyecto(nombreProyecto);
        if (p==null) {
            throw new InvalidArgumentException("Proyecto inexistente");
        }
        usuario.apoyar(p,nRecompensa,cantidad, comentario);
    }

    public void comprobarPlazoFinalizacionProyectos() {
        Collection<Proyecto> proyectos = catalogoProyectos.getAllProyectos();
        for (Proyecto p : proyectos ) {
            p.comprobarPlazo();
        }
    }

    public void hacerPregunta(String nombreProyecto, String asunto, String cuerpo) throws InvalidArgumentException {
        Proyecto p = catalogoProyectos.getProyecto(nombreProyecto);
        if (p==null) {
            throw new InvalidArgumentException("Proyecto inexistente");
        }
        Usuario creador = p.getCreador();
        creador.hacerPregunta(p, usuario, asunto, cuerpo);
    }

    public void responderPregunta(String nombreProyecto, int idPregunta, String respuesta)
            throws InvalidArgumentException, InvalidStateException {
        Proyecto p = catalogoProyectos.getProyecto(nombreProyecto);
        if (p==null) {
            throw new InvalidArgumentException("Proyecto inexistente");
        }
        Usuario creador = p.getCreador();
        if (creador == usuario) {
            creador.responderPregunta(idPregunta, respuesta);
        }
        else {
            throw new InvalidArgumentException("El usuario no puede responder preguntas ajenas a él.");
        }
    }

    public void marcarNotificacionLeida (int idNotificacion) throws InvalidArgumentException {
        usuario.marcarNotificacionLeida(idNotificacion);
    }
	
}
	