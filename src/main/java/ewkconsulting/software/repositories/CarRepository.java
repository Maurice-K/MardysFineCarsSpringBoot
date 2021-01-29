package ewkconsulting.software.repositories;

import ewkconsulting.software.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Car findBySku(String sku);

}
