package com.ceiba.Parqueadero.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ceiba.Parqueadero.entidad.Vehiculo;
import org.springframework.data.repository.CrudRepository;

@Repository
@Transactional
@EntityScan(basePackages = "com.ceiba.Parqueadero.entidad")
public class RepositorioVehiculos {
	
	@PersistenceContext
	EntityManager entity;
		
	public Vehiculo create(Vehiculo vehiculo) {
		
		entity.merge(vehiculo);
		return vehiculo;
	}
	
	public List<Vehiculo> findAll(){
		
		return null;
	}
	
	public Vehiculo encontrarPorId(int id) {
		
		return entity.find(Vehiculo.class, id);
	}
	
	
}
