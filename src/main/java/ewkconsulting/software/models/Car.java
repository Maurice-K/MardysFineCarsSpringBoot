package ewkconsulting.software.models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "cars")
public class Car {

    @Id
    @Column(name = "sku")
    private String sku;

    @Column(name = "mileage")
    private Long mileage;

    @Column(name = "year")
    private Long year;

    @Column(name = "model")
    private String model;

    @Column(name = "color")
    private String color;

    @Column(name = "price")
    private Long price;

    @Column(name = "cylinders")
    private Long cylinders;

    // mappedBy is the relation in control of the relationship
    /**
     * cascade = CascadeType.All will make sure you can operate on child objects from the parent object.
     * Ex: You can find details about deal jackets form a Car object.
      */
    @OneToMany(mappedBy = "cars", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DealJacket> dealJackets = new HashSet<DealJacket>();

    private void addDealJacket(DealJacket dealJacket) {
        dealJackets.add(dealJacket);
    }

    private void removeDealJacket(DealJacket dealJacket) {
        dealJackets.remove(dealJacket);
    }

}
