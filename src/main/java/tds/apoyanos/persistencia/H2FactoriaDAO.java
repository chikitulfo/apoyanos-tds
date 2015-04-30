package tds.apoyanos.persistencia;


/**
 * Factoria concreta DAO para H2.
 * Devuelve siempre los mismos objetos.
 * 
 */

public final class H2FactoriaDAO extends FactoriaDAO {

	private UsuarioDAO usuarioDAO;
	private ProyectoDAO proyectoDAO;
	private RecompensaDAO recompensaDAO;
	private ApoyoDAO apoyoDAO;
	private NotificacionDAO notificacionDAO;
	private PreguntaDAO preguntaDAO;

	public H2FactoriaDAO()  {
	}
	
	@Override
	public UsuarioDAO getUsuarioDAO() {
		if (usuarioDAO == null){
			usuarioDAO = new H2UsuarioDAO(this);
		}
		return usuarioDAO;
	}

	@Override
	public ProyectoDAO getProyectoDAO() {
		if (proyectoDAO == null){
			proyectoDAO = new H2ProyectoDAO(this);
		}
		return proyectoDAO;
	}

	@Override
	public RecompensaDAO getRecompensaDAO() {
		if (recompensaDAO == null){
			recompensaDAO = new H2RecompensaDAO(this);
		}
		return recompensaDAO;
	}

	@Override
	public ApoyoDAO getApoyoDAO() {
		if (apoyoDAO == null){
			apoyoDAO = new H2ApoyoDAO(this);
		}
		return apoyoDAO;
	}

	@Override
	public NotificacionDAO getNotificacionDAO() {
		if (notificacionDAO == null){
			notificacionDAO = new H2NotificacionDAO(this);
		}
		return notificacionDAO;
	}

	@Override
	public PreguntaDAO getPreguntaDAO() {
		if (preguntaDAO == null){
			preguntaDAO = new H2PreguntaDAO(this);
		}
		return preguntaDAO;
	}

}
