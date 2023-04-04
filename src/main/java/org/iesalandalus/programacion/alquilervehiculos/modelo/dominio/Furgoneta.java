package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;

public class Furgoneta extends Vehiculo {

	private static final int FACTOR_PMA = 100;
	private static final int FACTOR_PLAZAS = 1;
	private int pma;
	private int plazas;
	
	public Furgoneta(String marca, String modelo, int pma, int plazas, String matricula) {
		super(marca, modelo, matricula);
		setPma(pma);
		setPlazas(plazas);
		// TODO Auto-generated constructor stub
	}

	public Furgoneta(Furgoneta furgoneta) {
		super(furgoneta);
		setPma(furgoneta.getPma());
		setPlazas(furgoneta.getPlazas());
	}
	public int getPma() {
		return pma;
	}


	public void setPma(int pma) {
		if (pma < 0)
			throw new NullPointerException("ERROR: pma debe ser positivo.");
		this.pma = pma;
	}


	public int getPlazas() {
		return plazas;
	}


	public void setPlazas(int plazas) {
		if (plazas < 0) 
			throw new NullPointerException("ERROR: Las plazas deben ser positivas.");
		
		this.plazas = plazas;
	}


	@Override
	public int hashCode() {
		return super.hashCode();
	}


	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}


	@Override
	protected int getFactorPrecio() {
		// TODO Auto-generated method stub
		return pma / FACTOR_PMA + plazas * FACTOR_PLAZAS;
	}


	@Override
	public String toString() {
		return super.getMarca()+" "+super.getModelo()+" plazas:"+plazas+" pma:"+pma+" "+super.getMatricula();
	}
	
	
}
