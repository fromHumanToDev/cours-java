package fr.coursjava.produit.api.data;

import fr.coursjava.api.model.Declinaison;
import fr.coursjava.api.model.FamilleReference;
import fr.coursjava.api.model.Produit;
import fr.coursjava.api.model.ProduitReference;
import fr.coursjava.produit.api.rowmapper.ProduitRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProduitData {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<ProduitReference> list() {
        return jdbcTemplate.query("select id, nom from produit", ProduitRowMapper::asReference);
    }

    public void creer(Produit p) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into produit(nom, description, famille_id) values (?, ?, ?)", new String[]{"id"});
            ps.setString(1, p.getNom());
            ps.setString(2, p.getDescription());
            ps.setLong(3, p.getFamille().getId());
            return ps;
        }, keyHolder);

        p.setId(keyHolder.getKey().longValue());
    }

    public Produit get(Long produitId) {
        Produit p = new Produit();

        StringBuilder query = new StringBuilder(" select ")
                .append("produit.id as produitId, produit.nom as produitNom, produit.description as produitDescription, ")
                .append("famille.id as familleId, famille.nom as familleNom, ")
                .append("declinaison.id as declinaisonId, declinaison.nom as declinaisonNom, ")
                .append("prix.valeur prix_valeur ")
                .append("from produit ")
                .append("join famille on famille.id = produit.famille_id ")
                .append("join declinaison on declinaison.produit_id = produit.id ")
                .append("join prix on prix.declinaison_id = declinaison.id ")
                .append("join ( ")
                .append("select declinaison_id, min(debut) debut from prix ")
                .append("where fin is null or fin > current_date() ")
                .append("group by declinaison_id ")
                .append(") prix_actifs on prix_actifs.declinaison_id = prix.declinaison_id and prix_actifs.debut = prix.debut ")
                .append("where produit.id = ?");

        jdbcTemplate.query(query.toString(), new Object[]{produitId}, rs -> {
            if (p.getId() == null) {
                p.setId(rs.getLong("produitId"));
                p.setNom(rs.getString("produitNom"));
                p.setDescription(rs.getString("produitDescription"));
                p.setFamille(new FamilleReference());
                p.getFamille().setId(rs.getLong("familleId"));
                p.getFamille().setNom(rs.getString("familleNom"));
                p.setDeclinaisons(new ArrayList<>());
            }


            Declinaison declinaison = new Declinaison();
            declinaison.setId(rs.getLong("declinaisonId"));
            declinaison.setNom(rs.getString("declinaisonNom"));
            declinaison.setPrix(rs.getDouble("prix_valeur"));
            p.getDeclinaisons().add(declinaison);


        });


        return p;

    }
}

