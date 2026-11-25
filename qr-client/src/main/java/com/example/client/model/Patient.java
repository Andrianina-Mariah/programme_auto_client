package com.example.client.model;

public class Patient {

    public Long id;
    public String nom;
    public String prenom;
    public String dateNaissance;
    public String sexe;
    public String telephone;
    public String email;

    public Patient() {
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
