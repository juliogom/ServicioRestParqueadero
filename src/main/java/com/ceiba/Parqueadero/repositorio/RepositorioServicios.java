package com.ceiba.Parqueadero.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Repository;

import com.ceiba.Parqueadero.entidad.Servicio;


@Repository
@EntityScan(basePackages = "com.ceiba.Parqueadero.entidad")
public class RepositorioServicios {
	
	@PersistenceContext
	private EntityManager entity;
	
	public List<Servicio> findAll() {
		TypedQuery<Servicio> actividades= entity.createNamedQuery("find_all_servicios",Servicio.class);
		
		return actividades.getResultList();
	}
	
	public Servicio findById(int id) {
		return entity.find(Servicio.class, id);
	}
	
}
