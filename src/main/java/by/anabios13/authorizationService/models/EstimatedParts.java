package by.anabios13.authorizationService.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "estimated_parts")
public class EstimatedParts {
    @Id
    @Column(name = "estimated_part_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int estimatedPartId;

    @ManyToOne
    @JoinColumn(name = "type_of_parts_id", referencedColumnName = "type_of_parts_id")
    private TypeOfParts typeOfParts;

    @Column(name = "description")
    private String description;

    @Column(name = "labor_hours")
    private BigDecimal labor_hours;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "labor_rate")
    private BigDecimal laborRate;

    @ManyToOne
    @JoinColumn(name = "supplement_id", referencedColumnName = "supplement_id")
    private Supplement supplement;

    public EstimatedParts (){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstimatedParts that = (EstimatedParts) o;
        return Objects.equals(typeOfParts, that.typeOfParts) && Objects.equals(description, that.description) && Objects.equals(labor_hours, that.labor_hours) && Objects.equals(price, that.price) && Objects.equals(laborRate, that.laborRate) && Objects.equals(supplement, that.supplement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeOfParts, description, labor_hours, price, laborRate, supplement);
    }

    public int getEstimatedPartId() {
        return estimatedPartId;
    }

    public void setEstimatedPartId(int estimatedPartId) {
        this.estimatedPartId = estimatedPartId;
    }

    public TypeOfParts getTypeOfParts() {
        return typeOfParts;
    }

    public void setTypeOfParts(TypeOfParts typeOfParts) {
        this.typeOfParts = typeOfParts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getLabor_hours() {
        return labor_hours;
    }

    public void setLabor_hours(BigDecimal labor_hours) {
        this.labor_hours = labor_hours;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getLaborRate() {
        return laborRate;
    }

    public void setLaborRate(BigDecimal laborRate) {
        this.laborRate = laborRate;
    }

    public Supplement getSupplement() {
        return supplement;
    }

    public void setSupplement(Supplement supplement) {
        this.supplement = supplement;
    }
}
