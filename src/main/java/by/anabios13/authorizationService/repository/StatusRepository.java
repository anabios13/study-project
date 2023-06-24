package by.anabios13.authorizationService.repository;

import by.anabios13.authorizationService.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status,Integer> {
    Optional<Status> findByName(String name);
    Optional<Status> findByStatusId(String id);
}
