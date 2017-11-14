package com.ceiba.Parqueadero.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="CLIENTE")
@NamedQuery(name="find_all_Clientes",query="Select c from Cliente c")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull
	private String cedula;

	@NotNull
	private String nombre;

	@NotNull
	private int edad;

	@NotNull
	private String apellido;

	@NotNull
	private String telefono;
	
	public Cliente() {
		
	}
	
	public Cliente(int id, String cedula, String nombre, int edad, String apellido, String telefono) {
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.edad = edad;
		this.apellido = apellido;
		this.telefono = telefono;
	}

	public Cliente(String cedula, String nombre, int edad, String apellido, String telefono) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.edad = edad;
		this.apellido = apellido;
		this.telefono = telefono;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}