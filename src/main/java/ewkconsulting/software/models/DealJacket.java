package ewkconsulting.software.models;

import lombok.Data;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@Table(name="deal_jackets")
public class DealJacket {

    @Id
    @GeneratedValue
    private Long dealJacketId;
    
    @Column(name = "sku")
    private String sku;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "amount_of_income")
    private Long amountOfIncome;

    @Column(name = "type_of_income")
    private String typeOfIncome;

    @Column(name = "payment_schedule")
    private String paymentSchedule;


    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Car car;

    @Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Car )) return false;
		return dealJacketId != null && dealJacketId.equals(((Car) o).getCarId());
	}
    
    @Override
	public int hashCode() {
		return 31;
	}
}
