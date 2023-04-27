package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class UtilidadesXml {
	private UtilidadesXml() {}
	
	public static Document xmlToDom(String rutaXml) {

		Document documento = null;
		try {
		    // 1º Creamos una nueva instancia de un fábrica de constructores de documentos.
		    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		    // 2º A partir de la instancia anterior, fabricamos un constructor de documentos, que procesará el XML.
		    DocumentBuilder db = dbf.newDocumentBuilder();
		    // 3º Procesamos el documento (almacenado en un archivo) y lo convertimos en un árbol DOM.	
		   documento=db.parse(rutaXml);
		   
		} catch (Exception e) {
            System.out.println(e.getMessage());
		}  
		
		return documento;
	}
	
	public static void domToXml(Document dom, String rutaXml) {

		 try {
			 File f=new File(rutaXml);
			 TransformerFactory tFactory=TransformerFactory.newInstance();
			 tFactory.setAttribute("indent-number", new Integer(4));
			 Transformer transformer = tFactory.newTransformer();
	           
			 transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	           
			 FileOutputStream fos =new FileOutputStream(f);
			 StreamResult result = new StreamResult(new OutputStreamWriter(fos,"UTF-8"));  
			 DOMSource source = new DOMSource(dom);
			 transformer.transform(source, result);         
		 } catch (TransformerException ex) {
	            ex.getMessage();           
		 } catch (UnsupportedEncodingException uee){
	        	uee.getMessage();  
		 } catch (FileNotFoundException fnfe){
	        	fnfe.getMessage();  
		 }
		 
	  	}
		
		
		/*	
		try {

            // 1º Creamos una instancia de la clase File para acceder al archivo donde guardaremos el XML.
            File f=new File(rutaXml);

            //2º Creamos una nueva instancia del transformador a través de la fábrica de transformadores.
            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            //3º Establecemos algunas opciones de salida, como por ejemplo, la codificación de salida.
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");            

            //4º Creamos el StreamResult, que intermediará entre el transformador y el archivo de destino.
            StreamResult result = new StreamResult(f);            

            //5º Creamos el DOMSource, que intermediará entre el transformador y el árbol DOM.
            DOMSource source = new DOMSource(dom);

            //6º Realizamos la transformación.
            transformer.transform(source, result);         
	
		} catch (TransformerException e) {
		     System.out.println(e.getMessage());            	
		}
		
	}
	*/
	public static Document crearDomVacio(String raiz) {
		
        DocumentBuilder db;
        Document d = null ;
        try {
        	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        	db = dbf.newDocumentBuilder() ;
        	d = db.newDocument() ;
            d.appendChild(d.createElement(raiz));
            return d;
        } catch (Exception e) {
        	e.getMessage();
        }
        return d ;
    }
		
	/*	
		
		Document documento = null;

		try {
	        // 1º Creamos una nueva instancia de un fábrica de constructores de documentos.
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        
	        // 2º A partir de la instancia anterior, fabricamos un constructor de documentos, que procesará el XML.
	        DocumentBuilder db = dbf.newDocumentBuilder();
	
	        // 3º Procesamos el documento (almacenado en un archivo) y lo convertimos en un árbol DOM.
	        documento=db.newDocument();
	        
	       // Node nodo = ;
	        documento.appendChild(documento.createElement(raiz));
	        System.out.println("crearDomVacio:raiz"+documento.getDocumentElement());// raiz[Clientes: null]
	        return documento;
		} catch (Exception e) {
            System.out.println(e.getMessage());
		}  


		return documento;
	}
	*/
}
