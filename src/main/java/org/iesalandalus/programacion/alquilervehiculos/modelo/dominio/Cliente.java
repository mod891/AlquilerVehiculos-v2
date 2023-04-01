package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {
	private static String ER_NOMBRE = "[A-Z]{1}[a-z]+((\s)?(([A-Z]{1}[a-z]+)+))*";
	private static String ER_DNI = "(([0-9]{8})([A-Z|a-z]))";
	private static String ER_TELEFONO = "([0-9]{9})";
	private String nombre;
	private String dni;
	private String telefono;
	
	public Cliente(String nombre, String dni, String telefono) {
		this.setNombre(nombre);
		this.setDni(dni);
		this.setTelefono(telefono);
	}
	public Cliente(Cliente cliente) {
		if (cliente == null) 
			throw new NullPointerException("ERROR: No es posible copiar un cliente nulo.");

		this.setNombre(cliente.getNombre());
		this.setDni(cliente.getDni());
		this.setTelefono(cliente.getTelefono());
	}
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		if (nombre == null) 
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		if (nombre.trim().isEmpty())
			throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
		
		if (nombre.matches(ER_NOMBRE))
			this.nombre = nombre;//.trim();
		else 
			throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
	}
	
	public String getDni() {
		return dni;
	}
	
	private void setDni(String dni) {
		if (dni == null) 
			throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
		
		if (this.comprobarLetraDni(dni))
			this.dni = dni;
		else
			throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
	}
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		if (telefono == null) 
			throw new NullPointerException("ERROR: El teléfono no puede ser nulo.");
		if (telefono.matches(ER_TELEFONO))
			this.telefono = telefono;
		else 
			throw new IllegalArgumentException("ERROR: El teléfono no tiene un formato válido.");
	}
	
	private boolean comprobarLetraDni(String dni) {
		
		int num=0;
		char letra = '\0';
		char [] letras = {  'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E' };
		
		Pattern pattern = Pattern.compile(ER_DNI);
		Matcher matcher = pattern.matcher(dni); 
	
		if (matcher.matches()) {		
			num = Integer.parseInt( matcher.group(2) );
			letra =  Character.toUpperCase( matcher.group(3).charAt(0) );
		} else
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
		
		num = (num % 23);		
		return letras[num] == letra;
	}
	
	public static Cliente getClienteConDni(String dni) {
		return new Cliente("Abc Def",dni,"123456789");
	}
	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dni, other.dni);
	}
	@Override
	public String toString() {
		return nombre + " - " + dni + " (" + telefono + ")";
	}
}
