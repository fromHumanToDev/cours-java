package fr.coursjava.produit.api.data;

import fr.coursjava.api.model.Produit;
import fr.coursjava.api.model.ProduitReference;
import fr.coursjava.produit.api.rowmapper.ProduitRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
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


        return p;

    }
}
