package fr.coursjava.produit.api.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Date;

@Repository
public class PrixData {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void creer(Long declinaisonId, Double valeur, Date debut, Date fin){
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into prix(valeur, declinaison_id, debut, fin) values (?, ?, ?, ?)");
            ps.setDouble(1, valeur);
            ps.setLong(2, declinaisonId);
            ps.setDate(3, new java.sql.Date(debut.getTime()));
            ps.setDate(4, fin == null ? null : new java.sql.Date(fin.getTime()));
            return ps;
        });
    }
}
