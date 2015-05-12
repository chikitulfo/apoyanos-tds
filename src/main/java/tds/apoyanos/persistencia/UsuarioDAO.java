package tds.apoyanos.persistencia;

import tds.apoyanos.modelo.Usuario;

import java.util.List;

public interface UsuarioDAO {
	
	public void registrar(Usuario usuario);
	public boolean borrar(Usuario usuario);
	public void actualizarUsuario(Usuario usuario);
	public Usuario recuperar(int id);
	public List<Usuario> getUsuarios();
	
}
