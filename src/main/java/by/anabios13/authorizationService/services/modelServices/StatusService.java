package by.anabios13.authorizationService.services.modelServices;

import by.anabios13.authorizationService.models.Status;
import by.anabios13.authorizationService.repository.StatusRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StatusService {
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Transactional
    public void save(Status status) {
        statusRepository.save(status);
    }

    public Status findById(int id) {
        Optional<Status> foundStatus = statusRepository.findById(id);
        return foundStatus.orElse(null);
    }

}

