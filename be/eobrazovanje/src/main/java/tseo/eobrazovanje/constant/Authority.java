package tseo.eobrazovanje.constant;

import org.springframework.boot.jackson.JsonObjectSerializer;

public class Authority {
	private String[] preJasonUser = {"user:read"};
	
    public static final String[] USER_AUTHORITIES = { "user:read" };
    public static final String[] MANAGER_AUTHORITIES = { "user:read", "user:update" };
    public static final String[] ADMIN_AUTHORITIES = { "user:read", "user:create", "user:update" };
    public static final String[] SUPER_ADMIN_AUTHORITIES = { "user:read", "user:create", "user:update", "user:delete" };
    
    
}
