package com.gestionventes.model;

public class LigneFacture {
    private Produit produit;
    private int quantite;

    public LigneFacture() {}

    public LigneFacture(Produit produit, int quantite) {
        this.produit = produit;
        this.quantite = quantite;
    }

    public Produit getProduit() { return produit; }
    public void setProduit(Produit produit) { this.produit = produit; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    public double getTotalHT() {
        return produit.getPrixHT() * quantite;
    }

    public double getTotalTVA() {
        return produit.getPrixHT() * produit.getTva() * quantite;
    }

    public double getTotalTTC() {
        return produit.getPrixTTC() * quantite;
    }

    @Override
    public String toString() {
        return "- " + produit.getLibelle() +
                " | Qté=" + quantite +
                " | PU HT=" + String.format("%.2f", produit.getPrixHT()) +
                " | TVA=" + (int)(produit.getTva() * 100) + "%" +
                " | Total TTC=" + String.format("%.2f", getTotalTTC());
    }
}
