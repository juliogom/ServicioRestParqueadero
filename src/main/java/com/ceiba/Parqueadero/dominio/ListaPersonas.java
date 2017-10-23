package com.ceiba.Parqueadero.dominio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ListaPersonas {
	
	private List<Persona> data=new ArrayList<Persona>();
	
	public ListaPersonas() {
		
		Persona persona1=new Persona();
		
		persona1.setId(1);
		persona1.setNombre("Julio");
		
		data.add(persona1);
		
	}
	
	public List<Persona> getData() {
		return data;
	}

	public void setData(List<Persona> data) {
		this.data = data;
	}
	
}
