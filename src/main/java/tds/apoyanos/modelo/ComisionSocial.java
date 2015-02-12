package tds.apoyanos.modelo;

public class ComisionSocial extends PoliticaComisiones{
	private double comision = 0.02;
	
	public double calcular(double financiacion){
		return (financiacion * comision);
	}
}
