package com.gestionventes.model;

public class Produit {

    private int id;
    private String libelle;
    private double prix;
    private int stock;

    public Produit(int id, String libelle, double prix, int stock) {
        this.id = id;
        this.libelle = libelle;
        this.prix = prix;
        this.stock = stock;
    }

    public int getId() { return id; }
    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

     @Override
    public String toString() {
        return "ID=" + id + " | Libelle=" + libelle + " | Prix=" + prix + " | Stock=" + stock;
    }
}
