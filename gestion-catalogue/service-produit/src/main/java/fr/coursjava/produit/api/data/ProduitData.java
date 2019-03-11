package fr.coursjava.produit.api.data;

import fr.coursjava.api.model.FamilleReference;
import fr.coursjava.api.model.Produit;
import fr.coursjava.api.model.ProduitReference;
import fr.coursjava.produit.api.rowmapper.ProduitRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            PreparedStatement ps = connection.prepareStatement("insert into produit(nom, famille_id) values (?, ?)", new String[]{"id"});
            ps.setString(1, p.getNom());
            ps.setLong(2, p.getFamille().getId());
            return ps;
        }, keyHolder);

        p.setId(keyHolder.getKey().longValue());
    }

    public Produit get(Long produitId) {
        Produit p = new Produit();

        StringBuilder query = new StringBuilder("select ")
                .append("produit.id as produitId, produit.nom as produitNom ")
                .append(", famille.id as familleId, famille.nom as familleNom ")
                .append("from produit ")
                .append("join famille on famille.id = produit.famille_id")
                ;


        jdbcTemplate.query(query.toString(), rs -> {
            if (p.getId() == null) {
                p.setId(rs.getLong("produitId"));
                p.setNom(rs.getString("produitNom"));
                p.setFamille(new FamilleReference());
                p.getFamille().setId(rs.getLong("familleId"));
                p.getFamille().setNom(rs.getString("familleNom"));
            }
        });


        return p;

    }
}
