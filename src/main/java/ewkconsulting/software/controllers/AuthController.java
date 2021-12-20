package ewkconsulting.software.controllers;

import java.nio.charset.Charset;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ewkconsulting.software.DTOs.TempCredentials;
import ewkconsulting.software.security.JwtTokenUtil;
import ewkconsulting.software.security.models.JwtRequest;
import ewkconsulting.software.security.models.JwtResponse;
import ewkconsulting.software.security.models.User;
import ewkconsulting.software.security.services.JwtUserDetailsService;
//import ewkconsulting.software.services.EmailServiceImpl;

/**
 * 
 * @author Damond Howard
 * @apiNote this is a restful controller that handles all registration and authentication of users both customers and store owners
 *
 */

//TODO jwt token 30 mins and reject after logout
@RestController
@RequestMapping(path = "/auth-api/")
public class AuthController {
	
	 	@Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private JwtTokenUtil jwtTokenUtil;

	    @Autowired
	    private JwtUserDetailsService userDetailsService;
	    
	    //Autowired
	    //private EmailServiceImpl emailSender;
	    
	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    
	    @RequestMapping(value = "/delete", method = RequestMethod.POST)
	    public void deleteUser(@AuthenticationPrincipal UserDetails userDetails) {
	    	userDetailsService.deleteUserByUsername(userDetails.getUsername());
	    }
	    
	    /**
	     * 
	     * @param authenticationRequest
	     * @return the newly registered users information 
	     * @throws Exception
	     */
	    @RequestMapping(value = "/register", method = RequestMethod.POST)
	    public UserDetails registerUser(@RequestBody JwtRequest authenticationRequest) throws Exception {
	    	
	    	if (authenticationRequest.getRole() == null || authenticationRequest.getEmail() == null || authenticationRequest.getPassword() == null) {
	    		return null;
	    	}
	    	if (this.userDetailsService.existsUsername(authenticationRequest.getUsername())) {
	    		return this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
	    	}
	    	final UserDetails userDetails = userDetailsService.createUserByUsername(authenticationRequest.getUsername(), authenticationRequest.getPassword(), authenticationRequest.getRole(), authenticationRequest.getEmail(), authenticationRequest.getFirstName(), authenticationRequest.getLastName());
	    	return userDetails;
	    }
	   
	    /**
	     * 
	     * @param authenticationRequest
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping(value = "/signin", method = RequestMethod.POST)
	    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
	    	authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
	    	final UserDetails userDetails = userDetailsService
	    			.loadUserByUsername(authenticationRequest.getUsername());
	    	final String token = jwtTokenUtil.generateToken(userDetails);
	    	return ResponseEntity.ok(new JwtResponse(token));
	    }
	    
	    /**
	     * 
	     * @param credentials
	     * @return
	     */
	    @RequestMapping(value = "/tmp-signin", method = RequestMethod.POST)
	    public ResponseEntity<?> createAuthenticationTokenWithTempPass(@RequestBody TempCredentials credentials){
	    	if (credentials.getTmpPassword().equals(null)) {
	    		return ResponseEntity.ok("Wrong password");
	    	}
	    	final UserDetails userDetails = userDetailsService
	    			.loadUserByUsername(credentials.getUsername());
	    	final User user = (User) userDetailsService.loadUserByUsername(credentials.getUsername());
	    	if (!user.getTmpPassword().equals(passwordEncoder.encode(credentials.getTmpPassword()))) {
	    		throw new AccessDeniedException("You don't have the correct temporary password");
	    	}
	    	user.setTmpPassword(null);
	    	userDetailsService.saveUser(user);
	    	final String token = jwtTokenUtil.generateToken(userDetails);
	    	return ResponseEntity.ok(new JwtResponse(token));
	    }
	    
	    /**
	     * 
	     * @param username
	     * @return
	     */
	    @RequestMapping(value = "/forgot-password/{username}", method = RequestMethod.POST)
	    public String forgotPassword(@PathVariable(name = "username") String username) {
	    	String tmpPassword = generateTmpPassword();
	    	User user = userDetailsService.getUser(username).get();
	    	user.setTmpPassword(passwordEncoder.encode(tmpPassword));
	    	this.userDetailsService.saveUser(user);
	    	try {
				//emailSender.send(user.getEmail(), "Temporary Password", tmpPassword);
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    		return e.toString();
	    	} 
	    	return "Email sent successfully";
	    }
	    
	    private String generateTmpPassword() {
	    	byte[] array = new byte[8];
	    	new Random().nextBytes(array);
	    	String generatedString = new String(array, Charset.forName("UTF-8"));
	    	return generatedString;
	    }
	    
	    /**
	     * 
	     * @param username
	     * @param password
	     * @throws Exception
	     */
	    private void authenticate(String username, String password) throws Exception {
	    	try {
	    		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	    	} catch (DisabledException e) {
	    		throw new Exception("USER_DISABLED", e);
	    	} catch (BadCredentialsException e) {
	    		throw new Exception("INVALID_CREDENTIALS", e);
	    	}
	    }
}