package com.ceiba.Parqueadero.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.Parqueadero.entidad.Actividad;
import com.ceiba.Parqueadero.entidad.TipoVehiculo;
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
		public int grabarVehiculo() {
				
				TipoVehiculo tipoVehiculo=servicioVehiculo.obtenerTipoVehiculo(1);
				Vehiculo vehiculo = new Vehiculo("Prado",2017,"HZQ-123","Negro",tipoVehiculo);
				
				logger.info("Inserting 1004 -> { }",
						tipoVehiculo);
				
				//servicioVehiculo.crear(vehiculo);
				
				return servicioVehiculo.registroVehiculo(vehiculo);
		}
		
		@GetMapping("/Actividad")
		public List<Actividad> obtenerActividades() {
				
				return servicioVehiculo.ObtenerParqueados();
		}
		
		
		
		@RequestMapping(value = "/persistVehiculo", method = RequestMethod.POST)
	    public ResponseEntity<Vehiculo>  persistPerson(@RequestBody Vehiculo vehiculo) {
	        
			
	        return new ResponseEntity<Vehiculo>(vehiculo,HttpStatus.OK);
	    }
}
