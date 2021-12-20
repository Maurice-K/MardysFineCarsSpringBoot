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
    @GeneratedValue
    private Long carId;
    
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

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DealJacket> dealJackets = new HashSet<>();

    public void addDealJacket(DealJacket dealJacket) {
        dealJackets.add(dealJacket);
        dealJacket.setCar(this);
    }

    public void removeDealJacket(DealJacket dealJacket) {
        dealJackets.remove(dealJacket);
        dealJacket.setCar(null);
    }

	public void setDealJacket(DealJacket dealJacket) {
		
	}

}
