package com.ceiba.Parqueadero.servicio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.Parqueadero.dominio.ListaPersonas;
import com.ceiba.Parqueadero.entidad.Persona;
import com.ceiba.Parqueadero.entidad.Vehiculo;
import com.ceiba.Parqueadero.repositorio.RepositorioVehiculos;

@ComponentScan("com.ceiba.Parqueadero.dominio,com.ceiba.Parqueadero.repositorio")
@Transactional
@Service
public class VehiculoServicio {
	
	@Autowired
	private RepositorioVehiculos repositorioVehiculo;
	
	public void crear(Vehiculo vehiculo) {
		repositorioVehiculo.create(vehiculo);
	}

}