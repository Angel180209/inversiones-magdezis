package com.soft.inversiones.magdezis.model;

public class Rol {
    public static final String USER = "USER";
    public static final String ADMIN = "ADMIN";

    private int id;
    private String nombre;

    public Rol() { }

    public Rol(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
