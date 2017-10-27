package com.ceiba.Parqueadero.entidad;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@NamedQuery(name = "find_all_Actividades", query = "select A from Actividad A")
public class Actividad {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull
	private int estado;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	public Date fechaInicio;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Servicio servicio;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Slot slot;

	@OneToOne(cascade = CascadeType.ALL)
	private Vehiculo vehiculo;
	
	public Actividad() {
		super();
	}
	
	public Actividad(int id, int estado, Date fechaInicio,Servicio servicio, Slot slot, Vehiculo vehiculo) {
		this.id = id;
		this.fechaInicio = fechaInicio;
		this.estado = estado;
		this.servicio=servicio;
		this.slot = slot;
		this.vehiculo = vehiculo;
	}

	public Actividad(int estado, Date fechaInicio,Servicio servicio, Slot slot, Vehiculo vehiculo) {
		this.estado = estado;
		this.fechaInicio = fechaInicio;
		this.servicio=servicio;
		this.slot = slot;
		this.vehiculo = vehiculo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	
	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

}
