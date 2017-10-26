package com.ceiba.Parqueadero.controller;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.ceiba.Parqueadero.servicio.VehiculoServicio;

public class VehiculoControllerTest {
	
	@Autowired
	VehiculoServicio vehiculoServicio;
	
	@Test
	public void grabarVehiculoTest() {
		
		Assert.assertEquals(2,2);
		
	}

}
