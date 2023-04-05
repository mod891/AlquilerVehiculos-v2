package org.iesalandalus.programacion.alquilervehiculos;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.Modelo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.ModeloCascada;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;
import org.iesalandalus.programacion.utilidades.Entrada;

public class MainApp {

	public static void main(String[] args) {

		Modelo modelo = new ModeloCascada(FactoriaFuenteDatos.MEMORIA.crear());
		Vista vista = new Vista();
		Controlador controlador= new Controlador(modelo, vista);
		controlador.comenzar();
		
	}

}
