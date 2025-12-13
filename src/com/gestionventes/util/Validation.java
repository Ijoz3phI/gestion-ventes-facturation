package com.gestionventes.util;

public class Validation {
    public static boolean nomValide(String nom) { // en check si les donnee sont juste ou non
        return nom != null && !nom.trim().isEmpty();
    }


    public static boolean emailValide(String email) {
        return email != null && email.contains("@");
    }
}
