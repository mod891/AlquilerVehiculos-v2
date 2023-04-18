package org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;

public class ModeloCascada extends Modelo {
	
	public ModeloCascada(FactoriaFuenteDatos factoriaFuenteDatos) {
		super(factoriaFuenteDatos);
	}
	
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		
		if (vehiculo==null) 
			throw new NullPointerException("ERROR: El vehículo es nulo");

		vehiculos.insertar(vehiculo);
	}

	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente==null) 
			throw new NullPointerException("ERROR: El cliente es nulo");

		this.clientes.insertar(cliente);
	}

	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler==null) {
			throw new NullPointerException("ERROR: No se puede realizar un alquiler nulo.");
		}
		Cliente clienteBuscado = buscar(alquiler.getCliente());
		Vehiculo vehiculoBuscado = buscar(alquiler.getVehiculo());
	
		if (clienteBuscado == null) {
			throw new OperationNotSupportedException("ERROR: No existe el cliente del alquiler.");
		} else if (vehiculoBuscado == null) {
			throw new OperationNotSupportedException("ERROR: No existe el vehículo del alquiler.");
		} else {
		
			this.alquileres.insertar(new Alquiler(clienteBuscado, vehiculoBuscado, alquiler.getFechaAlquiler()));
		} 
	}
	
	public Cliente buscar(Cliente cliente) {
		Cliente clienteBuscado = clientes.buscar(cliente);
		if (clienteBuscado == null) return null;
		else return new Cliente(clienteBuscado);
		
	}

	public Vehiculo buscar(Vehiculo vehiculo) {
		Vehiculo buscado = vehiculos.buscar(vehiculo);
		if (buscado == null) return null;
		else return  buscado;
	}

	public Alquiler buscar(Alquiler alquiler) {
		Alquiler alquilerBuscado = alquileres.buscar(alquiler);
		if (alquilerBuscado == null) return null;
		else return new Alquiler(alquilerBuscado);
	}

	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		this.clientes.modificar(cliente, nombre, telefono);
	}
	
	public void devolver(Cliente cliente, LocalDate fechadevolucion) throws OperationNotSupportedException {
		if (cliente == null) 
			throw new NullPointerException("ERROR: No se puede devolver un cliente nulo.");
		
		if (fechadevolucion == null) 
			throw new NullPointerException("ERROR: No se puede realizar una devolución nula.");
		
		alquileres.devolver(cliente, fechadevolucion); 
	}
	public void devolver(Vehiculo vehiculo, LocalDate fechadevolucion) throws OperationNotSupportedException {
		
		if (vehiculo == null) 
			throw new NullPointerException("ERROR: No pueder ser un vehículo nulo.");
		
		if (fechadevolucion == null) 
			throw new NullPointerException("ERROR: No se puede realizar una devolución nula.");
		 
		alquileres.devolver(vehiculo, fechadevolucion); 
	}
	
	
	
    public void borrar(Cliente cliente) throws OperationNotSupportedException {
       for (Alquiler alquiler : alquileres.get(cliente)) 
    	   alquileres.borrar(alquiler);
    	
    	clientes.borrar(cliente);
    }

    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        for (Alquiler alquiler : alquileres.get(vehiculo)) 
     	   alquileres.borrar(alquiler);
     	
    	vehiculos.borrar(vehiculo);
    }

    public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
        alquileres.borrar(alquiler);
    }
    
    public List<Cliente> getClientes() {
    	List<Cliente> listaCopia = new ArrayList<>();
		for (Cliente it : clientes.get())
			listaCopia.add(new Cliente(it));
		return listaCopia;
    }

    public List<Vehiculo> getVehiculos() {
    	List<Vehiculo> listaCopia = new ArrayList<>();
		for (Vehiculo it : vehiculos.get())
			listaCopia.add(it);
		return listaCopia;
    }

    public List<Alquiler> getAlquileres() {
    	List<Alquiler> listaCopia = new ArrayList<>();
		for (Alquiler it : alquileres.get())
			listaCopia.add(new Alquiler(it.getCliente(),it.getVehiculo(),it.getFechaAlquiler()));
		return listaCopia;
    
    }

    public List<Alquiler> getAlquileres(Cliente cliente) {
    	List<Alquiler> listaCopia = new ArrayList<>();
        for (Alquiler it : alquileres.get(cliente))
        	listaCopia.add(new Alquiler(it.getCliente(),it.getVehiculo(),it.getFechaAlquiler()));
        return listaCopia;
    }

    public List<Alquiler> getAlquileres(Vehiculo vehiculo) {
    	List<Alquiler> listaCopia = new ArrayList<>();
        for (Alquiler it : alquileres.get(vehiculo))
        	listaCopia.add(new Alquiler(it.getCliente(),it.getVehiculo(),it.getFechaAlquiler()));
        return listaCopia;
    }
}
