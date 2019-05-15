package fr.coursjava.commande.repository;

import fr.coursjava.commande.bo.Commande;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommandeRepository extends MongoRepository<Commande, String> {

    List<Commande> findAllByUserId(Long userId, Pageable pageable);
}
