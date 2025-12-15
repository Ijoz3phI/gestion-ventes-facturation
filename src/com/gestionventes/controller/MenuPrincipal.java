package com.gestionventes.controller;

import com.gestionventes.dao.ClientDAO;
import com.gestionventes.dao.FactureDAO;
import com.gestionventes.dao.ProduitDAO;
import com.gestionventes.service.ClientService;
import com.gestionventes.service.FactureService;
import com.gestionventes.service.ProduitService;

import java.util.Scanner;

public class MenuPrincipal {

    private final Scanner scanner = new Scanner(System.in);

    // DAO
    private final ClientDAO clientDAO = new ClientDAO();
    private final ProduitDAO produitDAO = new ProduitDAO();
    private final FactureDAO factureDAO = new FactureDAO();

    // Services
    private final ClientService clientService = new ClientService(clientDAO);
    private final ProduitService produitService = new ProduitService(produitDAO);
    private final FactureService factureService = new FactureService(factureDAO);

    // Controllers
    private final ClientController clientController = new ClientController(scanner, clientService);
    private final ProduitController produitController = new ProduitController(scanner, produitService);
    private final FactureController factureController = new FactureController(scanner, factureService, clientService, produitService);

    public void demarrer() {
        int choix;
        do {
            System.out.println("\n==============================");
            System.out.println("  APPLICATION GESTION VENTES");
            System.out.println("==============================");
            System.out.println("1) Gestion des clients");
            System.out.println("2) Gestion des produits");
            System.out.println("3) Gestion des factures");
            System.out.println("0) Quitter");
            System.out.print("Votre choix : ");

            choix = lireEntier();

            switch (choix) {
                case 1 -> clientController.menu();
                case 2 -> produitController.menu();
                case 3 -> factureController.menu();
                case 0 -> System.out.println("Au revoir 👋");
                default -> System.out.println("Choix invalide !");
            }
        } while (choix != 0);
    }

    private int lireEntier() {
        while (true) {
            try {
                String s = scanner.nextLine();
                return Integer.parseInt(s.trim());
            } catch (Exception e) {
                System.out.print("Entrée invalide. Réessayez : ");
            }
        }
    }
}
