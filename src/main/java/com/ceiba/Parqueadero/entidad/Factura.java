package com.ceiba.Parqueadero.entidad;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Factura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	public Date fecha;
	
	@NotNull
	public int total;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Cliente cliente;
	
	public Factura() {
		super();
	}
	
	public Factura(int id,Date fecha,int total,Cliente cliente) {
		this.id=id;
		this.fecha=fecha;
		this.total=total;
		this.cliente=cliente;
	}
	
	public Factura(Date fecha,int total,Cliente cliente) {
		this.fecha=fecha;
		this.total=total;
		this.cliente=cliente;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
	
	

}
