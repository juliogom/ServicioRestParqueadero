package com.ceiba.Parqueadero.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.ceiba.Parqueadero.entidad.Actividad;
import com.ceiba.Parqueadero.entidad.Cliente;
import com.ceiba.Parqueadero.repositorio.RepositorioClientes;

@Service
@ComponentScan("com.ceiba.Parqueadero.repositorio")
public class UsuarioServicio {
	
	@Autowired
	private RepositorioClientes repositorioClientes;
	
	public List<Cliente> obtenerActividades() {
		return repositorioClientes.findAll();
	}
	
	public Cliente crearCliente(Cliente cliente) {
		return repositorioClientes.create(cliente);
	}
	
}
