package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;


public class Vehiculos implements IVehiculos {
	List<Vehiculo> coleccionVehiculos;
	
	public Vehiculos() {
		coleccionVehiculos = new ArrayList<>();
	}
	public List<Vehiculo> get() {
		return coleccionVehiculos;
	}
	public int getCantidad() {
		return coleccionVehiculos.size();
	}
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if (vehiculo == null) 
			throw new NullPointerException("ERROR: No se puede insertar un vehículo nulo.");
		if (coleccionVehiculos.contains(vehiculo))
			throw new OperationNotSupportedException("ERROR: Ya existe un vehículo con esa matrícula.");
		
		coleccionVehiculos.add(vehiculo);
	}
	
	public Vehiculo buscar(Vehiculo vehiculo) {
		if (vehiculo == null) 
			throw new NullPointerException("ERROR: No se puede buscar un vehiculo nulo.");
		
		if (coleccionVehiculos.contains(vehiculo))
			return coleccionVehiculos.get(coleccionVehiculos.indexOf(vehiculo));
		return null;
	}
	
	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if (vehiculo == null)
			throw new NullPointerException("ERROR: No se puede borrar un vehiculo nulo.");
		if (coleccionVehiculos.contains(vehiculo))
			coleccionVehiculos.remove(vehiculo);
		else
			throw new OperationNotSupportedException("ERROR: No existe ningún vehiculo con esa matrícula.");
	}
	
	
}
