package com.pruebassolid2;


import javax.xml.crypto.Data;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        DataBaseManager db = DataBaseManager.getInstance();

        Connection connection = db.getConnection();


    }
}