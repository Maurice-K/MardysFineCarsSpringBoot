package ewkconsulting.software.models;

import lombok.Data;

@Data
public class CarDto {
	private String sku;
	private int mileage;
	private int year;
	private String model;
	private String color;
	private double price;
	private int cyclinders;
}
