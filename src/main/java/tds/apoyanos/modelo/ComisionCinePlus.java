package tds.apoyanos.modelo;

public class ComisionCinePlus extends PoliticaComisiones{
	private final double comision = 0.05;
	
	public double calcular(double financiacion){
		return (financiacion * comision);
	}
}
