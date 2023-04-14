package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

public enum Accion { 
	SALIR("Salir"), 
	INSERTAR_CLIENTE("Insertar Cliente"), 
	INSERTAR_VEHICULO("Insertar Vehículo"),
	INSERTAR_ALQUILER("Insertar Alquiler"), 
	BUSCAR_CLIENTE("Buscar Cliente"), 
	BUSCAR_VEHICULO("Buscar Vehículo"), 
	BUSCAR_ALQUILER("Buscar Alquiler"),
	MODIFICAR_CLIENTE("Modificar Cliente"), 
	DEVOLVER_ALQUILER("Devolver Alquiler"), 
	BORRAR_CLIENTE("Borrar Cliente"), 
	BORRAR_VEHICULO("Borrar Vehículo"),
	BORRAR_ALQUILER("Borrar Alquiler"), 
	LISTAR_CLIENTES("Listar Clientes"),
	LISTAR_VEHICULOS("Listar Vehículos"), 
	LISTAR_ALQUILERES("Listar Alquileres"), 
	LISTAR_ALQUILERES_CLIENTE("Listar Alquileres Cliente"), 
	LISTAR_ALQUILERES_VEHICULO("Listar Alquileres Vehículo"),
	DEVOLVER_ALQUILER_CLIENTE("Devolver Alquiler Cliente"),
	DEVOLVER_ALQUILER_VEHICULO("Devolver Alquiler Vehículo"),
	MOSTRAR_ESTADISTICAS_MESUALES("Mostrar Estadisticas Mensuales");
	
	private String texto; 
	
	private Accion(String texto) {
		
		this.texto=texto; 		
	}
	
	static boolean esOrdinalValido(int ordinal) {
		
		return (ordinal >= 0 && ordinal <= Accion.values().length - 1);
	}
	
	public static Accion get(int ordinal) {
		
		if (esOrdinalValido(ordinal))
			
			return values()[ordinal];
		else
			throw new IllegalArgumentException("Ordinal de la opción no válido");
	}

	public String toString() {
		
		return String.format("%d.- %s", ordinal(), texto);
	}
}