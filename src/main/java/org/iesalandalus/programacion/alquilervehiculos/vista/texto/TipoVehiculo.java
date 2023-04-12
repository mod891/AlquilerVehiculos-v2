package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public enum TipoVehiculo {
	TURISMO("Turismo"), FURGONETA("Furgoneta"), AUTOBUS("Autobús");
	private String nombre;
	
	private TipoVehiculo(String nombre) {
		this.nombre = nombre;
	}
	
	private static boolean esOrdinalValido(int ordinal) {
		return ordinal >= 0 && ordinal <= values().length-1;
	}
	
	public static TipoVehiculo get(int ordinal) {
		if (esOrdinalValido(ordinal))  
			return values()[ordinal];
		
		throw new IllegalArgumentException("ERROR: ordinal no válido");
	}
	
	public static TipoVehiculo get(Vehiculo vehiculo) {
		TipoVehiculo tipo = null;
		
		if (vehiculo instanceof Turismo)
			tipo = TURISMO;
		else if (vehiculo instanceof Autobus)
			tipo = AUTOBUS;
		else if (vehiculo instanceof Furgoneta)
			tipo = FURGONETA;
		
		return tipo;
	}
	
	@Override
	public String toString() {
		return String.format("%d.- %s", ordinal(), nombre);
	}
}
