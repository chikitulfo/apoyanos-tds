package tds.apoyanos.modelo;

public class ComisionStandard extends PoliticaComisiones{
	private final double comision=0.04;
	
	public double calcular(double financiacion){
		return (financiacion * comision);
	}
}
