package by.anabios13.authorizationService.repository;

import by.anabios13.authorizationService.models.VehicleCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleConditionRepository extends JpaRepository<VehicleCondition,Integer> {

}
