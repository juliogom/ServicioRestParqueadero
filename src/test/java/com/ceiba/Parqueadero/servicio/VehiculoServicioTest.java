package com.ceiba.Parqueadero.servicio;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ceiba.Parqueadero.ServicioRestParqueadero.ServicioRestParqueaderoApplication;
import com.ceiba.Parqueadero.ServicioRestParqueadero.ServicioRestParqueaderoApplicationTests;
import com.ceiba.Parqueadero.entidad.Actividad;
import com.ceiba.Parqueadero.entidad.Servicio;
import com.ceiba.Parqueadero.entidad.Slot;
import com.ceiba.Parqueadero.entidad.Vehiculo;
import com.ceiba.Parqueadero.repositorio.RepositorioVehiculos;

import co.ceiba.Parqueadero.testDataBuilder.ActividadTestDataBuilder;
import co.ceiba.Parqueadero.testDataBuilder.ServicioTestDataBuilder;
import co.ceiba.Parqueadero.testDataBuilder.SlotTestDataBuilder;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ServicioRestParqueaderoApplication.class)
public class VehiculoServicioTest  {
	
	@Autowired
	private VehiculoServicio vehiculoServicio;
	
    private RepositorioVehiculos repositorioVehiculos;
	
    private ActividadServicio actividadServicio;
    
    @Before
    public void setUp() {
    	
    	repositorioVehiculos = Mockito.mock(RepositorioVehiculos.class);
        vehiculoServicio = new VehiculoServicio();
    }
	
    @Test
    public void obtenerCobroTest() {
    	//ARRANGE
    	Servicio servicio=new ServicioTestDataBuilder().build();
    	Slot slot=new SlotTestDataBuilder().build();
    	
    	Actividad actividad=new ActividadTestDataBuilder().withServicio(servicio).withSlot(slot).build();
    	
    	//ACT
    	
    	//ASSERT
    }
    
	@Test
	public void verificarLetraInicialTest() {
		//ARRANGE
		boolean contieneLetraA=false;
		//ACT
		contieneLetraA=vehiculoServicio.verificarLetraInicial("ART-2016");
		
		//ASSERT
		Assert.assertEquals(true,contieneLetraA);
		
	}
	
	@Test
	public void verificarLetraInicialTest2() {
		//ARRANGE
		boolean contieneLetraA=false;
		//ACT
		contieneLetraA=vehiculoServicio.verificarLetraInicial("aRT-2016");
		
		//ASSERT
		Assert.assertEquals(true,contieneLetraA);
		
	}

}
