package tseo.eobrazovanje.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
public class PredispitneObaveze {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@ManyToOne
	@JoinColumn
	private Predmet predmet;
	@NotNull
	private int ukupnoBodova;
	@NotNull
	private int minimumBodova;
	private String naziv;
	@OneToMany(mappedBy = "sablon", cascade =CascadeType.ALL)
	@JsonIgnore
	private Set<PredispitneObavezePolaganje> polaganja;

	public PredispitneObaveze() {
	}

	public PredispitneObaveze(Long id, Predmet predmet, int ukupnoBodova, int minimumBodova, String naziv,
			Set<PredispitneObavezePolaganje> polaganja) {
		super();
		this.id = id;
		this.predmet = predmet;
		this.ukupnoBodova = ukupnoBodova;
		this.minimumBodova = minimumBodova;
		this.naziv = naziv;
		this.polaganja = polaganja;
	}
	
	public void remove(PredispitneObavezePolaganje pop) {
		polaganja.remove(pop);
		pop.setSablon(null);
	}

    public void dismissChild(PredispitneObavezePolaganje polaganja) {
        this.polaganja.remove(polaganja);
    }

    public void dismissChildren() {
       this.polaganja.forEach(child -> child.dismissParent()); // SYNCHRONIZING THE OTHER SIDE OF RELATIONSHIP 
       this.polaganja.clear();
    }
    
	public Long getId() {
		return id;
	}

	public Predmet getPredmet() {
		return predmet;
	}

	public int getUkupnoBodova() {
		return ukupnoBodova;
	}

	public int getMinimumBodova() {
		return minimumBodova;
	}

	public String getNaziv() {
		return naziv;
	}

	public Set<PredispitneObavezePolaganje> getPolaganja() {
		return polaganja;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}

	public void setUkupnoBodova(int ukupnoBodova) {
		this.ukupnoBodova = ukupnoBodova;
	}

	public void setMinimumBodova(int minimumBodova) {
		this.minimumBodova = minimumBodova;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public void setPolaganja(Set<PredispitneObavezePolaganje> polaganja) {
		this.polaganja = polaganja;
	}
}
