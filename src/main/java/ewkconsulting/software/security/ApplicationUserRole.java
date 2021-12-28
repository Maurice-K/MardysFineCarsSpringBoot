package ewkconsulting.software.security;

import java.util.Set;
import java.util.stream.Collectors;
import com.google.common.collect.Sets;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import static ewkconsulting.software.security.ApplicationUserPermission.*;
/**
 * 
 * @author Damond Howard
 * @apiNote this Enumeration contains user roles
 *
 */
public enum ApplicationUserRole {
	ADMIN(Sets.newHashSet(CAR_READ, CAR_WRITE, DEAL_JACKET_READ, DEAL_JACKET_WRITE)),
	CUSTOMER(Sets.newHashSet(CAR_READ));

	private final Set<ApplicationUserPermission> permissions;
	
	/**
	 * 
	 * @param permissions a set containing the user permissions based off ApplicationUserPermission enum
	 */
	ApplicationUserRole(Set<ApplicationUserPermission> permissions){
		this.permissions = permissions;
	}
	
	/**
	 * 
	 * @return the set containing the user permissions based off ApplicationUserPermission enum
	 */
	public Set<ApplicationUserPermission> getPermissions(){
		return permissions;
	}
	
	/**
	 * 
	 * @return the user's granted Authorities based off the users permissions
	 */
	public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
		Set<SimpleGrantedAuthority> permissions = 
		getPermissions().stream()
			.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
			.collect(Collectors.toSet());
		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return permissions;
	}
}