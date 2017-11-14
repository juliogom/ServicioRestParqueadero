package co.ceiba.Parqueadero.testDataBuilder;

import com.ceiba.Parqueadero.entidad.Cliente;
import com.ceiba.Parqueadero.entidad.Servicio;
import com.ceiba.Parqueadero.entidad.TipoVehiculo;
import com.ceiba.Parqueadero.entidad.Vehiculo;

public class VehiculoTestDataBuilder {
	
	private int id;
	private String nombre;
	private int modelo;
	private String placa;
	private String color;
	private int cilindraje;
	private  TipoVehiculo tipoVehiculo;
	
	public VehiculoTestDataBuilder() {
		super();
		id=1;
		nombre="RX-100";
		modelo=2010;
		placa ="Rq2-2bg";
		color="Azul";
		cilindraje=100;
		tipoVehiculo=new TipoVehiculo();
		tipoVehiculo.setId(2);
	}
	
	public VehiculoTestDataBuilder withId(int id) {
		this.id=id;	
		return this;
	}
	
	public VehiculoTestDataBuilder withNombre(String nombre) {
		this.nombre=nombre;
		return this;
	}
	
	public VehiculoTestDataBuilder withPlaca(String placa) {
		this.placa=placa;
		return this;
	}
	
	public VehiculoTestDataBuilder withColor(String color) {
		this.color=color;
		return this;
	}
	
	public VehiculoTestDataBuilder withCilindraje(int cilindraje) {
		this.cilindraje=cilindraje;
		return this;
	}
	
	public VehiculoTestDataBuilder withTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo=tipoVehiculo;
		return this;
	}
	
	public Vehiculo build() {
		return new Vehiculo(id, nombre, modelo, placa, color, cilindraje, null,tipoVehiculo);
	}
}
