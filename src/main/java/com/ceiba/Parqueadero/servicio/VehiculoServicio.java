package com.ceiba.Parqueadero.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.Parqueadero.dominio.ListaPersonas;
import com.ceiba.Parqueadero.dominio.Persona;
import com.ceiba.Parqueadero.entidad.Vehiculo;
import com.ceiba.Parqueadero.repositorio.RepositorioVehiculos;

@RestController
@ComponentScan("com.ceiba.Parqueadero.dominio,com.ceiba.Parqueadero.repositorio")
public class VehiculoServicio {

	@Autowired
	private RepositorioVehiculos repositorioVehiculo;

	@GetMapping("/Vehiculos")
	public String obtenerPersonas() {

		try {
			Vehiculo vehiculo = new Vehiculo();
			repositorioVehiculo.insertarVehiculo(vehiculo);
		} catch (Exception e) {

			System.out.println(e);

		}

		return "Guardado";
	}

}