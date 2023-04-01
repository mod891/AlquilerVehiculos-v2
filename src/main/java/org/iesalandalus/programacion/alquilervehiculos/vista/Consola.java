package org.iesalandalus.programacion.alquilervehiculos.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	private final static String PATRON_FECHA = "^([0-2][0-9]|3[0-1])\\/(0[1-9]|1[0-2])\\/[0-9]{4}$";
	private final static DateTimeFormatter FORMATO_FECHA= DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private Consola() {
		
	}
	
	public static void mostrarCabecera(String mensaje) {
		System.out.println(mensaje);
		System.out.println("_".repeat(mensaje.length()));
	}
	
	public static void mostrarMenu() {
		System.out.println("Alquiler de vehículos");
		System.out.println("_____________________");
		System.out.println("1: INSERTAR CLIENTE");
		System.out.println("2: INSERTAR TURISMO");
		System.out.println("3: INSERTAR ALQUILER");
		System.out.println("4: BUSCAR CLIENTE");
		System.out.println("5: BUSCAR TURISMO");
		System.out.println("6: BUSCAR ALQUILER");
		System.out.println("7: MODIFICAR CLIENTE");
		System.out.println("8: DEVOLVER ALQUILER");
		System.out.println("9: BORRAR CLIENTE");
		System.out.println("10: BORRAR TURISMO");
		System.out.println("11: BORRAR ALQUILER");
		System.out.println("12: LISTAR CLIENTES");
		System.out.println("13: LISTAR TURISMOS");
		System.out.println("14: LISTAR ALQUILERES");
		System.out.println("15: LISTAR ALQUILERES CLIENTE");
		System.out.println("16: LISTAR ALQUILERES TURISMO");
		System.out.println("0: SALIR");
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
	
	public static Opcion elegirOpcion() {
		int i;
		int opt = leerEntero("Elija una opcion:");
		boolean opcionValida = false;
		Opcion opcion = null;
		do {
			try {
				opcion = Opcion.get(opt);
				opcionValida = true;
	        } catch (Exception E) {	        
	           opt = leerEntero("Opcion incorrecta, elija una correcta: ");
	        }
		} while (opcionValida == false);
		
		return opcion;
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
	return leerCadena("introduzca un telefono: ");
	}
	
	public static Turismo leerTurismo() {
		String marca=leerCadena("Introduzca la marca del vehiculo: ");
		String modelo=leerCadena("Introduzca el modelo del vehiculo: ");
		int cilindrada=leerEntero("Introduzca la cilindrada del vehiculo: ");
		String matricula=leerCadena("Introduzca la matricula del vehiculo: ");
		return new Turismo(marca,modelo,cilindrada,matricula);
	}
	public static Turismo leerTurismoMatricula() {
		String matricula=leerCadena("Introduzaca el la matricula del turismo: ");
		return Turismo.getTurismoConMatricula(matricula);
	}
	public static Alquiler leerAlquiler() {
		Cliente cliente=leerClienteDni();
		Turismo turismo=leerTurismoMatricula();
		LocalDate fechaAlquiler=leerFecha("Introduzca la fecha de alquiler: ");
		return new Alquiler(cliente,turismo,fechaAlquiler);
	}
	public static LocalDate leerFechaDevolucion() {
		LocalDate fechaDevolucion=leerFecha("Introduzca la fecha de devolución: ");
		return fechaDevolucion;
	}
}
