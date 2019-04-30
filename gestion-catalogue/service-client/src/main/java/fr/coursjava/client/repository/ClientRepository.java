package fr.coursjava.client.repository;

import fr.coursjava.client.bo.ClientBo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientBo, Long> {
}
