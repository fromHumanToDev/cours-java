package packageUn;

public class Humain {

    private String nom;
    private String prenom;
    private Civilite civilite;

    public Humain(String nom, String prenom, Civilite civilite) {
        this.nom = nom;
        this.prenom = prenom;
        this.civilite = civilite;

        if(Civilite.MME.equals(this.civilite)){
        }
    }
}
