package by.anabios13.authorizationService.controllers;

import by.anabios13.authorizationService.dto.*;
import by.anabios13.authorizationService.models.*;
import by.anabios13.authorizationService.repository.TypeOfContactRepository;
import by.anabios13.authorizationService.security.UserDetails;
import by.anabios13.authorizationService.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/client")
public class ClientController {
    private final UserDetailsService userDetailsService;
    private ModelMapper modelMapper = new ModelMapper();
    private final AssignmentService assignmentService;
    private final VehicleInformationService vehicleInformationService;
    private final VehicleConditionService vehicleConditionService;
    private final TypeOfContactService typeOfContactService;

    private final AddressService addressService;
    private final PhoneService phoneService;
    private final ContactService contactService;

    public ClientController(UserDetailsService userDetailsService,
                            AssignmentService assignmentService,
                            VehicleInformationService vehicleInformationService,
                            VehicleConditionService vehicleConditionService,
                            TypeOfContactService typeOfContactService,
                            TypeOfContactService typeOfContactService1, AddressService addressService,
                            PhoneService phoneService, ContactService contactService) {
        this.userDetailsService = userDetailsService;
        this.assignmentService = assignmentService;
        this.vehicleInformationService = vehicleInformationService;
        this.vehicleConditionService = vehicleConditionService;
        this.typeOfContactService = typeOfContactService;
        this.addressService = addressService;
        this.phoneService = phoneService;
        this.contactService = contactService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAssignment(@RequestBody AssignmentDTO assignmentDTO) {
        Assignment assignment = new Assignment();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        assignment.setClient(user);
        assignment.setNote(assignmentDTO.getAssignmentNote());
        assignment.setDateOfIncident(assignmentDTO.getDateOfIncident());

        if (assignmentDTO.getVehicleInformation() != null) {
            VehicleInformationDTO originalVehicleInformation = assignmentDTO.getVehicleInformation();
            VehicleInformation processedVehicleInformation = new VehicleInformation();
            processedVehicleInformation.setVin(originalVehicleInformation.getVin());
            processedVehicleInformation.setYear(originalVehicleInformation.getYear());
            processedVehicleInformation.setMakeIn(originalVehicleInformation.getMakeIn());
            processedVehicleInformation.setLicensePart(originalVehicleInformation.getLicensePart());
            processedVehicleInformation.setLicenseState(originalVehicleInformation.getLicenseState());
            processedVehicleInformation.setLicenseExpiration(originalVehicleInformation.getLicenseExpiration());
            processedVehicleInformation.setOdometerValue(originalVehicleInformation.getOdometerValue());
            assignment.setVehicleInformation(processedVehicleInformation);
            vehicleInformationService.save(processedVehicleInformation);
        }

        if (assignmentDTO.getVehicleCondition() != null) {
            VehicleConditionDTO originalVehicleCondition = assignmentDTO.getVehicleCondition();
            VehicleCondition processedVehicleCondition = new VehicleCondition();
            processedVehicleCondition.setAssignment(assignment);
            processedVehicleCondition.setImpact_direction(ImpactDirection.valueOf(originalVehicleCondition.getImpact_direction()));
            processedVehicleCondition.setPhotos(originalVehicleCondition.getPhotos().toString());//заглушка
            assignment.setVehicleCondition(processedVehicleCondition);
            vehicleConditionService.save(processedVehicleCondition);
        }


        if (assignmentDTO.getContacts() != null) {
            List<Phone> listOfPhones = new ArrayList<>();
            List<Address> listOfAddresses = new ArrayList<>();
            List<Contact> processedContactList = new ArrayList<>();
            for (ContactDTO originalContact : assignmentDTO.getContacts()) {

                Contact processedContact = new Contact();
                processedContact.setAssignment(assignment);
                TypeOfContact typeOfContactForSave = typeOfContactService.findByName(originalContact.getTypeOfContact().getNameOfType());
                processedContact.setTypeOfContact(typeOfContactForSave);
                processedContact.setFirstname(originalContact.getFirstname());
                processedContact.setLastname(originalContact.getLastname());
                processedContact.setEmail(originalContact.getEmail());
                processedContact.setNote(originalContact.getNote());

                for (PhoneDTO phone : originalContact.getPhones()) {
                    Phone phoneForSave = new Phone(processedContact,
                            phone.getNumber(),
                            TypeOfPhone.valueOf(phone.getTypeOfPhone()));
                    listOfPhones.add(phoneForSave);
                    phoneService.save(phoneForSave);
                }

                processedContact.setPhones(listOfPhones);

                for (AddressDTO address : originalContact.getAddresses()) {
                    Address addressForSave = new Address(
                            processedContact,
                            address.getCity(),
                            address.getState(),
                            address.getZip(),
                            address.getNote(),
                            TypeOfAddress.valueOf(address.getTypeOfAddress()));
                    listOfAddresses.add(addressForSave);
                    addressService.save(addressForSave);
                }

                contactService.save(processedContact);
            }
            assignment.setContacts(processedContactList);
        }
        assignmentService.save(assignment);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

}
