package com.ceiba.Parqueadero.servicio;

import java.util.List;
import java.util.stream.Stream;

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
import com.ceiba.Parqueadero.entidad.Actividad;
import com.ceiba.Parqueadero.entidad.Persona;
import com.ceiba.Parqueadero.entidad.TipoVehiculo;
import com.ceiba.Parqueadero.entidad.Vehiculo;
import com.ceiba.Parqueadero.repositorio.RepositorioTipoVehiculos;
import com.ceiba.Parqueadero.repositorio.RepositorioVehiculos;

@ComponentScan("com.ceiba.Parqueadero.dominio,com.ceiba.Parqueadero.repositorio")
@Transactional
@Service
public class VehiculoServicio {
	
	@Autowired
	private RepositorioVehiculos repositorioVehiculo;
	
	@Autowired
	private RepositorioTipoVehiculos repositorioTipo;
	
	@Autowired
	ActividadServicio actividadServicio;
	
	public void crear(Vehiculo vehiculo) {
		repositorioVehiculo.create(vehiculo);
	}
	
	public TipoVehiculo obtenerTipoVehiculo(int id) {
		return repositorioTipo.encontrarPorId(id);
	}
	
	
	public void registroVehiculo(Vehiculo vehiculo) {
		
		List<Actividad> actividades=ObtenerParqueados();
		
		Stream<Actividad> vehiculosActivos=actividades.stream().filter(p -> p.getEstado() == 1);
		
		
		//ObtenerParqueados
	}
	
	public void obtenerTodos() {
		
	}
	
	public List<Actividad> ObtenerParqueados() {
		return actividadServicio.obtenerActividades();
	}
	

}