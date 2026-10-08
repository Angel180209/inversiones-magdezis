package com.soft.inversiones.magdezis.model;

public class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasena; // hash BCrypt
    private Rol rol;

    public Usuario() { }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }

    public String getNombreCompleto() { return nombre + " " + apellido; }
}
