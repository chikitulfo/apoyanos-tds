package tds.apoyanos.persistencia;

/** 
 * Factoria concreta DAO para H2.
 * 
 */

public final class H2FactoriaDAO extends FactoriaDAO {
	
	public H2FactoriaDAO() {	}
	
	@Override
	public UsuarioDAO getUsuarioDAO() {	return new H2UsuarioDAO(); }

}
