package by.anabios13.authorizationService.controllers;

import by.anabios13.authorizationService.dto.AssignmentDTO;
import by.anabios13.authorizationService.models.NameOfImpactDirection;
import by.anabios13.authorizationService.models.TypeOfAddress;
import by.anabios13.authorizationService.models.TypeOfContact;
import by.anabios13.authorizationService.models.TypeOfPhone;
import by.anabios13.authorizationService.services.NewAssignmentService;
import by.anabios13.authorizationService.services.modelServices.TypeOfContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

private  final NewAssignmentService newAssignmentService;
private final TypeOfContactService typeOfContactService;

    public ClientController(NewAssignmentService newAssignmentService, TypeOfContactService typeOfContactService) {
        this.newAssignmentService = newAssignmentService;
        this.typeOfContactService = typeOfContactService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAssignment(@RequestBody AssignmentDTO assignmentDTO) {
       return newAssignmentService.createAssignment(assignmentDTO);
    }

    @RequestMapping(value = "/impact_directions", method = RequestMethod.GET)
    public ResponseEntity<NameOfImpactDirection[]> impactDirectionsIndex() {
        return ResponseEntity.status(HttpStatus.OK).body(NameOfImpactDirection.values());
    }

    @RequestMapping(value = "/types_of_contact", method = RequestMethod.GET)
    public ResponseEntity<List<TypeOfContact>> typesOfContactIndex() {
        return ResponseEntity.status(HttpStatus.OK).body(typeOfContactService.findAll());
    }

    @RequestMapping(value = "/types_of_phone", method = RequestMethod.GET)
    public ResponseEntity<TypeOfPhone[]> typesOfPhoneIndex() {
        return ResponseEntity.status(HttpStatus.OK).body(TypeOfPhone.values());
    }

    @RequestMapping(value = "/types_of_address", method = RequestMethod.GET)
    public ResponseEntity<TypeOfAddress[]> typesOfAddressIndex() {
        return ResponseEntity.status(HttpStatus.OK).body(TypeOfAddress.values());
    }
}
