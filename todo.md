# Projet : Gestion des patients et génération de QR codes

## Technologies utilisées

- **MySQL** : stockage des données des patients.
- **Spring Boot** : backend et API REST.
- **Java** : programme autonome de génération des QR codes.
- **Maven** : gestion des dépendances.
- **ZXing** : génération des QR codes.
- **PHP** : consultation des informations des patients.
- **CDN** : stockage et distribution des images JPEG.

---

# SPRINT 1 — CRUD Patient et génération automatique des QR codes

## Objectif

Créer une application Spring Boot permettant de gérer les patients, puis développer un programme Java autonome qui récupère les patients via l'API, génère leurs QR codes et enregistre les images JPEG dans un répertoire CDN.

## 1. Création de la base de données MySQL

### 1.1. Créer la base de données

- [X] Créer la base de données `gestion_patient`.
- [X] Configurer la connexion MySQL dans Spring Boot.
- [X] Vérifier que Spring Boot arrive à se connecter à MySQL: ./mvnw spring-boot:run

### 1.2. Créer la table PATIENT

| Champ          | Type MySQL   | Description                                    |
| -------------- | ------------ | ---------------------------------------------- |
| id             | BIGINT       | Identifiant, clé primaire, auto-incrémentée |
| nom            | VARCHAR(100) | Nom du patient                                 |
| prenom         | VARCHAR(100) | Prénom du patient                             |
| date_naissance | DATE         | Date de naissance                              |
| sexe           | VARCHAR(20)  | Sexe                                           |
| telephone      | VARCHAR(30)  | Numéro de téléphone                         |
| email          | VARCHAR(150) | Adresse email                                  |

- [X] Créer la table `patient`.
- [X] Vérifier la structure de la table.
- [X] Insérer quelques patients pour les tests.

## 2. Développement du CRUD Patient avec Spring Boot

### 2.1. Création des classes

- [X] Créer l'entité `Patient` avec `@Entity`.
- [X] Créer `PatientRepository` pour communiquer avec MySQL.
- [X] Créer `PatientService` pour gérer la logique métier.
- [X] Créer `PatientController` pour exposer les fonctionnalités.

### 2.2. Implémentation du CRUD dans PatientService.java

- [X] `ajouter()` : ajouter un patient.
- [X] `lister()` : récupérer tous les patients.
- [X] `trouverParId(id)` : récupérer un patient par son identifiant.
- [X] `modifier(id, patient)` : modifier un patient.
- [X] `supprimer(id)` : supprimer un patient.

### 2.3. Création des endpoints REST dans PatientController.java

| Méthode HTTP | Endpoint               | Fonction                      |
| ------------- | ---------------------- | ----------------------------- |
| GET           | `/api/patients`      | Récupérer tous les patients |
| GET           | `/api/patients/{id}` | Récupérer un patient        |
| POST          | `/api/patients`      | Ajouter un patient            |
| PUT           | `/api/patients/{id}` | Modifier un patient           |
| DELETE        | `/api/patients/{id}` | Supprimer un patient          |

### 2.4. Retourner les données au format JSON

- [X] Annoter le contrôleur avec `@RestController`.
- [X] Utiliser `@GetMapping`, `@PostMapping`, `@PutMapping` et `@DeleteMapping`.
- [X] Vérifier que `lister()` retourne une liste de patients en JSON.
- [X] Vérifier que `trouverParId()` retourne un patient en JSON.
- [X] Gérer les erreurs (patient inexistant, données invalides, etc.).

### 2.5. Tester le CRUD

- [X] Tester les endpoints avec cURL.
- [X] Vérifier les insertions dans MySQL.
- [X] Vérifier les modifications et suppressions.
- [X] Vérifier les réponses JSON.

## 3. Création du programme autonome Java

### 3.1. Préparation du projet

- [X] Créer une nouvelle branche Git : `sprint-1-qrcode`.
- [X] Créer un projet Java autonome avec Maven.

mvn archetype:generate -DgroupId=com.example.client -DartifactId=qr-client -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

- [X] Ajouter les dépendances nécessaires :
  - Client HTTP Java pour appeler l'API.
  - Jackson pour lire le JSON.
  - ZXing pour générer les QR codes.
- [X] Créer la classe principale `Main` avec la méthode `main()`.

### 3.2. Récupération des patients

- [X] Créer une classe `PatientApiClient`.
- [X] Configurer l'URL de l'API Spring Boot.
- [X] Appeler l'endpoint `GET /api/patients`.
- [X] Récupérer la réponse JSON.
- [X] Convertir le JSON en objets `Patient`.
- [X] Parcourir tous les patients récupérés.

Explication: 

HttpClient	Permet d'envoyer des requêtes HTTP
HttpRequest	Représente la requête GET
HttpResponse	Contient la réponse du serveur
response.body()	Récupère le JSON
ObjectMapper	Convertit le JSON en objets Java
List<Patient></patient>	Contient tous les patients récupérés

### 3.3. Création du générateur QR code

- [X] Créer une classe `QRCodeGenerator`.
- [X] Récupérer l'identifiant de chaque patient dans Main.java.
- [X] Construire dynamiquement une URL pour chaque patient.
- [X] Générer un QR code à partir de cette URL.
- [X] Convertir le QR code au format JPEG.

À ce stade, l'URL peut être une URL temporaire de test. Le véritable pointage vers PHP sera développé au Sprint 2.

## 4. Organisation et stockage des QR codes

### 4.1. Création du répertoire CDN

Créer un répertoire destiné à recevoir les images des patients.

Exemple :

```text
/usr/local/cdn/
    patient/
        1/
            qr-code.jpeg
        2/
            qr-code.jpeg
        3/
            qr-code.jpeg
```

### 4.2. Enregistrement des images

- [X] Créer le répertoire principal `cdn/patient`.
- [X] Créer un sous-répertoire pour chaque identifiant patient.
- [X] Enregistrer le QR code JPEG dans le répertoire correspondant.
- [X] Vérifier si le QR code existe déjà avant de le générer.
- [X] Éviter de régénérer inutilement les QR codes existants.
- [X] Vérifier que les images sont correctement enregistrées.

### 4.3. Configuration du CDN

- [X] Configurer un serveur HTTP pour rendre les images accessibles: 
  sudo ln -s "$(pwd)/cdn" /opt/lampp/htdocs/cdn
- [X] Définir le chemin public des images.
- [X] Vérifier qu'un navigateur peut afficher les QR codes.
- [X] Vérifier que les fichiers JPEG sont accessibles.

## 5. Automatisation du programme autonome

Le programme doit fonctionner automatiquement à partir de sa méthode `main()`.

- [ ] Démarrer le programme.
- [ ] Appeler automatiquement l'API `GET /api/patients`.
- [ ] Récupérer tous les patients.
- [ ] Parcourir chaque patient.
- [ ] Vérifier si son QR code existe.
- [ ] Générer les QR codes manquants.
- [ ] Enregistrer les images dans le CDN.
- [ ] Afficher le résultat dans la console.

Exemple de résultat attendu :

```text
Démarrage du programme...

Récupération des patients...

Patient 1 : QR code généré.
Patient 2 : QR code déjà existant.
Patient 3 : QR code généré.

Traitement terminé.
```

## 6. Tests du Sprint 1

- [ ] Tester le CRUD Patient.
- [ ] Vérifier la récupération des patients par l'API.
- [ ] Vérifier la communication entre Java autonome et Spring Boot.
- [ ] Vérifier la génération des QR codes.
- [ ] Vérifier le format JPEG.
- [ ] Vérifier la création des sous-répertoires.
- [ ] Vérifier l'accessibilité des images via HTTP.
- [ ] Relancer le programme et vérifier qu'il ne génère pas inutilement les mêmes QR codes.

### Livrable du Sprint 1

- Une base de données MySQL fonctionnelle.
- Un backend Spring Boot avec CRUD Patient.
- Une API REST retournant des données JSON.
- Un programme Java autonome utilisant l'API.
- Des QR codes générés au format JPEG.
- Un répertoire CDN contenant les QR codes des patients.

---

# SPRINT 2 — Pointage des QR codes vers PHP et consultation des patients

## Objectif

Faire en sorte que chaque QR code permette d'accéder à une URL PHP contenant l'identifiant du patient. PHP utilisera cet identifiant pour récupérer les informations du patient depuis l'API Spring Boot.

## 1. Définition de l'URL contenue dans le QR code

- [ ] Définir l'URL de base du serveur PHP.
- [ ] Définir le format de l'URL pour accéder à un patient.
- [ ] Associer dynamiquement l'identifiant du patient à l'URL.

Exemple :

```text
http://localhost:77/patient/9
```

Le QR code contient cette URL et non le chemin du fichier JPEG.

## 2. Modification du générateur QR code

- [ ] Modifier la construction de l'URL dans `QRCodeGenerator`.
- [ ] Utiliser l'URL PHP comme contenu du QR code.
- [ ] Générer les QR codes avec les nouvelles URL.
- [ ] Remplacer les anciens QR codes si nécessaire.
- [ ] Vérifier que chaque QR code pointe vers le bon patient.

## 3. Développement du programme PHP

### 3.1. Création du projet PHP

- [ ] Créer le projet PHP.
- [ ] Configurer le serveur PHP sur le port `77`.
- [ ] Définir une route `/patient/{id}`.
- [ ] Récupérer l'identifiant transmis dans l'URL.

### 3.2. Communication avec Spring Boot

- [ ] Créer un composant PHP chargé de communiquer avec l'API Spring Boot.
- [ ] Construire l'URL de l'API à partir de l'identifiant reçu.
- [ ] Envoyer une requête HTTP GET vers `/api/patients/{id}`.
- [ ] Récupérer la réponse JSON.
- [ ] Vérifier que la réponse contient les informations du patient.

Exemple :

```text
QR code scanné
      |
      v
http://localhost:77/patient/9
      |
      v
PHP récupère l'id = 9
      |
      v
PHP appelle Spring Boot
      |
      v
GET /api/patients/9
      |
      v
Spring Boot retourne JSON
      |
      v
PHP reçoit les informations du patient
```

## 4. Retour des informations au format JSON

- [ ] Récupérer les données retournées par Spring Boot.
- [ ] Vérifier les informations du patient.
- [ ] Retourner les données au format JSON depuis PHP.
- [ ] Définir le header `Content-Type: application/json`.
- [ ] Gérer le cas où l'identifiant n'existe pas.
- [ ] Gérer les erreurs de communication avec Spring Boot.

## 5. Tests du Sprint 2

- [ ] Scanner le QR code d'un patient.
- [ ] Vérifier que le navigateur accède à l'URL PHP.
- [ ] Vérifier que PHP récupère correctement l'identifiant.
- [ ] Vérifier que PHP appelle la bonne API Spring Boot.
- [ ] Vérifier que les informations du patient sont retournées en JSON.
- [ ] Tester avec plusieurs identifiants.
- [ ] Tester avec un identifiant inexistant.
- [ ] Vérifier que chaque QR code pointe vers le bon patient.

### Livrable du Sprint 2

Un système dans lequel chaque patient possède un QR code permettant d'accéder à son URL PHP. PHP récupère l'identifiant du patient, interroge l'API Spring Boot et retourne les informations correspondantes au format JSON.

---

# Récapitulatif des deux sprints

| Sprint   | Travail                                                                                   | Résultat                                                            |
| -------- | ----------------------------------------------------------------------------------------- | -------------------------------------------------------------------- |
| Sprint 1 | MySQL, CRUD Spring Boot, API REST, programme autonome, génération QR code, stockage CDN | Chaque patient possède son QR code JPEG stocké dans le CDN         |
| Sprint 2 | URL PHP, récupération de l'identifiant, appel API Spring Boot, retour JSON              | Le scan du QR code permet de récupérer les informations du patient |

# Architecture générale

```text
                 SPRINT 1
           
                  MySQL
                    |
                    v
              Spring Boot
                    |
               CRUD Patient
                    |
                 API REST
                    |
                    v
             Programme Java
                 autonome
                    |
             GET /api/patients
                    |
                    v
              QRCodeGenerator
                    |
                    v
               QR code JPEG
                    |
                    v
                   CDN


                 SPRINT 2

                QR code
                    |
                    v
              URL vers PHP
                    |
                    v
             PHP récupère ID
                    |
                    v
             Appel API Spring
                    |
                    v
             GET /api/patients/9
                    |
                    v
               MySQL / JSON
                    |
                    v
              Retour JSON PHP
```

**Remarque :** le programme autonome récupère les patients par l'API Spring Boot. Il n'a donc pas besoin d'un accès direct à MySQL pour cette opération. Le repository Spring Boot reste responsable de l'accès à la base de données.
