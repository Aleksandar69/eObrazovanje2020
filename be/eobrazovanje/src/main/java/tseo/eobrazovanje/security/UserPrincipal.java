package tseo.eobrazovanje.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import static java.util.Arrays.stream;

import java.util.ArrayList;

import tseo.eobrazovanje.enumeration.Role;
import tseo.eobrazovanje.model.User;

public class UserPrincipal implements UserDetails {

	private User user;

	public UserPrincipal(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		
		if(StringUtils.equals(user.getRole(), Role.ADMINISTRATOR.name())){
		return stream(Role.ADMINISTRATOR.getAuthorities()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		}
		else if(StringUtils.equals(user.getRole(), Role.NASTAVNIK.name())) {
			return stream(Role.NASTAVNIK.getAuthorities()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		}
		else if(StringUtils.equals(user.getRole(), Role.STUDENT.name())) {
			return stream(Role.STUDENT.getAuthorities()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		}
		System.out.println("ROLE: " + user.getRole());
		System.out.println("ADMIN: " + Role.ADMINISTRATOR.name());
		System.out.println("vraca null");
		return null;

	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
