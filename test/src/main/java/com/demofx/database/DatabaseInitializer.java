package com.demofx.database;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseInitializer {
    public static void createTables() {
        String createPatientTable = """
            CREATE TABLE IF NOT EXISTS Patient (
                id_patient INT PRIMARY KEY AUTO_INCREMENT,
                nom VARCHAR(50) NOT NULL,
                prenom VARCHAR(50) NOT NULL,
                cin VARCHAR(20) UNIQUE NOT NULL,
                telephone VARCHAR(15),
                email VARCHAR(50),
                date_naissance DATE
            );
        """;

        String createMedecinTable = """
            CREATE TABLE IF NOT EXISTS Medecin (
                id_medecin INT PRIMARY KEY AUTO_INCREMENT,
                nom VARCHAR(50) NOT NULL,
                prenom VARCHAR(50) NOT NULL,
                email VARCHAR(50),
                tel VARCHAR(15)
            );
        """;

        String createConsultationTable = """
            CREATE TABLE IF NOT EXISTS Consultation (
                id_consultation INT PRIMARY KEY AUTO_INCREMENT,
                date_consultation DATE NOT NULL,
                id_patient INT,
                id_medecin INT,
                FOREIGN KEY (id_patient) REFERENCES Patient(id_patient)
                    ON DELETE CASCADE
                    ON UPDATE CASCADE,
                FOREIGN KEY (id_medecin) REFERENCES Medecin(id_medecin)
                    ON DELETE CASCADE
                    ON UPDATE CASCADE
            );
        """;

        try (Connection connection = SignletonConnexionDB.getConnexion();
             Statement statement = connection.createStatement()) {

            // Création des tables
            statement.execute(createPatientTable);
            System.out.println("Table 'Patient' créée avec succès.");

            statement.execute(createMedecinTable);
            System.out.println("Table 'Medecin' créée avec succès.");

            statement.execute(createConsultationTable);
            System.out.println("Table 'Consultation' créée avec succès.");

        } catch (SQLException e) {
            System.err.println("Erreur lors de la création des tables : " + e.getMessage());
            e.printStackTrace();
        } finally {
            SignletonConnexionDB.closeConnexion();
        }
    }

    public static void main(String[] args) {
        createTables();
    }
}
