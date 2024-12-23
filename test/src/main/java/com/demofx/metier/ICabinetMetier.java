package com.demofx.metier;

import com.demofx.model.Patient;
import com.demofx.model.Medecin;
import com.demofx.model.Consultation;
import java.util.List;

public interface ICabinetMetier {

    // Gestion des Patients
    void ajouterPatient(Patient patient);
    void supprimerPatient(int idPatient);
    List<Patient> afficherPatients();
    List<Patient> rechercherPatientParMotCle(String motCle);
    List<Consultation> afficherConsultationsPatient(int idPatient);

    // Gestion des Médecins
    void ajouterMedecin(Medecin medecin);
    void supprimerMedecin(int idMedecin);
    List<Medecin> afficherMedecins();
    List<Consultation> afficherConsultationsMedecin(int idMedecin);

    // Gestion des Consultations
    void ajouterConsultation(Consultation consultation);
    void supprimerConsultation(int idConsultation);
    List<Consultation> afficherConsultations();
}
