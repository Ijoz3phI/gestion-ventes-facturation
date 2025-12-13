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
}
