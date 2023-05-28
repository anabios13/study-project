package by.anabios13.authorizationService.services;

import by.anabios13.authorizationService.dto.AuthenticationDTO;
import by.anabios13.authorizationService.models.Person;
import by.anabios13.authorizationService.models.Role;
import by.anabios13.authorizationService.pojo.ResponseWithMessage;
import by.anabios13.authorizationService.repository.PersonRepository;
import by.anabios13.authorizationService.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.security.auth.message.AuthException;
import javax.transaction.Transactional;
import java.util.Map;

@Component
public class RegistrationService {
    private final PersonRepository personRepository;
    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(PersonRepository personRepository, JWTUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(Person person, Role role) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole(role);
        personRepository.save(person);
    }

    public ResponseWithMessage performLogin(AuthenticationDTO authenticationDTO) throws AuthException {
        try {
            Person person = personRepository.findByLogin(authenticationDTO.getLogin()).orElseThrow(() -> new AuthException("Пользователь не найден"));

            if(authenticationDTO.getLogin().equals(person.getLogin()) && passwordEncoder.matches(authenticationDTO.getPassword(),person.getPassword())){
                String token = jwtUtil.generateToken(authenticationDTO.getLogin());
                return new ResponseWithMessage(Map.of("jwt-token",token));
            }else {
                return new ResponseWithMessage(Map.of("message","Incorrect credentials"));
            }
        }catch (AuthException e){
            return new ResponseWithMessage(Map.of("message","Incorrect credentials"));
        }
    }

}
