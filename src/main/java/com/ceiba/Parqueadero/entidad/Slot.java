package com.ceiba.Parqueadero.entidad;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@NamedQuery(name = "find_all_slots", query = "select S from Slot S")
public class Slot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	private int numero;
	
	@NotNull
	private boolean ocupado;

	@OneToOne(cascade = CascadeType.ALL)
	private TipoVehiculo tipoVehiculo;

	public Slot() {
		super();
	}

	public Slot(int id, int numero,boolean ocupado, TipoVehiculo tipoVehiculo) {
		this.id=id;
		this.numero=numero;
		this.ocupado=ocupado;
		this.tipoVehiculo=tipoVehiculo;
	}

	public Slot(int numero,boolean ocupado, TipoVehiculo tipoVehiculo) {
		this.numero=numero;
		this.ocupado=ocupado;
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

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}
	
	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	

}
