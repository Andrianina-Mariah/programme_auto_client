package com.example.gestion_patient.service;

import com.example.gestion_patient.entity.Patient;
import com.example.gestion_patient.repository.PatientRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // Ajouter un patient
    public Patient ajouter(Patient patient) {
        return patientRepository.save(patient);
    }

    // Récupérer tous les patients
    public List<Patient> lister() {
        return patientRepository.findAll();
    }

    // Récupérer un patient par son ID
    public Optional<Patient> trouverParId(Long id) {
        return patientRepository.findById(id);
    }

    // Modifier un patient
    public Patient modifier(Long id, Patient nouveauPatient) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient introuvable"));

        patient.setNom(nouveauPatient.getNom());
        patient.setPrenom(nouveauPatient.getPrenom());
        patient.setDateNaissance(nouveauPatient.getDateNaissance());
        patient.setSexe(nouveauPatient.getSexe());
        patient.setTelephone(nouveauPatient.getTelephone());
        patient.setEmail(nouveauPatient.getEmail());

        return patientRepository.save(patient);
    }

    // Supprimer un patient
    public void supprimer(Long id) {
        patientRepository.deleteById(id);
    }
}
