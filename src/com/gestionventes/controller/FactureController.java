package com.gestionventes.controller;

import com.gestionventes.model.Facture;
import com.gestionventes.model.LigneFacture;
import com.gestionventes.model.Produit;
import com.gestionventes.service.ClientService;
import com.gestionventes.service.FactureService;
import com.gestionventes.service.ProduitService;
import com.gestionventes.util.Validator;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

public class FactureController {
    private final Scanner scanner;
    private final FactureService factureService;
    private final ClientService clientService;
    private final ProduitService produitService;

    public FactureController(Scanner scanner,
                             FactureService factureService,
                             ClientService clientService,
                             ProduitService produitService) {
        this.scanner = scanner;
        this.factureService = factureService;
        this.clientService = clientService;
        this.produitService = produitService;
    }

    public void menu() {
        int choix;
        do {
            System.out.println("\n--- MENU FACTURES ---");
            System.out.println("1) Lister");
            System.out.println("2) Créer une facture");
            System.out.println("3) Afficher détails facture");
            System.out.println("4) Supprimer facture");
            System.out.println("0) Retour");
            System.out.print("Votre choix : ");

            choix = lireEntier();

            switch (choix) {
                case 1 -> lister();
                case 2 -> creer();
                case 3 -> afficherDetails();
                case 4 -> supprimer();
                case 0 -> {}
                default -> System.out.println("Choix invalide !");
            }
        } while (choix != 0);
    }

    private void lister() {
        System.out.println("\nListe des factures :");
        factureService.lister().forEach(System.out::println);
    }

    private void creer() {
        System.out.println("\n--- Création facture ---");

        // choisir client
        System.out.println("Clients disponibles :");
        clientService.lister().forEach(System.out::println);

        int idClient = lireEntier("ID du client : ");
        Optional<Client> optClient = clientService.trouver(idClient);
        if (optClient.isEmpty()) {
            System.out.println("❌ Client introuvable.");
            return;
        }

        Facture facture = new Facture(0, optClient.get(), LocalDate.now());

        // ajouter des lignes
        while (true) {
            System.out.println("\nProduits disponibles :");
            produitService.lister().forEach(System.out::println);

            int idProd = lireEntier("ID produit (0 pour terminer) : ");
            if (idProd == 0) break;

            Optional<Produit> optProduit = produitService.trouver(idProd);
            if (optProduit.isEmpty()) {
                System.out.println("❌ Produit introuvable.");
                continue;
            }

            int qte = lireQuantite("Quantité : ");
            facture.ajouterLigne(new LigneFacture(optProduit.get(), qte));
            System.out.println("✅ Ligne ajoutée.");
        }

        if (facture.getLignes().isEmpty()) {
            System.out.println("❌ Facture vide. Annulation.");
            return;
        }

        factureService.creer(facture);
        System.out.println("✅ Facture créée : " + facture);
        System.out.println("Total HT  : " + String.format("%.2f", facture.getTotalHT()));
        System.out.println("Total TVA : " + String.format("%.2f", facture.getTotalTVA()));
        System.out.println("Total TTC : " + String.format("%.2f", facture.getTotalTTC()));
    }

    private void afficherDetails() {
        int id = lireEntier("ID facture : ");
        Optional<Facture> opt = factureService.trouver(id);
        if (opt.isEmpty()) {
            System.out.println("❌ Facture introuvable.");
            return;
        }

        Facture f = opt.get();
        System.out.println("\n===== DÉTAIL FACTURE =====");
        System.out.println("ID : " + f.getId());
        System.out.println("Client : " + f.getClient().getNom());
        System.out.println("Date : " + f.getDate());
        System.out.println("\nLignes :");
        f.getLignes().forEach(System.out::println);

        System.out.println("\nTotal HT  : " + String.format("%.2f", f.getTotalHT()));
        System.out.println("Total TVA : " + String.format("%.2f", f.getTotalTVA()));
        System.out.println("Total TTC : " + String.format("%.2f", f.getTotalTTC()));
        System.out.println("==========================");
    }

    private void supprimer() {
        int id = lireEntier("ID facture : ");
        boolean ok = factureService.supprimer(id);
        System.out.println(ok ? "✅ Facture supprimée." : "❌ Facture introuvable.");
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

    private int lireQuantite(String message) {
        while (true) {
            System.out.print(message);
            try {
                int qte = Integer.parseInt(scanner.nextLine().trim());
                if (Validator.estQuantiteValide(qte)) return qte;
                System.out.println("Quantité doit être > 0.");
            } catch (Exception e) {
                System.out.println("Nombre invalide.");
            }
        }
    }
}
