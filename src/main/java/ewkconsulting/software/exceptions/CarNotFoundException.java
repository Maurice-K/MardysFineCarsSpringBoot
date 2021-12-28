package ewkconsulting.software.exceptions;

public class CarNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String MESSAGE = "";
	
	public CarNotFoundException(Long carId) {
		MESSAGE = "Sorry car with id " + carId + " was not found in the database";
	}
	
	public CarNotFoundException(String sku) {
		MESSAGE = "Sorry car with sku " + sku + " was not found in the database";
	}
	
	public CarNotFoundException(Long carId, String sku) {
		MESSAGE = "Sorry car with sku " + sku + " and id " + carId + " was not found in the database";
	}
	
	@Override
	public String toString() {
		return MESSAGE;
	}
}
