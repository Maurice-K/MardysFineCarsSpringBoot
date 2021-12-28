package ewkconsulting.software.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ewkconsulting.software.helper.CSVHelper;
import ewkconsulting.software.models.Car;
import ewkconsulting.software.repositories.CarRepository;

@Service
public class CSVService {

	@Autowired
	CarService carService;
	
	public void save(MultipartFile file) {
		System.out.println("CSVService");
		try {
			System.out.println("Try Block");
			List<Car> cars = CSVHelper.csvToCars(file.getInputStream());
			System.out.println("Try to save data");
			carService.saveAll(cars);
		} catch (IOException e) {
			throw new RuntimeException("Fail to store csv data: " + e.getMessage());
		}
	}
	
	public List<Car> getAllCars() {
		return carService.findAll();
	}
}
