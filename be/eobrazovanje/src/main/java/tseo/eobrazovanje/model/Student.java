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
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import tseo.eobrazovanje.enumeration.Role;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Student extends User {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private long studentid;
	
	
	@NotNull
	private String brojIndexa;
	@NotNull
	private String tekuciRacun;
	private Double stanje;
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Dokument> dokumenti;
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Uplata> uplate;
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "predmet_studenti")
	@JsonIgnore
	private Set<Predmet> predmeti;
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Prijava> prijave;
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<PredispitneObavezePolaganje> predispitneObaveze;
	@Column(unique=true)
	private String brojTelefona;
	

	public Student() {

	}

	public Student(Long id, String brojIndexa, String tekuciRacun, Double stanje, String username, String password,
			String ime, String prezime, String jmbg, String adresa, String brojTelefona, String profileImageUrl) {
		super( id,  username,password, ime, prezime, jmbg,adresa,
				Role.STUDENT.name() /*,Role.STUDENT.getAuthorities()*/ ,profileImageUrl);
		this.brojIndexa = brojIndexa;
		this.tekuciRacun = tekuciRacun;
		this.stanje = stanje;
		this.brojTelefona = brojTelefona;
	}

	
	public String getBrojIndexa() {
		return brojIndexa;
	}

	public Student setBrojIndexa(String brojIndexa) {
		this.brojIndexa = brojIndexa;
		return this;
	}

	public Set<Dokument> getDokumenti() {
		return dokumenti;
	}

	public Set<Uplata> getUplate() {
		return uplate;
	}

	public Set<Predmet> getPredmeti() {
		return predmeti;
	}

	public Set<Prijava> getPrijave() {
		return prijave;
	}

	public Set<PredispitneObavezePolaganje> getPredispitneObaveze() {
		return predispitneObaveze;
	}

	public void setDokumenti(Set<Dokument> dokumenti) {
		this.dokumenti = dokumenti;
	}

	public void setUplate(Set<Uplata> uplate) {
		this.uplate = uplate;
	}

	public void setPredmeti(Set<Predmet> predmeti) {
		this.predmeti = predmeti;
	}

	public void setPrijave(Set<Prijava> prijave) {
		this.prijave = prijave;
	}

	public void setPredispitneObaveze(Set<PredispitneObavezePolaganje> predispitneObaveze) {
		this.predispitneObaveze = predispitneObaveze;
	}

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


}
