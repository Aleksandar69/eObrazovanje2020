package tseo.eobrazovanje.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import tseo.eobrazovanje.model.Student;

@Entity
public class Predmet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String naziv;
	@NotNull
	private String oznaka;
	@NotNull
	private int espb;
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "predmeti")
	@JsonIgnore
	private Set<Nastavnik> nastavnici;
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "predmeti")
	@JsonIgnore
	private Set<Student> studenti;
	@OneToMany(mappedBy = "predmet", cascade = { CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.DETACH })
	@JsonIgnore
	private Set<PredispitneObavezePolaganje> predispitneObaveze;
	@OneToMany(mappedBy = "predmet",  cascade = { CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.DETACH }, fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<PredispitneObaveze> predispitneObavezeSabloni;
	@OneToMany(mappedBy = "predmet", cascade = { CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.DETACH }, fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Ispit> ispiti;

	public Predmet() {

	}

	public Predmet(Long id, String naziv, String oznaka, int espb) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.oznaka = oznaka;
		this.espb = espb;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public int getEspb() {
		return espb;
	}

	public void setEspb(int espb) {
		this.espb = espb;
	}

	public Set<Nastavnik> getNastavnici() {
		return nastavnici;
	}

	public Set<Student> getStudenti() {
		return studenti;
	}

	public void setNastavnici(Set<Nastavnik> nastavnici) {
		this.nastavnici = nastavnici;
	}

	public void setStudenti(Set<Student> studenti) {
		this.studenti = studenti;
	}

	public Set<PredispitneObavezePolaganje> getPredispitneObaveze() {
		return predispitneObaveze;
	}

	public Set<Ispit> getIspiti() {
		return ispiti;
	}

	public void setPredispitneObaveze(Set<PredispitneObavezePolaganje> predispitneObaveze) {
		this.predispitneObaveze = predispitneObaveze;
	}

	public void setIspiti(Set<Ispit> ispiti) {
		this.ispiti = ispiti;
	}

	public Set<PredispitneObaveze> getPredispitneObavezeSabloni() {
		return predispitneObavezeSabloni;
	}

	public void setPredispitneObavezeSabloni(Set<PredispitneObaveze> predispitneObavezeSabloni) {
		this.predispitneObavezeSabloni = predispitneObavezeSabloni;
	}

	@Override
	public String toString() {
		return "Naziv predmeta: " + naziv + ", Oznaka predmeta :" + oznaka + ", ESPB bodovi: " + espb + ".";
	}
	

}
