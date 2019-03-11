package fr.coursjava.produit.api.rowmapper;

import fr.coursjava.api.model.Produit;
import fr.coursjava.api.model.ProduitReference;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProduitRowMapper {

    private ProduitRowMapper(){

    }

    public static ProduitReference asReference (ResultSet rs, int i) throws SQLException {
        ProduitReference res =  new ProduitReference();

        res.setId(rs.getLong("id"));
        res.setNom(rs.getString("nom"));

        return res;
    }

    public static Produit asProduit(ResultSet rs, int i) throws SQLException{
        Produit p = new Produit();

        return p;
    }

}
