
/*drop table cliente;*/

/*
 * Create Table TipoVehiculo(

	Id integer auto_increment,
	Nombre varchar(100) not null,
	Descripcion varchar(150) not null,
	primary Key(ID)

);

Create Table Vehiculo( 
    Id integer auto_increment, 
    Nombre varchar(100) not null , 
    Modelo integer not null, 
    placa varchar(100) not null, 
    color varchar(100) not null,
    Id_Tipo integer not null,
    foreign key (Id_Tipo) references TIPOVEHICULO (Id),
    primary key(Id) 
)




Create Table Slot( 
    Id integer auto_increment, 
    numero integer not null , 
    Modelo integer not null, 
    placa varchar(100) not null, 
    color varchar(100) not null, 
    primary key(Id) 
);
*
*
*
**/
 






/*insert into Cliente(Nombre,Apellido,Telefono) values('Julio','Gomez','310534625');*/

/*insert into Vehiculo(Nombre,Modelo,Placa,Color,TIPO_VEHICULO_ID) values ('Suzuki',2016,'HAZ-1456','Roja',2);
 *UPDATE ACTIVIDAD SET ESTADO= 0 WHERE ID = 2; 
 *insert into ACTIVIDAD(ID,ESTADO,Fecha_Inicio,SLOT_ID,VEHICULO_ID) values (3,1,'2017-10-26 09:30:00',3,3)
 * insert into ACTIVIDAD(ID,ESTADO,Fecha_Inicio,SLOT_ID,VEHICULO_ID) values (1,1,'2017-10-26 09:30:00',1,1)*/


