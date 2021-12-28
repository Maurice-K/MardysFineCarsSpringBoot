package ewkconsulting.software.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ewkconsulting.software.exceptions.CarNotFoundException;
import ewkconsulting.software.models.Car;
import ewkconsulting.software.models.CarDto;
import ewkconsulting.software.models.DealJacket;
import ewkconsulting.software.repositories.CarRepository;

/**
 * 
 * @author Damond Howard
 * @apiNote This is a Service class that encapsulate the operations for cars which include creating deleting and retrieving
 *
 */
@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	public Car createCar(CarDto car) {
		Car myCar = new Car();
		myCar.setSku(car.getSku());
		myCar.setMileage(car.getMileage());
		myCar.setYear(car.getYear());
		myCar.setModel(car.getModel());
		myCar.setColor(car.getColor());
		myCar.setPrice(car.getPrice());
		myCar.setCylinders(car.getCyclinders());
	
		
		return carRepository.save(myCar);
	}
	
	public Car addDealJacket(Long carId, DealJacket dealJacket) throws CarNotFoundException {
		Car myCar = carRepository.findById(carId)
				.orElseThrow(() -> new CarNotFoundException(carId));
		myCar.addDealJacket(dealJacket);
		return carRepository.save(myCar);
	}

	public Car addDealJackets(Long carId, Set<DealJacket> dealJackets) throws CarNotFoundException{
		Car myCar = carRepository.findById(carId)
				.orElseThrow(() -> new CarNotFoundException(carId));
		myCar.setDealJackets(dealJackets);
		return carRepository.save(myCar);
	}
	
	public void save(Car car) {
		carRepository.save(car);
	}
	
	public Car findById(Long id) throws CarNotFoundException {
		return carRepository.findById(id)
				.orElseThrow(() -> new CarNotFoundException(id));
	}
	
	public Car findBySku(String sku) {
			return carRepository.findBySku(sku);
	}
	
	public void deleteCar(Long carId) {
		carRepository.deleteById(carId);
	}

	public List<Car> findAll() {
		return carRepository.findAll();
	}
	
	public void saveAll(List<Car> cars) {
		carRepository.saveAll(cars);
		
	}
	
}
