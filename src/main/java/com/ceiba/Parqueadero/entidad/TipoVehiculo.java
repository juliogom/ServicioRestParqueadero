package com.ceiba.Parqueadero.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="TIPOVEHICULO")
public class TipoVehiculo {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int id;
	private String nombre;
	private String descripcion;
	
	public TipoVehiculo() {
		super();
	}
	
	public TipoVehiculo(int id,String nombre,String descripcion) {
		this.id=id;
		this.nombre=nombre;
		this.descripcion=descripcion;
	}
	
	public TipoVehiculo(String nombre,String descripcion) {
		this.nombre=nombre;
		this.descripcion=descripcion;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}








