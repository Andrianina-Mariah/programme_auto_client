package com.example.gestion_patient.controller;

import com.example.gestion_patient.entity.Patient;
import com.example.gestion_patient.service.PatientService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // Ajouter un patient
    @PostMapping
    public Patient ajouter(@Valid @RequestBody Patient patient) {
        return patientService.ajouter(patient);
    }

    // Lister les patients
    @GetMapping
    public List<Patient> lister() {
        return patientService.lister();
    }

    // Trouver un patient
    @GetMapping("/{id}")
    public ResponseEntity<Patient> trouver(@PathVariable Long id) {
        return patientService.trouverParId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Modifier un patient
    @PutMapping("/{id}")
    public Patient modifier(@PathVariable Long id, @RequestBody Patient patient) {
        return patientService.modifier(id, patient);
    }

    // Supprimer un patient
    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Long id) {
        patientService.supprimer(id);
    }
}
