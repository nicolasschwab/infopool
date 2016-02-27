package model;

public enum Combustible {
	NAFTA(2.37),GASOIL(2.77),GNC(1.95),ELECTRICO(0);

	//Factor de emision del combustible (KgCO2/lit)
	private double factorEmision;
	
	private Combustible(double factorEmision){
		this.factorEmision = factorEmision;
	}
	
	public double getFactorEmision(){
		return factorEmision;
	}
}