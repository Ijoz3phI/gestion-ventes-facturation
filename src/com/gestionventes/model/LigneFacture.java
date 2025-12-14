package com.gestionventes.model;

public class LigneFacture {

    private Produit produit;
    private int quantite;
    private double prixUnitaire;

    public LigneFacture(Produit produit, int quantite) {
        this.produit = produit;
        this.quantite = quantite;
        this.prixUnitaire = produit.getPrix();
    }

    public double getTotalHT() {
        return prixUnitaire * quantite;
    }

    public Produit getProduit() {
        return produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }
}
