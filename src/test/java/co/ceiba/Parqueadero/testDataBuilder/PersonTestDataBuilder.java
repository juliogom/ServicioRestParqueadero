package co.ceiba.Parqueadero.testDataBuilder;

import com.ceiba.Parqueadero.entidad.Persona;

public class PersonTestDataBuilder {
	
	private int id;
	private String nombre;
	
	public PersonTestDataBuilder() {
		super();
		id=1;
		nombre="Julio";
	}
	
	public PersonTestDataBuilder withId(int id) {
		this.id=id;	
		return this;
	}
	
	public PersonTestDataBuilder withNombre(String nombre) {
		this.nombre=nombre;
		return this;
	}
	
	public Persona build() {
		return new Persona(this.id,this.nombre);
	}
	
}
