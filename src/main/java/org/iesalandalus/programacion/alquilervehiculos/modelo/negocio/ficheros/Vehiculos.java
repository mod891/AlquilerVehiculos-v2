package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;


public class Vehiculos implements IVehiculos {
	List<Vehiculo> coleccionVehiculos;
	private static Vehiculos instancia = null;
	
	private static final String RUTA_FICHERO = "datos/vehiculos.xml";
	private static final String RAIZ = "Vehiculos";
	private static final String VEHICULO = "Vehiculo";
	private static final String MARCA = "Marca";
	private static final String MODELO = "Modelo";
	private static final String MATRICULA = "Matricula";
	private static final String CILINDRADA = "Cilindrada";
	private static final String PLAZAS = "Plazas";
	private static final String PMA = "Pma";
	private static final String TIPO = "Tipo";
	private static final String TURISMO = "Turismo";
	private static final String AUTOBUS = "Autobus";
	private static final String FURGONETA = "Furgoneta";
	
	public Vehiculos() {
		coleccionVehiculos = new ArrayList<>();
	}
	
	protected static Vehiculos getInstancia() {
		if (instancia == null) 
			instancia = new Vehiculos();
		return instancia;
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
