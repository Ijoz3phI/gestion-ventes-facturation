package com.gestionventes.model;

public class Produit {

    private int id;
    private String libelle;
    private double prixHT;
    private double tva; // ex: 0.20 = 20%

    public Produit() {}

    public Produit(int id, String libelle, double prixHT, double tva) {
        this.id = id;
        this.libelle = libelle;
        this.prixHT = prixHT;
        this.tva = tva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getPrixHT() {
        return prixHT;
    }

    public void setPrixHT(double prixHT) {
        this.prixHT = prixHT;
    }

    public double getTva() {
        return tva;
    }

    public void setTva(double tva) {
        this.tva = tva;
    }

    public double getPrixTTC() {
        return prixHT * (1 + tva);
    }

    @Override
    public String toString() {
        return "ID=" + id +
                " | " + libelle +
                " | PU HT=" + String.format("%.2f", prixHT) +
                " | TVA=" + (int)(tva * 100) + "%" +
                " | PU TTC=" + String.format("%.2f", getPrixTTC());
    }
}
