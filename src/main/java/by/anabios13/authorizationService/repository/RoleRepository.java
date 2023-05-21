package by.anabios13.authorizationService.repository;

import by.anabios13.authorizationService.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByNameOfRole(String string);
}
