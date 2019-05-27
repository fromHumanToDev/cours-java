package fr.coursjava.commande.config;

import com.mongodb.MongoClient;
import fr.coursjava.api.client.router.ClientApi;
import fr.coursjava.api.produit.router.ProduitApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;

@Configuration
@Order(1)
@ComponentScan("fr.coursjava")
@EnableMongoRepositories("fr.coursjava")
public class AppConfig {


    @Bean
    public MongoClient mongo() {
        return new MongoClient("localhost");
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), "test");
    }

    @LoadBalanced
    @Bean("restTemplate")
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }



}
