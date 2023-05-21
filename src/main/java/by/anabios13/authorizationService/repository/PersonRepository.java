package by.anabios13.authorizationService.repository;

import by.anabios13.authorizationService.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {

    Optional<Person> findByLogin(String string);
}
