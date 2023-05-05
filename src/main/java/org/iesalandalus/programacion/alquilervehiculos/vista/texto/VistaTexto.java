package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public class VistaTexto extends Vista {
	private Controlador controlador;
	
	public void setControlador(Controlador controlador) {
		if (controlador == null)
			throw new NullPointerException("ERROR: El controlador es nulo");
	
		this.controlador = controlador;
	}
	
	public void comenzar() {
		Accion opcion;
		do {
			Consola.mostrarMenuAcciones();
			opcion=Consola.elegirOpcion();
			ejecutar(opcion);
		}
		while (opcion!=Accion.SALIR);

	}
	public void terminar() {
		controlador.terminar();
		System.out.println("La vista ha finalizado");
	}
	
	private void ejecutar(Accion opcion) {
		
		switch (opcion) {

			case SALIR: 
				terminar();
	            break;
			case INSERTAR_CLIENTE: 
				insertarCliente();
	            break;
			case INSERTAR_VEHICULO: 
				insertarVehiculo();
	            break;
			case INSERTAR_ALQUILER: 
				insertarAlquiler();
	            break;
			case BUSCAR_CLIENTE: 
				buscarCliente();
	            break;
			case BUSCAR_VEHICULO: 
				buscarVehiculo();
	            break;
			case BUSCAR_ALQUILER: 
				buscarAlquiler();
	            break;
			case MODIFICAR_CLIENTE: 
				modificarCliente();
	            break;
            /*
			case DEVOLVER_ALQUILER: 
				devolverALquiler();
	            break;
            */
			case BORRAR_CLIENTE: 
				borrarCliente();
	            break;
			case BORRAR_VEHICULO: 
				borrarVehiculo();
	            break;
			case BORRAR_ALQUILER: 
				borrarAlquiler();
	            break;
			case LISTAR_CLIENTES: 
				listarClientes();
	            break;
			case LISTAR_VEHICULOS: 
				listarVehiculos();
	            break;
			case LISTAR_ALQUILERES: 
				listarAlquileres();
	            break;
			case LISTAR_ALQUILERES_CLIENTE: 
				listarAlquileresCliente();
	            break;
			case LISTAR_ALQUILERES_VEHICULO: 
				listarAlquileresVehiculo();
	            break;
			case DEVOLVER_ALQUILER_CLIENTE: // ERR EN DEVOLVER()
				devolverAlquilerCliente();
				break;
			case DEVOLVER_ALQUILER_VEHICULO: // ERR EN DEVOLVER()
				devolverAlquilerVehiculo();
				break;
			case MOSTRAR_ESTADISTICAS_MESUALES: // CHECK
				mostrarEstadisticasMensualesTipoVehiculo();
				break;
		}
	}

	public void insertarCliente(){
		 Consola.mostrarCabecera("Ha seleccionado insertar cliente");
	        try {
	            controlador.insertar(Consola.leerCliente());
	            System.out.println("Ha insertado un nuevo cliente");
	        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        }
	}
    
	public void insertarVehiculo() {
		 Consola.mostrarCabecera("Ha seleccionado insertar vehiculo");
	        try {
	        	Vehiculo vehiculo =Consola.leerVehiculo();
	        	//System.out.println(vehiculo.toString());
	            controlador.insertar(vehiculo);
	            System.out.println("Ha insertado un nuevo vehículo");
	        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        }
	}
    
	public void insertarAlquiler() {
		 Consola.mostrarCabecera("Ha seleccionado insertar alquiler");
	        try {
	            controlador.insertar(Consola.leerAlquiler());
	            System.out.println("Ha insertado un nuevo alquiler");
	        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        }
	}
   
	public void buscarCliente() {
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
   
	public void buscarVehiculo() {
		 Consola.mostrarCabecera("Ha seleccionado buscar turismo");
	        try {
	            Vehiculo buscado = controlador.buscar(Consola.leerVehiculoMatricula());
	            if (buscado == null)
	            	System.out.println("No se ha encontrado el turismo");
	            else System.out.println("Vehículo encontrado: "+buscado.toString());
	            
	        } catch (NullPointerException | IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        }
		
	}
   
	public void buscarAlquiler() {
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
  
	public void modificarCliente() {
		 Consola.mostrarCabecera("Ha seleccionado modificar cliente");
	        try {
	            controlador.modificar(Consola.leerClienteDni(),Consola.leerNombre(),Consola.leerTelefono());
	            System.out.println("Cliente modificado");
	        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        }
		
	}
  /* DEPRECATED
	public void devolverALquiler() {
		Consola.mostrarCabecera("Ha seleccionado devolver alquiler");
        try {
            controlador.devolver(Consola.leerAlquiler(),Consola.leerFechaDevolucion());
            System.out.println("Alquiler devuelto");
        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
		
	}
*/
	public void devolverAlquilerCliente() {
		 Consola.mostrarCabecera("Ha seleccionado devolver Alquiler Cliente");
		 try {
	            controlador.devolver(Consola.leerClienteDni(),Consola.leerFechaDevolucion());
	            System.out.println("Alquiler devuelto");
	            
		 }  catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
	            System.out.println(e.getMessage());
		 }
	}
	
	public void devolverAlquilerVehiculo() {
		Consola.mostrarCabecera("Ha seleccionado devolver Alquiler Vehículo");
		 try {
	            controlador.devolver(Consola.leerVehiculoMatricula(),Consola.leerFechaDevolucion());
	            System.out.println("Alquiler devuelto");
	            
		 }  catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
	            System.out.println(e.getMessage());
		 }
		
	}
	
	public void borrarCliente() {
		 Consola.mostrarCabecera("Ha seleccionado borrar cliente");
	        try {
	           controlador.borrar(Consola.leerClienteDni());
	           System.out.println("Cliente borrado");
	        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        }
		
	}
 
	public void borrarVehiculo() {
		 Consola.mostrarCabecera("Ha seleccionado borrar vehículo");
	        try {
	            controlador.borrar(Consola.leerVehiculoMatricula());
	            System.out.println("Vehículo borrado");
	        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        }
		
	}
  
	public void borrarAlquiler() {
		 Consola.mostrarCabecera("Ha seleccionado borrar alquiler");
	        try {
	            controlador.borrar(Consola.leerAlquiler());
	            System.out.println("Alquiler borrado");
	        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        }
		
	}

	public void listarClientes() {
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

	public void listarVehiculos() {
		 Consola.mostrarCabecera("Ha seleccionado listar vehículos");
		 List<Vehiculo> vehiculos;
	        try {
	        	vehiculos = controlador.getVehiculos();
	        	for (Vehiculo it: vehiculos) 
	        		System.out.println(it.toString());
	        	
	        } catch (NullPointerException | IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        }
		
	}
    
	public void listarAlquileres() {
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
 
	public void listarAlquileresCliente() {
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
  
	public void listarAlquileresVehiculo() {
		 Consola.mostrarCabecera("Ha seleccionado listar alquileres vehículo");
		 List<Alquiler> alquileres;
	        try {
	        	alquileres = controlador.getAlquileres(Consola.leerVehiculoMatricula());
	        	for (Alquiler it: alquileres) 
	        		System.out.println(it.toString());
	        	
	        } catch (NullPointerException | IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        }
		
	}
	
	private Map<TipoVehiculo,Integer> inicializarEstadisticas() {
		Map<TipoVehiculo,Integer> mapa = new EnumMap<>(TipoVehiculo.class);
		
		mapa.put(TipoVehiculo.AUTOBUS, 0);
		mapa.put(TipoVehiculo.FURGONETA, 0);
		mapa.put(TipoVehiculo.TURISMO, 0);
		
		return mapa;
	}

	public void mostrarEstadisticasMensualesTipoVehiculo() {
		Map<TipoVehiculo,Integer> mapa = inicializarEstadisticas();
		LocalDate mes = Consola.leerMes();
		List<Alquiler> alquileres = controlador.getAlquileres();
		TipoVehiculo tipo = null;
		int nVeces = 0;
		
		for (Alquiler alquiler : alquileres) {
			if ( alquiler.getFechaAlquiler().getMonth().equals(mes.getMonth()) ) {
				tipo = TipoVehiculo.get(alquiler.getVehiculo());
				nVeces = mapa.get(tipo) +1;
				mapa.put(tipo,nVeces);
			}
					
			System.out.println(mapa.toString());
		}
	}
}
