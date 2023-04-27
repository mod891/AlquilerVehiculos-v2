package org.iesalandalus.programacion.alquilervehiculos;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.Modelo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.ModeloCascada;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades.UtilidadesXml;
import org.iesalandalus.programacion.alquilervehiculos.vista.texto.Vista;
import org.iesalandalus.programacion.alquilervehiculos.vista.texto.VistaTexto;
import org.iesalandalus.programacion.utilidades.Entrada;
import org.w3c.dom.Document;

public class MainApp {

	public static void main(String[] args) {
				  
		
//		Document dom = UtilidadesXml.crearDomVacio("Clientes");
//		UtilidadesXml.domToXml(dom, "datos/clientes.xml");
		
			
		Modelo modelo = new ModeloCascada(FactoriaFuenteDatos.FICHEROS);
		Vista vista = new VistaTexto();
		Controlador controlador= new Controlador(modelo, vista);
		try {
			controlador.comenzar();
		} catch (OperationNotSupportedException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	
		/* */
	}

}
