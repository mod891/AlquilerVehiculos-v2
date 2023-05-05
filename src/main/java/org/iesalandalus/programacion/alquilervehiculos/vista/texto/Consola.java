package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	private final static String PATRON_FECHA = "^([0-2][0-9]|3[0-1])\\/(0[1-9]|1[0-2])\\/[0-9]{4}$";
	private final static String PATRON_MES = "MM";
	private final static DateTimeFormatter FORMATO_FECHA= DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private Consola() {
		
	}
	
	public static void mostrarCabecera(String mensaje) {
		System.out.println(mensaje);
		System.out.println("_".repeat(mensaje.length()));
	}
	
	public static void mostrarMenuAcciones() {
		System.out.println("Alquiler de vehículos");
		System.out.println("_____________________");
		System.out.println("1: INSERTAR CLIENTE");
		System.out.println("2: INSERTAR VEHÍCULO");
		System.out.println("3: INSERTAR ALQUILER");
		System.out.println("4: BUSCAR CLIENTE");
		System.out.println("5: BUSCAR VEHÍCULO");
		System.out.println("6: BUSCAR ALQUILER");
		System.out.println("7: MODIFICAR CLIENTE");
		System.out.println("8: BORRAR CLIENTE");
		System.out.println("9: BORRAR VEHÍCULO");
		System.out.println("10: BORRAR ALQUILER");
		System.out.println("11: LISTAR CLIENTES");
		System.out.println("12: LISTAR VEHÍCULOS");
		System.out.println("13: LISTAR ALQUILERES");
		System.out.println("14: LISTAR ALQUILERES CLIENTE");
		System.out.println("15: LISTAR ALQUILERES VEHÍCULOS");
		System.out.println("16: DEVOLVER ALQUILER CLIENTE");
		System.out.println("17: DEVOLVER ALQUILER VEHÍCULO");
		System.out.println("18: MOSTRAR ESTADÍSTICAS MENSUALES");
		System.out.println("0: SALIR");
	}
	
	public static Accion elegirOpcion() {
		int i;
		int opt = leerEntero("Elija una acción:");
		boolean opcionValida = false;
		Accion opcion = null;
		do {
			try {
				opcion = Accion.get(opt);
				opcionValida = true;
	        } catch (Exception E) {	        
	           opt = leerEntero("Acción incorrecta, elija una correcta: ");
	        }
		} while (opcionValida == false);
		
		return opcion;
	}
	private static String leerCadena(String mensaje) {
		System.out.println(mensaje);
		return Entrada.cadena();
	}
	private static Integer leerEntero(String mensaje) {
		System.out.println(mensaje);
		return Entrada.entero();
	}
	private static LocalDate leerFecha(String mensaje) {
		String fecha;
		do {
			System.out.println(mensaje);
			fecha=Entrada.cadena();
		} while (!fecha.matches(PATRON_FECHA));
		
		return LocalDate.parse(fecha,FORMATO_FECHA);
	}
	

	
	public static Cliente leerCliente() {
		String nombre=leerCadena("Introduzca el nombre del cliente: ");
		String dni=leerCadena("Introduzaca el DNI del cliente: ");
		String telefono=leerCadena("introduzca el telefono del cliente: ");
		return new Cliente(nombre, dni, telefono);
	}
	public static Cliente leerClienteDni() {
		String dni=leerCadena("Introduzaca el DNI del cliente: ");
		return Cliente.getClienteConDni(dni);
	}
	public static String leerNombre() {
	return leerCadena("Introduzca un nombre: ");
		
	}
	public static String leerTelefono() {
	return leerCadena("Introduzca un teléfono: ");
	}
	
	public static Vehiculo leerVehiculo() {
		
		mostrarMenuTiposVehiculos();
		TipoVehiculo tipo = elegirTipoVehiculo();
		
		return leerVehiculo(tipo);	
	}
	
	private static void mostrarMenuTiposVehiculos() {
		
		 System.out.println("Tipos de vehículo:");
		for (TipoVehiculo tipo : TipoVehiculo.values()) 
			System.out.println(tipo);
	}
	
	private static TipoVehiculo elegirTipoVehiculo() {
		int tipo;
		
		do{
			tipo = leerEntero("Introduzca el tipo [0-2]:");
		} while (tipo < 0 || tipo > 2);
		
		return TipoVehiculo.get(tipo);
	}
	
	public static Vehiculo leerVehiculo(TipoVehiculo tipoVehiculo) {
		Vehiculo vehiculo = null;
		String marca, modelo, matricula;
		int cilindrada, plazas, pma;
		
		
		
		if(tipoVehiculo == TipoVehiculo.TURISMO) {
			marca = leerCadena("Introduzca la marca del turismo: ");
			modelo = leerCadena("Introduzca el modelo del turismo: ");
			cilindrada = leerEntero("Introduzca la cilindrada del turismo: ");
			matricula = leerCadena("Introduzca la matrícula del turismo: ");
			
			vehiculo = new Turismo(marca, modelo, cilindrada, matricula);
		
		} else if(tipoVehiculo == TipoVehiculo.AUTOBUS) {
			marca = leerCadena("Introduzca la marca del autobús: ");
			modelo = leerCadena("Introduzca el modelo del autobús: ");
			plazas = leerEntero("Introduzca las plazas del autobús: ");
			matricula = leerCadena("Introduzca la matrícula del autobús: ");
			
			vehiculo = new Autobus(marca, modelo, plazas, matricula);
			
		} else if(tipoVehiculo == TipoVehiculo.FURGONETA) {
			marca = leerCadena("Introduzca la marca de la furgoneta:");
			modelo = leerCadena("Introduzca el modelo de la furgoneta:");
			pma = leerEntero("Introduzca el PMA de la furgoneta :");
			plazas = leerEntero("Introduzca las plazas de la furgoneta:");
			matricula = leerCadena("Introduzca la matrícula de la furgoneta:");
			
			vehiculo = new Furgoneta(marca, modelo, pma, plazas, matricula);
		}
		
		return vehiculo;
	}
	
	public static Vehiculo leerVehiculoMatricula() {
		String matricula=leerCadena("Introduzca la matricula del turismo:");
		return Vehiculo.getVehiculoConMatricula(matricula);
	}
	public static Alquiler leerAlquiler() {
		Cliente cliente=leerClienteDni();
		Vehiculo vehiculo=leerVehiculoMatricula();
		LocalDate fechaAlquiler=leerFecha("Introduzca la fecha de alquiler:");
		
		return new Alquiler(cliente,vehiculo,fechaAlquiler);
	}
	public static LocalDate leerFechaDevolucion() {
		LocalDate fechaDevolucion=leerFecha("Introduzca la fecha de devolución:");
		return fechaDevolucion;
	}
	
	public static LocalDate leerMes() {
		LocalDate mes = null;
		
		try {
			mes = LocalDate.parse(Entrada.cadena(), DateTimeFormatter.ofPattern(PATRON_MES));
		
		} catch(DateTimeParseException e) {
			System.out.println(e.getMessage());
		}	
		return mes;
	}
}
