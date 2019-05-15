package fr.coursjava.commande.api;

import fr.coursjava.api.client.model.Client;
import fr.coursjava.api.client.router.ClientApi;
import fr.coursjava.api.commande.model.CommandeCreation;
import fr.coursjava.api.commande.router.CommandesApi;
import fr.coursjava.api.produit.model.Declinaison;
import fr.coursjava.api.produit.model.Produit;
import fr.coursjava.api.produit.router.ProduitApi;
import fr.coursjava.commande.bo.Adresse;
import fr.coursjava.commande.bo.Commande;
import fr.coursjava.commande.bo.CommandeLine;
import fr.coursjava.commande.bo.Destinataire;
import fr.coursjava.commande.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CommandeController implements CommandesApi {

    @Autowired
    private ClientApi clientApi;

    @Autowired
    private ProduitApi produitApi;

    @Autowired
    private CommandeRepository commandeRepository;


    @Override
    public ResponseEntity<Void> creerCommande(Long clientId, @Valid CommandeCreation commandeCreation) {

        Commande commande = new Commande();
        commande.setUserId(clientId);

        List<CommandeLine> commandeLines = commandeCreation.getLignes().stream().map(ligne -> {
            Produit produit = produitApi.getProduit(ligne.getProduitId());

            Double prix = produit.getDeclinaisons().stream()
                    .filter(d -> ligne.getDeclinaisonId().equals(d.getId()))
                    .mapToDouble(Declinaison::getPrix)
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);


            CommandeLine commandeLine = new CommandeLine();
            commandeLine.setQuantite(ligne.getQuantite());
            commandeLine.setDeclinaisonId(ligne.getDeclinaisonId());
            commandeLine.setPrix(prix);
            commandeLine.setProduitId(produit.getId());
            return commandeLine;
        }).collect(Collectors.toList());

        commande.setLignes(commandeLines);

        Client client = clientApi.getClient(clientId);

        commande.setAdresse(new Adresse());
        commande.getAdresse().setRue(client.getAdresse().getRue());
        commande.getAdresse().setCodePostal(client.getAdresse().getCodePostal());
        commande.getAdresse().setVille(client.getAdresse().getVille());

        commande.setDestinataire(new Destinataire());
        commande.getDestinataire().setNom(client.getNom());
        commande.getDestinataire().setPrenom(client.getPrenom());

        Double prixTotal = commande.getLignes().stream().mapToDouble(ligne ->  ligne.getQuantite() * ligne.getPrix()).sum();
        commande.setPrixTotal(prixTotal);
        commandeRepository.save(commande);
        return null;
    }

    @Override
    public ResponseEntity<List<fr.coursjava.api.commande.model.Commande>> getCommandes(Long clientId, @Valid Integer page, @Valid Integer size) {
        List<Commande> commandes = commandeRepository.findAllByUserId(clientId, PageRequest.of(page, size));

        List<fr.coursjava.api.commande.model.Commande> result = commandes.stream().map(c -> {
            fr.coursjava.api.commande.model.Commande commande = new fr.coursjava.api.commande.model.Commande();
            commande.setId(c.getId());
            commande.setClientId(c.getUserId());
            commande.setAdresseLivraison(new fr.coursjava.api.commande.model.Adresse());
            commande.getAdresseLivraison().setRue(c.getAdresse().getRue());
            commande.getAdresseLivraison().setCodePostal(c.getAdresse().getCodePostal());
            commande.getAdresseLivraison().setVille(c.getAdresse().getVille());
            commande.setMontantTotal(c.getPrixTotal().floatValue());
            for(CommandeLine ligne : c.getLignes()){
                fr.coursjava.api.commande.model.CommandeLine newLine = new fr.coursjava.api.commande.model.CommandeLine();
                newLine.setDeclinaisonId(ligne.getDeclinaisonId());
                newLine.setPrixAchat(ligne.getPrix().floatValue());
                newLine.setProduitId(ligne.getProduitId());
                newLine.setQuantite(ligne.getQuantite());
                commande.addLignesItem(newLine);
            }

            return commande;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(result);

    }
}
