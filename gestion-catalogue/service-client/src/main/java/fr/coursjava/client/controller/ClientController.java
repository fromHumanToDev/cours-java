package fr.coursjava.client.controller;

import fr.coursjava.api.model.Adresse;
import fr.coursjava.api.model.Client;
import fr.coursjava.api.model.ClientCreation;
import fr.coursjava.api.model.ClientReference;
import fr.coursjava.api.router.ClientsApi;
import fr.coursjava.client.bo.AdresseBo;
import fr.coursjava.client.bo.ClientBo;
import fr.coursjava.client.bo.VilleBo;
import fr.coursjava.client.mapper.ClientMapper;
import fr.coursjava.client.repository.ClientRepository;
import fr.coursjava.client.repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ClientController implements ClientsApi {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private VilleRepository villeRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public ResponseEntity<Void> creerClient(@Valid ClientCreation clientCreation) {

        ClientBo client = new ClientBo();
        client.setNom(clientCreation.getNom());
        client.setPrenom(clientCreation.getPrenom());
        client.setAdresse(new AdresseBo());
        client.getAdresse().setRue(clientCreation.getAdresse().getRue());

        //recherche si le nom existe en bdd sinon cree un nouveau
        VilleBo ville = villeRepository.findByNom(clientCreation.getAdresse().getVille()).orElseGet(() -> {
            VilleBo bo = new VilleBo();
            bo.setCodePostal(clientCreation.getAdresse().getCodePostal());
            bo.setNom(clientCreation.getAdresse().getVille());
            return bo;
        });

        client.getAdresse().setVille(ville);

        clientRepository.save(client);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<List<ClientReference>> listClients(@Valid Integer page, @Valid Integer size) {
        page = page == null ? 0 : page;
        size = size == null ? 5 : size;
        Page<ClientBo> clients = clientRepository.findAll(PageRequest.of(page, size));

        List<ClientReference> result;
        /*List<ClientReference> result = clients.getContent().stream()
                .map(bo -> {
                    ClientReference reference = new ClientReference();
                    reference.setId(bo.getId());
                    reference.setNom(bo.getNom());
                    reference.setPrenom(bo.getPrenom());
                    return reference;
                }).collect(Collectors.toList());
        */

        result = clients.getContent().stream().map(clientMapper::boToReference).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Client> getClient(Long clientId) {
        Optional<ClientBo> client = clientRepository.findById(clientId);
        if(!client.isPresent()){
            return ResponseEntity.notFound().build();
        }
        ClientBo bo = client.get();

        /*Client result = new Client();
        result.setId(bo.getId());
        result.setNom(bo.getNom());
        result.setPrenom(bo.getPrenom());
        result.setAdresse(new Adresse());
        result.getAdresse().setCodePostal(bo.getAdresse().getVille().getCodePostal());
        result.getAdresse().setRue(bo.getAdresse().getRue());
        result.getAdresse().setVille(bo.getAdresse().getVille().getNom());
         */

        return ResponseEntity.ok(clientMapper.boToDto(bo));
    }
}
