package com.gestionventes.util;

public class Validator {
    
 public static void verifierPrix(double prix) {
        if (prix < 0) {
            throw new IllegalArgumentException("Le prix ne peut pas être négatif");
        }
    }

    public static void verifierStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("Le stock ne peut pas être négatif");
        }
    }
}
