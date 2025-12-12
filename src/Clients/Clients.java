package Clients;

public class Clients{
    private int id ;
    private String nom ;
    private String prenom;
    private int num_tel;
    private String email;

    public Clients(String email, int id, String nom, int num_tel, String prenom) {
        this.email = email;
        this.id = id;
        this.nom = nom;
        this.num_tel = num_tel;
        this.prenom = prenom;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public int getNum_tel() {
        return num_tel;
    }
    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}