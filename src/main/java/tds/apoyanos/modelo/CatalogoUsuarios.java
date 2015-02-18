package umu.tds.modelo;

import java.util.HashMap;
import java.util.List;

import umu.tds.Constantes;
import umu.tds.dao.DAOException;
import umu.tds.dao.FactoriaDAO;
import umu.tds.dao.UsuarioDAO;

public class CatalogoUsuarios {

	private HashMap<Integer, Usuario> usuarios_por_ID;
	private HashMap<String, Usuario> usuarios_por_login;
	
	
	private static CatalogoUsuarios unicaInstancia = new CatalogoUsuarios();
	private List<Usuario> listaUsuarios;

	public static CatalogoUsuarios getUnicaInstancia() {
		return unicaInstancia;
	}

	private CatalogoUsuarios (){
		usuarios_por_ID = new HashMap<Integer, Usuario>();
		usuarios_por_login = new HashMap<String, Usuario>();
		try {
			listaUsuarios = recuperarUsuarios();
			for (Usuario usuario : listaUsuarios) {
				usuarios_por_ID.put(new Integer(usuario.getId()), usuario);
				usuarios_por_login.put(usuario.getLogin(), usuario);
			}
		} catch (DAOException eDAO) {
			eDAO.printStackTrace();
		}
	}
	
	private List<Usuario> recuperarUsuarios() throws DAOException {
		return FactoriaDAO.getFactoriaDAO(Constantes.TipoDAO).getUsuarioDAO().getUsuarios();
	}
	
	public Usuario getUsuario(String nombreUsuario) {
		return usuarios_por_login.get(nombreUsuario);
	}

	public boolean esRegistrado(String nombreUsuario) {
		return usuarios_por_login.containsKey(nombreUsuario);
	}

	public Usuario getUsuario(int key) {
		return usuarios_por_ID.get(new Integer(key));
	}
	
	public void addUsuario(Usuario usuario) {
		UsuarioDAO usuarioBD = null; /*Adaptador DAO para almacenar el nuevo usuario en la BD*/
		try {
			usuarioBD=FactoriaDAO.getFactoriaDAO(Constantes.TipoDAO).getUsuarioDAO();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		usuarioBD.registrar(usuario);
		usuarios_por_ID.put(new Integer(usuario.getId()), usuario);
		usuarios_por_login.put(usuario.getLogin(), usuario);
		listaUsuarios.add(usuario);
	}
	
	public void removeUsuario(Usuario usuario) {
		usuarios_por_ID.remove(new Integer(usuario.getId()));
		usuarios_por_login.remove(usuario.getLogin());
		listaUsuarios.remove(usuario);
		UsuarioDAO usuarioBD=null; /*Adaptador DAO para borrar el usuario de la BD*/
		try {
			usuarioBD=FactoriaDAO.getFactoriaDAO(Constantes.TipoDAO).getUsuarioDAO();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		usuarioBD.borrar(usuario);		
	}
	
}
