package by.anabios13.authorizationService.repository;

import by.anabios13.authorizationService.models.VehicleInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleInformationRepository extends JpaRepository<VehicleInformation,Integer> {

}
