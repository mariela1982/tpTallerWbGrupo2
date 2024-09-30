/*create table "Usuario"
(
    id varchar(255) null,
    nombre    varchar(255) not null,
    apellido  varchar(255) not null,
    dni       varchar(255) not null,
    direccion varchar(255) not null,
    email     varchar(255) not null,
    password  varchar(255) not null,
    admin     tinyint      not null
);


INSERT INTO "Usuario"(id, nombre, apellido, dni, direccion,email, password, admin)
VALUES(null, 'mari','manri','29363692','florencia 1900','test@unlam.edu.ar', '123', true);
*/

-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS futbolDB;

-- Usar la base de datos
USE futbolDB;

-- Crear la tabla jugador
CREATE TABLE IF NOT EXISTS jugador (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       nombre VARCHAR(255) NOT NULL,
                                       fecha_nacimiento VARCHAR(255) NOT NULL,
                                       posicion VARCHAR(255) NOT NULL,
                                       dni VARCHAR(255) UNIQUE NOT NULL,
                                       partido VARCHAR(255) NOT NULL  -- Este campo almacenará los valores de tu enum PartidosDeBsAs
);

-- Insertar un jugador de ejemplo
INSERT INTO jugador (nombre, fecha_nacimiento, posicion, dni, partido)
VALUES ('Lionel Messi', '1987-06-24', 'Delantero', '12345678', 'AlmiranteBrown');

