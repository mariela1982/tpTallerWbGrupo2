INSERT INTO Usuario(id, nombre, apellido, dni, direccion, email, password, admin, saldo, esJugador, posicion, partido, telefono) VALUES
                    (null, 'Mari', 'manri', '29363692', 'florencia 1900', 'test@unlam.edu.ar', '123', true, 0, false, null, 'LA_MATANZA', '123456789'),
                    (null, 'Leo', 'arias', '12345678', 'florencia 1900', 'correo@correo.com', '123', false, 0, false, null, 'MERLO', '987654321'),
                    (5, 'Mariel', 'm', '12345878', 'florencia 1900', 'm@gmail.com', '123', false, 0, false, null, 'MORON', '456123789'),

                    -- Jugadores
                    (null, 'Roman', 'Riquelme', '29333555', 'fuentes222', 'roman@gmail.com', '123', false, 0, true, 'Delantero', 'TIGRE', '1522522214'),
                    (null, 'Marcos', 'Rojo', '29333556', 'fuentes223', 'marcos@gmail.com', '123', false, 0, true, 'Defensor', 'AVELLANEDA', '1522522215'),
                    (null, 'Fernando', 'Gago', '29333557', 'fuentes224', 'gago@gmail.com', '123', false, 0, true, 'Mediocampista', 'TANDIL', '1522522216'),
                    (null, 'Roberto', 'Abbondanzieri', '29333558', 'fuentes225', 'abbondanzieri@gmail.com', '123', false, 0, true, 'Arquero', 'OLAVARRIA', '1522522217'),
                    (null, 'Martin', 'Palermo', '29333559', 'fuentes226', 'martin@gmail.com', '123', false, 0, true, 'Delantero', 'JUNIN', '1522522218'),
                    (null, 'Enzo', 'Perez', '29333560', 'fuentes227', 'enzo@gmail.com', '123', false, 0, true, 'Mediocampista', 'PILAR', '1522522219'),
                    (null, 'Franco', 'Armani', '29333561', 'fuentes228', 'armani@gmail.com', '123', false, 0, true, 'Arquero', 'QUILMES', '1522522220'),
                    (null, 'Julian', 'Alvarez', '29333562', 'fuentes229', 'julian@gmail.com', '123', false, 0, true, 'Delantero', 'MORENO', '1522522221'),
                    (null, 'Milton', 'Casco', '29333563', 'fuentes230', 'milton@gmail.com', '123', false, 0, true, 'Defensor', 'TRES_ARROYOS', '1522522222'),
                    (null, 'Ignacio', 'Fernandez', '29333564', 'fuentes231', 'nacho@gmail.com', '123', false, 0, true, 'Mediocampista', 'NECOCHEA', '1522522223');


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

INSERT INTO Cancha(id, nombre, direccion, telefono) VALUES
                    (null, 'Cancha Central', 'Av. Principal 123', '123456789'),
                    (null, 'Cancha Este', 'Calle Este 456', '987654321'),
                    (null, 'Cancha Oeste', 'Calle Oeste 789', '456123789'),
                    (null, 'Cancha Norte', 'Calle Norte 101', '789456123'),
                    (null, 'Cancha Sur', 'Calle Sur 202', '321654987');

INSERT INTO Torneo(id, nombre, premio, precioEntrada, fechaInicio, cantidadEquipos, partido) VALUES
                    (1, 'Copa Super Campeones', 1000, 10000, '2025-06-01', 4, 64),
                    (2, 'Copa Argentina', 2000, 20000, '2025-06-01', 8, 65),
                    (3, 'Copa Libertadores', 3000, 30000, '2025-06-01', 16, 60),
                    (4, 'Copa Sudamericana', 4000, 40000, '2025-06-01', 32, 82);

INSERT INTO Equipo(id, nombre, torneo_id, orden, dt_id) VALUES
                    (1, 'Boca',  1, 0,null),
                    (2, 'River',  1, 1,null),
                    (3, 'Racing',  1, 2,null),
                    (4, 'Independiente', 1, 3,null),
                    (null, 'San Lorenzo', 2, 0,null),
                    (null, 'Huracan',  2, 1,null),
                    (null, 'Velez',  2, 2,null),
                    (null, 'Lanus', 2, 3,null),
                    (null, 'Estudiantes',  2, 4,null),
                    (null, 'Gimnasia',  2, 5,null),
                    (null, 'Banfield',  2, 6,null),
                    (null, 'Union',  2, 7,null),

                    (null, 'Defensa y Justicia', 3, 0,null),
                    (null, 'Godoy Cruz',  3, 1,null),
                    (null, 'Newells',  3, 2,null),
                    (null, 'Rosario Central',  3, 3,null),
                    (null, 'Talleres',  3, 4,null),
                    (null, 'Colon',  3, 5,null),
                    (null, 'Argentinos Juniors',  3, 6,null),
                    (null, 'Patronato',  3, 7,null),

                    (null, 'Chacarita',  null, null,null),
                    (null, 'Brown',  null, null,null),
                    (null, 'chicago',  null, null,null),

                    (null, 'Boca', 4, 0,null),
                    (null, 'River',  4, 1,null),
                    (null, 'Racing',  4, 2,null),
                    (null, 'Independiente',  4, 3,null),
                    (null, 'San Lorenzo',  4, 4,null);

-- INSERT INTO Equipo(id, nombre, cbu, dtDni, torneo_id, orden) VALUES
--                     (1, 'Boca', '123456789', 29363692, 1, 0),
--                     (2, 'River', '123456789', 29363691, 1, 1),
--                     (3, 'Racing', '123456789', 29363691, 1, 2),
--                     (4, 'Independiente', '123456789', 29363691, 1, 3),
--
--                     (null, 'San Lorenzo', '123456789', 29363691, 2, 0),
--                     (null, 'Huracan', '123456789', 29363642, 2, 1),
--                     (null, 'Velez', '123456789', 29363642, 2, 2),
--                     (null, 'Lanus', '123456789', 29363642, 2, 3),
--                     (null, 'Estudiantes', '123456789', 25363692, 2, 4),
--                     (null, 'Gimnasia', '123456789', 29463692, 2, 5),
--                     (null, 'Banfield', '123456789', 29563692, 2, 6),
--                     (null, 'Union', '123456789', 29365692, 2, 7),
--
--                     (null, 'Defensa y Justicia', '123456789', 24363692, 3, 0),
--                     (null, 'Godoy Cruz', '123456789', 29363592, 3, 1),
--                     (null, 'Newells', '123456789', 29361692, 3, 2),
--                     (null, 'Rosario Central', '123456789', 21363692, 3, 3),
--                     (null, 'Talleres', '123456789', 29363192, 3, 4),
--                     (null, 'Colon', '123456789', 29363192, 3, 5),
--                     (null, 'Argentinos Juniors', '123456789', 29163692, 3, 6),
--                     (null, 'Patronato', '123456789', 29313692, 3, 7),
--
--                     (null, 'Chacarita', '123456789', 29361692, null, null),
--                     (null, 'Brown', '123456789', 29361692, null, null),
--                     (null, 'chicago', '123456789', 29361692, null, null),
--
--                     (null, 'Boca', '123456789', 29361692, 4, 0),
--                     (null, 'River', '123456789', 29361692, 4, 1),
--                     (null, 'Racing', '123456789', 29361692, 4, 2),
--                     (null, 'Independiente', '123456789', 21363692, 4, 3),
--                     (null, 'San Lorenzo', '123456789', 29163692, 4, 4);


INSERT INTO Jugador(id, nombreYapellido, fechaNacimiento, posicion, dni, direccion, email, telefono, numeroCamiseta, tarjetaAmarilla, tarjetaRoja, equipo_id, dt_id) VALUES
                    (1, 'Roman Riquelme', '1978-06-24', 'Delantero', '29333555', 'fuentes222', 'roman@gmail.com', '1522522214', 10, false, false, null,5),
                    (2, 'Marcos Rojo', '1990-03-20', 'Defensor', '29333556', 'fuentes223', 'marcos@gmail.com', '1522522215', 6, false, false, null,5),
                    (3, 'Fernando Gago', '1986-04-10', 'Mediocampista', '29333557', 'fuentes224', 'gago@gmail.com', '1522522216', 5, false, false, null,5),
                    (4, 'Roberto Abbondanzieri', '1972-08-19', 'Arquero', '29333558', 'fuentes225', 'abbondanzieri@gmail.com', '1522522217', 1, false, false, null,5),
                    (5, 'Martin Palermo', '1973-11-07', 'Delantero', '29333559', 'fuentes226', 'palermo@gmail.com', '1522522218', 9, false, false, null,5),

                    (6, 'Enzo Perez', '1986-02-22', 'Mediocampista', '29333560', 'fuentes227', 'enzo@gmail.com', '1522522219', 8, false, false, null,5),
                    (7, 'Franco Armani', '1986-10-16', 'Arquero', '29333561', 'fuentes228', 'armani@gmail.com', '1522522220', 1, false, false, null,5),
                    (8, 'Julian Alvarez', '2000-01-31', 'Delantero', '29333562', 'fuentes229', 'julian@gmail.com', '1522522221', 9, false, false, null,5),
                    (9, 'Milton Casco', '1988-04-11', 'Defensor', '29333563', 'fuentes230', 'casco@gmail.com', '1522522222', 4, false, false, null,5),
                    (10, 'Ignacio Fernandez', '1990-01-12', 'Mediocampista', '29333564', 'fuentes231', 'nacho@gmail.com', '1522522223', 10, false, false, null,5),

                    (11, 'Lisandro Lopez', '1983-03-02', 'Delantero', '29333565', 'fuentes232', 'lisandro@gmail.com', '1522522224', 7, false, false, null,5),
                    (12, 'Gabriel Arias', '1987-09-13', 'Arquero', '29333566', 'fuentes233', 'arias@gmail.com', '1522522225', 1, false, false, null,5),
                    (13, 'Leonardo Sigali', '1987-05-29', 'Defensor', '29333567', 'fuentes234', 'sigali@gmail.com', '1522522226', 2, false, false, null,5),
                    (14, 'Matias Rojas', '1995-11-03', 'Mediocampista', '29333568', 'fuentes235', 'rojas@gmail.com', '1522522227', 8, false, false, null,5),
                    (15, 'Nery Dominguez', '1990-04-09', 'Mediocampista', '29333569', 'fuentes236', 'nery@gmail.com', '1522522228', 5, false, false, null,5),

                    (16, 'Silvio Romero', '1988-07-22', 'Delantero', '29333570', 'fuentes237', 'silvio@gmail.com', '1522522229', 9, false, false, null,5),
                    (17, 'Martin Campaña', '1989-05-29', 'Arquero', '29333571', 'fuentes238', 'campana@gmail.com', '1522522230', 1, false, false, null,5),
                    (18, 'Fabricio Bustos', '1996-04-28', 'Defensor', '29333572', 'fuentes239', 'bustos@gmail.com', '1522522231', 4, false, false, null,5),
                    (19, 'Lucas Romero', '1994-04-18', 'Mediocampista', '29333573', 'fuentes240', 'lucas@gmail.com', '1522522232', 5, false, false, null,5),
                    (20, 'Alan Velasco', '2002-07-27', 'Delantero', '29333574', 'fuentes241', 'velasco@gmail.com', '1522522233', 10, false, false, null,5),

                    (21, 'Angel Romero', '1992-07-04', 'Delantero', '29333575', 'fuentes242', 'angel@gmail.com', '1522522234', 11, false, false, null,5),
                    (22, 'Sebastian Torrico', '1980-02-22', 'Arquero', '29333576', 'fuentes243', 'torrico@gmail.com', '1522522235', 1, false, false, null,5),
                    (23, 'Fabricio Coloccini', '1982-01-22', 'Defensor', '29333577', 'fuentes244', 'coloccini@gmail.com', '1522522236', 2, false, false, null,5),
                    (24, 'Juan Ramirez', '1993-12-25', 'Mediocampista', '29333578', 'fuentes245', 'ramirez@gmail.com', '1522522237', 8, false, false, null,5),
                    (25, 'Oscar Romero', '1992-07-04', 'Mediocampista', '29333579', 'fuentes246', 'oscar@gmail.com', '1522522238', 10, false, false, null,5);

INSERT INTO Partido(id, golesLocal, golesVisitante, fecha, hora, fase, equipoLocal_id, equipoVisitante_id, torneo_id, cancha_id, arbitro_id) VALUES
                    (null, null, null, '2025-06-01', '20:00:00', 'Semifinal', 1, 2, 1, 1, 1),
                    (null, null, null, '2025-06-01', '20:00:00', 'Semifinal', 3, 4, 1, 1, 2);

                    -- (null, null, null, '2025-06-01', '20:00:00', 'Cuartos de Final', 5, 6, 2, 2, 3),
                    -- (null, null, null, '2025-06-01', '20:00:00', 'Cuartos de Final', 7, 8, 2, 2, 4),
                    -- (null, null, null, '2025-06-01', '20:00:00', 'Cuartos de Final', 9, 10, 2, 2, 5),
                    -- (null, null, null, '2025-06-01', '20:00:00', 'Cuartos de Final', 11, 12, 2, 2, 2);



