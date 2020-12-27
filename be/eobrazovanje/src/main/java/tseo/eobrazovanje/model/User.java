package tseo.eobrazovanje.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import tseo.eobrazovanje.enumeration.Role;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	protected String username;
	protected String password;
	protected String ime;
	protected String prezime;
	protected String jmbg;
	protected String adresa;
//	@Enumerated(EnumType.STRING)
	protected String role;
    private String[] authorities;

	public User() {

	}

	public User(Long id, String username, String password, String ime, String prezime, String jmbg, String adresa,
			String role, String[] authorities) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.role = role;
		this.username = username;
		this.password = password;
		this.adresa = adresa;
        this.authorities = authorities;

	}

	public String getUsername() {
		return username;
	}

//	@JsonIgnore
//	@JsonProperty(value = "password")
	public String getPassword() {
		return password;
	}

	

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getIme() {
		return ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public String getJmbg() {
		return jmbg;
	}

	public String getRole() {
		return role;
	}

	public User setId(Long id) {
		this.id = id;
		return this;
	}

	public User setIme(String ime) {
		this.ime = ime;
		return this;
	}

	public User setPrezime(String prezime) {
		this.prezime = prezime;
		return this;
	}

	public User setJmbg(String jmbg) {
		this.jmbg = jmbg;
		return this;
	}

	public User setRole(String role) {
		this.role = role;
		return this;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String[] getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String[] authorities) {
		this.authorities = authorities;
	}

}
