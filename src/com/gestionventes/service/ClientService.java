package com.gestionventes.service;

import com.gestionventes.dao.ClientDAO;
import com.gestionventes.model.Client;

import java.util.List;
import java.util.Optional;

public class ClientService {
    private final ClientDAO dao;

    public ClientService(ClientDAO dao) {
        this.dao = dao;
    }

    public List<Client> lister() {
        return dao.trouverTous();
    }

    public Optional<Client> trouver(int id) {
        return dao.trouverParId(id);
    }

    public Client ajouter(Client c) {
        return dao.ajouter(c);
    }

    public boolean modifier(Client c) {
        return dao.modifier(c);
    }

    public boolean supprimer(int id) {
        return dao.supprimer(id);
    }
}
