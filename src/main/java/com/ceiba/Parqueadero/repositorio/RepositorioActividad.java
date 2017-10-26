package com.ceiba.Parqueadero.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Repository;

import com.ceiba.Parqueadero.entidad.Actividad;
import com.ceiba.Parqueadero.entidad.Vehiculo;

@Repository
@EntityScan(basePackages = "com.ceiba.Parqueadero.entidad")
public class RepositorioActividad {
	
		@PersistenceContext
		private EntityManager entity;
		
		public List<Actividad> buscarTodos() {
			TypedQuery<Actividad> actividades= entity.createNamedQuery("find_all_Actividades",Actividad.class);
			
			return actividades.getResultList();
		}
		
		public Actividad create(Actividad actividad) {
			
			entity.merge(actividad);
			return actividad;
		}
		
		
		
}
