package by.anabios13.authorizationService.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

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
