package com.demofx.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionDB {
    private static Connection connection;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/CabinetMedicalDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connection successful");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error connecting to the database: " + e.getMessage());
        }

    }
    public static boolean isConnectionClosed() {
        try {
            System.out.println(connection == null || connection.isClosed());
            return connection == null || connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }
    public static void closeConnexion() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Connection closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

