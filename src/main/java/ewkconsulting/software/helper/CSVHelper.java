package ewkconsulting.software.helper;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;


import ewkconsulting.software.models.Car;


public class CSVHelper {
	
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "Vehicle Year", "Vehicle Make", "Vehicle Model","Mileage", "Cylinders", "Vehicle Color", "Vehicle Color 2", "Retail Price" };
  
  public static boolean hasCSVFormat(MultipartFile file) {
	  if (!TYPE.equals(file.getContentType())) {
      return false;
    }
    return true;
  }

  public static List<Car> csvToCars(InputStream is) {
    
	  try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
			  ) {
		  System.out.println("Second try in csvToCars");
      List<Car> cars = new ArrayList<Car>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
      
      for (CSVRecord csvRecord : csvRecords) {
    	  System.out.println("Inside for loop");
    	Car car = new Car();
    	car.setMileage(Integer.parseInt(csvRecord.get("Mileage")));
    	car.setYear(Integer.parseInt(csvRecord.get("Vehicle Year")));
    	car.setModel(csvRecord.get("Vehicle Model"));
    	car.setMake(csvRecord.get("Vehicle Make"));
    	car.setColor(csvRecord.get("Vehicle Color"));
    	car.setPrice(Double.parseDouble(csvRecord.get("Retail Price")));
    	car.setCylinders(Integer.parseInt(csvRecord.get("Cylinders")));
      
        cars.add(car);
        System.out.println(cars.size());
      }
      System.out.println("End of cars");
      return cars;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }
}