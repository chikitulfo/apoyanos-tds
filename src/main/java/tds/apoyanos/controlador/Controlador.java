package tds.apoyanos.controlador;

import tds.apoyanos.Config;
import tds.apoyanos.exceptions.InvalidArgumentException;
import tds.apoyanos.exceptions.InvalidStateException;
import tds.apoyanos.modelo.*;
import tds.apoyanos.modelo.Proyecto.Estado;
import tds.apoyanos.persistencia.DAOException;
import tds.apoyanos.persistencia.FactoriaDAO;
import tds.apoyanos.vista.RecompensaVista;
import umu.tds.cargador.ComponenteCargadorFinanciacion;
import umu.tds.cargador.FinanciacionEvent;
import umu.tds.cargador.IFinanciacionListener;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

public final class Controlador implements IFinanciacionListener {
    private static Controlador unicaInstancia = new Controlador();

    private Usuario usuario = null;
    private CatalogoUsuarios catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();
    private CatalogoProyectos catalogoProyectos = CatalogoProyectos.getUnicaInstancia();
    private int adelantoReloj = 0;

	private Controlador() {	}

	public static Controlador getUnicaInstancia() {
		return unicaInstancia;
	}
	
	/**
	 * Comprueba si un usuario está registrado en el sistema
	 * (comprueba si está en el catálogo)
	 */
	public boolean esRegistrado(String login) {
		return catalogoUsuarios.esRegistrado(login);
	}
	
	public boolean registrarUsuario(String nombre,
									String apellidos, 
									String dni,
									String email,
                                    String login,
                                    String password) {
        if (catalogoUsuarios.esRegistrado(login)) return false;

        Usuario usuario = new Usuario(nombre,apellidos,dni,email,login,password);
        registrarPersistencia(usuario);
        catalogoUsuarios.addUsuario(usuario);
        return true;
	}
	
	public boolean login(String nombreUsuario,String password) {
		Usuario usuario = catalogoUsuarios.getUsuario(nombreUsuario);
		if (usuario != null) {
			if (usuario.getPassword().equals(password)) {
				this.usuario = usuario;
				return true;
			}
		}
		return false;
	}

    public boolean logout(){
        if (usuario != null){
            usuario = null;
            return true;
        } else {
            return false;
        }
    }

    public Usuario getUsuario(){
        return usuario;
    }

    public boolean esCreado(String nombreProyecto){
        return catalogoProyectos.esRegistrado(nombreProyecto);
    }
    
    public Proyecto getProyecto(String nombreProyecto){
        return catalogoProyectos.getProyecto(nombreProyecto);
    }

    public boolean crearProyecto (String nombre, String descripcion, double cantidadMinima,
                                  GregorianCalendar plazoFinanciacion, String categoria,
                                  Collection<RecompensaVista> recompensas) throws InvalidArgumentException {
        // Si ya está registrado, o la colección de recompensas está vacía, se lanza una excepción
        if (catalogoProyectos.esRegistrado(nombre))
            throw new InvalidArgumentException("El nombre de proyecto debe de ser único");
        if ( recompensas == null || recompensas.isEmpty()) {
            throw new InvalidArgumentException("El proyecto necesita al menos una recompensa");
        }
        if (plazoFinanciacion == null || plazoFinanciacion.before(new GregorianCalendar())) {
            throw new InvalidArgumentException("El proyecto necesita un plazo de financiación válido");
        }
        Proyecto proyec = new Proyecto(nombre, descripcion, usuario, cantidadMinima,
                plazoFinanciacion, Categoria.valueOfNombre(categoria));
        for (RecompensaVista r : recompensas) {
            proyec.addRecompensa(r.getNombre(), r.getDescripcion(), r.getCantidadMinima(),
                    r.getMaximoParticipantes());
        }
        if (proyec.validarProyecto()) {
        	// Registrar persistencia del proyecto y de las recompensas
        	this.registrarPersistencia(proyec);
    		for (Recompensa re : proyec.getRecompensas()) {
    			this.registrarPersistencia(re);
    		}
        	//Actualizar persistencia del proyecto
    		this.actualizarPersistencia(proyec);
            usuario.addProyectoCreado(proyec);
            //Actualizar persistencia del usuario que ha creado el proyecto
            this.actualizarPersistencia(usuario);
            catalogoProyectos.addProyecto(proyec);
            return true;
        }
        else return false;
    }


    /**
     * @param nombreProyecto
     * @return Si el proyecto ya ha sido votado o no
     * @throws InvalidArgumentException si el proyecto no existe
     */
    public boolean isVotadoProyecto(String nombreProyecto) throws InvalidArgumentException {
        Proyecto p = catalogoProyectos.getProyecto(nombreProyecto);
        if (p==null) {
            throw new InvalidArgumentException("Proyecto inexistente");
        }
        return usuario.isVotado(p);
    }

    public void votarProyecto (String nombreProyecto) throws InvalidStateException, InvalidArgumentException {
        Proyecto p = catalogoProyectos.getProyecto(nombreProyecto);
        if (p==null) {
            throw new InvalidArgumentException("Proyecto inexistente");
        }
        //Actualizar persistencia de proyecto y usuario si no estaba ya votado
        if (!usuario.isVotado(p)){
        	usuario.votar(p);
        	this.actualizarPersistencia(p);
        	this.actualizarPersistencia(usuario);
        }

    }

    /**
     * Devuelve todos los proyectos que están en fase de votación.
     * @return Lista de proyectos en fase de votación.
     */
    public List<Proyecto> getProyectosEnVotacion(){
        return catalogoProyectos.getAllProyectosVotacion();
    }

    /**
     * Devuelve los proyectos de categoría categoria que están en fase de votación.
     * @return Lista de proyectos en fase de votación de la categoría.
     */
    public List<Proyecto> getProyectosEnVotacion(String categoria) throws InvalidArgumentException {
        Categoria cat = Categoria.valueOfNombre(categoria);
        return catalogoProyectos.getProyectosVotacion(cat);
    }

    /**
     * Devuelve todos los proyectos que están en fase de financiación.
     * @return Lista de proyectos en fase de financiación.
     */
    public List<Proyecto> getProyectosEnFinanciacion(){
        return catalogoProyectos.getAllProyectosFinanciacion();
    }

    /**
     * Devuelve los proyectos de categoría categoria que están en fase de financiación.
     * @return Lista de proyectos en fase de financiación de la categoría.
     */
    public List<Proyecto> getProyectosEnFinanciacion(String categoria) throws InvalidArgumentException {
        Categoria cat = Categoria.valueOfNombre(categoria);
        return catalogoProyectos.getProyectosFinanciacion(cat);
    }
    
    /**
     * Devuelve los apoyos que ha realizo el usuario actual
     * @return Lista de apoyos realizada por el usuario actual
     */
    public Collection<Apoyo> getApoyos(){
    	return usuario.getApoyos();
    }

    public void apoyarProyecto(String nombreProyecto, String nRecompensa, double cantidad, String comentario)
            throws InvalidStateException, InvalidArgumentException {
        Proyecto p = catalogoProyectos.getProyecto(nombreProyecto);
        if (p==null) {
            throw new InvalidArgumentException("Proyecto inexistente");
        } 
        boolean estaFinanciado = p.esFinanciado();        
        usuario.apoyar(p,nRecompensa,cantidad, comentario);
        // Registrar el nuevo apoyo
        // Actualizar recompensa asociada al apoyo.
        // Actualizar al usuario que ha realizado el apoyo.
        // Actualizar el proyecto que ha sido apoyado.
        for (Apoyo a : usuario.getApoyos()) {
        	if (a.getId()==0){
        		this.registrarPersistencia(a);
        		this.actualizarPersistencia(a.getRecompensa());
        		this.actualizarPersistencia(a.getUsuario());
        		this.actualizarPersistencia(a.getProyecto());
        		
        		// Si el apoyo hace que se llegue a la financiación del proyecto 
        		// se generan notificaciones (proyecto pasa a financiado) registrar las notificaciones 
        		// y actualizar al creador del proyecto y sus mecenas
                if (!estaFinanciado && p.esFinanciado()){
                	//Registrar notificación del creador del proyecto
                	//Actualizar al creador del proyecto
                    for (Notificacion n : p.getCreador().getNotificaciones() ) {
                    	if (n.getId()==0){
                    		this.registrarPersistencia(n);
                    		this.actualizarPersistencia(p.getCreador());
                    	}
                    }
                    //Registrar notificación de los mecenas del proyecto
                    //Actualizar a los mecenas del proyecto.
                    for (Recompensa r: p.getRecompensas()){
                        for (Usuario u: r.getMecenas()){
                            for (Notificacion n : u.getNotificaciones() ) {
                            	if (n.getId()==0){
                            		this.registrarPersistencia(n);
                            		this.actualizarPersistencia(u);
                            	}
                            }
                        }
                    }
                }
        	}
        }
    }

    public void adelantarRelojUnDia(){
        adelantoReloj+=1;
    }

    public void comprobarPlazoFinalizacionProyectos() {
        GregorianCalendar Fecha = new GregorianCalendar();
        Fecha.add(Calendar.DAY_OF_YEAR, adelantoReloj);
        Collection<Proyecto> proyectos = catalogoProyectos.getAllProyectos();
        for (Proyecto p : proyectos ) {
        	Estado estado = p.getEstado();
            p.comprobarPlazo(Fecha);
            //Si comprobarPlazo genera notificaciones hay que registrarlas (las del creador y mecenas) y también actualizar al creador del proyecto y sus mecenas.
            if (!estado.equals(p.getEstado())){
            	//Actualizar proyecto
            	this.actualizarPersistencia(p);
            	//Registrar notificación del creador del proyecto
            	//Actualizar al creador del proyecto
                for (Notificacion n : p.getCreador().getNotificaciones() ) {
                	if (n.getId()==0){
                		this.registrarPersistencia(n);
                		this.actualizarPersistencia(p.getCreador());
                	}
                }
                //Registrar notificación de los mecenas del proyecto
                //Actualizar a los mecenas del proyecto.
                for (Recompensa r: p.getRecompensas()){
                    for (Usuario u: r.getMecenas()){
                        for (Notificacion n : u.getNotificaciones() ) {
                        	if (n.getId()==0){
                        		this.registrarPersistencia(n);
                        		this.actualizarPersistencia(u);
                        	}
                        }
                    }
                }
            }            
        }
    }

    public void hacerPregunta(String nombreProyecto, String asunto, String cuerpo) throws InvalidArgumentException {
        Proyecto p = catalogoProyectos.getProyecto(nombreProyecto);
        if (p==null) {
            throw new InvalidArgumentException("Proyecto inexistente");
        }
        Usuario creador = p.getCreador();
        creador.hacerPregunta(p, usuario, asunto, cuerpo);
        //Registrar pregunta emitida
        //Actualizar usuario emisor
        //Actualizar usuario receptor
        for (Pregunta pre : usuario.getPreguntasEmitidas()) {
        	if (pre.getId()==0){
        		this.registrarPersistencia(pre);
        		this.actualizarPersistencia(pre.getEmisor());
        		this.actualizarPersistencia(pre.getReceptor());
        	}
        }
    }

    public void responderPregunta(String nombreProyecto, int idPregunta, String respuesta)
            throws InvalidArgumentException, InvalidStateException {
        Proyecto p = catalogoProyectos.getProyecto(nombreProyecto);
        if (p==null) {
            throw new InvalidArgumentException("Proyecto inexistente");
        }
        Usuario creador = p.getCreador();
        if (creador == usuario) {
            creador.responderPregunta(idPregunta, respuesta);
            //Actualizar la persistencia de la pregunta respondida
            for (Pregunta pre : creador.getPreguntasRecibidas()) {
            	if (pre.getId()==idPregunta){
            		this.actualizarPersistencia(pre);
            	}
            }
        }
        else {
            throw new InvalidArgumentException("El usuario no puede responder preguntas ajenas a él.");
        }
    }

    public void marcarNotificacionLeida (int idNotificacion) throws InvalidArgumentException {
        usuario.marcarNotificacionLeida(idNotificacion);
        //Actualizar la persistencia de la notificación leída
        for (Notificacion noti : usuario.getNotificaciones()) {
        	if (noti.getId()==idNotificacion){
        		this.actualizarPersistencia(noti);
        	}
        }
    }

    public void setComponenteFinanciacion(ComponenteCargadorFinanciacion componente){
        componente.addFinanciacionListener(this);
    }

    @Override
    public void obtenerFinanciacion(FinanciacionEvent ev) {
        umu.tds.cargador.Proyectos proyectosexternos = ev.getProyectos();
        for (umu.tds.cargador.Proyecto pexterno : proyectosexternos.getProyecto()) {
            Proyecto p = catalogoProyectos.getProyecto(pexterno.getId());
            for (umu.tds.cargador.Ingreso ingreso : pexterno.getIngreso()) {
            	boolean estaFinanciado = p.esFinanciado();   
                p.addFinanciacionExterna(ingreso.getImporte());
                //Actualizar persistencia del proyecto
                this.actualizarPersistencia(p);
                //Si hay financiación externa y completa el proyecto esta genera notificaciones (proyecto pasa a financiado)
                //registrar las notificaciones y actualizar al creador del proyecto y sus mecenas
                if (!estaFinanciado && p.esFinanciado()){
                	//Registrar notificación del creador del proyecto
                	//Actualizar al creador del proyecto
                    for (Notificacion n : p.getCreador().getNotificaciones() ) {
                    	if (n.getId()==0){
                    		this.registrarPersistencia(n);
                    		this.actualizarPersistencia(p.getCreador());
                    	}
                    }
                    //Registrar notificación de los mecenas del proyecto
                    //Actualizar a los mecenas del proyecto.
                    for (Recompensa r: p.getRecompensas()){
                        for (Usuario u: r.getMecenas()){
                            for (Notificacion n : u.getNotificaciones() ) {
                            	if (n.getId()==0){
                            		this.registrarPersistencia(n);
                            		this.actualizarPersistencia(u);
                            	}
                            }
                        }
                    }
                }
                
            }
        }
    }
    
    /**
     * Registrar Persistencia para el objeto Usuario.
     * @param u
     */
    public void registrarPersistencia(Usuario u){
        try {
            FactoriaDAO.getFactoriaDAO(Config.TipoDAO).getUsuarioDAO().registrar(u);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualizar persistencia para objetos Usuario.
     * @param u
     */
    public void actualizarPersistencia(Usuario u){
        try {
            FactoriaDAO.getFactoriaDAO(Config.TipoDAO).getUsuarioDAO().actualizarUsuario(u);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Registra presistencia del objeto Proyecto.
     * @param p
     */
    public void registrarPersistencia(Proyecto p){
        try {
            FactoriaDAO.getFactoriaDAO(Config.TipoDAO).getProyectoDAO().registrar(p);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualiza la persistencia de los objetos Proyecto.
     * @param p
     */
    public void actualizarPersistencia(Proyecto p){
        try {
            FactoriaDAO.getFactoriaDAO(Config.TipoDAO).getProyectoDAO().actualizarProyecto(p);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Registra persistencia de objetos Recompensa.
     * @param r
     */
    public void registrarPersistencia(Recompensa r){
        try {
            FactoriaDAO.getFactoriaDAO(Config.TipoDAO).getRecompensaDAO().registrar(r);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualiza persistencia de objetos Recompensa.
     * @param r
     */
    public void actualizarPersistencia(Recompensa r){
        try {
            FactoriaDAO.getFactoriaDAO(Config.TipoDAO).getRecompensaDAO().actualizarRecompensa(r);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Registra persistencia de los objetos Apoyo.
     * @param a
     */
	public void registrarPersistencia(Apoyo a){
		try {
			FactoriaDAO.getFactoriaDAO(Config.TipoDAO).getApoyoDAO().registrar(a);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Actualiza persistencia de los objetos Apoyo.
	 * @param a
	 */
	public void actualizarPersistencia(Apoyo a){
		try {
			FactoriaDAO.getFactoriaDAO(Config.TipoDAO).getApoyoDAO().actualizarApoyo(a);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Registra la persistencia de los objetos Pregunta.
	 * @param p
	 */
	public void registrarPersistencia(Pregunta p){
		try {
			FactoriaDAO.getFactoriaDAO(Config.TipoDAO).getPreguntaDAO().registrar(p);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Actualiza la persistencia de los objetos Pregunta.
	 * @param p
	 */
	public void actualizarPersistencia(Pregunta p){
		try {
			FactoriaDAO.getFactoriaDAO(Config.TipoDAO).getPreguntaDAO().actualizarPregunta(p);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Registra la persistencia de los objetos Notificación.
	 * @param n
	 */
    public void registrarPersistencia(Notificacion n){
        try {
            FactoriaDAO.getFactoriaDAO(Config.TipoDAO).getNotificacionDAO().registrar(n);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualiza la persistencia de los objetos Notificación.
     * @param n
     */
    public void actualizarPersistencia(Notificacion n){
        try {
            FactoriaDAO.getFactoriaDAO(Config.TipoDAO).getNotificacionDAO().actualizarNotificacion(n);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
	
}
	