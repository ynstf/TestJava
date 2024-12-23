package com.demofx.metier;

import com.demofx.model.Consultation;
import com.demofx.model.Medecin;
import com.demofx.model.Patient;

import java.util.Date;
import java.util.List;

public class TestImpl {
    public static void main(String[] args) {
        ICabinetMetier icm = new CabinetMetierImpl();
        //Patient patient1 = new Patient(1, "Doe", "John", "CIN12345", "0612345678", "john.doe@example.com", new Date());
        //icm.ajouterPatient(patient1);
        //icm.ajouterPatient(new Patient(1, "Doe", "John", "CIN12345", "0612345678", "john.doe@example.com", new java.util.Date()));
        //icm.ajouterPatient(new Patient(2, "Smith", "Anna", "CIN98765", "0612345679", "anna.smith@example.com", new java.util.Date()));
        //icm.ajouterPatient(new Patient(3, "Doe", "Jane", "CIN54321", "0612345680", "jane.doe@example.com", new java.util.Date()));


        //icm.supprimerPatient(1);  // This will delete the patient with id 1 from the database

        List<Patient> patients = icm.afficherPatients();
        for (Patient patient : patients) {
            System.out.println("ID: " + patient.getIdPatient() + ", Name: " + patient.getNom() + " " + patient.getPrenom());
        }

        // Test searching by keyword "Doe"
        System.out.println("Searching for patients with keyword 'Doe':");
        // Print the result
        for (Patient patient : icm.rechercherPatientParMotCle("Doe")) {
            System.out.println("Patient Found: " + patient.getNom() + " " + patient.getPrenom() + " - " + patient.getEmail());
        }

        // Test to display consultations for a specific patient (for example, patient with id 1)
        List<Consultation> consultations = icm.afficherConsultationsPatient(1);

        if (consultations.isEmpty()) {
            System.out.println("No consultations found for this patient.");
        } else {
            for (Consultation c : consultations) {
                System.out.println("Consultation ID: " + c.getIdConsultation() +
                        ", Date: " + c.getDateConsultation() +
                        ", Medecin: " + c.getMedecin().getNom() + " " + c.getMedecin().getPrenom() +
                        ", Patient: " + c.getPatient().getNom() + " " + c.getPatient().getPrenom());
            }
        }

        // Test 1: Adding a Medecin
        System.out.println("Test 1: Ajouter un Médecin");
        Medecin medecin1 = new Medecin(0, "Smith", "John", "john.smith@example.com", "0612345678");
        icm.ajouterMedecin(medecin1);

        // Test 2: Display all Medecins
        System.out.println("\nTest 2: Afficher les Médecins");
        List<Medecin> medecins = icm.afficherMedecins();
        for (Medecin medecin : medecins) {
            System.out.println("Medecin ID: " + medecin.getIdMedecin() +
                    ", Name: " + medecin.getNom() + " " + medecin.getPrenom());
        }

        // Test 5: Deleting the Medecin
        System.out.println("\nTest 5: Supprimer un Médecin");
        //icm.supprimerMedecin(medecin1.getIdMedecin());

        System.out.println("Test 1: Ajouter une Consultation");
        Patient patient = new Patient(2, "Smith", "Anna", "CIN98765", "0612345679", "anna.smith@example.com", new java.util.Date());
        Consultation consultation = new Consultation(0, new java.sql.Date(new java.util.Date().getTime()), patient, medecin1);
        icm.ajouterConsultation(consultation);
    }
}
