package com.gestionventes.app;

import com.gestionventes.dao.ClientDAO;
import com.gestionventes.service.ClientService;
import com.gestionventes.gui.ClientGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        ClientService service = new ClientService(new ClientDAO());
        SwingUtilities.invokeLater(() -> new ClientGUI(service).setVisible(true));
    }
}
