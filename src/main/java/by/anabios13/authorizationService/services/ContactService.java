package by.anabios13.authorizationService.services;

import by.anabios13.authorizationService.models.Contact;
import by.anabios13.authorizationService.repository.ContactRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Transactional
    public void save(Contact contact){contactRepository.saveAndFlush(contact);}
}
