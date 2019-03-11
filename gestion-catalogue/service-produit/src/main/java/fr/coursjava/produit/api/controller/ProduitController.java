package fr.coursjava.produit.api.controller;


import fr.coursjava.api.model.Declinaison;
import fr.coursjava.api.model.Produit;
import fr.coursjava.api.model.ProduitReference;
import fr.coursjava.api.router.ProduitsApi;
import fr.coursjava.produit.api.data.DeclinaisonData;
import fr.coursjava.produit.api.data.FamilleData;
import fr.coursjava.produit.api.data.PrixData;
import fr.coursjava.produit.api.data.ProduitData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
public class ProduitController implements ProduitsApi {

    @Autowired
    private ProduitData produitData;

    @Autowired
    private FamilleData familleData;

    @Autowired
    private DeclinaisonData declinaisonData;

    @Autowired
    private PrixData prixData;

    @Override
    public ResponseEntity<List<ProduitReference>> listProduits() {
        return new ResponseEntity<>(produitData.list(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> creerProduit(@Valid Produit produit) {
        if(produit.getFamille().getId() == null){
            familleData.creer(produit.getFamille());
        }

        produitData.creer(produit);

        for (Declinaison declinaison : produit.getDeclinaisons()) {
            declinaisonData.creer(produit.getId(), declinaison);
            prixData.creer(declinaison.getId(), declinaison.getPrix(), new Date(), null);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Produit> getProduit(Long produitId) {
        return produitData.get(produitId);
    }
}
