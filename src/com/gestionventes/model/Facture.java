package com.gestionventes.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Facture {
    private int id;
    private Client client;
    private LocalDate date;
    private List<LigneFacture> lignes = new ArrayList<>();

    public Facture() {}

    public Facture(int id, Client client, LocalDate date) {
        this.id = id;
        this.client = client;
        this.date = date;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public List<LigneFacture> getLignes() { return lignes; }

    public void ajouterLigne(LigneFacture ligne) {
        this.lignes.add(ligne);
    }

    public double getTotalHT() {
        return lignes.stream().mapToDouble(LigneFacture::getTotalHT).sum();
    }

    public double getTotalTVA() {
        return lignes.stream().mapToDouble(LigneFacture::getTotalTVA).sum();
    }

    public double getTotalTTC() {
        return lignes.stream().mapToDouble(LigneFacture::getTotalTTC).sum();
    }

    @Override
    public String toString() {
        return "Facture{id=" + id +
                ", client=" + (client != null ? client.getNom() : "N/A") +
                ", date=" + date +
                ", totalTTC=" + String.format("%.2f", getTotalTTC()) +
                '}';
    }
}
