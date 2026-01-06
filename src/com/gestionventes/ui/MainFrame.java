package com.gestionventes.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainFrame extends JFrame {

    private final JButton btnClients = new JButton("Gestion des Clients");
    private final JButton btnProduits = new JButton("Gestion des Produits");
    private final JButton btnFactures = new JButton("Gestion des Factures");
    private final JButton btnQuitter = new JButton("Quitter");

    public MainFrame() {
        setTitle("Application de Gestion des Ventes");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        /* ===== TITRE ===== */
        JLabel title = new JLabel("Application de Gestion des Ventes", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(new EmptyBorder(30, 10, 30, 10));
        add(title, BorderLayout.NORTH);

        /* ===== BOUTONS CENTRAUX ===== */
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(4, 1, 15, 15));
        center.setBorder(new EmptyBorder(20, 120, 20, 120));

        styleButton(btnClients, new Color(52, 152, 219));
        styleButton(btnProduits, new Color(46, 204, 113));
        styleButton(btnFactures, new Color(155, 89, 182));
        styleButton(btnQuitter, Color.GRAY);

        center.add(btnClients);
        center.add(btnProduits);
        center.add(btnFactures);
        center.add(btnQuitter);

        add(center, BorderLayout.CENTER);
    }

    private void styleButton(JButton btn, Color color) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setForeground(Color.WHITE);
        btn.setBackground(color);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(200, 45));
    }

    /* ===== GETTERS POUR MainUI ===== */
    public JButton getBtnClients() {
        return btnClients;
    }

    public JButton getBtnProduits() {
        return btnProduits;
    }

    public JButton getBtnFactures() {
        return btnFactures;
    }

    public JButton getBtnQuitter() {
        return btnQuitter;
    }
}
