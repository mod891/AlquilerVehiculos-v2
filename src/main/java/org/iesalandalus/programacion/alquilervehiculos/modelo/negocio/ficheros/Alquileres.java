package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades.UtilidadesXml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Alquileres implements IAlquileres {

	List<Alquiler> coleccionAlquileres;
	private static Alquileres instancia = null;
	
	private static final String RUTA_FICHERO = "/datos/alquileres.xml";
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final String RAIZ = "Alquileres"; 
	private static final String ALQUILER = "Alquiler";
	private static final String DNI_CLIENTE = "Dni";
	private static final String MATRICULA = "Matricula";
	private static final String FECHA_ALQUILER = "FechaAlquiler";
	private static final String FECHA_DEVOLUCION = "FechaDevolucion";
	private static final String FORMATO = "Formato";
	
	private Alquileres() {
		
		coleccionAlquileres = new ArrayList<Alquiler>();
	}
	
	protected static Alquileres getInstancia() {
		if (instancia == null)
			instancia = new Alquileres();
		return instancia;
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

	private Alquiler elementToAlquiler(Element elemento) {
		Alquiler alquiler = null;
		Vehiculo vehiculo = null;
		LocalDate fecha = null;
		Cliente cliente = null;
	/*	
		Element nNombre, nTelefono;
		String nombre,telefono,dni;
		System.out.println("returnando nuevo cliente");
		dni = elemento.getAttribute(DNI);
		nNombre = (Element) elemento.getElementsByTagName(NOMBRE).item(0);
		nombre = nNombre.getTextContent();
		nTelefono = (Element) elemento.getElementsByTagName(TELEFONO).item(0);
		telefono = nTelefono.getTextContent();
		System.out.println("returnando nuevo cliente"+nombre+dni+telefono);
		*/
		return new Alquiler(cliente,vehiculo,fecha);
	}
	private void leerXml() throws OperationNotSupportedException {
		
		Document document = UtilidadesXml.xmlToDom(RUTA_FICHERO);
		Element raiz = document.getDocumentElement();
		
		NodeList nodeList = raiz.getElementsByTagName(ALQUILER);
		System.out.println("leyendo"+nodeList.getLength());
		
		for (int i=0; i<nodeList.getLength(); i++) {
			Node nodo = nodeList.item(i);
			System.out.println("iterando");
			if(nodo.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println("iterando2");
				Alquiler alquiler = elementToAlquiler((Element) nodo);
				System.out.println("INSERT");
				insertar(alquiler);
			}
		}
	}
	
	private void escribirXml() {
		
	}
	
	@Override
	public void comenzar() throws OperationNotSupportedException {
	//	leerXml();
	}

	@Override
	public void terminar() {
		escribirXml();
	}
}
