package tds.apoyanos.modelo;



import java.util.GregorianCalendar;

public class Apoyo {
	private String comentario;
	private GregorianCalendar fecha;
	private Usuario usuario;
	private Recompensa recompensa;
    private double cantidad;
	private int id;

	public Apoyo(String comentario, Recompensa recompensa, double cantidad){
        this.comentario = comentario;
        this.recompensa = recompensa;
        this.cantidad = cantidad;
        this.fecha = new GregorianCalendar();
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

	public GregorianCalendar getFecha() {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFecha(GregorianCalendar fecha) {
		this.fecha = fecha;
	}

	public void setUsuario(Usuario usuario) {
		if ( this.usuario == null) {
			this.usuario = usuario;
		}
	}

	public void setRecompensa(Recompensa recompensa) {
		if ( this.recompensa == null) {
			this.recompensa = recompensa;
		}
	}
}
