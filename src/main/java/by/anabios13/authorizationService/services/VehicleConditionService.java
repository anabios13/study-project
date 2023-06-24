package by.anabios13.authorizationService.services;

import by.anabios13.authorizationService.models.VehicleCondition;
import by.anabios13.authorizationService.repository.VehicleConditionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VehicleConditionService {
    private final VehicleConditionRepository vehicleConditionRepository;

    public VehicleConditionService(VehicleConditionRepository vehicleConditionRepository) {
        this.vehicleConditionRepository = vehicleConditionRepository;
    }

    @Transactional
    public void save(VehicleCondition vehicleCondition){vehicleConditionRepository.save(vehicleCondition);}
}
