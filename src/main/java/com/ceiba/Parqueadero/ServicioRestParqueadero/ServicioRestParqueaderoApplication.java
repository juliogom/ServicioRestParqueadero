package com.ceiba.Parqueadero.ServicioRestParqueadero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.ceiba.Parqueadero.servicio,com.ceiba.Parqueadero.controller")
public class ServicioRestParqueaderoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioRestParqueaderoApplication.class, args);
		
		
	}
}
