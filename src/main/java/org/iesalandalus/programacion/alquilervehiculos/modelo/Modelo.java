package org.iesalandalus.programacion.alquilervehiculos.modelo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;


public abstract class Modelo {
	protected IClientes clientes;
	protected IAlquileres alquileres;
	protected IVehiculos vehiculos;
	protected IFuenteDatos fuenteDatos;
	
	public void comenzar() {

		clientes = fuenteDatos.crearClientes();
		alquileres = fuenteDatos.crearAlquileres();
		vehiculos = fuenteDatos.crearVehiculos();
	}
	
	public void terminar() {
		System.out.println("El modelo ha terminado.");
	}
	
	protected void setFuenteDatos(IFuenteDatos fuenteDatos) {
		this.fuenteDatos = fuenteDatos;
	}

	public abstract void insertar(Vehiculo vehiculo) throws OperationNotSupportedException;

	public abstract void insertar(Cliente cliente) throws OperationNotSupportedException;
	
	public abstract void insertar(Alquiler alquiler) throws OperationNotSupportedException;
	
	public abstract Cliente buscar(Cliente cliente);

	public abstract Vehiculo buscar(Vehiculo vehiculo);

	public abstract Alquiler buscar(Alquiler alquiler);
	
	public abstract void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException;
	
	public abstract void devolver(Cliente cliente, LocalDate fechadevolucion) throws OperationNotSupportedException;

	public abstract void devolver(Vehiculo vehiculo, LocalDate fechadevolucion) throws OperationNotSupportedException;

    public abstract void borrar(Cliente cliente) throws OperationNotSupportedException;
    
    public abstract void borrar(Vehiculo vehiculo) throws OperationNotSupportedException;

    public abstract void borrar(Alquiler alquiler) throws OperationNotSupportedException;
    
    public abstract List<Cliente> getClientes();

    public abstract List<Vehiculo> getVehiculos();
    
    public abstract List<Alquiler> getAlquileres();
    
    public abstract List<Alquiler> getAlquileres(Cliente cliente);

    public abstract List<Alquiler> getAlquileres(Vehiculo vehiculo);
	
}	
