package com.gestionventes.controller;

import com.gestionventes.model.Client;
import com.gestionventes.service.ClientService;
import com.gestionventes.util.Validator;

import java.util.Optional;
import java.util.Scanner;

public class ClientController {
    private final Scanner scanner;
    private final ClientService service;

    public ClientController(Scanner scanner, ClientService service) {
        this.scanner = scanner;
        this.service = service;
    }

    public void menu() {
        int choix;
        do {
            System.out.println("\n--- MENU CLIENTS ---");
            System.out.println("1) Lister");
            System.out.println("2) Ajouter");
            System.out.println("3) Modifier");
            System.out.println("4) Supprimer");
            System.out.println("0) Retour");
            System.out.print("Votre choix : ");

            choix = lireEntier();

            switch (choix) {
                case 1 -> lister();
                case 2 -> ajouter();
                case 3 -> modifier();
                case 4 -> supprimer();
                case 0 -> {}
                default -> System.out.println("Choix invalide !");
            }
        } while (choix != 0);
    }

    private void lister() {
        System.out.println("\nListe des clients :");
        service.lister().forEach(System.out::println);
    }

    private void ajouter() {
        System.out.println("\n--- Ajout client ---");
        String nom = lireTexteObligatoire("Nom : ");
        String email = lireEmail("Email : ");
        String tel = lireTelephone("Téléphone (chiffres) : ");

        Client c = new Client(0, nom, email, tel);
        service.ajouter(c);
        System.out.println("✅ Client ajouté : " + c);
    }

    private void modifier() {
        System.out.println("\n--- Modification client ---");
        int id = lireEntier("ID client : ");
        Optional<Client> opt = service.trouver(id);
        if (opt.isEmpty()) {
            System.out.println("❌ Client introuvable.");
            return;
        }

        Client c = opt.get();
        System.out.println("Client actuel : " + c);

        String nom = lireTexteObligatoire("Nouveau nom : ");
        String email = lireEmail("Nouveau email : ");
        String tel = lireTelephone("Nouveau téléphone : ");

        c.setNom(nom);
        c.setEmail(email);
        c.setTelephone(tel);

        if (service.modifier(c)) {
            System.out.println("✅ Client modifié : " + c);
        } else {
            System.out.println("❌ Échec modification.");
        }
    }

    private void supprimer() {
        System.out.println("\n--- Suppression client ---");
        int id = lireEntier("ID client : ");
        boolean ok = service.supprimer(id);
        System.out.println(ok ? "✅ Client supprimé." : "❌ Client introuvable.");
    }

    // Helpers saisie
    private int lireEntier() {
        return lireEntier("");
    }

    private int lireEntier(String message) {
        while (true) {
            if (!message.isBlank()) System.out.print(message);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Entrée invalide.");
            }
        }
    }

    private String lireTexteObligatoire(String message) {
        while (true) {
            System.out.print(message);
            String s = scanner.nextLine();
            if (Validator.estNonVide(s)) return s.trim();
            System.out.println("Champ obligatoire.");
        }
    }

    private String lireEmail(String message) {
        while (true) {
            System.out.print(message);
            String email = scanner.nextLine().trim();
            if (Validator.estEmailValide(email)) return email;
            System.out.println("Email invalide.");
        }
    }

    private String lireTelephone(String message) {
        while (true) {
            System.out.print(message);
            String tel = scanner.nextLine().trim();
            if (Validator.estTelephoneValide(tel)) return tel;
            System.out.println("Téléphone invalide (9 à 15 chiffres).");
        }
    }
}
