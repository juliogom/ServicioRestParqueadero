
/*drop table cliente;*/

/*Create Table Cliente(
	Id integer auto_increment,
	Nombre varchar(100) not null ,
	Apellido varchar(100) not null,
	Telefono varchar(100) not null,
    primary key(id)
);*/

 Create Table Vehiculo( 
    Id integer auto_increment, 
    Nombre varchar(100) not null , 
    Modelo integer not null, 
    placa varchar(100) not null, 
    color varchar(100) not null, 
    primary key(id) 
);
 


/*insert into Cliente(Nombre,Apellido,Telefono) values('Julio','Gomez','310534625');*/

/*insert into Vehiculo(Nombre,Modelo,Placa,Color) values ('Mazda',2015,'HAZ-1456','AZUL');*/