package com.pruebassolid2.controller;

import com.pruebassolid2.model.Authenticator;
import com.pruebassolid2.model.DataBaseManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final Authenticator authenticator;

    public LoginController() {
        this.authenticator = new Authenticator(DataBaseManager.getInstance());
    }

    @PostMapping("/login")
    public boolean login(@RequestBody LoginRequest request) {
        return authenticator.comprobacion(request.getNombre(), request.getPassword());
    }

    // Clase auxiliar para recibir JSON del frontend
    public static class LoginRequest {
        private String nombre;
        private String password;

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
