package com.gestionventes.controller;


import com.gestionventes.controller.*;
import facturation.service.FactureService;
import model.Client;
import model.Produit;

import java.util.ArrayList;
import java.util.Scanner;

public class FactureController {

    private FactureService service = new FactureService();
    private Scanner sc = new Scanner(System.in);

    // Ces listes viennent normalement d'autres modules
    private ArrayList<Client> clients;
    private ArrayList<Produit> produits;

    public FactureController(ArrayList<Client> clients, ArrayList<Produit> produits) {
        this.clients = clients;
        this.produits = produits;
    }

    public void creerFacture() {
        System.out.print("ID Facture : ");
        int idFacture = sc.nextInt();

        Client client = choisirClient();
        Facture facture = service.creerFacture(idFacture, client);

        boolean continuer = true;
        while (continuer) {
            Produit produit = choisirProduit();
            System.out.print("Quantité : ");
            int qte = sc.nextInt();

            if (!service.ajouterProduit(facture, produit, qte)) {
                System.out.println("❌ Stock insuffisant !");
            }

            System.out.print("Ajouter un autre produit ? (1=Oui / 0=Non) : ");
            continuer = sc.nextInt() == 1;
        }

        service.validerFacture(facture);
        afficherFacture(facture);
    }

    private Client choisirClient() {
        System.out.println("Liste des clients :");
        for (Client c : clients) {
            System.out.println(c.getId() + " - " + c.getNom());
        }

        System.out.print("Choisir ID client : ");
        int id = sc.nextInt();

        for (Client c : clients) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    private Produit choisirProduit() {
        System.out.println("Liste des produits :");
        for (Produit p : produits) {
            System.out.println(p.getId() + " - " + p.getNom() +
                    " | Prix: " + p.getPrix() +
                    " | Stock: " + p.getStock());
        }

        System.out.print("Choisir ID produit : ");
        int id = sc.nextInt();

        for (Produit p : produits) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    private void afficherFacture(Facture f) {
        System.out.println("\n===== FACTURE =====");
        System.out.println("Client : " + f.getClient().getNom());

        for (LigneFacture l : f.getLignes()) {
            System.out.println(
                l.getProduit().getNom() +
                " | Qte: " + l.getQuantite() +
                " | PU: " + l.getPrixUnitaire() +
                " | Total: " + l.getTotalHT()
            );
        }

        System.out.println("-------------------");
        System.out.println("Total HT : " + f.calculTotalHT());
        System.out.println("TVA (20%) : " + f.calculTVA());
        System.out.println("Total TTC : " + f.calculTotalTTC());
    }
}
