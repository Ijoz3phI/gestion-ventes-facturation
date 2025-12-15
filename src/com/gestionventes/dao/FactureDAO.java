package com.gestionventes.dao;

import com.gestionventes.model.Facture;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FactureDAO {
    private final List<Facture> factures = new ArrayList<>();
    private int sequenceId = 1;

    public List<Facture> trouverTous() {
        return new ArrayList<>(factures);
    }

    public Optional<Facture> trouverParId(int id) {
        return factures.stream().filter(f -> f.getId() == id).findFirst();
    }

    public Facture ajouter(Facture f) {
        f.setId(sequenceId++);
        factures.add(f);
        return f;
    }

    public boolean supprimer(int id) {
        return factures.removeIf(f -> f.getId() == id);
    }
}
