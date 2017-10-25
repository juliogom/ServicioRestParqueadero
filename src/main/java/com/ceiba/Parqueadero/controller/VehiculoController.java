package com.ceiba.Parqueadero.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.Parqueadero.entidad.Vehiculo;
import com.ceiba.Parqueadero.repositorio.RepositorioVehiculos;
import com.ceiba.Parqueadero.servicio.VehiculoServicio;


@RestController
@EnableAutoConfiguration
@ComponentScan("com.ceiba.Parqueadero.servicio,com.ceiba.Parqueadero.repositorio")
public class VehiculoController {
		
		private Logger logger=LoggerFactory.getLogger(this.getClass());
	
		@Autowired
		RepositorioVehiculos repositorioVehiculo;
		
		@Autowired
		VehiculoServicio servicioVehiculo;
		
		@GetMapping("/ingresar")
		public String obtenerPersonas() {
				
				return repositorioVehiculo.encontrarPorId(1).getNombre();
		}
		
		@GetMapping("/Grabar")
		public String grabarPersonas() {
				Vehiculo vehiculo = new Vehiculo("Toyota",2015,"HZQ-123","Negro");
				
				servicioVehiculo.crear(vehiculo);
				
				//logger.info("Inserting 1004 -> { }",
						//repositorioVehiculo.create(new Vehiculo("Ferrary",2015,"HZQ-123","Negro")));
				
				return "Guardado";
		}
		
	
}
