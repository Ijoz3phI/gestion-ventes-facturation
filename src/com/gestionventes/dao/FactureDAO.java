package com.gestionventes.dao; //modification du package

import com.gestionventes.model.Facture; //modification de l import
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