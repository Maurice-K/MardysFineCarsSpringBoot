package ewkconsulting.software.security;

/**
 * 
 * @author Damond Howard
 * @apiNote Enumeration containing certain application user permissions has sets
 *
 */
public enum ApplicationUserPermission {
	CAR_READ("car:read"),
	CAR_WRITE("car:write"),
	DEAL_JACKET_READ("dealjacket:read"),
	DEAL_JACKET_WRITE("dealjacket:write");

	private final String permission;
	
	/**
	 * 
	 * @param creates a set that represents a permission
	 */
	ApplicationUserPermission(String permission){
		this.permission = permission;
	}
	
	/**
	 * 
	 * @return the permission 
	 */
	public String getPermission() {
		return permission;
	}
}