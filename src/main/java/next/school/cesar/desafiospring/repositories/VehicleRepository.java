package next.school.cesar.desafiospring.repositories;

import next.school.cesar.desafiospring.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
