package com.ceiba.Parqueadero.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ceiba.Parqueadero.entidad.Vehiculo;

@Repository
@Transactional
public class RepositorioVehiculos {
	
	@PersistenceContext
	EntityManager entity;
	
	
	public Vehiculo encontrarPorId() {
		
		return entity.find(Vehiculo.class, "id");
	}
	
	
}
