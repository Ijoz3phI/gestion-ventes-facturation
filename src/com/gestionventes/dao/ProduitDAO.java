package com.gestionventes.dao;

public class ProduitDAO {

     private final ArrayList<Produit> produits = new ArrayList<>();

    public void save(Produit produit) { produits.add(produit); }

    public ArrayList<Produit> findAll() { return produits; }

    public Produit findById(int id) {
        for (Produit p : produits) {
            if (p.getId() == id) return p;
        }
        return null;}
        
    public void delete(Produit produit) { produits.remove(produit); }
}
