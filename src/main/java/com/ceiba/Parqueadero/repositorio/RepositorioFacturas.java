package com.ceiba.Parqueadero.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Repository;

import com.ceiba.Parqueadero.entidad.DetalleFactura;
import com.ceiba.Parqueadero.entidad.Factura;
import com.ceiba.Parqueadero.entidad.Vehiculo;

@Repository
@EntityScan(basePackages = "com.ceiba.Parqueadero.entidad")
public class RepositorioFacturas {
	
	@PersistenceContext
	EntityManager entity;
	
	public Factura create(Factura factura) {

		entity.merge(factura);
		return factura;
	}
	
	public DetalleFactura createDetalle(DetalleFactura detalle) {
		
		entity.merge(detalle);
		
		return detalle;
		
	}
	
}
