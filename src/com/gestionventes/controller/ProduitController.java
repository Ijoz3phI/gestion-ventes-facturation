package com.gestionventes.controller;

import com.gestionventes.model.Produit;
import com.gestionventes.service.ProduitService;
import com.gestionventes.util.Validator;

import java.util.Optional;
import java.util.Scanner;

public class ProduitController {
    private final Scanner scanner;
    private final ProduitService service;

    public ProduitController(Scanner scanner, ProduitService service) {
        this.scanner = scanner;
        this.service = service;
    }

    public void menu() {
        int choix;
        do {
            System.out.println("\n--- MENU PRODUITS ---");
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
        System.out.println("\nListe des produits :");
        service.lister().forEach(System.out::println);
    }

    private void ajouter() {
        System.out.println("\n--- Ajout produit ---");
        String libelle = lireTexteObligatoire("Libellé : ");
        double prixHT = lireDoublePositif("Prix HT : ");
        double tva = lireTVA("TVA (ex: 0.20 pour 20%) : ");

        Produit p = new Produit(0, libelle, prixHT, tva);
        service.ajouter(p);
        System.out.println("✅ Produit ajouté : " + p);
    }

    private void modifier() {
        System.out.println("\n--- Modification produit ---");
        int id = lireEntier("ID produit : ");
        Optional<Produit> opt = service.trouver(id);
        if (opt.isEmpty()) {
            System.out.println("❌ Produit introuvable.");
            return;
        }

        Produit p = opt.get();
        System.out.println("Produit actuel : " + p);

        String libelle = lireTexteObligatoire("Nouveau libellé : ");
        double prixHT = lireDoublePositif("Nouveau prix HT : ");
        double tva = lireTVA("Nouvelle TVA : ");

        p.setLibelle(libelle);
        p.setPrixHT(prixHT);
        p.setTva(tva);

        if (service.modifier(p)) {
            System.out.println("✅ Produit modifié : " + p);
        } else {
            System.out.println("❌ Échec modification.");
        }
    }

    private void supprimer() {
        System.out.println("\n--- Suppression produit ---");
        int id = lireEntier("ID produit : ");
        boolean ok = service.supprimer(id);
        System.out.println(ok ? "✅ Produit supprimé." : "❌ Produit introuvable.");
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

    private double lireDoublePositif(String message) {
        while (true) {
            System.out.print(message);
            try {
                double v = Double.parseDouble(scanner.nextLine().trim());
                if (Validator.estPrixValide(v)) return v;
                System.out.println("Valeur doit être >= 0.");
            } catch (Exception e) {
                System.out.println("Nombre invalide.");
            }
        }
    }

    private double lireTVA(String message) {
        while (true) {
            System.out.print(message);
            try {
                double tva = Double.parseDouble(scanner.nextLine().trim());
                if (Validator.estTvaValide(tva)) return tva;
                System.out.println("TVA invalide. Exemple : 0.20 (20%).");
            } catch (Exception e) {
                System.out.println("Nombre invalide.");
            }
        }
    }
} 