package tds.apoyanos.persistencia;

import beans.Entidad;
import beans.Propiedad;
import tds.apoyanos.modelo.*;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import java.util.*;

/**
 * 
 * Clase que implementa el Adaptador DAO concreto de Usuario para el tipo H2.
 * 
 */

public final class H2UsuarioDAO implements UsuarioDAO {

	private FactoriaDAO factoriaDAO;
	private final ServicioPersistencia servPersistencia;
	private HashMap<Integer, Usuario> pool;

	public H2UsuarioDAO(FactoriaDAO factoria) {
		pool = new HashMap<Integer, Usuario>();
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		factoriaDAO = factoria;
	}

	private Entidad usuario_a_entidad(Usuario usuario) {
		/* Crear entidad */
		Entidad  eUsuario = new Entidad();
		eUsuario.setNombre("usuario"); /*"tipo" de entidad */
		/* crear propiedades y rellenar datos */
		StringBuilder votos = new StringBuilder();
		for ( Proyecto p : usuario.getVotos()){
			votos.append(p.getId());
			votos.append(';');
		}
		StringBuilder proyectosCreados = new StringBuilder();
		for ( Proyecto p : usuario.getProyectosCreados()){
			proyectosCreados.append(p.getId());
			proyectosCreados.append(';');
		}
		StringBuilder apoyos = new StringBuilder();
		for ( Apoyo a : usuario.getApoyos()){
			apoyos.append(a.getId());
			apoyos.append(';');
		}
		StringBuilder notificaciones = new StringBuilder();
		for ( Notificacion n : usuario.getNotificaciones()){
			notificaciones.append(n.getId());
			notificaciones.append(';');
		}
		StringBuilder preguntasemitidas = new StringBuilder();
		for ( Pregunta p : usuario.getPreguntasEmitidas()){
			preguntasemitidas.append(p.getId());
			preguntasemitidas.append(';');
		}
		StringBuilder preguntasrecibidas = new StringBuilder();
		for ( Pregunta p : usuario.getPreguntasRecibidas()){
			preguntasrecibidas.append(p.getId());
			preguntasrecibidas.append(';');
		}
		eUsuario.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("nombre", usuario.getNombre()), 
						new Propiedad("apellidos", usuario.getApellidos()),
						new Propiedad("dni",usuario.getDni()),
						new Propiedad("email", usuario.getEmail()),
						new Propiedad("login", usuario.getLogin()),
						new Propiedad("password", usuario.getPassword()),
						new Propiedad("votos", votos.toString()),
						new Propiedad("proyectoscreados", proyectosCreados.toString()),
						new Propiedad("apoyos", apoyos.toString()),
						new Propiedad("notificaciones", notificaciones.toString()),
						new Propiedad("preguntasemitidas", preguntasemitidas.toString()),
						new Propiedad("preguntasrecibidas", preguntasrecibidas.toString())
						))
				);
		return eUsuario;
	}
	
	public void registrar(Usuario usuario) {

		Entidad eUsuario = this.usuario_a_entidad(usuario);
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		usuario.setId(eUsuario.getId());
		pool.put(eUsuario.getId(),usuario);
	}
	
	/**
	 * Actualiza la información del usuario en la BBDD
	 */
	public void actualizarUsuario(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		Entidad newEUsuario = usuario_a_entidad(usuario);
		eUsuario.setPropiedades(newEUsuario.getPropiedades());
		servPersistencia.modificarEntidad(eUsuario);
	}
	
	public Usuario recuperar(int id) {
		// Recuperamos del pool
		Usuario usuario = pool.get(id);
		if (usuario == null) { //No está registrado en el pool de usuarios, hay que ir a la bd
			Entidad eUsuario = servPersistencia.recuperarEntidad(id);
			/* Se recuperan primero aquellos atributos que no dependen de recuperar otra entidad */
			String nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre");
			String apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, "apellidos");
			String dni = servPersistencia.recuperarPropiedadEntidad(eUsuario, "dni");
			String email = servPersistencia.recuperarPropiedadEntidad(eUsuario, "email");
			String login = servPersistencia.recuperarPropiedadEntidad(eUsuario, "login");
			String password = servPersistencia.recuperarPropiedadEntidad(eUsuario, "password");
			/* Se crea el usuario con los atributos actuales */
			usuario = new Usuario(nombre,apellidos,dni,email,login,password);
			usuario.setId(id);
			/* Se introduce en el pool para evitar ciclos de llamadas recursivas */
			pool.put(id,usuario);
			/* Se recupera el resto de atributos */

			ProyectoDAO proyectoDAO = factoriaDAO.getProyectoDAO();

			String idproyectosVotados = servPersistencia.recuperarPropiedadEntidad(eUsuario, "votos");
			LinkedList<Proyecto> pVotados = new LinkedList<>();
			if ( idproyectosVotados.length() > 0) {
				for (String idVotado : idproyectosVotados.split(";")) {
					pVotados.add(proyectoDAO.recuperar(Integer.parseInt(idVotado)));
				}
			}
			usuario.setVotos(pVotados);

			String idproyectosCreados = servPersistencia.recuperarPropiedadEntidad(eUsuario, "proyectoscreados");
			LinkedList<Proyecto> pCreados = new LinkedList<Proyecto>();
			if ( idproyectosCreados.length() > 0) {
				for (String idVotado : idproyectosCreados.split(";")) {
					pCreados.add(proyectoDAO.recuperar(Integer.parseInt(idVotado)));
				}
			}
			usuario.setproyectosCreados(pCreados);

			ApoyoDAO apoyoDAO = factoriaDAO.getApoyoDAO();
			String idApoyos = servPersistencia.recuperarPropiedadEntidad(eUsuario, "apoyos");
			LinkedList<Apoyo> apoyos = new LinkedList<>();
			if ( idApoyos.length() > 0) {
				for (String idVotado : idApoyos.split(";")) {
					apoyos.add(apoyoDAO.recuperar(Integer.parseInt(idVotado)));
				}
			}
			usuario.setApoyos(apoyos);

			NotificacionDAO notificacionDAO = factoriaDAO.getNotificacionDAO();
			String idNotificaciones = servPersistencia.recuperarPropiedadEntidad(eUsuario, "notificaciones");
			LinkedList<Notificacion> notificaciones = new LinkedList<>();
			if ( idNotificaciones.length() > 0) {
				for (String idVotado : idNotificaciones.split(";")) {
					notificaciones.add(notificacionDAO.recuperar(Integer.parseInt(idVotado)));
				}
			}
			usuario.setNotificaciones(notificaciones);

			PreguntaDAO preguntaDAO = factoriaDAO.getPreguntaDAO();

			String idPreguntasEmitidas = servPersistencia.recuperarPropiedadEntidad(eUsuario, "preguntasemitidas");
			LinkedList<Pregunta> pEmitidas = new LinkedList<>();
			if ( idPreguntasEmitidas.length() > 0) {
				for (String idVotado : idPreguntasEmitidas.split(";")) {
					pEmitidas.add(preguntaDAO.recuperar(Integer.parseInt(idVotado)));
				}
			}
			usuario.setPreguntasEmitidas(pEmitidas);

			String idPreguntasRecibidas = servPersistencia.recuperarPropiedadEntidad(eUsuario, "preguntasrecibidas");
			LinkedList<Pregunta> pRecibidas = new LinkedList<>();
			if ( idPreguntasRecibidas.length() > 0) {
				for (String idVotado : idPreguntasRecibidas.split(";")) {
					pRecibidas.add(preguntaDAO.recuperar(Integer.parseInt(idVotado)));
				}
			}
			usuario.setPreguntasRecibidas(pRecibidas);
		}
		return usuario;
	}
	
	public List<Usuario> getUsuarios() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades("usuario");
		List<Usuario> usuarios  = new LinkedList<Usuario>();
		
		for (Entidad eUsuario : entidades) {
			usuarios.add(recuperar(eUsuario.getId()));
		}
		return usuarios;
	}
	
	public boolean borrar(Usuario usuario) {
		Entidad eUsuario= servPersistencia.recuperarEntidad(usuario.getId());
		return servPersistencia.borrarEntidad(eUsuario);
	}
}
