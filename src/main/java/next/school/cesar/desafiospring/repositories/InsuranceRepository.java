package next.school.cesar.desafiospring.repositories;

import next.school.cesar.desafiospring.entities.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}
