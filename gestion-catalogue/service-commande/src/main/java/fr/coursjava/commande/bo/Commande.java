package fr.coursjava.commande.bo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Commande {

    @Id
    private String id;

    private Long userId;

    private Destinataire destinataire;

    private Adresse adresse;

    private List<CommandeLine> lignes;

    private Double prixTotal = 0.0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<CommandeLine> getLignes() {
        return lignes;
    }

    public void setLignes(List<CommandeLine> lignes) {
        this.lignes = lignes;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Destinataire getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(Destinataire destinataire) {
        this.destinataire = destinataire;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", lignes=" + lignes +
                ", prixTotal=" + prixTotal +
                '}';
    }
}
