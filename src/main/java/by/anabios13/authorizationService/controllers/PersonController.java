package by.anabios13.authorizationService.controllers;

import by.anabios13.authorizationService.services.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PersonController {
private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/client")
    public String showHelloClient(){
        return "Hello Client";
    }
    @GetMapping("/insurance_agency")
    public String showHelloInsuranceAgency(){
        return "Hello Insurance agency";
    }
    @GetMapping("/estimator")
    public String showHelloEstimator(){
        return "Hello Estimator";
    }

    @GetMapping("/hello")
    public String showHello(){
        return "Hello";
    }
}
