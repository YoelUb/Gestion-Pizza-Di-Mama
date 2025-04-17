package com.pruebassolid2.model;

import com.pruebassolid2.model.interfaces.IDataBaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManager implements IDataBaseManager {

    private static DataBaseManager instance;
    private static Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/pizzeriaDiMama";
    private final String user = "root";
    private final String password = "";

    private DataBaseManager() {
        conectar();
    }

    private void conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Conexión establecida con la base de datos.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.err.println("❌ Error al conectar con la base de datos.");
        }
    }

    public static DataBaseManager getInstance() {
        if (instance == null) {
            instance = new DataBaseManager();
        }
        return instance;
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                System.out.println("ℹ️ Reconectando a la base de datos...");
                getInstance().conectar();
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al comprobar el estado de la conexión: " + e.getMessage());
            return null;
        }

        return connection;
    }

    @Override
    public void guardarPedido(Pedido pedido) {
        System.out.println("Pedido guardado: " + pedido);
    }

    @Override
    public void actualizarPedido(int id, Pedido pedido) {
        System.out.println("Pedido actualizado: " + id);
    }

    @Override
    public void eliminarPedido(int id) {
        System.out.println("Pedido eliminado: " + id);
    }
}
