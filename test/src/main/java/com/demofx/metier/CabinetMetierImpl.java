package com.demofx.metier;

import com.demofx.database.SignletonConnexionDB;
import com.demofx.model.Consultation;
import com.demofx.model.Medecin;
import com.demofx.model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class CabinetMetierImpl implements ICabinetMetier{
    private Connection conn;
    public CabinetMetierImpl() {
        this.conn = SignletonConnexionDB.getConnexion();
    }
    // Helper method to get Patient by ID
    private Patient getPatientById(int idPatient) {
        Patient patient = null;
        String sql = "SELECT * FROM patient WHERE id_patient = ?";

        try (Connection conn = SignletonConnexionDB.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPatient);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    patient = new Patient(
                            rs.getInt("id_patient"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getString("cin"),
                            rs.getString("telephone"),
                            rs.getString("email"),
                            rs.getDate("date_naissance")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patient;
    }

    // Helper method to get Medecin by ID
    private Medecin getMedecinById(int idMedecin) {
        Medecin medecin = null;
        String sql = "SELECT * FROM medecin WHERE id_medecin = ?";

        try (Connection conn = SignletonConnexionDB.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMedecin);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    medecin = new Medecin(
                            rs.getInt("id_medecin"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getString("email"),
                            rs.getString("tel")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medecin;
    }

    @Override
    public void ajouterPatient(Patient patient) {

        String sql = "INSERT INTO patient (nom, prenom, cin, telephone, email, date_naissance) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patient.getNom());
            stmt.setString(2, patient.getPrenom());
            stmt.setString(3, patient.getCin());
            stmt.setString(4, patient.getTelephone());
            stmt.setString(5, patient.getEmail());

            // Convert java.util.Date to java.sql.Date
            java.sql.Date sqlDate = new java.sql.Date(patient.getDateNaissance().getTime());
            stmt.setDate(6, sqlDate);  // Set the converted java.sql.Date

            stmt.executeUpdate();
            System.out.println("Patient ajouté avec succès.");
        }
         catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimerPatient(int idPatient) {
        String sql = "DELETE FROM patient WHERE id_patient = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the id_patient parameter to the PreparedStatement
            stmt.setInt(1, idPatient);

            // Execute the delete query
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Patient supprimé avec succès.");
            } else {
                System.out.println("Aucun patient trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Patient> afficherPatients() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patient";

        try (
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idPatient = rs.getInt("id_patient");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String cin = rs.getString("cin");
                String telephone = rs.getString("telephone");
                String email = rs.getString("email");
                java.sql.Date dateNaissance = rs.getDate("date_naissance");

                Patient patient = new Patient(idPatient, nom, prenom, cin, telephone, email, new java.util.Date(dateNaissance.getTime()));
                patients.add(patient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }


    @Override
    public List<Patient> rechercherPatientParMotCle(String motCle) {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patient WHERE nom LIKE ? OR prenom LIKE ? OR cin LIKE ? OR email LIKE ?";

        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the search keyword (adding "%" for partial matches)
            String keyword = "%" + motCle + "%";
            stmt.setString(1, keyword);
            stmt.setString(2, keyword);
            stmt.setString(3, keyword);
            stmt.setString(4, keyword);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Create a new Patient object for each result
                    Patient patient = new Patient(
                            rs.getInt("id_patient"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getString("cin"),
                            rs.getString("telephone"),
                            rs.getString("email"),
                            rs.getDate("date_naissance")
                    );
                    patients.add(patient);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }


    @Override
    public List<Consultation> afficherConsultationsPatient(int idPatient) {
        List<Consultation> consultations = new ArrayList<>();
        String sql = "SELECT * FROM consultation WHERE id_patient = ?";

        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the patient ID parameter in the SQL query
            stmt.setInt(1, idPatient);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Retrieve consultation data from the result set
                    int idConsultation = rs.getInt("id_consultation");
                    java.sql.Date dateConsultation = rs.getDate("date_consultation");
                    int idMedecin = rs.getInt("id_medecin");

                    // Retrieve Patient information
                    Patient patient = getPatientById(idPatient);

                    // Retrieve Medecin information
                    Medecin medecin = getMedecinById(idMedecin);

                    // Create a new Consultation object and add it to the list
                    Consultation consultation = new Consultation(idConsultation, dateConsultation, patient, medecin);
                    consultations.add(consultation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return consultations;
    }



    @Override
    public void ajouterMedecin(Medecin medecin) {
        String sql = "INSERT INTO medecin (nom, prenom, email, tel) VALUES (?, ?, ?, ?)";
        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, medecin.getNom());
            stmt.setString(2, medecin.getPrenom());
            stmt.setString(3, medecin.getEmail());
            stmt.setString(4, medecin.getTel());

            stmt.executeUpdate();
            System.out.println("Médecin ajouté avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimerMedecin(int idMedecin) {
        String sql = "DELETE FROM medecin WHERE id_medecin = ?";
        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMedecin);
            stmt.executeUpdate();
            System.out.println("Médecin supprimé avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Medecin> afficherMedecins() {
        List<Medecin> medecins = new ArrayList<>();
        String sql = "SELECT * FROM medecin";
        try (
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Medecin medecin = new Medecin(
                        rs.getInt("id_medecin"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("tel")
                );
                medecins.add(medecin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medecins;
    }

    @Override
    public List<Consultation> afficherConsultationsMedecin(int idMedecin) {
        List<Consultation> consultations = new ArrayList<>();
        String sql = "SELECT * FROM consultation WHERE id_medecin = ?";

        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMedecin);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idConsultation = rs.getInt("id_consultation");
                    java.sql.Date dateConsultation = rs.getDate("date_consultation");
                    int idPatient = rs.getInt("id_patient");

                    // Retrieve Patient information
                    Patient patient = getPatientById(idPatient);

                    // Retrieve Medecin information
                    Medecin medecin = getMedecinById(idMedecin);

                    // Create a new Consultation object and add it to the list
                    Consultation consultation = new Consultation(idConsultation, dateConsultation, patient, medecin);
                    consultations.add(consultation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return consultations;
    }


    @Override
    public void ajouterConsultation(Consultation consultation) {
        String sql = "INSERT INTO consultation (date_consultation, id_patient, id_medecin) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Convert java.util.Date to java.sql.Date
            java.sql.Date sqlDate = new java.sql.Date(consultation.getDateConsultation().getTime());
            stmt.setDate(1, sqlDate);
            stmt.setInt(2, consultation.getPatient().getIdPatient()); // Assuming Patient has an ID
            stmt.setInt(3, consultation.getMedecin().getIdMedecin()); // Assuming Medecin has an ID

            stmt.executeUpdate();
            System.out.println("Consultation ajoutée avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimerConsultation(int idConsultation) {
        String sql = "DELETE FROM consultation WHERE id_consultation = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idConsultation);
            stmt.executeUpdate();
            System.out.println("Consultation supprimée avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Consultation> afficherConsultations() {
        List<Consultation> consultations = new ArrayList<>();
        String sql = "SELECT * FROM consultation";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int idConsultation = rs.getInt("id_consultation");
                Date dateConsultation = rs.getDate("date_consultation");
                int idPatient = rs.getInt("id_patient");
                int idMedecin = rs.getInt("id_medecin");

                // Create consultation object (assuming you have methods to get Patient and Medecin by ID)
                Patient patient = getPatientById(idPatient);
                Medecin medecin = getMedecinById(idMedecin);

                consultations.add(new Consultation(idConsultation, dateConsultation, patient, medecin));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultations;
    }

}