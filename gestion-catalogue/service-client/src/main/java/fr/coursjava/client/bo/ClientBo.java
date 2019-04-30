package fr.coursjava.client.bo;

import javax.persistence.*;

@Entity
public class ClientBo {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private AdresseBo adresse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public AdresseBo getAdresse() {
        return adresse;
    }

    public void setAdresse(AdresseBo adresse) {
        this.adresse = adresse;
    }
}
