-- Inversiones Magdezis - Script de base de datos (MySQL 8+)
DROP DATABASE IF EXISTS inversiones_magdezis_db;
CREATE DATABASE inversiones_magdezis_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER IF NOT EXISTS 'IN4BM'@'localhost' IDENTIFIED BY '$DmynM4A';
GRANT ALL PRIVILEGES ON inversiones_magdezis_db.* TO 'IN4BM'@'localhost';
FLUSH PRIVILEGES;

USE inversiones_magdezis_db;

CREATE TABLE roles (
    id     INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(20) NOT NULL UNIQUE
) ENGINE=InnoDB;

CREATE TABLE usuarios (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    nombre     VARCHAR(60)  NOT NULL,
    apellido   VARCHAR(60)  NOT NULL,
    correo     VARCHAR(120) NOT NULL UNIQUE,
    contrasena VARCHAR(100) NOT NULL,  -- hash BCrypt
    rol_id     INT NOT NULL,
    creado_en  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_usuarios_roles FOREIGN KEY (rol_id) REFERENCES roles(id)
) ENGINE=InnoDB;

INSERT INTO roles (nombre) VALUES ('USER'), ('ADMIN');

-- Para probar el rol ADMIN: registra un usuario en la app y ejecuta:
-- UPDATE usuarios SET rol_id = (SELECT id FROM roles WHERE nombre='ADMIN') WHERE correo = 'tu@correo.com';
