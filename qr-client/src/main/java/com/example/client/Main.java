package com.example.client;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.example.client.api.PatientApiClient;
import com.example.client.model.Patient;
import com.example.client.qr.QRCodeGenerator;

public class Main {

    public static void main(String[] args) throws Exception {

        // 1. Récupérer les patients depuis l'API
        PatientApiClient apiClient = new PatientApiClient();
        List<Patient> patients = apiClient.getAllPatients();

        // 2. Créer le générateur QR
        QRCodeGenerator qrGenerator = new QRCodeGenerator();

        // 3. Définir le répertoire principal
        Path repertoirePrincipal = Path.of("cdn", "patient");

        Files.createDirectories(repertoirePrincipal);

        // 4. Parcourir tous les patients
        for (Patient patient : patients) {

            Long id = patient.id;

            // 5. Créer le répertoire du patient
            Path dossierPatient = repertoirePrincipal.resolve(
                    String.valueOf(id)
            );

            Files.createDirectories(dossierPatient);

            // 6. Définir le chemin du QR code
            Path qrCode = dossierPatient.resolve("qr-code.jpeg");

            // 7. Vérifier si le QR code existe déjà
            if (Files.exists(qrCode)) {

                System.out.println(
                        "QR code déjà existant pour le patient " + id
                );

            } else {

                // 8. Générer le QR code
                qrGenerator.genererQRCode(
                        id,
                        qrCode.toString()
                );

                // 9. Vérifier que le fichier a bien été créé
                if (Files.exists(qrCode) && Files.size(qrCode) > 0) {
                    System.out.println(
                            "QR code enregistré : " + qrCode
                    );
                } else {
                    System.out.println(
                            "Erreur lors de la création du QR code du patient " + id
                    );
                }
            }
        }

        System.out.println("Traitement terminé !");
    }
}
