package com.ceiba.Parqueadero.servicio;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class PersonaServicioTest {
	
	private PersonasServicio personaServicio;
	
	@Before
	public void setUp() {
		personaServicio=mock(PersonasServicio.class);
	}
	
	
	@Test
	public void obtenerPersonastest() {
		//Arrange
		
		//Act
		//Mockito.when(personaServicio.obtenerPersonas()).thenReturn("Hello Soft");
		
		//Assert
		
		
	}

}
