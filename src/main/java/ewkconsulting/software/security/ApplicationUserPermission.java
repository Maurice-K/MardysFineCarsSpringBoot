package ewkconsulting.software.security;

/**
 * 
 * @author Damond Howard
 * @apiNote Enumeration containing certain application user permissions has sets
 *
 */
public enum ApplicationUserPermission {
	STORE_READ("store:read"),
	STORE_WRITE("store:write"),
	CUSTOMER_READ("customer:read"),
	CUSTOMER_WRITE("customer:write"),
	DRIVER_READ("driver:read"),
	DRIVER_WRITE("driver:write"),
	INVENTORY_READ("inventory:read"),
	INVENTORY_WRITE("inventory:write"),
	EMPLOYEE_READ("employee:read"),
	EMPLOYEE_WRITE("employee:write"),
	FILE_READ("file:read"),
	FILE_WRITE("file:write"),
	BEAUTY_HERO_READ("beauty:read"),
	BEAUTY_HERO_WRITE("beauty:write"),
	ORDER_READ("order:read"),
	ORDER_WRITE("order:write"),
	PAYMENT_WRITE("payment:write"),
	PAYMENT_READ("payment:read");

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