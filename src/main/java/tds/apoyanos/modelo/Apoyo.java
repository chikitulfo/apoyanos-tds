package tds.apoyanos.modelo;

import java.util.Date;


public class Apoyo {
	private final String comentario;
	private final Date fecha;
	private final Usuario usuario;
	private final Recompensa recompensa;
	
	public Apoyo(Usuario usuario, String comentario, Recompensa recompensa){
		this.usuario = usuario;
		this.comentario = comentario;
		this.recompensa = recompensa;
		this.fecha = new Date();
	}

	public String getComentario() {
		return comentario;
	}

	public Date getFecha() {
		return fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Recompensa getRecompensa() {
		return recompensa;
	} 
	
	public Proyecto getProyecto(){
		return recompensa.getProyecto();
	}
	
	
}
