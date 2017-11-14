package co.ceiba.Parqueadero.testDataBuilder;

import java.util.Date;

import com.ceiba.Parqueadero.entidad.Actividad;
import com.ceiba.Parqueadero.entidad.Servicio;
import com.ceiba.Parqueadero.entidad.Slot;
import com.ceiba.Parqueadero.entidad.TipoVehiculo;
import com.ceiba.Parqueadero.entidad.Vehiculo;

public class SlotTestDataBuilder {
	
	private int id;
	private int numero;
	private boolean ocupado;
	
	
	public SlotTestDataBuilder() {
		super();
		id=1;
		numero=1;
		ocupado=true;
	}
	
	public SlotTestDataBuilder withId(int id) {
		this.id=id;	
		return this;
	}
	
	public SlotTestDataBuilder withNumero(int numero) {
		this.numero=numero;
		return this;
	}
	
	public SlotTestDataBuilder withOcupado(boolean ocupado) {
		this.ocupado=ocupado;
		return this;
	}
	
	
	public Slot build() {
		return new Slot(id, numero, ocupado,null);
	}

}
