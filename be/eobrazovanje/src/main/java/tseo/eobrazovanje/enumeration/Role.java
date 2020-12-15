package tseo.eobrazovanje.enumeration;

import static tseo.eobrazovanje.constant.Authority.*;

public enum Role {
	
	STUDENT(USER_AUTHORITIES),
    NASTAVNIK(ADMIN_AUTHORITIES),
    ADMINISTRATOR(SUPER_ADMIN_AUTHORITIES);

    private String[] authorities;

    Role(String... authorities) {
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return authorities;
    }


}
