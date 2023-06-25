package by.anabios13.authorizationService.dto;

import by.anabios13.authorizationService.models.NameOfImpactDirection;
import by.anabios13.authorizationService.models.VehicleCondition;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

public class ImpactDirectionDTO {

    private String name;

    public ImpactDirectionDTO(){}

    public ImpactDirectionDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
