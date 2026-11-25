package com.example.client.qr;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class QRCodeGenerator {

    // Adresse de base du futur endpoint PHP
    private static final String BASE_URL =
            "http://localhost/patient.php?id=";

    public void genererQRCode(Long patientId, String cheminFichier)
            throws Exception {

        // 1. Construire l'URL du patient
        String url = BASE_URL + patientId;

        // 2. Définir les dimensions du QR code
        int largeur = 300;
        int hauteur = 300;

        // 3. Configurer l'encodage
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 1);

        // 4. Générer le QR code
        BitMatrix matrix = new MultiFormatWriter().encode(
                url,
                BarcodeFormat.QR_CODE,
                largeur,
                hauteur,
                hints
        );

        // 5. Enregistrer le QR code au format JPEG
        MatrixToImageWriter.writeToPath(
                matrix,
                "JPG",
                Path.of(cheminFichier)
        );

        System.out.println("QR code généré pour le patient : " + patientId);
    }
}
