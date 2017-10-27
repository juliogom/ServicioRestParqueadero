package com.ceiba.Parqueadero.servicio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.ceiba.Parqueadero.entidad.Actividad;
import com.ceiba.Parqueadero.entidad.DetalleFactura;
import com.ceiba.Parqueadero.entidad.Factura;
import com.ceiba.Parqueadero.repositorio.RepositorioFacturas;

@ComponentScan("com.ceiba.Parqueadero.dominio,com.ceiba.Parqueadero.repositorio")
@Transactional
@Service
public class FacturaServicio {
	
	@Autowired
	private RepositorioFacturas repositorioFacturas;
	
	public DetalleFactura crear(Factura factura,List<Actividad> detalleActividades) {
		
		DetalleFactura detalle=new DetalleFactura();
		detalle.setActividades(detalleActividades);
		detalle.setFactura(factura);
		repositorioFacturas.createDetalle(detalle);
		return detalle;
		
	}
	
	public void guardarDetalle(DetalleFactura detalleFact) {
		repositorioFacturas.createDetalle(detalleFact);
	}
	
	
}
