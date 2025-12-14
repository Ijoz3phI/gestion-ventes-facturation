package facturation.model;

import model.Client;
import java.util.ArrayList;

public class Facture {
    private int id;
    private Client client;
    private ArrayList<LigneFacture> lignes;
    public static final double TVA = 0.20;

    public Facture(int id, Client client) {
        this.id = id;
        this.client = client;
        this.lignes = new ArrayList<>();
    }

    public void ajouterLigne(LigneFacture ligne) {
        lignes.add(ligne);
    }

    public double calculTotalHT() {
        double total = 0;
        for (LigneFacture l : lignes) {
            total += l.getTotalHT();
        }
        return total;
    }

    public double calculTVA() {
        return calculTotalHT() * TVA;
    }

    public double calculTotalTTC() {
        return calculTotalHT() + calculTVA();
    }

    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public ArrayList<LigneFacture> getLignes() {
        return lignes;
    }
}
