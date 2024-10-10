package com.sostmaky.tp1lab3.model;


import android.net.Uri;

import java.io.Serializable;

public class Usuario implements Serializable {
    private long dni;
    private String apellido;
    private String nombre;
    private String mail;
    private String contrasena;
    private String imagen;

    public Usuario() {
    }

    public Usuario(long dni, String apellido, String nombre, String mail, String contrasena, String imagen) {
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.mail = mail;
        this.contrasena = contrasena;
        this.imagen=imagen;
    }

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}