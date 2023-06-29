package by.anabios13.authorizationService.services.modelServices;

import by.anabios13.authorizationService.models.VehicleInformation;
import by.anabios13.authorizationService.repository.VehicleInformationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VehicleInformationService {
    private final VehicleInformationRepository vehicleInformationRepository;

    public VehicleInformationService(VehicleInformationRepository vehicleInformationRepository) {
        this.vehicleInformationRepository = vehicleInformationRepository;
    }

    @Transactional
    public void save(VehicleInformation vehicleInformation){vehicleInformationRepository.save(vehicleInformation);}
}
