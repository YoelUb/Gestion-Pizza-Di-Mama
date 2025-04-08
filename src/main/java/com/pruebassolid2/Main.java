package com.pruebassolid2;


import com.pruebassolid2.model.Authenticator;
import com.pruebassolid2.model.DataBaseManager;

public class Main {
    public static void main(String[] args) {
        DataBaseManager db = DataBaseManager.getInstance();
        Authenticator auth = new Authenticator(db);

        boolean resultado = auth.comprobacion("Yoel", "1234");

        if (resultado) {
            System.out.println("✅ Acceso permitido");
        } else {
            System.out.println("❌ Usuario o contraseña incorrectos");
        }

    }

}
