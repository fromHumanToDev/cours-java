package fr.coursjava.client.bo;

import javax.persistence.*;

@Entity
public class AdresseBo {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String rue;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private VilleBo ville;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public VilleBo getVille() {
        return ville;
    }

    public void setVille(VilleBo ville) {
        this.ville = ville;
    }
}
