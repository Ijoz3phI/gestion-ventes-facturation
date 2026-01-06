package com.gestionventes.app;

import com.gestionventes.ui.*;

import javax.swing.*;

public class MainUI {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            // Services (partagés via UIFactory)
            var clientService = UIFactory.clientService();
            var produitService = UIFactory.produitService();
            var factureService = UIFactory.factureService();

            // Fenêtre principale
            MainFrame frame = new MainFrame();

            // Navigation (via getters → PROPRE)
            frame.getBtnClients().addActionListener(e ->
                    new ClientFrame(clientService).setVisible(true)
            );

            frame.getBtnProduits().addActionListener(e ->
                    new ProduitFrame(produitService).setVisible(true)
            );

            frame.getBtnFactures().addActionListener(e ->
                    new FactureFrame(
                            factureService,
                            clientService,
                            produitService
                    ).setVisible(true)
            );

            frame.getBtnQuitter().addActionListener(e ->
                    System.exit(0)
            );

            frame.setVisible(true);
        });
    }
}
