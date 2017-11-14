package com.ceiba.Parqueadero.servicio;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.ceiba.Parqueadero.entidad.Actividad;
import com.ceiba.Parqueadero.entidad.Cliente;
import com.ceiba.Parqueadero.entidad.DetalleFactura;
import com.ceiba.Parqueadero.entidad.Factura;
import com.ceiba.Parqueadero.repositorio.RepositorioFacturas;

@ComponentScan("com.ceiba.Parqueadero.dominio,com.ceiba.Parqueadero.repositorio")
@Transactional
@Service
public class FacturaServicio {
	
	@Autowired
	private RepositorioFacturas repositorioFacturas;
	
	public DetalleFactura crear(List<Actividad> detalleActividades,int total,double numeroDias) {
		
		Cliente cliente=null;
		
		DetalleFactura detalle=new DetalleFactura();
		detalle.setActividades(detalleActividades);
		
		for (Actividad actividad : detalleActividades) {
			
			detalle.setCantidad((int)numeroDias);
			detalle.setTotal(actividad.getServicio().getPrecio()*(int)numeroDias);
			cliente=actividad.getVehiculo().getCliente();
			actividad.getSlot().setOcupado(false);
			actividad.setEstado(0);
			
		}
		
		Calendar calendar = Calendar.getInstance();
		Date fecha=calendar.getTime();
		
		Factura factura=new Factura(fecha,total,cliente);
		
		detalle.setFactura(factura);
		repositorioFacturas.createDetalle(detalle);
		return detalle;
		
	}
	
	public void guardarDetalle(DetalleFactura detalleFact) {
		repositorioFacturas.createDetalle(detalleFact);
	}
	
	
}
