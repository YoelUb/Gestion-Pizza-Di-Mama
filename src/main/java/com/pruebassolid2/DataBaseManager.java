package com.pruebassolid2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManager {

    private static DataBaseManager instance;
    private Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/pizzeriaDiMama";
    private final String user = "root";
    private final String password = "";

    //Singleton nadie instancia de esta clase
    private DataBaseManager() {
        conectar();
    }

    private void conectar() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida con la base de datos.");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al conectar con la base de datos");
        }
    }


    public static DataBaseManager getInstance() {
        if (instance == null) {
            instance = new DataBaseManager();
        }
        return instance;
    }


    public Connection getConnection() {
        return connection;
    }


}
