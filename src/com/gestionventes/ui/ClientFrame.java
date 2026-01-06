package com.gestionventes.ui;

import com.gestionventes.model.Client;
import com.gestionventes.service.ClientService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ClientFrame extends JFrame {

    private final ClientService service;
    private final DefaultListModel<Client> model = new DefaultListModel<>();
    private final JList<Client> list = new JList<>(model);

    public ClientFrame(ClientService service) {
        this.service = service;

        setTitle("Gestion des Clients");
        setSize(800, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        /* ===== TITRE ===== */
        JLabel title = new JLabel("Gestion des Clients", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(new EmptyBorder(15, 10, 15, 10));
        add(title, BorderLayout.NORTH);

        /* ===== LISTE ===== */
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        list.setFixedCellHeight(34);

        // RENDERER PERSONNALISÉ (SANS EMOJI + AVEC ID)
        list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean cellHasFocus) {

                JLabel label = (JLabel) super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus);

                if (value instanceof Client c) {
                    label.setText(
                            "ID: " + c.getId() +
                                    " | Nom: " + c.getNom() +
                                    " | Email: " + c.getEmail() +
                                    " | Téléphone: " + c.getTelephone()
                    );
                }

                label.setBorder(new EmptyBorder(5, 15, 5, 15));
                return label;
            }
        });

        refresh();

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBorder(new EmptyBorder(10, 20, 10, 20));
        add(scrollPane, BorderLayout.CENTER);

        /* ===== BOUTONS ===== */
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));

        JButton btnAdd = createButton("Ajouter", new Color(46, 204, 113));
        JButton btnEdit = createButton("Modifier", new Color(52, 152, 219));
        JButton btnDelete = createButton("Supprimer", new Color(231, 76, 60));
        JButton btnClose = createButton("Fermer", Color.GRAY);

        buttons.add(btnAdd);
        buttons.add(btnEdit);
        buttons.add(btnDelete);
        buttons.add(btnClose);

        add(buttons, BorderLayout.SOUTH);

        /* ===== ACTIONS ===== */
        btnAdd.addActionListener(e -> ajouter());
        btnEdit.addActionListener(e -> modifier());
        btnDelete.addActionListener(e -> supprimer());
        btnClose.addActionListener(e -> dispose());
    }

    private JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(color);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(140, 38));
        return btn;
    }

    private void refresh() {
        model.clear();
        service.lister().forEach(model::addElement);
    }

    private void ajouter() {
        JTextField nom = new JTextField();
        JTextField email = new JTextField();
        JTextField tel = new JTextField();

        Object[] champs = {
                "Nom :", nom,
                "Email :", email,
                "Téléphone :", tel
        };

        int result = JOptionPane.showConfirmDialog(
                this,
                champs,
                "Ajouter un client",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            if (nom.getText().isBlank()) {
                JOptionPane.showMessageDialog(this, "Le nom est obligatoire");
                return;
            }

            Client c = new Client(
                    0,
                    nom.getText().trim(),
                    email.getText().trim(),
                    tel.getText().trim()
            );

            service.ajouter(c);   // ID attribué ici par le DAO
            refresh();

            // Sélection automatique du client ajouté (avec ID visible)
            list.setSelectedValue(c, true);
        }
    }

    private void modifier() {
        Client c = list.getSelectedValue();
        if (c == null) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un client");
            return;
        }

        JTextField nom = new JTextField(c.getNom());
        JTextField email = new JTextField(c.getEmail());
        JTextField tel = new JTextField(c.getTelephone());

        Object[] champs = {
                "Nom :", nom,
                "Email :", email,
                "Téléphone :", tel
        };

        int result = JOptionPane.showConfirmDialog(
                this,
                champs,
                "Modifier le client",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            c.setNom(nom.getText().trim());
            c.setEmail(email.getText().trim());
            c.setTelephone(tel.getText().trim());

            service.modifier(c);
            refresh();
            list.setSelectedValue(c, true);
        }
    }

    private void supprimer() {
        Client c = list.getSelectedValue();
        if (c == null) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un client");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Supprimer le client ID " + c.getId() + " ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            service.supprimer(c.getId());
            refresh();
        }
    }
}
