package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;

public class Autobus extends Vehiculo {

	private static final int FACTOR_PLAZAS = 2;
	private int plazas;
	
	public Autobus(String marca, String modelo, int plazas, String matricula) {
		super(marca, modelo, matricula);
		setPlazas(plazas);
	}

	public Autobus(Autobus autobus) {
		super(autobus);
		setPlazas(plazas);
	}
	public int getPlazas() {
		return plazas;
	}

	public void setPlazas(int plazas) {
		if (plazas <= 0) 
			throw new NullPointerException("ERROR: El número de plazas debe ser positivo");
		this.plazas = plazas;
	}

	@Override
	protected int getFactorPrecio() {
		// TODO Auto-generated method stub
		return plazas*FACTOR_PLAZAS;
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
	public String toString() {
		return super.getMarca()+" "+super.getModelo()+" plazas:"+plazas+" "+super.getMatricula();

	}
}
