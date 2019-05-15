package fr.coursjava.commande.bo;

public class CommandeLine {

    private Long produitId;
    private Double prix;
    private Integer quantite;
    private Long declinaisonId;


    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Long getDeclinaisonId() {
        return declinaisonId;
    }

    public void setDeclinaisonId(Long declinaisonId) {
        this.declinaisonId = declinaisonId;
    }

    @Override
    public String toString() {
        return "CommandeLine{" +
                "produitId=" + produitId +
                ", prix=" + prix +
                ", quantite=" + quantite +
                '}';
    }
}
