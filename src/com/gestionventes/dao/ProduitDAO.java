package com.gestionventes.dao;

import com.gestionventes.model.Produit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProduitDAO {
    private final List<Produit> produits = new ArrayList<>();
    private int sequenceId = 1;

    public ProduitDAO() {
        // Données de démo
        ajouter(new Produit(0, "Clavier", 120.0, 0.20));
        ajouter(new Produit(0, "Souris", 60.0, 0.20));
        ajouter(new Produit(0, "Écran 24\"", 1500.0, 0.20));
    }

    public List<Produit> trouverTous() {
        return new ArrayList<>(produits);
    }

    public Optional<Produit> trouverParId(int id) {
        return produits.stream().filter(p -> p.getId() == id).findFirst();
    }

    public Produit ajouter(Produit p) {
        p.setId(sequenceId++);
        produits.add(p);
        return p;
    }

    public boolean modifier(Produit p) {
        for (int i = 0; i < produits.size(); i++) {
            if (produits.get(i).getId() == p.getId()) {
                produits.set(i, p);
                return true;
            }
        }
        return false;
    }

    public boolean supprimer(int id) {
        return produits.removeIf(p -> p.getId() == id);
    }
} 
