INSERT INTO Usuario(id, nombre, apellido, dni, direccion,email, password, admin, saldo) VALUES
                    (null, 'mari','manri','29363692','florencia 1900','test@unlam.edu.ar', '123', true, 0),
                    (null, 'leo', 'arias', '12345678', 'florencia 1900', 'correo@correo.com', '123', false, 0),
                    (null, 'mari', 'm', '12345878', 'florencia 1900', 'm@gmail.com', '123', false, 0);




-- INSERT INTO JUGADOR(id,nombreYapellido,fechaNacimiento,posicion, dni,direccion, email, telefono, numeroCamiseta,tarjetaAmarilla, tarjetaRoja) VALUES
--                     (null,'francisco paz', '2025-06-01','DELANTERO','29333555','fuentes222','f@gmail.com','1522522214','15',false,false),
--                     (null,'Gabriel Lopez', '2020-06-01','ARQUERO','29333555','fuentes222','f@gmail.com','1522522214','15',false,false),
--                     (null,'Roberto Carlos', '2022-06-01','DEFENSA','29333555','fuentes222','f@gmail.com','1522522214','15',false,false),
--                     (null,'Miguel Sanchez', '2021-06-01','MEDIOCAMPISTA','29333555','fuentes222','f@gmail.com','1522522214','15',false,false),
--                     (null,'Roman Riquelme', '2025-06-01','DELANTERO','29333555','fuentes222','f@gmail.com','1522522214','15',false,false),
--                     (null,'Carlos Tevez', '2020-06-01','DEFENSA','29333555','fuentes222','f@gmail.com','1522522214','15',false,false),
--                     (null,'Martin Palermo','2020-06-01','DELANTERO','29333555','fuentes222','f@gmail.com','1522522214','15',false,false),
--                     (null,'Marcos Rojo', '2021-06-01','DELANTERO','29333555','fuentes222','f@gmail.com','1522522214','15',false,false),
--                     (null,'Lisandro Lopez', '2025-06-01','MEDIOCAMPISTA','29333555','fuentes222','f@gmail.com','1522522214','15',false,false),
--                     (null,'Paz Martinez', '2025-06-01','ARQUERO','29333555','fuentes222','f@gmail.com','1522522214','15',false,false);



INSERT INTO Arbitro(id, nombre, apellido, partido) VALUES
                    (null, 'Juan', 'Perez', 64),
                    (null, 'Pedro', 'Rodriguez', 64),
                    (null, 'Carlos', 'Sanchez', 65),
                    (null, 'Jose', 'Lopez', 60),
                    (null, 'Miguel', 'Gonzalez', 60);

INSERT INTO Cancha(id, nombre) VALUES
                    (null, 'Cancha Central'),
                    (null, 'Cancha Este'),
                    (null, 'Cancha Oeste'),
                    (null, 'Cancha Norte'),
                    (null, 'Cancha Sur');


INSERT INTO Torneo(id, nombre, premio, precioEntrada, fechaInicio, cantidadEquipos, partido) VALUES
                    (null, 'Torneo 1', 1000, 100, '2025-06-01', 4, 64),
                    (null, 'Torneo 2', 2000, 200, '2025-06-01', 8, 65),
                    (null, 'Torneo 3', 3000, 300, '2025-06-01', 16, 60),
                    (null, 'Torneo 4', 4000, 400, '2025-06-01', 32, 82),
                    (null, 'Copa Campeones', 1000, 100, '2025-06-01', 4, 64),
                    (null, 'Copa Argentina', 2000, 200, '2025-06-01', 8, 65),
                    (null, 'Copa Libertadores', 3000, 300, '2025-06-01', 16, 60),
                    (null, 'Copa Sudamericana', 4000, 400, '2025-06-01', 32, 82);

INSERT INTO Equipo(id, nombre, cbu, dtDni, torneo_id, orden) VALUES
                    (null, 'boca', '123456789', 29363692, null, 0),
                    (null, 'River', '123456789', 29363691, 1, 1),
                    (null, 'Racing', '123456789', 29363691, 1, 2),
                    (null, 'Independiente', '123456789', 29363691, 1, 3),

                    (null, 'San Lorenzo', '123456789', 29363691, 2, 0),
                    (null, 'Huracan', '123456789', 29363642, 2, 1),
                    (null, 'Velez', '123456789', 29363642, 2, 2),
                    (null, 'Lanus', '123456789', 29363642, 2, 3),
                    (null, 'Estudiantes', '123456789', 25363692, 2, 4),
                    (null, 'Gimnasia', '123456789', 29463692, 2, 5),
                    (null, 'Banfield', '123456789', 29563692, 2, 6),
                    (null, 'Union', '123456789', 29365692, 2, 7),

                    (null, 'Defensa y Justicia', '123456789', 24363692, 3, 0),
                    (null, 'Godoy Cruz', '123456789', 29363592, 3, 1),
                    (null, 'Newells', '123456789', 29361692, 3, 2),
                    (null, 'Rosario Central', '123456789', 21363692, 3, 3),
                    (null, 'Talleres', '123456789', 29363192, 3, 4),
                    (null, 'Colon', '123456789', 29363192, 3, 5),
                    (null, 'Argentinos Juniors', '123456789', 29163692, 3, 6),
                    (null, 'Patronato', '123456789', 29313692, 3, 7),

                    (null, 'Boca', '123456789', 29361692, 4, 0),
                    (null, 'River', '123456789', 29361692, 4, 1),
                    (null, 'Racing', '123456789', 29361692, 4, 2),
                    (null, 'Independiente', '123456789', 21363692, 4, 3),
                    (null, 'San Lorenzo', '123456789', 29163692, 4, 4);

INSERT INTO Jugador(id, nombreyapellido, posicion, equipo_id) VALUES
                    (null, 'Roman Riquelme', 'Delantero', 1),
                    (null, 'Marcos Rojo', 'Defensor', 1),
                    (null, 'Fernando Gago', 'Mediocampista', 1),
                    (null, 'Roberto Abbondanzieri', 'Arquero', 1),
                    (null, 'Martin Palermo', 'Delantero', 1),

                    (null, 'Enzo Perez', 'Mediocampista', 2),
                    (null, 'Franco Armani', 'Arquero', 2),
                    (null, 'Julian Alvarez', 'Delantero', 2),
                    (null, 'Milton Casco', 'Defensor', 2),
                    (null, 'Ignacio Fernandez', 'Mediocampista', 2),

                    (null, 'Lisandro Lopez', 'Delantero', 3),
                    (null, 'Gabriel Arias', 'Arquero', 3),
                    (null, 'Leonardo Sigali', 'Defensor', 3),
                    (null, 'Matias Rojas', 'Mediocampista', 3),
                    (null, 'Nery Dominguez', 'Mediocampista', 3),

                    (null, 'Silvio Romero', 'Delantero', 4),
                    (null, 'Martin Campaña', 'Arquero', 4),
                    (null, 'Fabricio Bustos', 'Defensor', 4),
                    (null, 'Lucas Romero', 'Mediocampista', 4),
                    (null, 'Alan Velasco', 'Delantero', 4),

                    (null, 'Angel Romero', 'Delantero', 5),
                    (null, 'Sebastian Torrico', 'Arquero', 5),
                    (null, 'Fabricio Coloccini', 'Defensor', 5),
                    (null, 'Juan Ramirez', 'Mediocampista', 5),
                    (null, 'Oscar Romero', 'Mediocampista', 5);

INSERT INTO Partido(id, golesLocal, golesVisitante, fecha, hora, fase, equipoLocal_id, equipoVisitante_id, torneo_id, cancha_id, arbitro_id) VALUES
                    (null, null, null, '2025-06-01', '20:00:00', 'Semifinal', 1, 2, 1, 1, 1),
                    (null, null, null, '2025-06-01', '20:00:00', 'Semifinal', 3, 4, 1, 1, 2),

                    (null, null, null, '2025-06-01', '20:00:00', 'Cuartos de Final', 5, 6, 2, 2, 3),
                    (null, null, null, '2025-06-01', '20:00:00', 'Cuartos de Final', 7, 8, 2, 2, 4),
                    (null, null, null, '2025-06-01', '20:00:00', 'Cuartos de Final', 9, 10, 2, 2, 5),
                    (null, null, null, '2025-06-01', '20:00:00', 'Cuartos de Final', 11, 12, 2, 2, 2);



