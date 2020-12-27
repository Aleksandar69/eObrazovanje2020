package tseo.eobrazovanje.model;

import java.util.Set;

import javax.persistence.CascadeType;
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

import tseo.eobrazovanje.enumeration.Role;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Nastavnik extends User {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private long nastavnikid;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable
	@JsonIgnore
	private Set<Predmet> predmeti;
	@OneToMany(mappedBy = "nastavnik", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Ispit> ispiti;

	public Nastavnik() {

	}

	public Nastavnik(Long id, String username, String password, String ime, String prezime, String jmbg,
			String adresa, String rola, String[] autorities) {
		super(id, username, password, ime, prezime, jmbg, adresa, Role.NASTAVNIK.name(), Role.NASTAVNIK.getAuthorities());
	}

	public Set<Predmet> getPredmeti() {
		return predmeti;
	}

	public Set<Ispit> getIspiti() {
		return ispiti;
	}

	public void setPredmeti(Set<Predmet> predmeti) {
		this.predmeti = predmeti;
	}

	public void setIspiti(Set<Ispit> ispiti) {
		this.ispiti = ispiti;
	}

}
