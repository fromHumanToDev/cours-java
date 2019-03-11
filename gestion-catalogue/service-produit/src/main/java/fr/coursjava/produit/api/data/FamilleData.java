package fr.coursjava.produit.api.data;

import fr.coursjava.api.model.FamilleReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class FamilleData {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void creer(FamilleReference famille) {
        KeyHolder keyHolder = new GeneratedKeyHolder();


        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into famille(nom) values (?)", new String[]{"id"});
            ps.setString(1, famille.getNom());
            return ps;
        }, keyHolder);

        famille.setId(keyHolder.getKey().longValue());
    }
}
