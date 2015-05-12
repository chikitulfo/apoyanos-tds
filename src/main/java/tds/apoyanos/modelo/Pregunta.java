package tds.apoyanos.modelo;

import tds.apoyanos.Config;
import tds.apoyanos.exceptions.InvalidStateException;
import tds.apoyanos.persistencia.DAOException;
import tds.apoyanos.persistencia.FactoriaDAO;

import java.util.GregorianCalendar;

public class Pregunta {
    private int id=0; // FIXME Temporal mientras no haya persistencia
	private String asunto;
	private String cuerpo;
	private GregorianCalendar fecha;
	private Usuario emisor;
	private Usuario receptor;
	private Proyecto proyecto;
    private String respuesta;
	
	public Pregunta(Usuario emisor, Usuario receptor, String asunto, String cuerpo, Proyecto proyecto){
		this.emisor = emisor;
		this.receptor = receptor;
		this.asunto = asunto;
		this.cuerpo = cuerpo;
		this.fecha = new GregorianCalendar();
		this.proyecto = proyecto;
	}

    public void addRespuesta(String respuesta) throws InvalidStateException {
        if (this.respuesta == null) {
            this.respuesta = respuesta;
			this.actualizarPersistencia();
        } else {
            throw new InvalidStateException("La pregunta ya está contestada");
        }
    }

	public String getAsunto() {
		return asunto;
	}

	public String getCuerpo(){
		return cuerpo;
	}
	
	public GregorianCalendar getFecha() {
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

	public void setFecha(GregorianCalendar fecha) {
		this.fecha = fecha;
	}

	public void setProyecto(Proyecto proyecto) {
		if ( this.proyecto == null) {
			this.proyecto = proyecto;
		}
	}

	public void setEmisor(Usuario emisor) {
		if ( this.emisor == null) {
			this.emisor = emisor;
		}
	}

	public void setReceptor(Usuario receptor) {
		if ( this.emisor == null) {
			this.receptor = receptor;
		}
	}

	public void registrarPersistencia(){
		try {
			FactoriaDAO.getFactoriaDAO(Config.TipoDAO).getPreguntaDAO().registrar(this);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public void actualizarPersistencia(){
		try {
			FactoriaDAO.getFactoriaDAO(Config.TipoDAO).getPreguntaDAO().actualizarPregunta(this);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
}
