package ewkconsulting.software.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class MainController {
	
	@GetMapping(path = "/test") 
	public String test() {
		return "Test Passed";
	}
}
