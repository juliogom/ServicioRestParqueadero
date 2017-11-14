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
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.ceiba.Parqueadero.entidad.Slot;
import com.ceiba.Parqueadero.entidad.TipoVehiculo;
import com.ceiba.Parqueadero.entidad.Vehiculo;
import com.ceiba.Parqueadero.repositorio.RepositorioActividad;
import com.ceiba.Parqueadero.repositorio.RepositorioVehiculos;
import com.ceiba.Parqueadero.servicio.FacturaServicio;
import com.ceiba.Parqueadero.servicio.UsuarioServicio;
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

	@Autowired
	UsuarioServicio usuarioServicio;
	
	@GetMapping("/ingresar")
	public String obtenerPersonas() throws ParseException {

		return "";
	}


	@RequestMapping(value = "/persistVehiculo", method = RequestMethod.POST)
	public ResponseEntity<Vehiculo> persistPerson(@RequestBody Vehiculo vehiculo) {

		TipoVehiculo tipoVehiculo = servicioVehiculo.obtenerTipoVehiculo(1);
		vehiculo.setTipoVehiculo(tipoVehiculo);

		return new ResponseEntity<Vehiculo>(vehiculo, HttpStatus.OK);
	}

	// Método que contiene la implementación real de ingresar un vehículo
	@RequestMapping(value = "/parqueadero/servicio", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	public String persistVehiculo(@RequestBody Actividad actividad) {
		
		Vehiculo vehiculo=actividad.getVehiculo();
		TipoVehiculo tipoVehiculo = servicioVehiculo.obtenerTipoVehiculo(vehiculo.getTipoVehiculo().getId());//vehiculo.getTipoVehiculo()//servicioVehiculo.obtenerTipoVehiculo(1);
		
		Slot slot=servicioVehiculo.obtenerSlotPorId(actividad.getSlot().getId());
		
		vehiculo.setTipoVehiculo(tipoVehiculo);
		//System.out.println("Salida: "+servicioVehiculo.registro(vehiculo,slot));
		//System.out.println( "" +slot.getId());
		return servicioVehiculo.registro(vehiculo,slot);
	}

	// Método que contiene la implementación de pruebas
	@RequestMapping(value = "/Salida", method = RequestMethod.POST)
	public String registrarSalida(@RequestBody Vehiculo vehiculo) {
		int id = vehiculo.getId();
		//String salida = servicioVehiculo.realizarSalida(id);
		return "" + id;
	}

	// Método que contiene la implementación real de facturar y salir
	@RequestMapping(value = "parqueadero/factura", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	public ResponseEntity<DetalleFactura> facturaGuardar(@RequestBody Actividad actividad){
		
		return new ResponseEntity<DetalleFactura> (servicioVehiculo.realizarSalida(actividad),HttpStatus.OK);
	}
	
	//Método que contiene la implementación real de Obtener los vehiculos
	@GetMapping("/parqueadero/vehiculos")
	@CrossOrigin(origins = "*")
	public Stream<Actividad> obtenerVehiculos() {
		
		return servicioVehiculo.obtenerVehiculosParqueados();
	}
	
	//Método que contiene la implementación real de Obtener los Usuarios
	@GetMapping("/parqueadero/usuarios")
	@CrossOrigin(origins="*")
	public List<Cliente> obtenerClientes(){
		return usuarioServicio.obtenerActividades();
	}
	
	@RequestMapping(value = "/parqueadero/usuarios/guardar", method = RequestMethod.POST)
	@CrossOrigin(origins="*")
	public ResponseEntity<Cliente> usuarioGuardar(@RequestBody Cliente cliente) {
		System.out.println(cliente);
		return new ResponseEntity<Cliente> (usuarioServicio.crearCliente(cliente),HttpStatus.OK);
	}
	
	
	//Método que contiene la implementación real de Obtener los Usuarios
	@GetMapping("/parqueadero/slots")
	@CrossOrigin(origins="*")
	public List<Slot> obtenerSlots(){
		return servicioVehiculo.obtenerSlots();
	}
	
	//Método que contiene la implementación real de Obtener los tipos de vehiculo
	@GetMapping("/vehiculos/tiposVehiculos")
	@CrossOrigin(origins="*")
	public List<TipoVehiculo> obtenerTiposVehiculo(){
		return servicioVehiculo.obtenerTiposVehiculo();
	}
	
	
	@RequestMapping(value = "/parqueadero/vehiculos", method = RequestMethod.POST)
	@CrossOrigin(origins="*")
	public ResponseEntity<Vehiculo> GuardarVehiculo(@RequestBody Vehiculo Vehiculo) {
		return new ResponseEntity<Vehiculo> (servicioVehiculo.guardarVehiculo(Vehiculo),HttpStatus.OK);
	}
	
		
	@GetMapping("/vehiculos")
	@CrossOrigin(origins="*")
	public  List<Vehiculo> obtenerTodosVehiculos(){
		
		return servicioVehiculo.obtenerListadoVehiculos();
	}
	
	
	

}
