package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;

public abstract class Vehiculo {
	private static String ER_MARCA = "(Seat|Land Rover|KIA|Rolls-Royce|SsangYong)";
	private static String ER_MATRICULA = "[0-9]{4}[B-Z]{1}[A-Z]{2}";
	private String marca;
	private String modelo;
	private String matricula;
	
	
	public Vehiculo(String marca, String modelo, String matricula) {
		setMarca(marca);
		setModelo(modelo);
		setMatricula(matricula);
		
	}
	public Vehiculo(Vehiculo vehiculo) {
		if (vehiculo == null) 
			throw new NullPointerException("ERROR: No es posible copiar un vehiculo nulo.");
		
		setMarca(vehiculo.getMarca());
		setModelo(vehiculo.getModelo());
		setMatricula(vehiculo.getMatricula());
	}
	public String getMarca() {
		return marca;
	}
	
	private void setMarca(String marca) {
		if (marca == null) 
			throw new NullPointerException("ERROR: La marca no puede ser nula.");
		
		if (marca.matches(ER_MARCA))
			this.marca = marca;
		else
			throw new IllegalArgumentException("ERROR: La marca no tiene un formato válido.");
	}
	public String getModelo() {
		return modelo;
	}
	private void setModelo(String modelo) {
		if (modelo == null) 
			throw new NullPointerException("ERROR: El modelo no puede ser nulo.");
		if (!modelo.trim().isEmpty())
			this.modelo = modelo;
		else 
			throw new IllegalArgumentException("ERROR: El modelo no puede estar en blanco.");
	}

	public String getMatricula() {
		return matricula;
	}
	private void setMatricula(String matricula) {
		if (matricula == null) 
			throw new NullPointerException("ERROR: La matrícula no puede ser nula.");
		
		if (matricula.matches(ER_MATRICULA))
			this.matricula = matricula;
		else
			throw new IllegalArgumentException("ERROR: La matrícula no tiene un formato válido.");
		this.matricula = matricula;
	}
	
	public static Vehiculo getVehiculoConMatricula(String matricula) {
		return new Turismo("Seat","León",1000,matricula);
	}
	
	protected abstract int getFactorPrecio();
	
	public Vehiculo copiar(Vehiculo vehiculo) {
		Vehiculo copia = null;
		if (vehiculo instanceof Turismo) {
			copia = new Turismo( (Turismo) vehiculo);
		} else if (vehiculo instanceof Furgoneta) {
			copia = new Furgoneta( (Furgoneta) vehiculo);
		} else if (vehiculo instanceof Autobus) {
			copia = new Autobus((Autobus) vehiculo);
		}
		
		return copia;
	}
	@Override
	public int hashCode() {
		return Objects.hash(matricula);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if (!(obj instanceof Vehiculo))
			return false;
		Vehiculo other = (Vehiculo) obj;
		return Objects.equals(matricula, other.matricula);
	}
	@Override
	public String toString() {
		return marca + " "+ modelo + " "+ matricula;
			
	}
	
}
