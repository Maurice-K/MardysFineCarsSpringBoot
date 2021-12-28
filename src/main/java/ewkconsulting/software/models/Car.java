package ewkconsulting.software.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "cars")
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue
    private Long carId;
    
    @Column(name = "sku")
    private String sku;

    @Column(name = "mileage")
    private int mileage;

    @Column(name = "year")
    private int year;

    @Column(name = "model")
    private String model;

    @Column(name = "make")
    private String make;
    
    @Column(name = "color")
    private String color;

    @Column(name = "price")
    private double price;

    @Column(name = "cylinders")
    private int cylinders;

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

	public Car(int mileage, int year, String model, String make, String color, double price, int cylinders) {
		super();
		this.mileage = mileage;
		this.year = year;
		this.model = model;
		this.make = make;
		this.color = color;
		this.price = price;
		this.cylinders = cylinders;
	}
}
