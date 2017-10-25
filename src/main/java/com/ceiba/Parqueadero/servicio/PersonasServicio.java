package com.ceiba.Parqueadero.servicio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.Parqueadero.dominio.ListaPersonas;
import com.ceiba.Parqueadero.entidad.Persona;


@Transactional
@ComponentScan("com.ceiba.Parqueadero.dominio")
public class PersonasServicio {
	
	@Autowired
	private ListaPersonas data;
	
	@GetMapping("/personas")
	public List<Persona> obtenerPersonas() {
		return data.getData();
	}
	
	@RequestMapping("/home")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
	
	@GetMapping("/ingresar")
	public String obtenerVehiculo() {

		try {
			//Vehiculo vehiculo = new Vehiculo("Mazda",2015,"HZQ-123","Negro");
			//vehiculoServicio.crear(vehiculo);
		} catch (Exception e) {

			System.out.println(e);

		}

		return "Guardado";
	}

}