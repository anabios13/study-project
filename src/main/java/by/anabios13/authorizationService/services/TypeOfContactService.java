package by.anabios13.authorizationService.services;

import by.anabios13.authorizationService.models.TypeOfContact;
import by.anabios13.authorizationService.repository.TypeOfContactRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TypeOfContactService {
    private final TypeOfContactRepository typeOfContactRepository;

    public TypeOfContactService(TypeOfContactRepository typeOfContactRepository) {
        this.typeOfContactRepository = typeOfContactRepository;
    }

    @Transactional
    public void save(TypeOfContact typeOfContact){typeOfContactRepository.save(typeOfContact);}
    public TypeOfContact findByName(String name){
        Optional<TypeOfContact> typeOfContact= typeOfContactRepository.findByName(name);
    return typeOfContact.orElse(null);
    }
}
