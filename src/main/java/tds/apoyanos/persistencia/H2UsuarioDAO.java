package tds.apoyanos.persistencia;

import beans.Entidad;
import beans.Propiedad;
import tds.apoyanos.modelo.Usuario;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Clase que implementa el Adaptador DAO concreto de Usuario para el tipo H2.
 * 
 */

public final class H2UsuarioDAO implements UsuarioDAO {
	
	private ServicioPersistencia servPersistencia;
	
	public H2UsuarioDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	private Usuario entidad_a_usuario(Entidad eUsuario) {
		/* Crear usuario vacio */
		/* Rellenar datos */
		int id = eUsuario.getId();
		String nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre");
		String apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, "apellidos");
		String dni = servPersistencia.recuperarPropiedadEntidad(eUsuario, "dni");
		String email = servPersistencia.recuperarPropiedadEntidad(eUsuario, "email");
		String login = servPersistencia.recuperarPropiedadEntidad(eUsuario, "login");
		String password = servPersistencia.recuperarPropiedadEntidad(eUsuario, "password");
		
		Usuario usuario = new Usuario(nombre,apellidos,dni,email,login,password);
		return usuario;
	}
	
	private Entidad usuario_a_entidad(Usuario usuario) {
		/* Crear entidad */
		Entidad  eUsuario = new Entidad();
		eUsuario.setNombre("usuario"); /*"tipo" de entidad */
		/* crear propiedades y rellenar datos */
		eUsuario.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("nombre", usuario.getNombre()), 
						new Propiedad("apellidos", usuario.getApellidos()),
						new Propiedad("dni",usuario.getDni()),
						new Propiedad("email", usuario.getEmail()),
						new Propiedad("login", usuario.getLogin()),
						new Propiedad("password", usuario.getPassword())
						))
				);
		return eUsuario;
	}
	
	public void registrar(Usuario usuario) {
		Entidad  eUsuario = this.usuario_a_entidad(usuario);
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		usuario.setId(eUsuario.getId());
	}
	
	/**
	 * Permite que un usuario modifique su password y su email
	 */
	public void actualizarPerfil(Usuario usuario ) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "password");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "password",usuario.getPassword());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "email");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "email",usuario.getEmail());
	}
	
	public Usuario recuperar(int id) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(id);
		return entidad_a_usuario(eUsuario);
	}
	
	public List<Usuario> getUsuarios() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades("usuario");
		List<Usuario> usuarios  = new LinkedList<Usuario>();
		
		for (Entidad eUsuario : entidades) {usuarios.add(recuperar(eUsuario.getId()));}
		return usuarios;
	}
	
	public boolean borrar(Usuario usuario) {
		Entidad eUsuario;
		eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		return servPersistencia.borrarEntidad(eUsuario);
	}
}
