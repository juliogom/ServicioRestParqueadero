package com.ceiba.Parqueadero.entidad;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name="VEHICULO")
@NamedQuery(name="find_all_Vehiculos",query="Select v from Vehiculo v")
public class Vehiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int id;
	
	private String nombre;
	
	private int modelo;
	
	private String placa;
	
	private String color;
	
	private int cilindraje;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Cliente cliente;
	

	@OneToOne(cascade = CascadeType.ALL)
	private TipoVehiculo tipoVehiculo;
	
	//Always
	public Vehiculo() {
		super();
	}
	
	public Vehiculo(int id,String nombre,int modelo,String placa,String color,int cilindraje,Cliente cliente,TipoVehiculo tipoVehiculo) {
		this.id=id;
		this.nombre=nombre;
		this.modelo=modelo;
		this.placa=placa;
		this.color=color;
		this.cilindraje=cilindraje;
		this.cliente=cliente;
		this.tipoVehiculo=tipoVehiculo;
	}
	
	public Vehiculo(String nombre,int modelo,String placa,String color,int cilindraje,Cliente cliente,TipoVehiculo tipoVehiculo) {
		this.nombre=nombre;
		this.modelo=modelo;
		this.placa=placa;
		this.color=color;
		this.cilindraje=cilindraje;
		this.cliente=cliente;
		this.tipoVehiculo=tipoVehiculo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getModelo() {
		return modelo;
	}

	public void setModelo(int modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	
	
}
