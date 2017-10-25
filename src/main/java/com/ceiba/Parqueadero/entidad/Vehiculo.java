package com.ceiba.Parqueadero.entidad;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name="VEHICULO")
public class Vehiculo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int id;
	
	private String nombre;
	
	private int modelo;
	
	private String placa;
	
	private String color;
	
	//Always
	public Vehiculo() {
		super();
	}
	
	public Vehiculo(int id,String nombre,int modelo,String placa,String color) {
		this.id=id;
		this.nombre=nombre;
		this.modelo=modelo;
		this.placa=placa;
		this.color=color;
	}
	
	public Vehiculo(String nombre,int modelo,String placa,String color) {
		this.nombre=nombre;
		this.modelo=modelo;
		this.placa=placa;
		this.color=color;
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
	
	
	
}
