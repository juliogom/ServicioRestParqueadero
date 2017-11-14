package com.ceiba.Parqueadero.servicio;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.ceiba.Parqueadero.entidad.DetalleFactura;
import com.ceiba.Parqueadero.entidad.Factura;
import com.ceiba.Parqueadero.entidad.Persona;
import com.ceiba.Parqueadero.entidad.Servicio;
import com.ceiba.Parqueadero.entidad.Slot;
import com.ceiba.Parqueadero.entidad.TipoVehiculo;
import com.ceiba.Parqueadero.entidad.Vehiculo;
import com.ceiba.Parqueadero.repositorio.RepositorioServicios;
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
	
	private final int VALOR_ADICIONAL_CILINDRAJE = 2000;
	private final int VALOR_DIA_CARRO=8000;
	private final int VALOR_DIA_MOTO=6000;

	@Autowired
	private RepositorioVehiculos repositorioVehiculo;

	@Autowired
	private RepositorioTipoVehiculos repositorioTipo;

	@Autowired
	private RepositorioSlot repositorioSlot;

	@Autowired
	ActividadServicio actividadServicio;
	
	@Autowired
	FacturaServicio serviciofactura;
	
	@Autowired
	RepositorioServicios repositorioServicio;
	
	public void crear(Vehiculo vehiculo) {
		repositorioVehiculo.create(vehiculo);
	}

	public Vehiculo obtenerPorId(int id) {

		return repositorioVehiculo.encontrarPorId(id);

	}

	public TipoVehiculo obtenerTipoVehiculo(int id) {
		return repositorioTipo.encontrarPorId(id);
	}
	
	public List<TipoVehiculo> obtenerTiposVehiculo(){
		return repositorioTipo.buscarTodos();
	}

	public String registro(Vehiculo vehiculo,Slot slot){

		int cantidadVehiculosActivo = obtenerVehiculosActivos(obtenerParqueados(), vehiculo).toArray().length;

		boolean espacio = false;
		boolean guardar = false;

		String mensaje = "";

		Date fechaActual = new Date();

		int tipoVehiculo = vehiculo.getTipoVehiculo().getId();

		Servicio servicio = null;
		
		if ((tipoVehiculo == 1) && (cantidadVehiculosActivo <= NUMERO_CARROS)) {
			espacio = true;
			
			servicio = this.repositorioServicio.findById(1);
			
		} else if ((tipoVehiculo == 2) && (cantidadVehiculosActivo <= NUMERO_MOTOS)) {
			espacio = true;
			
			servicio = this.repositorioServicio.findById(2);
		} else {
			espacio = false;
			
			servicio=null;
			
			System.out.println("No es ninguno");
		}

		if (espacio) {
			
			boolean letraInicial = verificarLetraInicial(vehiculo.getPlaca());
			guardar=true;
			if (letraInicial) {

				Calendar calendar = Calendar.getInstance();
				int diaActual = calendar.get(Calendar.DAY_OF_WEEK);

				if (diaActual == 1 || diaActual == 2) {
					guardar = true;
				} else {
					guardar = false;
					mensaje = "no puede ingresar al parqueadero porque no es un día hábil";
				}

			} else {
				guardar = true;
			}

		} else {
			guardar = false;
			mensaje = "No hay espacios disponibles en el parqueadero para su tipo de vehiculo";
		}

		if (guardar && (!vehiculoParqueado(vehiculo.getId())) ) {
			
				if(!slot.isOcupado()) {
					slot.setOcupado(true);
					
					Actividad actividad = new Actividad(1, fechaActual, servicio, slot, vehiculo);
					actividadServicio.crearActividad(actividad);
					mensaje = "bienvenido";
				}else {
					mensaje = "Slot Ocupado";
				}
			
		}

		return mensaje;
	}
	
	public boolean vehiculoParqueado(int id) {
		
		 Optional vehiculoActivo=this.actividadServicio.obtenerActividades().stream()
				.filter(act -> act.getVehiculo().getId() == id && act.getEstado() == 1).findFirst();
		
		if(vehiculoActivo.isPresent()) {
			return true;
		}else
			return false;
		 
	}
	
	
	public DetalleFactura realizarSalida(Actividad act) {
		
		Actividad actividad=this.actividadServicio.buscarActividadPorId(act.getId());
		
			DetalleFactura detalleFactura=null;
			
			int cobroTotal=0;
			double numeroDias=0;
			
			if(actividad != null) {
				
				cobroTotal=obtenerCobro(actividad,actividad.getServicio().getPrecio());
				numeroDias=obtenerDias(actividad.getFechaInicio());
				
				actividad.getSlot().setOcupado(false);
				
				List<Actividad> actividades=new ArrayList<Actividad>();
				actividades.add(actividad);
				
				detalleFactura=serviciofactura.crear(actividades,cobroTotal,numeroDias);
			}
		return detalleFactura;
	}
	
	public int obtenerCobro(Actividad actividad,int valorDia) {
		        
        double dias=obtenerDias(actividad.getFechaInicio());
        
        BigDecimal number = new BigDecimal(dias);
        
        long parteEntera=number.longValue();
                
        double fraccionDoble=number.remainder(BigDecimal.ONE).doubleValue()*24;
        
        int horas=0;
        
        if(fraccionDoble>=9){
        	horas=valorDia;
        	
        }else {
        	horas=(int) fraccionDoble *actividad.getServicio().getPrecio();
        }
        
        if(actividad.getVehiculo().getTipoVehiculo().getId()==2 && actividad.getVehiculo().getCilindraje() > 500) {
        	return (horas + (int)(parteEntera*valorDia)) + 2000;
        }else {
        	return (horas + (int)(parteEntera*valorDia));
        }
	}
	
	public double obtenerDias(Date fechaInicial) {
		Calendar calendar = Calendar.getInstance();
		 
        Date fechaFinal=calendar.getTime();
		
        int minutos=(int) ((fechaFinal.getTime()-fechaInicial.getTime())/1000);
        
        int diferenciaHoras=(int)Math.floor(minutos/3600);
        
        double dias=(double)diferenciaHoras/24;
        
        return dias;
	}
	
	

	public boolean verificarLetraInicial(String placa) {

		boolean contiene = false;
		
		if ((""+placa.charAt(0)).toLowerCase().equals((""+RESTRICCION_LETRA).toLowerCase()) )
			contiene = true;
		
		
		return contiene;
	}

	public Stream<Vehiculo> obtenerVehiculosActivos(List<Actividad> actividades, Vehiculo vehiculo) {

		return actividades.stream()
				.filter(p -> p.getEstado() == 1
						&& p.getVehiculo().getTipoVehiculo().getId() == vehiculo.getTipoVehiculo().getId())
				.map(new Function<Actividad, Vehiculo>() {
					@Override
					public Vehiculo apply(Actividad actividad) {

						return actividad.getVehiculo();
					}
				});

	}

	public Optional<Slot> obtenerPrimerSlotDisponible() {
		return repositorioSlot.buscarTodos().stream().filter(s -> !s.isOcupado()).findFirst();
	}
	
	public List<Slot> obtenerSlots(){
		return repositorioSlot.buscarTodos();
	}
	
	public Slot obtenerSlotPorId(int id) {
		return repositorioSlot.findById(id);
	}

	public List<Actividad> obtenerParqueados() {
		return actividadServicio.obtenerActividades();
	}
	
	public Stream<Actividad> obtenerVehiculosParqueados(){
		
		 return obtenerParqueados().stream().filter(v -> v.getEstado() == 1);
		
	}
	
	public Vehiculo guardarVehiculo(Vehiculo vehiculo) {
		return this.repositorioVehiculo.create(vehiculo);
	}
	
	public List<Vehiculo> obtenerListadoVehiculos(){
		return repositorioVehiculo.findAll();
	}
	
	
}