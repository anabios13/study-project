package by.anabios13.authorizationService.controllers;

import by.anabios13.authorizationService.dto.ImpactDirectionDTO;
import by.anabios13.authorizationService.dto.VehicleConditionDTO;
import by.anabios13.authorizationService.models.VehicleCondition;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
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

    @GetMapping("/client/test")
    public VehicleConditionDTO showVehicleCond(){
        ImpactDirectionDTO impactDirectionDTO = new ImpactDirectionDTO();
        impactDirectionDTO.setName("asdasd");
        ImpactDirectionDTO impactDirectionDTO1 = new ImpactDirectionDTO();
        impactDirectionDTO.setName("asdasd1");
        List<ImpactDirectionDTO> list = new ArrayList<>();
        list.add(impactDirectionDTO);
        list.add(impactDirectionDTO1);
        return new VehicleConditionDTO(list,null);
    }
}
