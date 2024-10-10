INSERT INTO Usuario(id, nombre, apellido, dni, direccion,email, password, admin, saldo) VALUES
                    (null, 'mari','manri','29363692','florencia 1900','test@unlam.edu.ar', '123', true, 0),
                    (null, 'leo', 'arias', '12345678', 'florencia 1900', 'correo@correo.com', '123', false, 0),
                    (null, 'mari', 'm', '12345878', 'florencia 1900', 'm@gmail.com', '123', false, 0);

INSERT INTO Torneo(id, nombre, premio, precioEntrada, fechaInicio, cantidadEquipos, partido) VALUES
                    (null, 'Torneo 1', 1000, 100, '2025-06-01', 4, 64),
                    (null, 'Torneo 2', 2000, 200, '2025-06-01', 8, 65),
                    (null, 'Torneo 3', 3000, 300, '2025-06-01', 16, 60),
                    (null, 'Torneo 4', 4000, 400, '2025-06-01', 32, 82);


INSERT INTO Equipo(idEquipo, nombre, cbu, dtDni, torneo_id) VALUES
                    (null, 'Equipo 1', '123456789', 29363692, 1),
                    (null, 'Equipo 2', '123456789', 29363692, 1),
                    (null, 'Equipo 3', '123456789', 29363692, 1),
                    (null, 'Equipo 4', '123456789', 29363692, 1),
                    (null, 'Equipo 5', '123456789', 29363692, 2),
                    (null, 'Equipo 6', '123456789', 29363692, 2),
                    (null, 'Equipo 7', '123456789', 29363692, 2),
                    (null, 'Equipo 8', '123456789', 29363692, 2),
                    (null, 'Equipo 9', '123456789', 29363692, 3),
                    (null, 'Equipo 10', '123456789', 29363692, 3),
                    (null, 'Equipo 11', '123456789', 29363692, 3),
                    (null, 'Equipo 12', '123456789', 29363692, 3),
                    (null, 'Equipo 13', '123456789', 29363692, 4),
                    (null, 'Equipo 14', '123456789', 29363692, 4),
                    (null, 'Equipo 15', '123456789', 29363692, 4),
                    (null, 'Equipo 16', '123456789', 29363692, 4);

INSERT INTO JUGADOR(id,nombreYapellido,fechaNacimiento,posicion, dni,direccion, email, telefono, numeroCamiseta,tarjetaAmarilla, tarjetaRoja) VALUES
                    (null,'francisco paz', '2025-06-01','DELANTERO','29333555','fuentes222','f@gmail.com','1522522214','15',false,false),
                    (null,'Gabriel Lopez', '2020-06-01','ARQUERO','29333555','fuentes222','f@gmail.com','1522522214','15',false,false),
                    (null,'Roberto Carlos', '2022-06-01','DEFENSA','29333555','fuentes222','f@gmail.com','1522522214','15',false,false),
                    (null,'Miguel Sanchez', '2021-06-01','MEDIOCAMPISTA','29333555','fuentes222','f@gmail.com','1522522214','15',false,false),
                    (null,'Roman Riquelme', '2025-06-01','DELANTERO','29333555','fuentes222','f@gmail.com','1522522214','15',false,false),
                    (null,'Carlos Tevez', '2020-06-01','DEFENSA','29333555','fuentes222','f@gmail.com','1522522214','15',false,false),
                    (null,'Martin Palermo','2020-06-01','DELANTERO','29333555','fuentes222','f@gmail.com','1522522214','15',false,false),
                    (null,'Marcos Rojo', '2021-06-01','DELANTERO','29333555','fuentes222','f@gmail.com','1522522214','15',false,false),
                    (null,'Lisandro Lopez', '2025-06-01','MEDIOCAMPISTA','29333555','fuentes222','f@gmail.com','1522522214','15',false,false),
                    (null,'Paz Martinez', '2025-06-01','ARQUERO','29333555','fuentes222','f@gmail.com','1522522214','15',false,false);