package com.example.client.api;

import com.example.client.model.Patient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PatientApiClient {

    // Adresse de ton API Spring Boot
    private static final String API_URL =
            "http://localhost:8080/api/patients";

    // Client HTTP Java
    private final HttpClient httpClient = HttpClient.newHttpClient();

    // Convertisseur JSON
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Patient> getAllPatients() throws Exception {

        // 1. Préparer la requête GET
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();

        // 2. Envoyer la requête et récupérer la réponse
        HttpResponse<String> response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        // 3. Vérifier le code HTTP
        if (response.statusCode() != 200) {
            throw new RuntimeException(
                    "Erreur API : " + response.statusCode()
            );
        }

        // 4. Convertir le JSON en liste de patients
        List<Patient> patients = objectMapper.readValue(
                response.body(),
                new TypeReference<List<Patient>>() {}
        );

        return patients;
    }
}
