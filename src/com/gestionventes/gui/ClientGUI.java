package com.gestionventes.gui;

import com.gestionventes.model.Client;
import com.gestionventes.service.ClientService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClientGUI extends JFrame {

    private final ClientService service;
    private final DefaultTableModel tableModel;
    private final JTable table;

    public ClientGUI(ClientService service) {
        this.service = service;

        setTitle("Gestion Clients");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Nom", "Email", "Téléphone"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Buttons
        JPanel panel = new JPanel();
        JButton addBtn = new JButton("Ajouter");
        JButton editBtn = new JButton("Modifier");
        JButton deleteBtn = new JButton("Supprimer");
        panel.add(addBtn);
        panel.add(editBtn);
        panel.add(deleteBtn);
        add(panel, BorderLayout.SOUTH);

        // Load data
        refreshTable();

        // Button actions
        addBtn.addActionListener(e -> ajouterClient());
        editBtn.addActionListener(e -> modifierClient());
        deleteBtn.addActionListener(e -> supprimerClient());
    }

    private void refreshTable() {
        tableModel.setRowCount(0); // clear table
        List<Client> clients = service.lister();
        for (Client c : clients) {
            tableModel.addRow(new Object[]{c.getId(), c.getNom(), c.getEmail(), c.getTelephone()});
        }
    }

    private void ajouterClient() {
        String nom = JOptionPane.showInputDialog(this, "Nom :");
        String email = JOptionPane.showInputDialog(this, "Email :");
        String tel = JOptionPane.showInputDialog(this, "Téléphone :");
        if (nom != null && email != null && tel != null) {
            service.ajouter(new Client(0, nom, email, tel));
            refreshTable();
        }
    }

    private void modifierClient() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        int id = (int) tableModel.getValueAt(row, 0);
        Client c = service.trouver(id).orElse(null);
        if (c == null) return;

        String nom = JOptionPane.showInputDialog(this, "Nom :", c.getNom());
        String email = JOptionPane.showInputDialog(this, "Email :", c.getEmail());
        String tel = JOptionPane.showInputDialog(this, "Téléphone :", c.getTelephone());

        if (nom != null && email != null && tel != null) {
            c.setNom(nom);
            c.setEmail(email);
            c.setTelephone(tel);
            service.modifier(c);
            refreshTable();
        }
    }

    private void supprimerClient() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        int id = (int) tableModel.getValueAt(row, 0);
        service.supprimer(id);
        refreshTable();
    }
}
