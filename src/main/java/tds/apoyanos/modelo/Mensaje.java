package tds.apoyanos.modelo;

import java.util.Date;

public class Mensaje {
	private String asunto;
	private String cuerpo;
	private Date fecha;
	private Usuario emisor;
	private Usuario receptor;
	private Proyecto proyecto;
	
	public Mensaje(Usuario emisor, Usuario receptor, String asunto, String cuerpo, Proyecto proyecto){
		this.emisor = emisor;
		this.receptor = receptor;
		this.asunto = asunto;
		this.cuerpo = cuerpo;
		this.fecha = new Date();
		this.proyecto = proyecto;
	}

	public String getAsunto() {
		return asunto;
	}

	public String getCuerpo(){
		return cuerpo;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public Usuario getEmisor() {
		return emisor;
	}
	
	public Usuario getReceptor(){
		return receptor;
	}
	
	public Proyecto getProyecto(){
		return proyecto;
	}
	
}
