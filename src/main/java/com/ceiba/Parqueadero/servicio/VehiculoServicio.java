package com.ceiba.Parqueadero.servicio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import com.ceiba.Parqueadero.entidad.Slot;
import com.ceiba.Parqueadero.entidad.TipoVehiculo;
import com.ceiba.Parqueadero.entidad.Vehiculo;
import com.ceiba.Parqueadero.repositorio.RepositorioSlot;
import com.ceiba.Parqueadero.repositorio.RepositorioTipoVehiculos;
import com.ceiba.Parqueadero.repositorio.RepositorioVehiculos;

@ComponentScan("com.ceiba.Parqueadero.dominio,com.ceiba.Parqueadero.repositorio")
@Transactional
@Service
public class VehiculoServicio {

	private final int NUMERO_CARROS = 20;
	private final int NUMERO_MOTOS = 10;
	private final char RESTRICCION_LETRA = 'A';

	@Autowired
	private RepositorioVehiculos repositorioVehiculo;

	@Autowired
	private RepositorioTipoVehiculos repositorioTipo;
	
	@Autowired
	private RepositorioSlot repositorioSlot;
	
	@Autowired
	ActividadServicio actividadServicio;

	public void crear(Vehiculo vehiculo) {
		repositorioVehiculo.create(vehiculo);
	}
	
	public Vehiculo obtenerPorId(int id) {
		
		return repositorioVehiculo.encontrarPorId(id);
		
	}
	
	
	public TipoVehiculo obtenerTipoVehiculo(int id) {
		return repositorioTipo.encontrarPorId(id);
	}

	public String registro(Vehiculo vehiculo) {

		int cantidadVehiculosActivo = obtenerVehiculosActivos(obtenerParqueados(), vehiculo).toArray().length;

		boolean espacio = false;
		boolean guardar=false;
		
		String mensaje="";
		
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date fechaActual = new Date();
		
		
		int tipoVehiculo = vehiculo.getTipoVehiculo().getId();

		if ((tipoVehiculo == 1) && (cantidadVehiculosActivo <= NUMERO_CARROS)) {
			espacio = true;

		} else if ((tipoVehiculo == 2) && (cantidadVehiculosActivo <= NUMERO_MOTOS)) {
			espacio = true;
		} else {
			espacio = false;
		}
		
		if (espacio) {
			
			boolean letraInicial =verificarLetraInicial(vehiculo.getPlaca());
			
			if(letraInicial ) {
				
				Calendar calendar = Calendar.getInstance();
				int diaActual = calendar.get(Calendar.DAY_OF_WEEK);
				
				if(diaActual == 6 || diaActual == 0) {
					guardar=true;
				}else {
					guardar=false;
					mensaje="no puede ingresar al parqueadero porque no es un día hábil";
				}
				
			}else {
				guardar=true;
			}
			
			
		}else {
			guardar=false;
		}
		
		
		if(guardar) {
			
			Optional<Slot> opcional=obtenerPrimerSlotDisponible();
			
			if(opcional.isPresent()) {
				Slot slot=opcional.get();
				slot.setOcupado(true);
				
				Actividad actividad=new Actividad(1,fechaActual,slot,vehiculo);
				actividadServicio.crearActividad(actividad);
				mensaje="bienvenido";
			}
			/*if(obtenerPorId(vehiculo.getId()) != null) {
			}*/
			
		}
		
		
		return mensaje;
		// ObtenerParqueados
	}
	
	
	public boolean verificarLetraInicial(String placa) {

		boolean contiene = false;

		if (placa.charAt(0) == RESTRICCION_LETRA)
			contiene = true;

		return contiene;
	}

	public Stream<Vehiculo> obtenerVehiculosActivos(List<Actividad> actividades, Vehiculo vehiculo) {

		Stream<Vehiculo> vehiculosActivos = actividades.stream()
				.filter(p -> p.getEstado() == 1
						&& p.getVehiculo().getTipoVehiculo().getId() == vehiculo.getTipoVehiculo().getId())
				.map(new Function<Actividad, Vehiculo>() {
					@Override
					public Vehiculo apply(Actividad actividad) {

						return actividad.getVehiculo();
					}
				});

		return vehiculosActivos;
	}
	
	public Optional<Slot> obtenerPrimerSlotDisponible(){
		
		return repositorioSlot.buscarTodos().stream()
				.filter(s -> ! s.isOcupado()).findFirst();

	}
	
	
	public List<Actividad> obtenerParqueados() {
		return actividadServicio.obtenerActividades();
	}

}