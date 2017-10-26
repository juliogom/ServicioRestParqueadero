package com.ceiba.Parqueadero.servicio;

import java.util.List;
import java.util.function.Function;
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
	
	private final int NUMERO_CARROS=20;
	private final int NUMERO_MOTOS=10;
	
	
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
	
	
	public int registroVehiculo(Vehiculo vehiculo) {
		
		int cantidadVehiculosActivo=obtenerVehiculosActivos(ObtenerParqueados(),vehiculo).toArray().length;
		
		if((vehiculo.getTipoVehiculo().getId() == 1) && (cantidadVehiculosActivo <= NUMERO_CARROS)) {
			
			
			
		}else if(vehiculo.getTipoVehiculo().getId() == 2 && cantidadVehiculosActivo <= NUMERO_MOTOS) {
			
		}else{
			
		}
		
		return cantidadVehiculosActivo;
		//ObtenerParqueados
	}
	
	public void verificarLetra() {
		
	}
	
	
	public Stream<Vehiculo> obtenerVehiculosActivos(List<Actividad> actividades,Vehiculo vehiculo) {
		
		Stream<Vehiculo> vehiculosActivos=actividades.stream().filter(p -> p.getEstado() == 1 && p.getVehiculo().getTipoVehiculo().getId() == vehiculo.getTipoVehiculo().getId())
				.map(new Function<Actividad,Vehiculo>() {
	                  @Override
	                  public Vehiculo apply(Actividad actividad) {
	                	  	                	  
	                     return actividad.getVehiculo();
	                  }
	              }
				);
		
		return vehiculosActivos;
	}
	
	public List<Actividad> ObtenerParqueados() {
		return actividadServicio.obtenerActividades();
	}
	

}