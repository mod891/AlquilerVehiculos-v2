package org.iesalandalus.programacion.alquilervehiculos.vista;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;

public class Vista {
	private Controlador controlador;
	
	public void setControlador(Controlador controlador) {
		if (controlador == null)
			throw new NullPointerException("ERROR: El controlador es nulo");
	
		this.controlador = controlador;
	}
	
	public void comenzar() {
		Opcion opcion;
		do {
			Consola.mostrarMenu();
			opcion=Consola.elegirOpcion();
			ejecutar(opcion);
		}
		while (opcion!=Opcion.SALIR);

	}
	public void terminar() {
		System.out.println("La vista ha finalizado");
	}
	
	public void ejecutar(Opcion opcion) {
		
		switch (opcion) {

			case SALIR: 
				terminar();
	            break;
			case INSERTAR_CLIENTE: 
				insertarCliente();
	            break;
			case INSERTAR_TURISMO: 
				insertarTurismo();
	            break;
			case INSERTAR_ALQUILER: 
				insertarAlquiler();
	            break;
			case BUSCAR_CLIENTE: 
				buscarCliente();
	            break;
			case BUSCAR_TURISMO: 
				buscarTurismo();
	            break;
			case BUSCAR_ALQUILER: 
				buscarAlquiler();
	            break;
			case MODIFICAR_CLIENTE: 
				modificarCliente();
	            break;
			case DEVOLVER_ALQUILER: 
				devolverALquiler();
	            break;
			case BORRAR_CLIENTE: 
				borrarCliente();
	            break;
			case BORRAR_TURISMO: 
				borrarTurismo();
	            break;
			case BORRAR_ALQUILER: 
				borrarAlquiler();
	            break;
			case LISTAR_CLIENTES: 
				listarClientes();
	            break;
			case LISTAR_TURISMOS: 
				listarTurismos();
	            break;
			case LISTAR_ALQUILERES: 
				listarAlquileres();
	            break;
			case LISTAR_ALQUILERES_CLIENTE: 
				listarAlquileresCliente();
	            break;
			case LISTAR_ALQUILERES_TURISMO: 
				listarAlquileresTurismo();
	            break;
		}
	}

	private void insertarCliente(){
		 Consola.mostrarCabecera("Ha seleccionado insertar cliente");
	        try {
	            controlador.insertar(Consola.leerCliente());
	            System.out.println("Ha insertado un nuevo cliente");
	        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        }
	}
    
	private void insertarTurismo() {
		 Consola.mostrarCabecera("Ha seleccionado insertar turismo");
	        try {
	        	Turismo t =Consola.leerTurismo();
	        	System.out.println(t.toString());
	            controlador.insertar(t);
	            System.out.println("Ha insertado un nuevo turismo");
	        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        }
	}
    
	private void insertarAlquiler() {
		 Consola.mostrarCabecera("Ha seleccionado insertar alquiler");
	        try {
	            controlador.insertar(Consola.leerAlquiler());
	            System.out.println("Ha insertado un nuevo alquiler");
	        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        }
	}
   
	private void buscarCliente() {
		 Consola.mostrarCabecera("Ha seleccionado buscar cliente");
		 Cliente cli = null;
		 	try {
	        	cli = controlador.buscar(Consola.leerClienteDni());
	        	if (cli == null) 
	        		System.out.println("No se ha encontrado el cliente");
	        	else	        		
	        		System.out.println("cliente encontrado: "+cli.toString());
	        } catch (NullPointerException | IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        }
		
	}
   
	private void buscarTurismo() {
		 Consola.mostrarCabecera("Ha seleccionado buscar turismo");
	        try {
	            Turismo buscado = controlador.buscar(Consola.leerTurismoMatricula());
	            if (buscado == null)
	            	System.out.println("No se ha encontrado el turismo");
	            else System.out.println("Turismo encontrado: "+buscado.toString());
	            
	        } catch (NullPointerException | IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        }
		
	}
   
	private void buscarAlquiler() {
		 Consola.mostrarCabecera("Ha seleccionado buscar alquiler");
	        try {
	        	Alquiler alquiler = Consola.leerAlquiler();
	        	if (alquiler == null) 
	        		System.out.println("Alquiler no encontrado");
	        	else 
	        		System.out.println("Alquiler encontrado: "+alquiler.toString());
	        } catch (NullPointerException | IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        }
		
	}
  
	private void modificarCliente() {
		 Consola.mostrarCabecera("Ha seleccionado modificar cliente");
	        try {
	            controlador.modificar(Consola.leerClienteDni(),Consola.leerNombre(),Consola.leerTelefono());
	            System.out.println("Cliente modificado");
	        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        }
		
	}
  
	private void devolverALquiler() {
		Consola.mostrarCabecera("Ha seleccionado devolver alquiler");
        try {
            controlador.devolver(Consola.leerAlquiler(),Consola.leerFechaDevolucion());
            System.out.println("Alquiler devuelto");
        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
		
	}

	private void borrarCliente() {
		 Consola.mostrarCabecera("Ha seleccionado borrar cliente");
	        try {
	           controlador.borrar(Consola.leerClienteDni());
	           System.out.println("Cliente borrado");
	        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        }
		
	}
 
	private void borrarTurismo() {
		 Consola.mostrarCabecera("Ha seleccionado borrar turismo");
	        try {
	            controlador.borrar(Consola.leerTurismoMatricula());
	            System.out.println("Turismo borrado");
	        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        }
		
	}
  
	private void borrarAlquiler() {
		 Consola.mostrarCabecera("Ha seleccionado borrar alquiler");
	        try {
	            controlador.borrar(Consola.leerAlquiler());
	            System.out.println("Alquiler borrado");
	        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        }
		
	}

	private void listarClientes() {
		 Consola.mostrarCabecera("Ha seleccionado listar clientes");
		 List<Cliente> clientes;
	        try {
	            clientes = controlador.getClientes();
	            for (Cliente it: clientes) 
	        		System.out.println(it.toString());
	            
	        } catch (NullPointerException | IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        }
		
	}

	private void listarTurismos() {
		 Consola.mostrarCabecera("Ha seleccionado listar turismos");
		 List<Turismo> turismos;
	        try {
	        	turismos = controlador.getTurismos();
	        	for (Turismo it: turismos) 
	        		System.out.println(it.toString());
	        	
	        } catch (NullPointerException | IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        }
		
	}
    
	private void listarAlquileres() {
		 Consola.mostrarCabecera("Ha seleccionado listar alquileres");
		 List<Alquiler> alquileres;
		 
	        try {
	        	alquileres = controlador.getAlquileres();           	
	        	for (Alquiler it: alquileres) 
	        		System.out.println(it.toString());
	        	
	        } catch (NullPointerException | IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        }
	}
 
	private void listarAlquileresCliente() {
		 Consola.mostrarCabecera("Ha seleccionado listar alquileres cliente");
		 List<Alquiler> alquileres;
	        try {
	        	alquileres = controlador.getAlquileres(Consola.leerClienteDni());
	           	for (Alquiler it: alquileres) 
	        		System.out.println(it.toString());
	        	
	        } catch (NullPointerException | IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        }
		
	}
  
	private void listarAlquileresTurismo() {
		 Consola.mostrarCabecera("Ha seleccionado listar alquileres turismo");
		 List<Alquiler> alquileres;
	        try {
	        	alquileres = controlador.getAlquileres(Consola.leerTurismoMatricula());
	        	for (Alquiler it: alquileres) 
	        		System.out.println(it.toString());
	        	
	        } catch (NullPointerException | IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        }
		
	}
}
