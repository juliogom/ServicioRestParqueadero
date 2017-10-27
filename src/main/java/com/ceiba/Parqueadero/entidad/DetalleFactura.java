package com.ceiba.Parqueadero.entidad;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class DetalleFactura {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull
	private int cantidad;

	@NotNull
	private int total;

	@OneToOne(cascade = CascadeType.ALL)
	private Factura factura;

	@OneToMany(
	        cascade = CascadeType.ALL, 
	        orphanRemoval = true
	    )
	//private List<PostComment> comments = new ArrayList<>();
	private List<Actividad> actividades;
	
	public DetalleFactura() {
		super();
	}
	
	public DetalleFactura(int id, int cantidad, int total, Factura factura, List<Actividad> actividades) {

		this.id = id;
		this.cantidad = cantidad;
		this.total = total;
		this.factura = factura;
		this.actividades = actividades;

	}

	public DetalleFactura(int cantidad, int total, Factura factura, List<Actividad> actividades) {

		this.cantidad = cantidad;
		this.total = total;
		this.factura = factura;
		this.actividades = actividades;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public List<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}

}
