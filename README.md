# Inversiones Magdezis - Login y Registro

Java 17+ · Ant · JavaFX (FXML / Scene Builder) · BCrypt (jBCrypt) · JDBC · MySQL 8

## Requisitos
1. Ejecutar `sql/inversiones_magdezis.sql` en MySQL.
2. Copiar a `lib/`: `jbcrypt-0.4.jar` y `mysql-connector-j-8.x.jar`.
3. Ajustar `javafx.sdk` en `build.properties`.
4. Copiar `src/main/resources/db.properties.example` a `db.properties` y poner la contraseña.

## Ejecutar
    ant run

## Capas
config · model · dto · repository · service · exception · controller · view
