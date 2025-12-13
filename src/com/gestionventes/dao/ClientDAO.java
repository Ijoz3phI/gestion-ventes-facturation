package com.gestionventes.dao;

import com.gestionventes.model.Client;
import java.util.ArrayList;

public class ClientDAO { // cette class est pour la gestion des accee des donnee
    private ArrayList<Client> clients = new ArrayList<>();
    private int compteurId = 1;

    public Client ajouter(Client client) { //ajout d un nouveau client en incrementant son id
        Client nouveauClient = new Client(
                compteurId++,
                client.getNom(),
                client.getPrenom(),
                client.getTelephone(),
                client.getAdresse(),
                client.getEmail()
        );
        clients.add(nouveauClient);
        return nouveauClient;
    }

    public ArrayList<Client> findAll() {
        return new ArrayList<>(clients); //ici je retourne une copie pour proteger mes clients
    }

    public Client findById(int id) {
        for (Client c : clients) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public boolean supprimer(int id) {
        Client c = findById(id);
        if (c != null) {
            clients.remove(c);
            return true;
        }
        return false;
    }
    public Client mettreAJour(Client clientModifie) { // mise ajour a mes clients
        for (int i = 0; i < clients.size(); i++) {
            Client c = clients.get(i);
            if (c.getId() == clientModifie.getId()) {
                c.setNom(clientModifie.getNom());
                c.setPrenom(clientModifie.getPrenom());
                c.setTelephone(clientModifie.getTelephone());
                c.setAdresse(clientModifie.getAdresse());
                c.setEmail(clientModifie.getEmail());
                return c;
            }
        }
        return null; // Client non trouvé
    }

    public ArrayList<Client> findByNom(String nom) {
        ArrayList<Client> resultats = new ArrayList<>();
        for (Client c : clients) {
            if (c.getNom().equalsIgnoreCase(nom)) {
                resultats.add(c);
            }
        }
        return resultats;
    }
    public void clear() {
        clients.clear();
        compteurId = 1;
    }
}