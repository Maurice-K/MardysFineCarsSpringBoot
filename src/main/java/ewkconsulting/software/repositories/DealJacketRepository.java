package ewkconsulting.software.repositories;

import ewkconsulting.software.models.DealJacket;
import org.springframework.data.jpa.repository.JpaRepository;


// JpaRepository take the Model being looked at and the 'type' of its primary key, @Id.

public interface DealJacketRepository extends JpaRepository<DealJacket, Long> {

    DealJacket find


}
