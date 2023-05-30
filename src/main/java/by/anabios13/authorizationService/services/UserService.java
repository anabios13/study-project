package by.anabios13.authorizationService.services;

import by.anabios13.authorizationService.models.User;
import by.anabios13.authorizationService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findOne(int id) {
        Optional<User> foundPerson = userRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void update(int id, User updatedUser) {
        updatedUser.setPersonId(id);
        userRepository.save(updatedUser);
    }

    public Optional<User> findUserByLogin(String login) throws UsernameNotFoundException {
        Optional<User> person = userRepository.findByLogin(login);
        return person;
    }

    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }


}
