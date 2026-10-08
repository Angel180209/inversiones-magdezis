package com.soft.inversiones.magdezis.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/** Centraliza la configuración y la creación de conexiones JDBC. */
public final class DatabaseConfig {

    private static final Properties PROPS = cargarPropiedades();

    private DatabaseConfig() { }

    private static Properties cargarPropiedades() {
        Properties props = new Properties();
        try (InputStream in = DatabaseConfig.class.getResourceAsStream("/db.properties")) {
            if (in == null) {
                throw new IllegalStateException("No se encontró db.properties en resources");
            }
            props.load(in);
        } catch (IOException e) {
            throw new IllegalStateException("Error leyendo db.properties", e);
        }
        return props;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                PROPS.getProperty("db.url"),
                PROPS.getProperty("db.user"),
                PROPS.getProperty("db.password"));
    }
}
