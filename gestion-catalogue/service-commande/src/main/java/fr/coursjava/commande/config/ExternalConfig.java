package fr.coursjava.commande.config;

import fr.coursjava.api.client.router.ClientApi;
import fr.coursjava.api.produit.router.ProduitApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.client.RestTemplate;

@Configuration
@Order(2)
public class ExternalConfig {

    @Autowired
    public void setProduitApiUrl(ProduitApi produitApi, RestTemplate restTemplate){
        produitApi.getApiClient().setBasePath("http://localhost:8082");
    }

    @Autowired
    public void setClientApiUrl(ClientApi clientApi, RestTemplate restTemplate){
        clientApi.getApiClient().setBasePath("http://localhost:8081");
    }
}
