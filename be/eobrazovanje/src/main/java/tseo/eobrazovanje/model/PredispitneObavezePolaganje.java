package tseo.eobrazovanje.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

@Entity
public class PredispitneObavezePolaganje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private Date datum;
	@NotNull
	private Float osvojeniBodovi;
	@NotNull
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn
	private Student student;
	@NotNull
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn
	private PredispitneObaveze sablon;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn
	private Predmet predmet;
	private boolean polozio;

	public PredispitneObavezePolaganje() {

	}

	public PredispitneObavezePolaganje(Long id, Date datum, Float osvojeniBodovi, Student student,
			PredispitneObaveze sablon, boolean polozio) {
		super();
		this.id = id;
		this.datum = datum;
		this.osvojeniBodovi = osvojeniBodovi;
		this.student = student;
		this.sablon = sablon;
		this.polozio = polozio;
	}

	public Long getId() {
		return id;
	}

	public Date getDatum() {
		return datum;
	}

	public Float getOsvojeniBodovi() {
		return osvojeniBodovi;
	}

	public Student getStudent() {
		return student;
	}

	public PredispitneObaveze getSablon() {
		return sablon;
	}

	public boolean isPolozio() {
		return polozio;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public void setOsvojeniBodovi(Float osvojeniBodovi) {
		this.osvojeniBodovi = osvojeniBodovi;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setSablon(PredispitneObaveze sablon) {
		this.sablon = sablon;
	}

	public void setPolozio(boolean polozio) {
		this.polozio = polozio;
	}

	public Predmet getPredmet() {
		return predmet;
	}

	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}

	
}
