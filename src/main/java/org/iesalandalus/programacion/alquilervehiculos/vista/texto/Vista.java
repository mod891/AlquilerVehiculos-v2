package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;

public abstract class Vista {
	protected Controlador controlador;
	
	public void setControlador(Controlador controlador) {
		if (controlador == null)
			throw new NullPointerException("ERROR: El controlador es nulo");
	
		this.controlador = controlador;
	}
	
	public abstract void comenzar();
	
	
	public abstract void terminar();
}
