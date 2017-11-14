package com.ceiba.Parqueadero.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Repository;

import com.ceiba.Parqueadero.entidad.Slot;
import com.ceiba.Parqueadero.entidad.TipoVehiculo;

@Repository
@Transactional
@EntityScan(basePackages = "com.ceiba.Parqueadero.entidad")
public class RepositorioTipoVehiculos {

		@PersistenceContext
		EntityManager entity;
		
		public TipoVehiculo encontrarPorId(int id) {
			return entity.find(TipoVehiculo.class, id);
		}
		
		public List<TipoVehiculo> buscarTodos() {
			TypedQuery<TipoVehiculo> tiposVehiculos=entity.createNamedQuery("find_all_Tipo_Vehiculo",TipoVehiculo.class);
			
			return tiposVehiculos.getResultList();
			
		}
		
	
}
