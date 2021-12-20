package ewkconsulting.software.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import ewkconsulting.software.security.models.User;
import ewkconsulting.software.security.repositories.UserRepository;

@Component
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
    private UserRepository users;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
    public JwtUserDetailsService(UserRepository users) {
        this.users = users;
    }

    public JwtUserDetailsService() {
    	super();
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.users.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
    }
    
    public void deleteUserByUsername(String username) throws UsernameNotFoundException {
    	this.users.deleteByUsername(username);
    }
    
    public UserDetails createUserByUsername(String username, String password, String role, String email, String firstName, String lastName) {
    	if (username.equals(null) || role.equals(null) || email.equals(null) || firstName.equals(null) || lastName.equals(null)) {
    		return null;
    	}
    	User newUser = User.builder()
				.username(username)
				.password(passwordEncoder.encode(password))
				.email(email)
				.firstName(firstName)
				.lastName(lastName)
				.role("ROLE_" + role)
				.isAccountNonExpired(true)
				.isAccountNonLocked(true)
				.isCredentialsNonExpired(true)
				.isEnabled(true)
				.build();
    		this.users.save(newUser);
    		return newUser;
    }

	public boolean existsUsername(String username) {
		if (this.users.existsUserByUsername(username)) {
			return true;
		}
		return false;
	}

	public Optional<User> getUser(String username) {
		return this.users.findByUsername(username);
	}

	public void saveUser(User user) {
		this.users.save(user);	
	}
}