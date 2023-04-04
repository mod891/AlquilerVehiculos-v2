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
	private Vehiculo vehiculo;
	
	public Alquiler(Cliente cliente, Vehiculo vehiculo, LocalDate fechaAlquiler) {
		this.setCliente(cliente);
		this.setVehiculo(vehiculo);
		this.setFechaAlquiler(fechaAlquiler);
	}
	public Alquiler(Alquiler copia) {
		if (copia == null) 
			throw new NullPointerException("ERROR: No es posible copiar un alquiler nulo.");
		this.setCliente(new Cliente(copia.getCliente()));
		
		if (copia.getVehiculo() instanceof Turismo)
			this.setVehiculo(new Turismo((Turismo) copia.getVehiculo()));
		else if (copia.getVehiculo() instanceof Furgoneta)
			this.setVehiculo(new Furgoneta((Furgoneta) vehiculo));
		else if (copia.getVehiculo() instanceof Autobus)
			this.setVehiculo(new Autobus((Autobus) vehiculo));
		
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
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		if (vehiculo == null) 
			throw new NullPointerException("ERROR: El vehículo no puede ser nulo.");
		this.vehiculo = vehiculo;
	}
	public void devolver(LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (this.fechaDevolucion != null) 
			throw new OperationNotSupportedException("ERROR: La devolución ya estaba registrada.");
		setFechaDevolucion(fechaDevolucion);
	}
	public int getPrecio() {
		if (this.fechaDevolucion == null)
			return 0;
		int numDias = (int) ChronoUnit.DAYS.between( fechaAlquiler,fechaDevolucion);
		
		return (Alquiler.PRECIO_DIA + this.vehiculo.getFactorPrecio()) * numDias;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cliente, fechaAlquiler, vehiculo);
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
				&& Objects.equals(vehiculo, other.vehiculo);
	}
	@Override
	public String toString() {
		
		String str = null;
		if (fechaDevolucion == null) {
			str = String.format("%s <---> %s, %s - %s (%d€)", cliente, vehiculo,
					fechaAlquiler.format(FORMATO_FECHA), "Aún no devuelto", getPrecio());
		} else {
			str = String.format("%s <---> %s, %s - %s (%d€)", cliente, vehiculo,
					getFechaAlquiler().format(FORMATO_FECHA), fechaDevolucion.format(FORMATO_FECHA), getPrecio());
		}
		return str;	
	}
	
}
