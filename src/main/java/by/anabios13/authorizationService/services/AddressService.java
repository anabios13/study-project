package by.anabios13.authorizationService.services;

import by.anabios13.authorizationService.models.Address;
import by.anabios13.authorizationService.repository.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional
    public void save(Address address) {
       addressRepository.saveAndFlush(address);
    }
}
