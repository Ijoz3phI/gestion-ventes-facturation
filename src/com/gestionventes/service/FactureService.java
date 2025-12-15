package com.gestionventes.service;


import com.gestionventes.service;
import facturation.dao.FactureDAO;
import model.Client;
import model.Produit;

public class FactureService {

    private FactureDAO factureDAO = new FactureDAO();

    public Facture creerFacture(int id, Client client) {
        return new Facture(id, client);
    }

    public boolean ajouterProduit(Facture facture, Produit produit, int quantite) {
        if (produit.getStock() < quantite) {
            return false;
        }
        facture.ajouterLigne(new LigneFacture(produit, quantite));
        return true;
    }

    public void validerFacture(Facture facture) {
        for (LigneFacture l : facture.getLignes()) {
            Produit p = l.getProduit();
            p.setStock(p.getStock() - l.getQuantite());
        }
        factureDAO.save(facture);
    }
}
