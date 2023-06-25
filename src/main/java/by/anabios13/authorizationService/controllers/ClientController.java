package by.anabios13.authorizationService.controllers;

import by.anabios13.authorizationService.dto.*;
import by.anabios13.authorizationService.services.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/client")
public class ClientController {

private  final NewAssignmentService newAssignmentService;

    public ClientController(NewAssignmentService newAssignmentService) {
        this.newAssignmentService = newAssignmentService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAssignment(@RequestBody AssignmentDTO assignmentDTO) {
       return newAssignmentService.createAssignment(assignmentDTO);
    }

}
