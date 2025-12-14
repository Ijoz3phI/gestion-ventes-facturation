package com.gestionventes.dao;

import facturation.model.Facture;
import java.util.ArrayList;

public class FactureDAO {
    private ArrayList<Facture> factures = new ArrayList<>();

    public void save(Facture facture) {
        factures.add(facture);
    }

    public ArrayList<Facture> findAll() {
        return factures;
    }
}
