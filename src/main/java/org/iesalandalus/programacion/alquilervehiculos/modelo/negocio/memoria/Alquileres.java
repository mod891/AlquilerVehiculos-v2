package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;

public class Alquileres implements IAlquileres {

	List<Alquiler> coleccionAlquileres;

	public Alquileres() {
		
		coleccionAlquileres = new ArrayList<Alquiler>();
	}

	public List<Alquiler> get() {
		
		List<Alquiler> copia = new ArrayList<>();
		for (int i=0; i<coleccionAlquileres.size(); i++) {
			copia.add(coleccionAlquileres.get(i));
		}
		return copia;
	}
	


	public List<Alquiler> get(Cliente cliente) {

		List<Alquiler> copia = new ArrayList<Alquiler>();
		
		for (int i=0; i<coleccionAlquileres.size(); i++) {
			if (coleccionAlquileres.get(i).getCliente().equals(cliente))
				copia.add(coleccionAlquileres.get(i));
		}
		return copia;
	}
	
	public List<Alquiler> get(Vehiculo vehiculo) {
		
		List<Alquiler> copia = new ArrayList<Alquiler>();
		
		for (int i=0; i<coleccionAlquileres.size(); i++) {
			if (coleccionAlquileres.get(i).getVehiculo().equals(vehiculo))
				copia.add(coleccionAlquileres.get(i));
		}
		return copia;
	}
	
	public int getCantidad() {
		return coleccionAlquileres.size();
	}
	

	private void comprobarAlquiler(Cliente cliente, Vehiculo vehiculo, LocalDate fechaAlquiler) throws OperationNotSupportedException {
		
		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getVehiculo().equals(vehiculo) && alquiler.getFechaDevolucion() == null) 
				throw new OperationNotSupportedException("ERROR: El turismo está actualmente alquilado.");
			 else if (alquiler.getCliente().equals(cliente) && alquiler.getFechaDevolucion() == null) 
				throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
			 else if (alquiler.getVehiculo().equals(vehiculo) && (alquiler.getFechaDevolucion().isAfter(fechaAlquiler) || alquiler.getFechaDevolucion().equals(fechaAlquiler))) 
				throw new OperationNotSupportedException("ERROR: El turismo tiene un alquiler posterior.");
			 else if (alquiler.getCliente().equals(cliente) && (alquiler.getFechaDevolucion().isAfter(fechaAlquiler) || alquiler.getFechaDevolucion().equals(fechaAlquiler))) 
				throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");			
		}
	}
	

	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) 
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		
		this.comprobarAlquiler(alquiler.getCliente(), alquiler.getVehiculo(), alquiler.getFechaAlquiler());
		
		coleccionAlquileres.add(alquiler);
	}
	
/*
	public void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (alquiler == null) 
			throw new NullPointerException("ERROR: No se puede devolver un alquiler nulo.");
		
		if (fechaDevolucion == null) 
			throw new NullPointerException("ERROR: No se puede devolver un alquiler nulo.");
		
		int indice = coleccionAlquileres.indexOf(alquiler);
		
		if (indice != -1) 
			coleccionAlquileres.get(indice).devolver(fechaDevolucion);
			
		else if (indice == -1) 
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
	}
*/	
	public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		Alquiler abierto = getAlquilerAbierto(cliente);
		
		if (cliente == null) 
			throw new NullPointerException("ERROR: el cliente es nulo");
		
		if (fechaDevolucion == null)
			throw new NullPointerException("ERROR: fecha de devolución nula");
		
		if (abierto == null) 
			throw new NullPointerException("ERROR: No existe un alquiler abierto para este cliente.");
		
		abierto.devolver(fechaDevolucion);
	}

	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		
		if (vehiculo == null) 
			throw new NullPointerException("ERROR: el vehículo es nulo");
		
		if (fechaDevolucion == null)
			throw new NullPointerException("ERROR: fecha de devolución nula");
		
		
		Alquiler abierto = getAlquilerAbierto(vehiculo);
		
		if (abierto == null) 
			throw new NullPointerException("ERROR: No existe un alquiler abierto para este vehículo.");
		
		abierto.devolver(fechaDevolucion);
	}
	
	private Alquiler getAlquilerAbierto(Cliente cliente) {

		Alquiler abierto = null;
		for (Alquiler it : coleccionAlquileres) {
			if (it.getCliente().equals(cliente) && it.getFechaDevolucion() == null)
				abierto = it;
		}
		return abierto;
	}
	
	private Alquiler getAlquilerAbierto(Vehiculo vehiculo) {
		Alquiler abierto = null;
		for (Alquiler it : coleccionAlquileres) {
			if (it.getVehiculo().equals(vehiculo) && it.getFechaDevolucion() == null)
				abierto = it;
		}
		return abierto;
	}
	
	public Alquiler buscar(Alquiler alquiler) {
		if (alquiler == null) 
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		if (!coleccionAlquileres.contains(alquiler))
			return null;
		else 
			return 	coleccionAlquileres.get(coleccionAlquileres.indexOf(alquiler));
	}
	
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		
		if (alquiler == null) 
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
		 if (!coleccionAlquileres.contains(alquiler)) 
				throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		 
		coleccionAlquileres.remove(alquiler);
	}

	@Override
	public void comenzar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void terminar() {
		// TODO Auto-generated method stub
		
	}
}
