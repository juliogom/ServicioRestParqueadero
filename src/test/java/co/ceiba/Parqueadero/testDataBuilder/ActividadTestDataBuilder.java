package co.ceiba.Parqueadero.testDataBuilder;

import java.util.Date;

import com.ceiba.Parqueadero.entidad.Actividad;
import com.ceiba.Parqueadero.entidad.Persona;
import com.ceiba.Parqueadero.entidad.Servicio;
import com.ceiba.Parqueadero.entidad.Slot;
import com.ceiba.Parqueadero.entidad.Vehiculo;

public class ActividadTestDataBuilder {
	private int id;
	private int estado;
	private Date fechaInicio;
	private Servicio servicio;
	private Vehiculo vehiculo;
	private Slot slot;
	
	public ActividadTestDataBuilder() {
		super();
		id=1;
		estado=1;
		fechaInicio=new Date("2017-10-26 09:40:00.0");
		servicio=null;
		vehiculo=null;
		slot=null;
	}
	
	public ActividadTestDataBuilder withId(int id) {
		this.id=id;	
		return this;
	}
	
	public ActividadTestDataBuilder withEstado(int estado) {
		this.estado=estado;
		return this;
	}
	
	public ActividadTestDataBuilder withFechaInicio(Date fechaInicio) {
		this.fechaInicio=fechaInicio;
		return this;
	}
	
	public ActividadTestDataBuilder withServicio(Servicio servicio) {
		this.servicio=servicio;
		return this;
	}
	
	public ActividadTestDataBuilder withVehiculo(Vehiculo vehiculo) {
		this.vehiculo=vehiculo;
		return this;
	}
	
	public ActividadTestDataBuilder withSlot(Slot slot) {
		this.slot=slot;
		return this;
	}
	
	public Actividad build() {
		return new Actividad(id,estado,fechaInicio,servicio,slot, vehiculo);
	}
}
