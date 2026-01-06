package com.gestionventes.ui;

import com.gestionventes.dao.*;
import com.gestionventes.service.*;

public class UIFactory {

    public static ClientService clientService() {
        return new ClientService(new ClientDAO());
    }

    public static ProduitService produitService() {
        return new ProduitService(new ProduitDAO());
    }

    public static FactureService factureService() {
        return new FactureService(new FactureDAO());
    }
}
