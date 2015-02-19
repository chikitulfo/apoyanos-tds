package tds.apoyanos.persistencia;

import java.util.List;

import tds.apoyanos.modelo.Usuario;

public interface UsuarioDAO {
	
	public void registrar(Usuario usuario);
	public boolean borrar(Usuario usuario);
	public void actualizarPerfil(Usuario usuario);
	public Usuario recuperar(int id);
	public List<Usuario> getUsuarios();
	
}
