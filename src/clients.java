public class clients {
    private int id;
    private int num_tel;
    private String prenom;
    private String nom;
    private String email;
    

    public clients(String nom, int id, String prenom,String email,int num_tel) {
        this.nom = nom;
        this.id = id;
        this.prenom = prenom;
        this.email=email;
        this.num_tel = num_tel;ig
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

}
