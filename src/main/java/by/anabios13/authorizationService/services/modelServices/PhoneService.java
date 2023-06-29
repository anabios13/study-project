package by.anabios13.authorizationService.services.modelServices;

import by.anabios13.authorizationService.models.Phone;
import by.anabios13.authorizationService.repository.PhoneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PhoneService {
    private final PhoneRepository phoneRepository;

    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Transactional
    public void save(Phone phone){phoneRepository.saveAndFlush(phone);}
}
