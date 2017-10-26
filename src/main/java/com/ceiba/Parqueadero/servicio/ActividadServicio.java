package com.ceiba.Parqueadero.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.ceiba.Parqueadero.entidad.TipoVehiculo;
import com.ceiba.Parqueadero.repositorio.RepositorioActividad;
import com.ceiba.Parqueadero.entidad.Actividad;

@Service
@ComponentScan("com.ceiba.Parqueadero.dominio,com.ceiba.Parqueadero.repositorio")
public class ActividadServicio {
	
	@Autowired
	private RepositorioActividad repositorioActividad;	
	
	public List<Actividad> obtenerActividades() {
		return repositorioActividad.buscarTodos();
	}
	
	public Actividad crearActividad(Actividad actividad) {
		return repositorioActividad.create(actividad);
	}
	
	
}
