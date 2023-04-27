package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades.UtilidadesXml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


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

	private Vehiculo elementToVehiculo(Element elemento) {
		Vehiculo vehiculo = null;
		String marca, modelo, matricula, tipo;
		int cilindrada;
		matricula = elemento.getAttribute(MATRICULA);
		
		Element elemModelo = (Element) elemento.getElementsByTagName(MODELO).item(0);
		Element elemMarca = (Element) elemento.getElementsByTagName(MARCA).item(0);
		modelo = elemModelo.getTextContent();
		marca = elemMarca.getTextContent();
		tipo = elemento.getAttribute(TIPO);
		
		switch (tipo) {
		case TURISMO:
			Element elemTurismo = (Element) elemento.getElementsByTagName(tipo).item(0);
			cilindrada = Integer.parseInt( ((Element) elemTurismo.getElementsByTagName(CILINDRADA).item(0)).getTextContent());
			vehiculo = new Turismo(marca, modelo, cilindrada, matricula);
			break;
		case AUTOBUS:
			Element elemAutobus = (Element) elemento.getElementsByTagName(tipo).item(0);
			Element elemPlazas = (Element) elemAutobus.getElementsByTagName(PLAZAS).item(0);
			int plazas = Integer.parseInt(elemPlazas.getTextContent());
			
			vehiculo = new Autobus(marca, modelo, plazas, matricula);
			break;
		}
		return vehiculo;
	}
	private void leerXml() throws OperationNotSupportedException {
		Document documento = UtilidadesXml.xmlToDom(RUTA_FICHERO);
		Element raiz = documento.getDocumentElement();
		
		NodeList listaNodos = raiz.getElementsByTagName(VEHICULO);
		
		for(int i = 0; i < listaNodos.getLength(); i++) {
			Node nodo = listaNodos.item(i);
			if (nodo.getNodeType() == Node.ELEMENT_NODE) {
				Element elemVehiculo = (Element) nodo;
				Vehiculo vehiculo = elementToVehiculo(elemVehiculo);
				
				insertar(vehiculo);
			}
		}
	}
	
	private void escribirXml() {
		
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
