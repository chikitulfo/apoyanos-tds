package tds.apoyanos.modelo;

import java.util.Date;

public class Apoyo {
	private String comentario;
	private Date fecha;
	private Usuario usuario;
	private Recompensa recompensa;
    private double cantidad;

    public Apoyo(String comentario, Recompensa recompensa, double cantidad){
        this.comentario = comentario;
        this.recompensa = recompensa;
        this.cantidad = cantidad;
        this.fecha = new Date();
    }

    public Apoyo(Usuario usuario, String comentario, Recompensa recompensa, double cantidad){
		this(comentario,recompensa, cantidad);
        this.usuario = usuario;
	}

    public double getCantidad() {
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
