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
		boolean guardar = false;

		String mensaje = "";

		Date fechaActual = new Date();

		int tipoVehiculo = vehiculo.getTipoVehiculo().getId();

		String nombreServicio = "";
		String descServicio = "";
		int valorServicio = 0;

		if ((tipoVehiculo == 1) && (cantidadVehiculosActivo <= NUMERO_CARROS)) {
			espacio = true;
			nombreServicio = "Servicio Carro";
			descServicio = "Servicio de parqueo Carro";
			valorServicio = 1000;

		} else if ((tipoVehiculo == 2) && (cantidadVehiculosActivo <= NUMERO_MOTOS)) {
			espacio = true;
			nombreServicio = "Servicio Moto";
			descServicio = "Servicio de parqueo Moto";
			valorServicio = 500;
		} else {
			espacio = false;
		}

		Servicio servicio = null;
		if (espacio) {
			servicio = new Servicio(nombreServicio, descServicio, valorServicio);

			boolean letraInicial = verificarLetraInicial(vehiculo.getPlaca());

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

		if (guardar) {

			Optional<Slot> opcional = obtenerPrimerSlotDisponible();

			if (opcional.isPresent()) {
				Slot slot = opcional.get();
				slot.setOcupado(true);

				Actividad actividad = new Actividad(1, fechaActual, servicio, slot, vehiculo);
				actividadServicio.crearActividad(actividad);
				mensaje = "bienvenido";
			}

		}

		return mensaje;
	}

	public DetalleFactura realizarSalida(int id) {
				
		Optional<Actividad>actividadOpcional=obtenerParqueados().stream().filter(s -> s.getVehiculo().getId() == id && s.getEstado()== 1).findFirst();
		
		DetalleFactura detalleFactura=null;
		
		if (actividadOpcional.isPresent()) {
			
			Actividad actividad = actividadOpcional.get();
			int valorDia=0;
			
			if(actividad.getVehiculo().getTipoVehiculo().getId()==1) {
				valorDia=VALOR_DIA_CARRO;
			}else {
				valorDia=VALOR_DIA_MOTO;
			}
			
			int cobroTotal=0;
			double numeroDias=0;
			
			cobroTotal=obtenerCobro(actividad,valorDia);
			numeroDias=obtenerDias(actividad);
			
			
			List<Actividad> actividades=new ArrayList<Actividad>();
			actividades.add(actividad);
			
			detalleFactura=serviciofactura.crear(actividades,cobroTotal,numeroDias);
			
			
		}
		
		return detalleFactura;
	}
	
	public int obtenerCobro(Actividad actividad,int valorDia) {
		        
        double dias=obtenerDias(actividad);
        
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
	
	public double obtenerDias(Actividad actividad) {
		Calendar calendar = Calendar.getInstance();
		 
        Date fechaInicial=actividad.getFechaInicio();
        Date fechaFinal=calendar.getTime();
		
        int minutos=(int) ((fechaFinal.getTime()-fechaInicial.getTime())/1000);
        
        int diferenciaHoras=(int)Math.floor(minutos/3600);
        
        double dias=(double)diferenciaHoras/24;
        
        return dias;
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

	public Optional<Slot> obtenerPrimerSlotDisponible() {
		return repositorioSlot.buscarTodos().stream().filter(s -> !s.isOcupado()).findFirst();
	}

	public List<Actividad> obtenerParqueados() {
		return actividadServicio.obtenerActividades();
	}
	
	public Stream<Actividad> obtenerVehiculosParqueados(){
		
		 return obtenerParqueados().stream().filter(v -> v.getEstado() == 1);
		
	}
	
	
	
}