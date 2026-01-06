package com.gestionventes.ui;

import com.gestionventes.model.Produit;
import com.gestionventes.service.ProduitService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProduitFrame extends JFrame {

    private final ProduitService service;
    private final DefaultListModel<Produit> model = new DefaultListModel<>();
    private final JList<Produit> list = new JList<>(model);

    public ProduitFrame(ProduitService service) {
        this.service = service;

        setTitle("Gestion des Produits");
        setSize(900, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Gestion des Produits", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(new EmptyBorder(15, 10, 15, 10));
        add(title, BorderLayout.NORTH);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFont(new Font("Consolas", Font.PLAIN, 14));
        list.setFixedCellHeight(36);

        list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {

                JLabel label = (JLabel) super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus);

                if (value instanceof Produit p) {
                    label.setText(String.format(
                            "ID : %-4d   Libellé : %-20s   Prix HT : %-10.2f   TVA : %-6.0f%%   Prix TTC : %.2f",
                            p.getId(),
                            p.getLibelle(),
                            p.getPrixHT(),
                            p.getTva() * 100,
                            p.getPrixTTC()
                    ));
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                }
                label.setBorder(new EmptyBorder(6, 10, 6, 10));
                return label;
            }
        });

        refresh();

        add(new JScrollPane(list), BorderLayout.CENTER);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        JButton add = btn("Ajouter", new Color(46, 204, 113));
        JButton edit = btn("Modifier", new Color(52, 152, 219));
        JButton del = btn("Supprimer", new Color(231, 76, 60));
        JButton close = btn("Fermer", Color.GRAY);

        buttons.add(add);
        buttons.add(edit);
        buttons.add(del);
        buttons.add(close);
        add(buttons, BorderLayout.SOUTH);

        add.addActionListener(e -> ajouter());
        edit.addActionListener(e -> modifier());
        del.addActionListener(e -> supprimer());
        close.addActionListener(e -> dispose());
    }

    private JButton btn(String t, Color c) {
        JButton b = new JButton(t);
        b.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b.setForeground(Color.WHITE);
        b.setBackground(c);
        b.setPreferredSize(new Dimension(150, 40));
        b.setFocusPainted(false);
        return b;
    }

    private void refresh() {
        model.clear();
        service.lister().forEach(model::addElement);
    }

    private void ajouter() {
        JTextField lib = new JTextField();
        JTextField prix = new JTextField();
        JTextField tva = new JTextField();

        Object[] f = {
                "Libellé :", lib,
                "Prix HT :", prix,
                "TVA (ex 0.20) :", tva
        };

        if (JOptionPane.showConfirmDialog(this, f, "Ajouter produit",
                JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {

            Produit p = new Produit(0,
                    lib.getText(),
                    Double.parseDouble(prix.getText()),
                    Double.parseDouble(tva.getText())
            );
            service.ajouter(p);
            refresh();
            list.setSelectedValue(p, true);
        }
    }

    private void modifier() {
        Produit p = list.getSelectedValue();
        if (p == null) return;

        JTextField lib = new JTextField(p.getLibelle());
        JTextField prix = new JTextField(String.valueOf(p.getPrixHT()));
        JTextField tva = new JTextField(String.valueOf(p.getTva()));

        Object[] f = {
                "Libellé :", lib,
                "Prix HT :", prix,
                "TVA :", tva
        };

        if (JOptionPane.showConfirmDialog(this, f, "Modifier produit",
                JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {

            p.setLibelle(lib.getText());
            p.setPrixHT(Double.parseDouble(prix.getText()));
            p.setTva(Double.parseDouble(tva.getText()));
            service.modifier(p);
            refresh();
        }
    }

    private void supprimer() {
        Produit p = list.getSelectedValue();
        if (p != null && JOptionPane.showConfirmDialog(this,
                "Supprimer le produit ID " + p.getId() + " ?",
                "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            service.supprimer(p.getId());
            refresh();
        }
    }
}
