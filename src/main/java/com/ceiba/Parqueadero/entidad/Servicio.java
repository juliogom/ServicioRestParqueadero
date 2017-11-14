package com.ceiba.Parqueadero.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

@Entity
@NamedQuery(name = "find_all_servicios", query = "select S from Servicio S")
public class Servicio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	private String nombre;
	
	@NotNull
	private String detalle;
	
	@NotNull
	private int precio;
	
	public Servicio() {
		super();
	}
	
	
	public Servicio(int id,String nombre,String detalle,int precio) {
		this.id=id;
		this.nombre=nombre;
		this.detalle=detalle;
		this.precio=precio;
		
	}
	
	public Servicio(String nombre,String detalle,int precio) {
		this.nombre=nombre;
		this.detalle=detalle;
		this.precio=precio;
		
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

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
}
