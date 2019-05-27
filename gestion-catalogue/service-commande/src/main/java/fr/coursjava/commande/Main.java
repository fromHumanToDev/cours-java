package fr.coursjava.commande;

import fr.coursjava.api.client.router.ClientApi;
import fr.coursjava.api.produit.router.ProduitApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableEurekaClient
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        ProduitApi api = context.getBean(ProduitApi.class);
        System.out.println(api.listProduits());
    }
}
