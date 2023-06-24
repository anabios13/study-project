package by.anabios13.authorizationService.repository;

import by.anabios13.authorizationService.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer> {
}
