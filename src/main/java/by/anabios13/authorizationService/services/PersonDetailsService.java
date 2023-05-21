package by.anabios13.authorizationService.services;

import by.anabios13.authorizationService.models.Person;
import by.anabios13.authorizationService.repository.PersonRepository;
import by.anabios13.authorizationService.security.PersonDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PersonRepository personRepository;

    public PersonDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Person> person = personRepository.findByLogin(login);
        if (person.isEmpty()) {
            throw new UsernameNotFoundException("User Not Found!");
        }
        return new PersonDetails(person.get());
    }
}
