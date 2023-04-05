package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public interface IVehiculos {
	
	List<Vehiculo> get();
	
	int getCantidad();
	
	Vehiculo buscar(Vehiculo vehiculo);
	
	void insertar(Vehiculo vehiculo) throws OperationNotSupportedException;
	
	void borrar(Vehiculo vehiculo) throws OperationNotSupportedException;
	
}