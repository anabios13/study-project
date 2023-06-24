package by.anabios13.authorizationService.repository;

import by.anabios13.authorizationService.models.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone,Integer> {

}
