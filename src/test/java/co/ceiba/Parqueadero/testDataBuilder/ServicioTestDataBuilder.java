package co.ceiba.Parqueadero.testDataBuilder;

import java.util.Date;

import com.ceiba.Parqueadero.entidad.Servicio;


public class ServicioTestDataBuilder {
	private int id;
	private String nombre;
	private String detalle;
	private int precio;
	
	public ServicioTestDataBuilder() {
		super();
		id=1;
		nombre="Parqueo";
		detalle="Servicio parqueadero";
		precio =500;
	}
	
	public ServicioTestDataBuilder withId(int id) {
		this.id=id;	
		return this;
	}
	
	public ServicioTestDataBuilder withNombre(String nombre) {
		this.nombre=nombre;
		return this;
	}
	
	public ServicioTestDataBuilder withDetalle(String detalle) {
		this.detalle=detalle;
		return this;
	}
	
	public ServicioTestDataBuilder withPrecio(int precio) {
		this.precio=precio;
		return this;
	}
	
	public Servicio build() {
		return new Servicio(id, nombre, detalle, precio);
	}
}
