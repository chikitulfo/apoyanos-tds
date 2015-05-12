package tds.apoyanos.modelo;

import tds.apoyanos.Config;
import tds.apoyanos.persistencia.DAOException;
import tds.apoyanos.persistencia.FactoriaDAO;

import java.util.HashMap;
import java.util.List;

public class CatalogoUsuarios {

	private HashMap<Integer, Usuario> usuarios_por_ID;
	private HashMap<String, Usuario> usuarios_por_login;


	private static CatalogoUsuarios unicaInstancia = new CatalogoUsuarios();


	public static CatalogoUsuarios getUnicaInstancia() {
		return unicaInstancia;
	}

	private CatalogoUsuarios (){
		usuarios_por_ID = new HashMap<Integer, Usuario>();
		usuarios_por_login = new HashMap<String, Usuario>();
		try {
			for (Usuario usuario : recuperarUsuarios()) {
				usuarios_por_ID.put(new Integer(usuario.getId()), usuario);
				usuarios_por_login.put(usuario.getLogin(), usuario);
			}
		} catch (DAOException eDAO) {
			eDAO.printStackTrace();
		}
	}
	
	private List<Usuario> recuperarUsuarios() throws DAOException {
		return FactoriaDAO.getFactoriaDAO(Config.TipoDAO).getUsuarioDAO().getUsuarios();
	}

    public boolean esRegistrado(String nombreUsuario) {
        return usuarios_por_login.containsKey(nombreUsuario);
    }

    public boolean esRegistrado(int key) {
        return usuarios_por_ID.containsKey(key);
    }

	public Usuario getUsuario(String nombreUsuario) {
        return usuarios_por_login.get(nombreUsuario);
	}


	public Usuario getUsuario(int key) {
        return usuarios_por_ID.get(new Integer(key));
	}
	
	public void addUsuario(Usuario usuario) {
		if ( !esRegistrado(usuario.getLogin())) {
			usuarios_por_ID.put(new Integer(usuario.getId()), usuario);
			usuarios_por_login.put(usuario.getLogin(), usuario);
		}
	}
	
	public void removeUsuario(Usuario usuario) {
		usuarios_por_ID.remove(new Integer(usuario.getId()));
		usuarios_por_login.remove(usuario.getLogin());
	}
	
}
