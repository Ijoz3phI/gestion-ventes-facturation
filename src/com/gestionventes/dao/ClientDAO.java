package com.gestionventes.dao;

import com.gestionventes.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDAO {
    private final List<Client> clients = new ArrayList<>();
    private int sequenceId = 1;

    public ClientDAO() {
        // Données de démo
        ajouter(new Client(0, "Ahmed El Amrani", "ahmed@gmail.com", "0612345678"));
        ajouter(new Client(0, "Sara Benali", "sara@gmail.com", "0622334455"));
    }

    public List<Client> trouverTous() {
        return new ArrayList<>(clients);
    }

    public Optional<Client> trouverParId(int id) {
        return clients.stream().filter(c -> c.getId() == id).findFirst();
    }

    public Client ajouter(Client c) {
        c.setId(sequenceId++);
        clients.add(c);
        return c;
    }

    public boolean modifier(Client c) {
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getId() == c.getId()) {
                clients.set(i, c);
                return true;
            }
        }
        return false;
    }

    public boolean supprimer(int id) {
        return clients.removeIf(c -> c.getId() == id);
    }
}
