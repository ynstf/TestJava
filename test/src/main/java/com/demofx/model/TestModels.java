package com.demofx.model;


import java.util.*;

public class TestModels {
    public static void main(String[] args) {
        // Collections pour simuler les données
        List<Patient> patients = new ArrayList<>();
        List<Medecin> medecins = new ArrayList<>();
        List<Consultation> consultations = new ArrayList<>();

        // Ajouter des patients
        System.out.println("\n=== Ajout des Patients ===");
        Patient patient1 = new Patient(1, "Doe", "John", "CIN12345", "0612345678", "john.doe@example.com", new Date());
        Patient patient2 = new Patient(2, "Smith", "Jane", "CIN67890", "0623456789", "jane.smith@example.com", new Date());
        patients.add(patient1);
        patients.add(patient2);
        displayPatients(patients);

        // Ajouter des médecins
        System.out.println("\n=== Ajout des Médecins ===");
        Medecin medecin1 = new Medecin(1, "House", "Gregory", "house@example.com", "0654321987");
        Medecin medecin2 = new Medecin(2, "Watson", "John", "watson@example.com", "0645678912");
        medecins.add(medecin1);
        medecins.add(medecin2);
        displayMedecins(medecins);

        // Ajouter des consultations
        System.out.println("\n=== Ajout des Consultations ===");
        Consultation consultation1 = new Consultation(1, new Date(), patient1, medecin1);
        Consultation consultation2 = new Consultation(2, new Date(), patient2, medecin2);
        consultations.add(consultation1);
        consultations.add(consultation2);
        displayConsultations(consultations);

        // Rechercher un patient par mot-clé
        System.out.println("\n=== Recherche Patient par Mot-Clé: 'Doe' ===");
        searchPatient(patients, "Doe");

        // Supprimer un patient
        System.out.println("\n=== Suppression du Patient ID 1 ===");
        deletePatient(patients, 1);
        displayPatients(patients);

        // Afficher les consultations d'un patient
        System.out.println("\n=== Consultations du Patient ID 2 ===");
        displayConsultationsByPatient(consultations, 2);
    }

    // Méthode pour afficher les patients
    private static void displayPatients(List<Patient> patients) {
        for (Patient patient : patients) {
            System.out.println("ID: " + patient.getIdPatient() + ", Nom: " + patient.getNom() + ", Prénom: " + patient.getPrenom());
        }
    }

    // Méthode pour afficher les médecins
    private static void displayMedecins(List<Medecin> medecins) {
        for (Medecin medecin : medecins) {
            System.out.println("ID: " + medecin.getIdMedecin() + ", Nom: " + medecin.getNom() + ", Prénom: " + medecin.getPrenom());
        }
    }

    // Méthode pour afficher les consultations
    private static void displayConsultations(List<Consultation> consultations) {
        for (Consultation consultation : consultations) {
            System.out.println("ID Consultation: " + consultation.getIdConsultation() +
                    ", Date: " + consultation.getDateConsultation() +
                    ", Patient: " + consultation.getPatient().getNom() +
                    ", Médecin: " + consultation.getMedecin().getNom());
        }
    }

    // Méthode pour rechercher un patient par mot-clé
    private static void searchPatient(List<Patient> patients, String keyword) {
        for (Patient patient : patients) {
            if (patient.getNom().contains(keyword) || patient.getPrenom().contains(keyword)) {
                System.out.println("Patient trouvé: ID: " + patient.getIdPatient() + ", Nom: " + patient.getNom());
            }
        }
    }

    // Méthode pour supprimer un patient
    private static void deletePatient(List<Patient> patients, int id) {
        patients.removeIf(patient -> patient.getIdPatient() == id);
        System.out.println("Patient avec ID " + id + " supprimé avec succès.");
    }

    // Méthode pour afficher les consultations d'un patient
    private static void displayConsultationsByPatient(List<Consultation> consultations, int patientId) {
        for (Consultation consultation : consultations) {
            if (consultation.getPatient().getIdPatient() == patientId) {
                System.out.println("ID Consultation: " + consultation.getIdConsultation() +
                        ", Date: " + consultation.getDateConsultation() +
                        ", Médecin: " + consultation.getMedecin().getNom());
            }
        }
    }
}
