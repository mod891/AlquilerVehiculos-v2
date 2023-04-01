package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;

public class Alquileres {

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
	
	public List<Alquiler> get(Turismo turismo) {
		
		List<Alquiler> copia = new ArrayList<Alquiler>();
		
		for (int i=0; i<coleccionAlquileres.size(); i++) {
			if (coleccionAlquileres.get(i).getTurismo().equals(turismo))
				copia.add(coleccionAlquileres.get(i));
		}
		return copia;
	}
	
	public int getCantidad() {
		return coleccionAlquileres.size();
	}
	

	private void comprobarAlquiler(Cliente cliente, Turismo turismo, LocalDate fechaAlquiler) throws OperationNotSupportedException {
		
		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getTurismo().equals(turismo) && alquiler.getFechaDevolucion() == null) 
				throw new OperationNotSupportedException("ERROR: El turismo está actualmente alquilado.");
			 else if (alquiler.getCliente().equals(cliente) && alquiler.getFechaDevolucion() == null) 
				throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
			 else if (alquiler.getTurismo().equals(turismo) && (alquiler.getFechaDevolucion().isAfter(fechaAlquiler) || alquiler.getFechaDevolucion().equals(fechaAlquiler))) 
				throw new OperationNotSupportedException("ERROR: El turismo tiene un alquiler posterior.");
			 else if (alquiler.getCliente().equals(cliente) && (alquiler.getFechaDevolucion().isAfter(fechaAlquiler) || alquiler.getFechaDevolucion().equals(fechaAlquiler))) 
				throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");			
		}
	}
	

	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) 
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		
		this.comprobarAlquiler(alquiler.getCliente(), alquiler.getTurismo(), alquiler.getFechaAlquiler());
		
		coleccionAlquileres.add(alquiler);
	}
	

	public void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (alquiler == null) 
			throw new NullPointerException("ERROR: No se puede devolver un alquiler nulo.");
		
		if (fechaDevolucion == null) 
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		
		int indice = coleccionAlquileres.indexOf(alquiler);
		
		if (indice != -1) 
			coleccionAlquileres.get(indice).devolver(fechaDevolucion);
			
		else if (indice == -1) 
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
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
}
