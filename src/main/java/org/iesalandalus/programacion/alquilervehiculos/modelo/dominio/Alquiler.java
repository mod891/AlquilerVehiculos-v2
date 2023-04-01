package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import javax.naming.OperationNotSupportedException;

public class Alquiler {
	 static DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");;
	private static int PRECIO_DIA = 20;
	private LocalDate fechaAlquiler;
	private LocalDate fechaDevolucion;
	private Cliente cliente;
	private Turismo turismo;
	
	public Alquiler(Cliente cliente, Turismo turismo, LocalDate fechaAlquiler) {
		this.setCliente(cliente);
		this.setTurismo(turismo);
		this.setFechaAlquiler(fechaAlquiler);
	}
	public Alquiler(Alquiler copia) {
		if (copia == null) 
			throw new NullPointerException("ERROR: No es posible copiar un alquiler nulo.");
		this.setCliente(new Cliente(copia.getCliente()));
		this.setTurismo(new Turismo(copia.getTurismo()));
		this.setFechaAlquiler(copia.getFechaAlquiler());
		this.setFechaDevolucion(copia.getFechaDevolucion());
	}
	public LocalDate getFechaAlquiler() {
		return fechaAlquiler;
	}
	
	public void setFechaAlquiler(LocalDate fechaAlquiler) {

		if (fechaAlquiler == null) 
			throw new NullPointerException("ERROR: La fecha de alquiler no puede ser nula.");
		
		if (fechaAlquiler.isAfter(LocalDate.now()))
			throw new IllegalArgumentException("ERROR: La fecha de alquiler no puede ser futura.");
		
		this.fechaAlquiler = fechaAlquiler;
	}
	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}
	public void setFechaDevolucion(LocalDate fechaDevolucion) {

		if (fechaDevolucion == null)
			throw new NullPointerException("ERROR: La fecha de devolución no puede ser nula.");
		
		if (fechaDevolucion.isAfter(LocalDate.now())) 
			throw new IllegalArgumentException("ERROR: La fecha de devolución no puede ser futura.");
		
		if (!fechaDevolucion.isAfter(getFechaAlquiler()))
			throw new IllegalArgumentException("ERROR: La fecha de devolución debe ser posterior a la fecha de alquiler.");
		
			this.fechaDevolucion = fechaDevolucion;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		if (cliente == null) 
			throw new NullPointerException("ERROR: El cliente no puede ser nulo.");
		this.cliente = cliente;
	}
	public Turismo getTurismo() {
		return turismo;
	}
	public void setTurismo(Turismo turismo) {
		if (turismo == null) 
			throw new NullPointerException("ERROR: El turismo no puede ser nulo.");
		this.turismo = turismo;
	}
	public void devolver(LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (this.fechaDevolucion != null) 
			throw new OperationNotSupportedException("ERROR: La devolución ya estaba registrada.");
		setFechaDevolucion(fechaDevolucion);
	}
	public int getPrecio() {
		if (this.fechaDevolucion == null)
			return 0;
		int factorCilindrada = this.getTurismo().getCilindrada() / 10;
		int numDias = (int) ChronoUnit.DAYS.between( fechaAlquiler,fechaDevolucion);
		return (Alquiler.PRECIO_DIA + factorCilindrada) * numDias;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cliente, fechaAlquiler, turismo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alquiler other = (Alquiler) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(fechaAlquiler, other.fechaAlquiler)
				&& Objects.equals(turismo, other.turismo);
	}
	@Override
	public String toString() {
		
		String str = null;
		if (fechaDevolucion == null) {
			str = String.format("%s <---> %s, %s - %s (%d€)", cliente, turismo,
					fechaAlquiler.format(FORMATO_FECHA), "Aún no devuelto", getPrecio());
		} else {
			str = String.format("%s <---> %s, %s - %s (%d€)", cliente, turismo,
					getFechaAlquiler().format(FORMATO_FECHA), fechaDevolucion.format(FORMATO_FECHA), getPrecio());
		}
		return str;	
	}
	
}
