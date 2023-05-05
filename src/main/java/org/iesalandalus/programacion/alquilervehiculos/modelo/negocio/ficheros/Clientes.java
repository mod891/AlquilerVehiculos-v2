package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades.UtilidadesXml;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Clientes implements IClientes {
	List<Cliente> coleccionClientes;

	private static final String RUTA_FICHERO ="datos/clientes.xml";
	private static final String RAIZ ="Clientes";
	private static final String CLIENTE = "Cliente";
	private static final String NOMBRE = "Nombre";
	private static final String DNI = "Dni";
	private static final String TELEFONO = "Telefono";
	
	private static Clientes instancia = null;
	
	private Clientes() {
		coleccionClientes = new ArrayList<>();
	}
	protected static Clientes getInstancia() {
		if (instancia == null)
			instancia = new Clientes();	
		return instancia;
	}
	
	public void comenzar() throws OperationNotSupportedException {
		
		leerXml();
	}
	
	private void leerXml() throws OperationNotSupportedException {
		// al comenzar lea el fichero XML de clientes, lo almacene en un una lista 
		Document document = UtilidadesXml.xmlToDom(RUTA_FICHERO);
		Element raiz = document.getDocumentElement();
		
		NodeList nodeList = raiz.getElementsByTagName(CLIENTE);
		System.out.println("leyendo"+nodeList.getLength());
		
		for (int i=0; i<nodeList.getLength(); i++) {
			Node nodo = nodeList.item(i);
			if(nodo.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println("iterando2");
				Cliente cliente = elementToCliente((Element) nodo);
				System.out.println("INSERT"+cliente);
				insertar(cliente);
			}
		}
	}
	
	private Cliente elementToCliente(Element elemento) {
		Cliente cliente = null;
		Element nNombre, nTelefono;
		String nombre,telefono,dni;
		System.out.println("returnando nuevo cliente");
		dni = elemento.getAttribute(DNI);
		nNombre = (Element) elemento.getElementsByTagName(NOMBRE).item(0);
		nombre = nNombre.getTextContent();
		nTelefono = (Element) elemento.getElementsByTagName(TELEFONO).item(0);
		telefono = nTelefono.getTextContent();
		System.out.println("returnando nuevo cliente"+nombre+dni+telefono);
		return new Cliente(nombre,dni,telefono);
	}
	
	private Element clienteToElement(Document dom, Cliente cliente) {
		Element raiz, nombre, telefono;
		
		raiz = dom.createElement(CLIENTE);
		raiz.setAttribute(DNI,cliente.getDni());
		nombre = dom.createElement(NOMBRE);
		nombre.setTextContent(cliente.getNombre());
		raiz.appendChild(nombre);
		telefono = dom.createElement(TELEFONO);
		telefono.setTextContent(cliente.getTelefono());
		raiz.appendChild(telefono);
		
		return raiz;
	
	}
	
	public void terminar() {
		// y al terminar lo vuelva a almacenar en dicho fichero
		escribirXml();
	}
	private void escribirXml() {
		
		Element raiz = null;
		Document document = UtilidadesXml.crearDomVacio(RAIZ);
		raiz = document.getDocumentElement();
		System.out.println("escribirxml");
		if (!coleccionClientes.isEmpty()) {
			for (Cliente it : coleccionClientes) {
				try {
				Element cliente = clienteToElement(document, it);
				System.out.println("aguardar:"+it.toString()+cliente);
				raiz.appendChild(cliente);
				} catch (DOMException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		
		UtilidadesXml.domToXml(document, RUTA_FICHERO); 
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
