package ewkconsulting.software.models;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Data
@Table(name="deal_jackets")
public class DealJacket {

    @Id
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

    // `fetch = FetchType.EAGER` makes sure all the Deal Jackets get loaded when a Car is queried.
    // `JsonIgnore` will ignore the car that has already been serialized; therefore, stopping loops.
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Car car;

    

}
