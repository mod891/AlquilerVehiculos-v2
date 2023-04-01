package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;

public class Turismo {
	private static String ER_MARCA = "(Seat|Land Rover|KIA|Rolls-Royce|SsangYong)";
	private static String ER_MATRICULA = "[0-9]{4}[B-Z]{1}[A-Z]{2}";
	private String marca;
	private String modelo;
	private int cilindrada;
	private String matricula;
	
	
	public Turismo(String marca, String modelo, int cilindrada, String matricula) {
		setMarca(marca);
		setModelo(modelo);
		setCilindrada(cilindrada);
		setMatricula(matricula);
		
	}
	public Turismo(Turismo turismo) {
		if (turismo == null) 
			throw new NullPointerException("ERROR: No es posible copiar un turismo nulo.");
		
		setMarca(turismo.getMarca());
		setModelo(turismo.getModelo());
		setCilindrada(turismo.getCilindrada());
		setMatricula(turismo.getMatricula());
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
	public int getCilindrada() {
		return cilindrada;
	}
	private void setCilindrada(int cilindrada) {
		if (cilindrada > 0 && cilindrada <= 5000)
			this.cilindrada = cilindrada;
		else
			throw new IllegalArgumentException("ERROR: La cilindrada no es correcta.");
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
	
	public static Turismo getTurismoConMatricula(String matricula) {
		return new Turismo("Seat","León",1000,matricula);
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
		Turismo other = (Turismo) obj;
		return Objects.equals(matricula, other.matricula);
	}
	@Override
	public String toString() {
		return marca + " "+ modelo + " (" + cilindrada + "CV) - "+ matricula;
			
	}
	
}
