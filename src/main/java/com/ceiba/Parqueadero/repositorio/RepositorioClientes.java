package com.ceiba.Parqueadero.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Repository;

import com.ceiba.Parqueadero.entidad.Actividad;
import com.ceiba.Parqueadero.entidad.Cliente;
import com.ceiba.Parqueadero.entidad.Vehiculo;

@EntityScan(basePackages="com.ceiba.Parqueadero.entidad")
@Transactional
@Repository
public class RepositorioClientes {
	
	@PersistenceContext
	EntityManager entity;
		
	public List<Cliente> findAll(){
		
		return entity.createNamedQuery("find_all_Clientes",Cliente.class).getResultList();
	}
	
	public Cliente create(Cliente cliente) {
		
		entity.merge(cliente);
		return cliente;
	}
			
}
