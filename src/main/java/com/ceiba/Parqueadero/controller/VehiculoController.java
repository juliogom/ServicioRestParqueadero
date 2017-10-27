package com.ceiba.Parqueadero.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

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
import com.ceiba.Parqueadero.entidad.Cliente;
import com.ceiba.Parqueadero.entidad.DetalleFactura;
import com.ceiba.Parqueadero.entidad.Factura;
import com.ceiba.Parqueadero.entidad.TipoVehiculo;
import com.ceiba.Parqueadero.entidad.Vehiculo;
import com.ceiba.Parqueadero.repositorio.RepositorioActividad;
import com.ceiba.Parqueadero.repositorio.RepositorioVehiculos;
import com.ceiba.Parqueadero.servicio.FacturaServicio;
import com.ceiba.Parqueadero.servicio.VehiculoServicio;

@RestController
@EnableAutoConfiguration
@ComponentScan("com.ceiba.Parqueadero.servicio,com.ceiba.Parqueadero.repositorio")
public class VehiculoController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	RepositorioVehiculos repositorioVehiculo;

	@Autowired
	VehiculoServicio servicioVehiculo;


	@GetMapping("/ingresar")
	public String obtenerPersonas() throws ParseException {

		return "";
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
	public ResponseEntity<Vehiculo> persistPerson(@RequestBody Vehiculo vehiculo) {

		TipoVehiculo tipoVehiculo = servicioVehiculo.obtenerTipoVehiculo(1);
		vehiculo.setTipoVehiculo(tipoVehiculo);

		System.out.println();

		return new ResponseEntity<Vehiculo>(vehiculo, HttpStatus.OK);
	}

	// Método que contiene la implementación real
	@RequestMapping(value = "/pruebaPost", method = RequestMethod.POST)
	public String persistVehiculo(@RequestBody Vehiculo vehiculo) {
		TipoVehiculo tipoVehiculo = servicioVehiculo.obtenerTipoVehiculo(1);

		vehiculo.setTipoVehiculo(tipoVehiculo);

		return servicioVehiculo.registro(vehiculo);
	}

	// Método que contiene la implementación de pruebas
	@RequestMapping(value = "/Salida", method = RequestMethod.POST)
	public String registrarSalida(@RequestBody Vehiculo vehiculo) {
		int id = vehiculo.getId();
		//String salida = servicioVehiculo.realizarSalida(id);
		return "" + id;
	}

	// Método que contiene la implementación real de facturar y salir
	@RequestMapping(value = "/factura", method = RequestMethod.POST)
	public ResponseEntity<DetalleFactura> facturaGuardar(@RequestBody Vehiculo vehiculo) {
		return new ResponseEntity<DetalleFactura> (servicioVehiculo.realizarSalida(vehiculo.getId()),HttpStatus.OK);
	}
	
	//Método que contiene la implementación real de Obtener los vehiculos
	@GetMapping("/parqueadero/vehiculos")
	public Stream<Actividad> obtenerVehiculos() {
		
		return servicioVehiculo.obtenerVehiculosParqueados();
	}
	
	

}
