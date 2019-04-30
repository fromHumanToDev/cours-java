package fr.coursjava.client.repository;

import fr.coursjava.client.bo.VilleBo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VilleRepository extends JpaRepository<VilleBo, Long> {

    Optional<VilleBo> findByNom(String nom);
}
