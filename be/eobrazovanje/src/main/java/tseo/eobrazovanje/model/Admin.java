package tseo.eobrazovanje.model;

import javax.persistence.Entity;

import tseo.eobrazovanje.enumeration.Role;

@Entity
public class Admin extends User {

	public Admin() {

	}

	public Admin(Long id, String username, String password, String ime, String prezime, String jmbg, String adresa, String rola, String[] authorities, String profileImageUrl) {
		super(id, username, password, ime, prezime, jmbg, adresa,
				Role.ADMINISTRATOR.name() /* ,Role.ADMINISTRATOR.getAuthorities() */, profileImageUrl);
	}

}
