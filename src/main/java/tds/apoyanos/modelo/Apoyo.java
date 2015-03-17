package tds.apoyanos.modelo;

import java.util.Date;

//TODO Falta la cantidad
//TODO Constructor que permita no usuario.

public class Apoyo {
	private String comentario;
	private Date fecha;
	private Usuario usuario;
	private Recompensa recompensa;
    private int cantidad;

    public Apoyo(String comentario, Recompensa recompensa, int cantidad){
        this.comentario = comentario;
        this.recompensa = recompensa;
        this.cantidad = cantidad;
        this.fecha = new Date();
    }

    public Apoyo(Usuario usuario, String comentario, Recompensa recompensa, int cantidad){
		this(comentario,recompensa, cantidad);
        this.usuario = usuario;
	}

    public int getCantidad() {
        return cantidad;
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
