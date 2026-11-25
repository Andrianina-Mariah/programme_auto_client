-- Active: 1795501256479@@127.0.0.1@3306@gestion_patient
CREATE DATABASE gestion_patient;
USE gestion_patient;

CREATE TABLE patient (
    id_patient INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100),
    prenom VARCHAR(50),
    date_naissance DATE,
    sexe VARCHAR(20),
    telephone VARCHAR(30),
    email VARCHAR(150)
);

INSERT INTO patient (nom, prenom, date_naissance, sexe, telephone, email) VALUES
('Doe', 'John', '1980-01-15', 'Masculin', '0345897125', 'john.doe@example.com'),
('Smith', 'Jane', '1990-05-20', 'Féminin', '0345897126', 'jane.smith@example.com'),
('Brown', 'Michael', '1975-09-10', 'Masculin', '0345897127', 'michael.brown@example.com');