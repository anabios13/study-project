package by.anabios13.authorizationService.mappers;

import by.anabios13.authorizationService.dto.AssignmentDTO;
import by.anabios13.authorizationService.models.Assignment;

public class AssignmentMapper implements DTOMapper<Assignment, AssignmentDTO>{
    @Override
    public AssignmentDTO mapToDTO(Assignment assignment) {
        return null;
    }

    @Override
    public Assignment mapToModel(AssignmentDTO assignmentDTO) {
        Assignment assignment = new Assignment();
         assignment.setNote(assignmentDTO.getAssignmentNote());
         assignment.setDateOfIncident(assignmentDTO.getDateOfIncident());
         return assignment;
    }
}
