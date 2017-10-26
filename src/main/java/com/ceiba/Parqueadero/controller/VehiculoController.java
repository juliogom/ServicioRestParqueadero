package com.ceiba.Parqueadero.controller;

import java.util.Calendar;
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
import org.springframework.web.bind.annotation.RequestParam;
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
		public String grabarVehiculo() {
				return "";
		}
		
		@GetMapping("/Actividad")
		public List<Actividad> obtenerActividades() {
			
				return servicioVehiculo.obtenerParqueados();
		}
		
		@GetMapping("/pruebas")
		public int utilidades() {
			Calendar calendar = Calendar.getInstance();
			int diaActual = calendar.get(Calendar.DAY_OF_WEEK);
				return diaActual;
		}
		
		@RequestMapping(value = "/persistVehiculo", method = RequestMethod.POST)
	    public ResponseEntity<Vehiculo>  persistPerson(@RequestBody Vehiculo vehiculo) {
	        
			TipoVehiculo tipoVehiculo=servicioVehiculo.obtenerTipoVehiculo(1);
			
			System.out.println(servicioVehiculo.registro(vehiculo));
			
	        return new ResponseEntity<Vehiculo>(vehiculo,HttpStatus.OK);
	    }
		
		@RequestMapping(value = "/pruebaPost", method = RequestMethod.POST)
	    public ResponseEntity<Vehiculo>  persistVehiculo(@RequestBody Vehiculo vehiculo) {        
			TipoVehiculo tipoVehiculo=servicioVehiculo.obtenerTipoVehiculo(1);
			
			vehiculo.setTipoVehiculo(tipoVehiculo);
			
	        return new  ResponseEntity<Vehiculo>(vehiculo,HttpStatus.OK);
	    }
		
		
}
