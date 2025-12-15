package com.gestionventes.service;

import com.gestionventes.dao.ProduitDAO;
import com.gestionventes.model.Produit;
import java.util.List;
import java.util.Optional;

public class ProduitService {
    private final ProduitDAO dao;

    public ProduitService(ProduitDAO dao) {
        this.dao = dao;
    }

    public List<Produit> lister() {
        return dao.trouverTous();
    }

    public Optional<Produit> trouver(int id) {
        return dao.trouverParId(id);
    }

    public Produit ajouter(Produit p) {
        return dao.ajouter(p);
    }

    public boolean modifier(Produit p) {
        return dao.modifier(p);
    }

    public boolean supprimer(int id) {
        return dao.supprimer(id);
    }
}
