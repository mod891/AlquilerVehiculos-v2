package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades.UtilidadesXml;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Alquileres implements IAlquileres {

	List<Alquiler> coleccionAlquileres;
	private static Alquileres instancia = null;
	
	private static final String RUTA_FICHERO = "datos/alquileres.xml";
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
		LocalDate fecha = null;
		Cliente cliente = null, clienteBuscado = null;
		Vehiculo vehiculo = null, vehiculoBuscado = null;
		String dni, matricula, fechaAlquilerStr, fechaDevolucionStr;
		Element fechaAlquiler, fechaDevolucion;
		dni = elemento.getAttribute(DNI_CLIENTE);
		cliente = Cliente.getClienteConDni(dni);
		clienteBuscado = Clientes.getInstancia().buscar(cliente);
		
		matricula = elemento.getAttribute(MATRICULA);
		vehiculo = Vehiculo.getVehiculoConMatricula(matricula);
		vehiculoBuscado = Vehiculos.getInstancia().buscar(vehiculo);
		
		fechaAlquiler = (Element) elemento.getElementsByTagName(FECHA_ALQUILER).item(0);
		fechaAlquilerStr = fechaAlquiler.getTextContent();
		
		try {
			fecha = LocalDate.parse(fechaAlquilerStr, FORMATO_FECHA);
		}catch(DateTimeParseException e) {
			fecha = null;
		}
		
		alquiler = new Alquiler(clienteBuscado,vehiculoBuscado,fecha);
		
		fechaDevolucion = (Element) elemento.getElementsByTagName(FECHA_DEVOLUCION).item(0);
		fechaDevolucionStr = fechaDevolucion.getTextContent();
		
		try {
			fecha = LocalDate.parse(fechaDevolucionStr, FORMATO_FECHA);
		}catch(DateTimeParseException e) {
			System.out.println(e.getMessage());
			fecha = null;
		}
		if (fecha != null) {
			try {
				System.out.println("Devolviendo alquiler");
				alquiler.devolver(fecha); // BUG
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("no devolviendo");
		}
	
		return alquiler;
	}
	private void leerXml() throws OperationNotSupportedException {
		
		Document document = UtilidadesXml.xmlToDom(RUTA_FICHERO);
		Element raiz = document.getDocumentElement();
		
		NodeList nodeList = raiz.getElementsByTagName(ALQUILER);
		System.out.println("leyendo alquileres"+nodeList.getLength());
		
		for (int i=0; i<nodeList.getLength(); i++) {
			Node nodo = nodeList.item(i);
			if(nodo.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println("iterando2");
				Alquiler alquiler = elementToAlquiler((Element) nodo);
				System.out.println("INSERT ALQUILER");
				insertar(alquiler);
			}
		}
	}
	
	private void escribirXml() {
		Document documento = UtilidadesXml.crearDomVacio(RAIZ);
		Element raiz = documento.getDocumentElement();
		
		
		for(Alquiler it :coleccionAlquileres) {
			
		    	try {
					Element alquiler = alquilerToElement(documento,it);
					raiz.appendChild(alquiler);
				} catch (DOMException e) {// TODO Auto-generated catch blocke.printStackTrace();}
					System.out.println(e.getMessage());
				}

		UtilidadesXml.domToXml(documento, RUTA_FICHERO);
		}
	}
	
	private Element alquilerToElement(Document documento, Alquiler alquiler) {
		Element raiz = documento.createElement(ALQUILER);
		raiz.setAttribute(DNI_CLIENTE, alquiler.getCliente().getDni());
		raiz.setAttribute(MATRICULA, alquiler.getVehiculo().getMatricula());
		Element eFechaAlquiler = documento.createElement(FECHA_ALQUILER);
		eFechaAlquiler.setTextContent(alquiler.getFechaAlquiler().format(FORMATO_FECHA));
		eFechaAlquiler.setAttribute(FORMATO, "dd/MM/yyyy");
		raiz.appendChild(eFechaAlquiler);
		
		Element eFechaDevolucion = documento.createElement(FECHA_DEVOLUCION);
		if(alquiler.getFechaDevolucion() != null) {
			eFechaDevolucion.setTextContent(alquiler.getFechaDevolucion().format(FORMATO_FECHA));
		}else {
			eFechaDevolucion.setTextContent("");
		}
		
		eFechaDevolucion.setAttribute(FORMATO, "dd/MM/yyyy");
		raiz.appendChild(eFechaDevolucion);
		
		return raiz;
	}

	@Override
	public void comenzar() throws OperationNotSupportedException {
		leerXml();
	}

	@Override
	public void terminar() {
		escribirXml();
	}
}
