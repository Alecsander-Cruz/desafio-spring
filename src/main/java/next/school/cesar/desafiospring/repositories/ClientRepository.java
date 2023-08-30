package next.school.cesar.desafiospring.repositories;

import next.school.cesar.desafiospring.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
