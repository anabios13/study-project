package by.anabios13.authorizationService.repository;

import by.anabios13.authorizationService.models.ImpactDirection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImpactDirectionRepository extends JpaRepository<ImpactDirection,Integer> {

    Optional<ImpactDirection> findByImpactDirectionId(Integer id);
}
