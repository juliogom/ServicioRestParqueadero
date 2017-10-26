package com.ceiba.Parqueadero.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Repository;

import com.ceiba.Parqueadero.entidad.Slot;

@Repository
@EntityScan(basePackages = "com.ceiba.Parqueadero.entidad")
public class RepositorioSlot {
	
	
	@PersistenceContext
	private EntityManager entity;
	
	public List<Slot> buscarTodos() {
		TypedQuery<Slot> actividades= entity.createNamedQuery("find_all_slots",Slot.class);
		
		return actividades.getResultList();
	}
}
