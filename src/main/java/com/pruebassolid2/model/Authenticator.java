package com.pruebassolid2.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authenticator {

    private final DataBaseManager dbManager;

    public Authenticator(DataBaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public boolean comprobacion(String nombre, String password) {
        String sql = "SELECT * FROM trabajadores WHERE nombre = ? AND password = ?";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

