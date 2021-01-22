package tseo.eobrazovanje.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import tseo.eobrazovanje.enumeration.Role;

@Entity
public class RegistracijaZahtev {
	
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
	@NotNull
	private String brojIndexa;
	@NotNull
	private String tekuciRacun;
	private Double stanje;
//	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
//	@JsonIgnore
//	private Set<Dokument> dokumenti;
//	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
//	@JsonIgnore
//	private Set<Uplata> uplate;
//	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
//	@JoinTable(name = "predmet_studenti")
//	@JsonIgnore
//	private Set<Predmet> predmeti;
//	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JsonIgnore
//	private Set<Prijava> prijave;
//	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JsonIgnore
//	private Set<PredispitneObaveze> predispitneObaveze;
//	@Column(unique=true)
	private String brojTelefona;
	boolean odobren;
	

	public RegistracijaZahtev() {}


	
	public RegistracijaZahtev(String username, String password, String ime, String prezime, String jmbg,
			String adresa, String role,  String brojIndexa, String tekuciRacun, Double stanje,
//			Set<Dokument> dokumenti, Set<Uplata> uplate, Set<Predmet> predmeti, Set<Prijava> prijave,
//			Set<PredispitneObaveze> predispitneObaveze, String[] authorities,
			String brojTelefona) {
		super();
		this.username = username;
		this.password = password;
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.adresa = adresa;
		this.role = role;
		//this.authorities = authorities;
		this.brojIndexa = brojIndexa;
		this.tekuciRacun = tekuciRacun;
		this.stanje = stanje;
//		this.dokumenti = dokumenti;
//		this.uplate = uplate;
//		this.predmeti = predmeti;
//		this.prijave = prijave;
//		this.predispitneObaveze = predispitneObaveze;
		this.brojTelefona = brojTelefona;
	}

	public String getBrojIndexa() {
		return brojIndexa;
	}

	public RegistracijaZahtev setBrojIndexa(String brojIndexa) {
		this.brojIndexa = brojIndexa;
		return this;
	}
//
//	public Set<Dokument> getDokumenti() {
//		return dokumenti;
//	}
//
//	public Set<Uplata> getUplate() {
//		return uplate;
//	}
//
//	public Set<Predmet> getPredmeti() {
//		return predmeti;
//	}
//
//	public Set<Prijava> getPrijave() {
//		return prijave;
//	}
//
//	public Set<PredispitneObaveze> getPredispitneObaveze() {
//		return predispitneObaveze;
//	}
//
//	public void setDokumenti(Set<Dokument> dokumenti) {
//		this.dokumenti = dokumenti;
//	}
//
//	public void setUplate(Set<Uplata> uplate) {
//		this.uplate = uplate;
//	}
//
//	public void setPredmeti(Set<Predmet> predmeti) {
//		this.predmeti = predmeti;
//	}
//
//	public void setPrijave(Set<Prijava> prijave) {
//		this.prijave = prijave;
//	}
//
//	public void setPredispitneObaveze(Set<PredispitneObaveze> predispitneObaveze) {
//		this.predispitneObaveze = predispitneObaveze;
//	}

	public String getTekuciRacun() {
		return tekuciRacun;
	}

	public void setTekuciRacun(String tekuciRacun) {
		this.tekuciRacun = tekuciRacun;
	}

	public Double getStanje() {
		return stanje;
	}

	public void setStanje(Double stanje) {
		this.stanje = stanje;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
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

	public RegistracijaZahtev setId(Long id) {
		this.id = id;
		return this;
	}

	public RegistracijaZahtev setIme(String ime) {
		this.ime = ime;
		return this;
	}

	public RegistracijaZahtev setPrezime(String prezime) {
		this.prezime = prezime;
		return this;
	}

	public RegistracijaZahtev setJmbg(String jmbg) {
		this.jmbg = jmbg;
		return this;
	}

	public RegistracijaZahtev setRole(String role) {
		this.role = role;
		return this;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

//	public String[] getAuthorities() {
//		return authorities;
//	}
//
//	public void setAuthorities(String[] authorities) {
//		this.authorities = authorities;
//	}

	


	public String getUsername() {
		return username;
	}



	public String getPassword() {
		return password;
	}



	public boolean isOdobren() {
		return odobren;
	}



	public void setOdobren(boolean odobren) {
		this.odobren = odobren;
	}
	
	

}
