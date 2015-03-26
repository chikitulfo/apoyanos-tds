package tds.apoyanos.modelo;

import tds.apoyanos.exceptions.InvalidStateException;

import java.util.Date;

public class Pregunta {
    private int id=0; // FIXME Temporal mientras no haya persistencia
	private String asunto;
	private String cuerpo;
	private Date fecha;
	private Usuario emisor;
	private Usuario receptor;
	private Proyecto proyecto;
    private String respuesta;
	
	public Pregunta(Usuario emisor, Usuario receptor, String asunto, String cuerpo, Proyecto proyecto){
		this.emisor = emisor;
		this.receptor = receptor;
		this.asunto = asunto;
		this.cuerpo = cuerpo;
		this.fecha = new Date();
		this.proyecto = proyecto;
	}

    public void addRespuesta(String respuesta) throws InvalidStateException {
        if (this.respuesta == null) {
            this.respuesta = respuesta;
        } else {
            throw new InvalidStateException();
        }
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

    public String getRespuesta() {
        return respuesta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
