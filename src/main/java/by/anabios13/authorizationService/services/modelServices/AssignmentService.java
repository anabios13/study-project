package by.anabios13.authorizationService.services.modelServices;

import by.anabios13.authorizationService.models.Assignment;
import by.anabios13.authorizationService.repository.AssignmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssignmentService {
    private final AssignmentRepository assignmentRepository;

    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }


    @Transactional
    public void save(Assignment assignment) {
        assignmentRepository.save(assignment);
    }
}
