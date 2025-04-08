package com.pruebassolid2;

public class Authenticator {


    private String nombre;
    private String password;

    public Authenticator(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }


    public boolean comprobacion(String nombre, String password) {
        if (nombre.equals("admin") && password.equals("admin")) {
            return true;
        } else {
            return false;
        }
    }


}
