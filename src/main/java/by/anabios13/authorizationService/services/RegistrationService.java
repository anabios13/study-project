package by.anabios13.authorizationService.services;

import by.anabios13.authorizationService.models.Person;
import by.anabios13.authorizationService.models.Role;
import by.anabios13.authorizationService.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class RegistrationService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(Person person, Role role) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole(role);
        personRepository.save(person);
    }

}
