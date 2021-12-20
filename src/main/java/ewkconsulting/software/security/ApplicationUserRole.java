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
	ADMIN(Sets.newHashSet(STORE_READ, STORE_WRITE, CUSTOMER_READ, CUSTOMER_WRITE, DRIVER_READ, 
			DRIVER_WRITE, INVENTORY_READ, INVENTORY_WRITE, EMPLOYEE_READ, EMPLOYEE_WRITE, 
			FILE_READ, FILE_WRITE, BEAUTY_HERO_READ, BEAUTY_HERO_WRITE, ORDER_READ, ORDER_WRITE)),
	DRIVER(Sets.newHashSet(DRIVER_READ, DRIVER_WRITE, FILE_READ, FILE_WRITE)),
	EMPLOYEE(Sets.newHashSet(EMPLOYEE_READ, EMPLOYEE_WRITE, INVENTORY_READ, INVENTORY_WRITE)),
	OWNER(Sets.newHashSet(STORE_READ, STORE_WRITE, DRIVER_READ, EMPLOYEE_READ, EMPLOYEE_WRITE, 
			FILE_READ, FILE_WRITE, ORDER_READ, ORDER_WRITE)),
	CUSTOMER(Sets.newHashSet(CUSTOMER_READ, CUSTOMER_WRITE, FILE_READ, FILE_WRITE, ORDER_READ, ORDER_WRITE)),
	LAST_MIN_HAIR(Sets.newHashSet(BEAUTY_HERO_READ, BEAUTY_HERO_WRITE, CUSTOMER_READ, DRIVER_READ, 
			STORE_READ, FILE_READ, FILE_WRITE, ORDER_READ, ORDER_WRITE));

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