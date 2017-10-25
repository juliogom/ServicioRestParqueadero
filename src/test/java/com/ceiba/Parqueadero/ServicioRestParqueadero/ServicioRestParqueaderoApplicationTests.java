package com.ceiba.Parqueadero.ServicioRestParqueadero;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServicioRestParqueaderoApplicationTests {
	
	@Autowired
	public ServicioRestParqueaderoApplicationTests() {
		
	}
	
	@Test
	public void contextLoads() {
		Assert.assertEquals(1, 1);
	}

}
