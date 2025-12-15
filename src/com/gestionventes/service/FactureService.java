package com.gestionventes.service;

import com.gestionventes.dao.FactureDAO;
import com.gestionventes.model.Facture;
import java.util.List;
import java.util.Optional;

public class FactureService {
    private final FactureDAO dao;

    public FactureService(FactureDAO dao) {
        this.dao = dao;
    }

    public List<Facture> lister() {
        return dao.trouverTous();
    }

    public Optional<Facture> trouver(int id) {
        return dao.trouverParId(id);
    }

    public Facture creer(Facture f) {
        return dao.ajouter(f);
    }

    public boolean supprimer(int id) {
        return dao.supprimer(id);
    }
}
