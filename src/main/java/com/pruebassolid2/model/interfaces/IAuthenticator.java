package com.pruebassolid2.model.interfaces;

public interface IAuthenticator {
    boolean authenticate(String username, String password);
    boolean comprobacion(String nombre, String password);
}
