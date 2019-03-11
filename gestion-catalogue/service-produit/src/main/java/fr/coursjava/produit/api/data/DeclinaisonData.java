package fr.coursjava.produit.api.data;

import fr.coursjava.api.model.Declinaison;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class DeclinaisonData {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void creer(Long produitId, Declinaison declinaison) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into declinaison(nom, produit_id) values (?, ?)", new String[]{"id"});
            ps.setString(1, declinaison.getNom());
            ps.setLong(2, produitId);
            return ps;
        }, keyHolder);

        declinaison.setId(keyHolder.getKey().longValue());
    }
}
