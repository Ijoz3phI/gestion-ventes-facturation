<<<<<<< HEAD
package com.gestionventes.dao;
=======
package com.gestionventes.dao; //modification du package
>>>>>>> a9e2029f69679102f2378cebf11c646bd35649dc

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
