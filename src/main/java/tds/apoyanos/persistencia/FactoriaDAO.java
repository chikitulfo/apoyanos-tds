package tds.apoyanos.persistencia;

/**
 * Factoria abstracta DAO 
 */

public abstract class FactoriaDAO {
	
	private static FactoriaDAO unicaInstancia = null;
	
	/**crea un tipo de factoria DAO
	 * Solo existe el tipo H2FactoriaDAO
	 */
	public static FactoriaDAO getFactoriaDAO(String tipo) throws DAOException{
		if (unicaInstancia == null)
			try { unicaInstancia=(FactoriaDAO) Class.forName(tipo).newInstance();
			} catch (Exception e) {	throw new DAOException(e.getMessage());} 
		return unicaInstancia;
	}
	
	/* Constructor */
	protected FactoriaDAO (){
		
	}
	
	// Metodos factoria para obtener adaptadores
	
	public abstract UsuarioDAO getUsuarioDAO();	
}
