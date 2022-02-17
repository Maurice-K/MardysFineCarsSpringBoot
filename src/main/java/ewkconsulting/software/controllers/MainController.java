package ewkconsulting.software.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ewkconsulting.software.exceptions.CarNotFoundException;
import ewkconsulting.software.helper.CSVHelper;
import ewkconsulting.software.models.Car;
import ewkconsulting.software.models.DealJacket;
import ewkconsulting.software.models.ResponseMessage;
import ewkconsulting.software.services.CSVService;
import ewkconsulting.software.services.CarService;

@RestController
@RequestMapping(path = "/api")
public class MainController {

	/**
	 * Endpoints for handling the upload and downloading of csv files
	 */
	@Autowired
	CSVService csvService;
	@Autowired
	CarService carService;

	@GetMapping("/cars")
	public ResponseEntity<List<Car>> getCars(@AuthenticationPrincipal UserDetails userDetails) {
		List<Car> cars = carService.getCars();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(cars);
	}

	@GetMapping("/car/{id}")
	public ResponseEntity<Car> getCarById(@AuthenticationPrincipal UserDetails userDetails,
			@PathVariable(name = "id") Long id) throws CarNotFoundException {
		Car car = carService.getCarById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(car);
	}

	@GetMapping("/car/{sku}")
	public ResponseEntity<Car> getCarBySKU(@AuthenticationPrincipal UserDetails userDetails,
			@PathVariable(name = "sku") String sku) {
		Car car = carService.findBySku(sku);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(car);
	}

	@GetMapping("/deal-jackets")
	public ResponseEntity<List<DealJacket>> getDealJackets(@AuthenticationPrincipal UserDetails userDetails) {
		List<Car> cars = carService.findAll();
		List<DealJacket> dealJacketsList = cars
				.stream()
				.map(car -> car.getDealJackets())
				.flatMap(dealJackets -> dealJackets.stream())
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(dealJacketsList);
	}

	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,
			@AuthenticationPrincipal UserDetails userDetails) {

		String message = "";

		if (CSVHelper.hasCSVFormat(file)) {
			try {
				csvService.save(file);
				message = "Uploaded the file successfully: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}
		message = "Please upload a csv file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}

}
