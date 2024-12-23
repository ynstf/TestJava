package com.demofx.metier;

import com.demofx.model.Consultation;
import com.demofx.model.Medecin;
import com.demofx.model.Patient;

import java.util.List;
import java.util.Scanner;

public class Console {
    public static void main(String[] args) {
        // Instanciation de l'implémentation de l'interface ICabinetMetier
        ICabinetMetier cabinetMetier = new CabinetMetierImpl();

        // Menu pour l'application
        Scanner scanner = new Scanner(System.in);
        int choix;
        do {
            System.out.println("\n--- Menu Cabinet Médical ---");
            System.out.println("1. Ajouter un patient");
            System.out.println("2. Supprimer un patient");
            System.out.println("3. Afficher tous les patients");
            System.out.println("4. Rechercher un patient par mot clé");
            System.out.println("5. Afficher les consultations d'un patient");
            System.out.println("6. Ajouter un médecin");
            System.out.println("7. Supprimer un médecin");
            System.out.println("8. Afficher tous les médecins");
            System.out.println("9. Afficher les consultations d'un médecin");
            System.out.println("10. Ajouter une consultation");
            System.out.println("11. Supprimer une consultation");
            System.out.println("12. Afficher toutes les consultations");
            System.out.println("0. Quitter");
            System.out.print("Entrez votre choix: ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choix) {
                case 1:
                    // Ajouter un patient
                    ajouterPatient(cabinetMetier, scanner);
                    break;
                case 2:
                    // Supprimer un patient
                    supprimerPatient(cabinetMetier, scanner);
                    break;
                case 3:
                    // Afficher tous les patients
                    afficherPatients(cabinetMetier);
                    break;
                case 4:
                    // Rechercher un patient par mot clé
                    rechercherPatientParMotCle(cabinetMetier, scanner);
                    break;
                case 5:
                    // Afficher les consultations d'un patient
                    afficherConsultationsPatient(cabinetMetier, scanner);
                    break;
                case 6:
                    // Ajouter un médecin
                    ajouterMedecin(cabinetMetier, scanner);
                    break;
                case 7:
                    // Supprimer un médecin
                    supprimerMedecin(cabinetMetier, scanner);
                    break;
                case 8:
                    // Afficher tous les médecins
                    afficherMedecins(cabinetMetier);
                    break;
                case 9:
                    // Afficher les consultations d'un médecin
                    afficherConsultationsMedecin(cabinetMetier, scanner);
                    break;
                case 10:
                    // Ajouter une consultation
                    ajouterConsultation(cabinetMetier, scanner);
                    break;
                case 11:
                    // Supprimer une consultation
                    supprimerConsultation(cabinetMetier, scanner);
                    break;
                case 12:
                    // Afficher toutes les consultations
                    afficherConsultations(cabinetMetier);
                    break;
                case 0:
                    System.out.println("Au revoir!");
                    break;
                default:
                    System.out.println("Choix invalide, réessayez.");
            }
        } while (choix != 0);

        scanner.close();
    }

    private static void ajouterPatient(ICabinetMetier cabinetMetier, Scanner scanner) {
        System.out.print("Nom: ");
        String nom = scanner.nextLine();
        System.out.print("Prénom: ");
        String prenom = scanner.nextLine();
        System.out.print("CIN: ");
        String cin = scanner.nextLine();
        System.out.print("Téléphone: ");
        String telephone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Date de naissance (yyyy-mm-dd): ");
        String dateNaissance = scanner.nextLine();

        Patient patient = new Patient(0, nom, prenom, cin, telephone, email, java.sql.Date.valueOf(dateNaissance));
        cabinetMetier.ajouterPatient(patient);
    }

    private static void supprimerPatient(ICabinetMetier cabinetMetier, Scanner scanner) {
        System.out.print("ID du patient à supprimer: ");
        int idPatient = scanner.nextInt();
        cabinetMetier.supprimerPatient(idPatient);
    }

    private static void afficherPatients(ICabinetMetier cabinetMetier) {
        List<Patient> patients = cabinetMetier.afficherPatients();
        System.out.println("Liste des patients:");
        for (Patient patient : patients) {
            System.out.println(patient);
        }
    }

    private static void rechercherPatientParMotCle(ICabinetMetier cabinetMetier, Scanner scanner) {
        System.out.print("Mot clé pour la recherche: ");
        String motCle = scanner.nextLine();
        List<Patient> patients = cabinetMetier.rechercherPatientParMotCle(motCle);
        System.out.println("Résultats de la recherche:");
        for (Patient patient : patients) {
            System.out.println(patient);
        }
    }

    private static void afficherConsultationsPatient(ICabinetMetier cabinetMetier, Scanner scanner) {
        System.out.print("ID du patient: ");
        int idPatient = scanner.nextInt();
        List<Consultation> consultations = cabinetMetier.afficherConsultationsPatient(idPatient);
        System.out.println("Consultations du patient:");
        for (Consultation consultation : consultations) {
            System.out.println(consultation);
        }
    }

    private static void ajouterMedecin(ICabinetMetier cabinetMetier, Scanner scanner) {
        System.out.print("Nom du médecin: ");
        String nom = scanner.nextLine();
        System.out.print("Prénom du médecin: ");
        String prenom = scanner.nextLine();
        System.out.print("Spécialité du médecin: ");
        String specialite = scanner.nextLine();
        System.out.print("Téléphone du médecin: ");
        String telephone = scanner.nextLine();
        System.out.print("Email du médecin: ");
        String email = scanner.nextLine();

        Medecin medecin = new Medecin(0, nom, prenom, specialite, telephone, email);
        cabinetMetier.ajouterMedecin(medecin);
    }

    private static void supprimerMedecin(ICabinetMetier cabinetMetier, Scanner scanner) {
        System.out.print("ID du médecin à supprimer: ");
        int idMedecin = scanner.nextInt();
        cabinetMetier.supprimerMedecin(idMedecin);
    }

    private static void afficherMedecins(ICabinetMetier cabinetMetier) {
        List<Medecin> medecins = cabinetMetier.afficherMedecins();
        System.out.println("Liste des médecins:");
        for (Medecin medecin : medecins) {
            System.out.println(medecin);
        }
    }

    private static void afficherConsultationsMedecin(ICabinetMetier cabinetMetier, Scanner scanner) {
        System.out.print("ID du médecin: ");
        int idMedecin = scanner.nextInt();
        List<Consultation> consultations = cabinetMetier.afficherConsultationsMedecin(idMedecin);
        System.out.println("Consultations données par le médecin:");
        for (Consultation consultation : consultations) {
            System.out.println(consultation);
        }
    }

    private static void ajouterConsultation(ICabinetMetier cabinetMetier, Scanner scanner) {
        System.out.print("ID du patient: ");
        int idPatient = scanner.nextInt();
        System.out.print("ID du médecin: ");
        int idMedecin = scanner.nextInt();
        scanner.nextLine(); // Consume the newline
        System.out.print("Date de la consultation (yyyy-mm-dd): ");
        String dateConsultation = scanner.nextLine();

        Patient patient = new Patient(idPatient, "", "", "", "", "", null);  // Just a mock, ideally fetch from DB
        Medecin medecin = new Medecin(idMedecin);  // Just a mock, ideally fetch from DB
        Consultation consultation = new Consultation(0, java.sql.Date.valueOf(dateConsultation), patient, medecin);
        cabinetMetier.ajouterConsultation(consultation);
    }

    private static void supprimerConsultation(ICabinetMetier cabinetMetier, Scanner scanner) {
        System.out.print("ID de la consultation à supprimer: ");
        int idConsultation = scanner.nextInt();
        cabinetMetier.supprimerConsultation(idConsultation);
    }

    private static void afficherConsultations(ICabinetMetier cabinetMetier) {
        List<Consultation> consultations = cabinetMetier.afficherConsultations();
        System.out.println("Liste des consultations:");
        for (Consultation consultation : consultations) {
            System.out.println(consultation);
        }
    }
}
