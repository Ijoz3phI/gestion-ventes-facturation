package com.gestionventes.util;

public class Validator {

    public static boolean estNonVide(String s) {
        return s != null && !s.trim().isEmpty();
    }

    public static boolean estEmailValide(String email) {
        if (!estNonVide(email)) return false;
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public static boolean estTelephoneValide(String tel) {
        if (!estNonVide(tel)) return false;
        // simple: 9 à 15 chiffres
        return tel.matches("^[0-9]{9,15}$");
    }

    public static boolean estTvaValide(double tva) {
        return tva >= 0 && tva <= 1; // 0.20 = 20%
    }

    public static boolean estPrixValide(double prix) {
        return prix >= 0;
    }

    public static boolean estQuantiteValide(int qte) {
        return qte > 0;
    }
}