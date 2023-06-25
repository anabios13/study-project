package by.anabios13.authorizationService.services;

import by.anabios13.authorizationService.models.ImpactDirection;
import by.anabios13.authorizationService.repository.ImpactDirectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class ImpactDirectionService {
    private final ImpactDirectionRepository impactDirectionRepository;

    public ImpactDirectionService(ImpactDirectionRepository impactDirectionRepository) {
        this.impactDirectionRepository = impactDirectionRepository;
    }

    public ImpactDirection findById(int id) {
        Optional<ImpactDirection> foundDirection = impactDirectionRepository.findByImpactDirectionId(id);
        return foundDirection.orElse(null);
    }

    @Transactional
    public void save(ImpactDirection impactDirection){impactDirectionRepository.save(impactDirection);}


}
