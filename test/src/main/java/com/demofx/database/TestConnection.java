package com.demofx.database;

public class TestConnection {
    public static void main(String[] args) {
        SignletonConnexionDB conn = new SignletonConnexionDB();
        conn.getConnexion();
    }

}
