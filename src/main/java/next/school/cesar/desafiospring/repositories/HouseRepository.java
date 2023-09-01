package next.school.cesar.desafiospring.repositories;

import next.school.cesar.desafiospring.entities.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
}
