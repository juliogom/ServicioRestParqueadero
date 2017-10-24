package com.ceiba.Parqueadero.servicio;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.Parqueadero.dominio.Persona;

import co.ceiba.Parqueadero.testDataBuilder.PersonTestDataBuilder;

public class PersonaServicioTest {
	
	private PersonasServicio personaServicio;
	
	@Before
	public void setUp() {
		personaServicio=mock(PersonasServicio.class);
	}
	
	@Test
	public void obtenerPersonastest() {
		//Arrange
		Persona persona=new PersonTestDataBuilder().build();
		List listaPersonas=new ArrayList<Persona>();
		listaPersonas.add(persona);
		//Act
		Mockito.when(personaServicio.obtenerPersonas()).thenReturn(listaPersonas);
		
		//Assert
		Assert.assertEquals(1, listaPersonas.size());
		
	}

}
