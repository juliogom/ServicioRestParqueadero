package com.ceiba.Parqueadero.entidad;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Slot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotNull
	private int numero;

	@OneToOne(cascade = CascadeType.ALL)
	private TipoVehiculo tipoVehiculo;

	public Slot() {
		super();
	}

	public Slot(int id, int numero, TipoVehiculo tipoVehiculo) {
		this.id=id;
		this.numero=numero;
		this.tipoVehiculo=tipoVehiculo;
	}

	public Slot(int numero, TipoVehiculo tipoVehiculo) {
		this.numero=numero;
		this.tipoVehiculo=tipoVehiculo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

}
