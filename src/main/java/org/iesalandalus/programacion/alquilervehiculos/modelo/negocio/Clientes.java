package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;

public class Clientes {
	List<Cliente> coleccionClientes;
	
	public Clientes() {
		coleccionClientes = new ArrayList<>();
	}
	
	public List<Cliente> get() {
		return coleccionClientes;
	}
	
	public int getCantidad() {
		return coleccionClientes.size();
	}
	
	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) 
			throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
		if (coleccionClientes.contains(cliente))
			throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese DNI.");
		
		coleccionClientes.add(cliente);
	}
	
	public Cliente buscar(Cliente cliente) {
		if (cliente == null) 
			throw new NullPointerException("ERROR: No se puede buscar un cliente nulo.");
		
		if (coleccionClientes.contains(cliente))
			return coleccionClientes.get(coleccionClientes.indexOf(cliente));
		return null;
	}
	
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null)
			throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");
		if (coleccionClientes.contains(cliente))
			coleccionClientes.remove(cliente);
		else
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
	}
	
	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		if (cliente == null)
			throw new NullPointerException("ERROR: No se puede modificar un cliente nulo.");
		if (coleccionClientes.contains(cliente)) {
			if (nombre != null && !nombre.trim().isEmpty()) 
				cliente.setNombre(nombre);
			if (telefono != null && !telefono.trim().isEmpty()) 
				cliente.setTelefono(telefono);
		
			coleccionClientes.set(coleccionClientes.indexOf(cliente),cliente);
		} else
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
	}
	
}
