package com.ceiba.Parqueadero.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Repository;

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
	
}
