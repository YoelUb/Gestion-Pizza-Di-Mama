package com.pruebassolid2.model;

import com.pruebassolid2.model.interfaces.IAuthenticator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authenticator implements IAuthenticator {

    @Override
    public boolean authenticate(String username, String password) {
        return "admin".equals(username) && "1234".equals(password);
    }

    @Override
    public boolean comprobacion(String nombre, String password) {
        Connection connection = null;

        try {
            connection = DataBaseManager.getConnection();

            if (connection == null || connection.isClosed()) {
                System.err.println("Conexión no disponible o cerrada");
                return false;
            }

            String query = "SELECT * FROM trabajadores WHERE nombre = ? AND password = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            return rs.next(); // true si encontró al usuario

        } catch (SQLException e) {
            System.err.println("Error al validar credenciales: " + e.getMessage());
            return false;
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
    }
}
