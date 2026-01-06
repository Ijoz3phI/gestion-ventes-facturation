package com.gestionventes.ui;

import com.gestionventes.model.*;
import com.gestionventes.service.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;

public class FactureFrame extends JFrame {

    private final FactureService factureService;
    private final ClientService clientService;
    private final ProduitService produitService;

    private final DefaultListModel<Facture> model = new DefaultListModel<>();
    private final JList<Facture> list = new JList<>(model);

    public FactureFrame(FactureService factureService,
                        ClientService clientService,
                        ProduitService produitService) {

        this.factureService = factureService;
        this.clientService = clientService;
        this.produitService = produitService;

        setTitle("Gestion des Factures");
        setSize(950, 540);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        /* ===== TITRE ===== */
        JLabel title = new JLabel("Gestion des Factures", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(new EmptyBorder(20, 10, 20, 10));
        add(title, BorderLayout.NORTH);

        /* ===== LISTE ===== */
        list.setFont(new Font("Consolas", Font.PLAIN, 14));
        list.setFixedCellHeight(36);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {

                JLabel label = (JLabel) super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus);

                if (value instanceof Facture f) {
                    label.setText(String.format(
                            "ID : %-4d   Client : %-20s   Date : %-12s   Total TTC : %.2f",
                            f.getId(),
                            f.getClient().getNom(),
                            f.getDate(),
                            f.getTotalTTC()
                    ));
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                }

                label.setBorder(new EmptyBorder(6, 10, 6, 10));
                return label;
            }
        });

        refresh();

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBorder(new EmptyBorder(10, 20, 10, 20));
        add(scrollPane, BorderLayout.CENTER);

        /* ===== BOUTONS (COMPLET) ===== */
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JButton btnCreate = createButton("Créer facture", new Color(52, 152, 219));
        JButton btnDetails = createButton("Voir détails", new Color(46, 204, 113));
        JButton btnDelete = createButton("Supprimer facture", new Color(231, 76, 60));
        JButton btnClose = createButton("Fermer", Color.GRAY);

        buttons.add(btnCreate);
        buttons.add(btnDetails);
        buttons.add(btnDelete);
        buttons.add(btnClose);

        add(buttons, BorderLayout.SOUTH);

        /* ===== ACTIONS ===== */
        btnCreate.addActionListener(e -> creerFacture());
        btnDetails.addActionListener(e -> details());
        btnDelete.addActionListener(e -> supprimerFacture());
        btnClose.addActionListener(e -> dispose());
    }

    /* ===== STYLE BOUTON ===== */
    private JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setForeground(Color.WHITE);
        btn.setBackground(color);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(180, 42));
        return btn;
    }

    private void refresh() {
        model.clear();
        factureService.lister().forEach(model::addElement);
    }

    /* ===== CRÉER FACTURE ===== */
    private void creerFacture() {

        Client client = (Client) JOptionPane.showInputDialog(
                this,
                "Choisir un client",
                "Client",
                JOptionPane.PLAIN_MESSAGE,
                null,
                clientService.lister().toArray(),
                null
        );

        if (client == null) return;

        Facture facture = new Facture(0, client, LocalDate.now());

        while (true) {
            Produit produit = (Produit) JOptionPane.showInputDialog(
                    this,
                    "Choisir un produit (Annuler pour terminer)",
                    "Produit",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    produitService.lister().toArray(),
                    null
            );

            if (produit == null) break;

            String qteStr = JOptionPane.showInputDialog(this, "Quantité :");
            if (qteStr == null) break;

            int qte;
            try {
                qte = Integer.parseInt(qteStr);
                if (qte <= 0) throw new NumberFormatException();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Quantité invalide");
                continue;
            }

            facture.ajouterLigne(new LigneFacture(produit, qte));
        }

        if (facture.getLignes().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Facture vide. Annulation.");
            return;
        }

        factureService.creer(facture);
        refresh();
        list.setSelectedValue(facture, true);
    }

    /* ===== DÉTAILS FACTURE ===== */
    private void details() {
        Facture f = list.getSelectedValue();
        if (f == null) {
            JOptionPane.showMessageDialog(this, "Sélectionnez une facture");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (LigneFacture l : f.getLignes()) {
            sb.append(l).append("\n");
        }

        sb.append("\n--------------------------------");
        sb.append("\nTotal HT  : ").append(String.format("%.2f", f.getTotalHT()));
        sb.append("\nTotal TVA : ").append(String.format("%.2f", f.getTotalTVA()));
        sb.append("\nTotal TTC : ").append(String.format("%.2f", f.getTotalTTC()));

        JOptionPane.showMessageDialog(
                this,
                sb.toString(),
                "Détails de la facture",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /* ===== SUPPRIMER FACTURE ===== */
    private void supprimerFacture() {
        Facture f = list.getSelectedValue();
        if (f == null) {
            JOptionPane.showMessageDialog(this, "Sélectionnez une facture");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Supprimer la facture ID " + f.getId() + " ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            factureService.supprimer(f.getId());
            refresh();
        }
    }
}
