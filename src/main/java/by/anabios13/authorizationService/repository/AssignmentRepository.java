package by.anabios13.authorizationService.repository;

import by.anabios13.authorizationService.models.Assignment;
import by.anabios13.authorizationService.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment,Long> {}
