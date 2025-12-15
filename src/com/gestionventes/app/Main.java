package com.gestionventes.app;

import com.gestionventes.controller.MenuPrincipal;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bonjour dans l application de gestion de vente");
        new MenuPrincipal().demarrer();
    }
}
