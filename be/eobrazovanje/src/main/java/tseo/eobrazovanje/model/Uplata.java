package tseo.eobrazovanje.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

@Entity
public class Uplata{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private Date datumUplate;
	@NotNull
	private double iznosUplate;
	@NotNull
	private String racunPrimaoca;
	@NotNull
	private String pozivNaBroj;
	private String svrhaUplate;
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	public Uplata() {
	}

	public Uplata(Long id, Date datumUplate, double iznosUplate, String racunPrimaoca, String pozivNaBroj,
			String svrhaUplate, Student student) {
		this.id = id;
		this.datumUplate = datumUplate;
		this.iznosUplate = iznosUplate;
		this.racunPrimaoca = racunPrimaoca;
		this.pozivNaBroj = pozivNaBroj;
		this.svrhaUplate = svrhaUplate;
		this.student = student;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatumUplate() {
		return datumUplate;
	}

	public void setDatumUplate(Date datumUplate) {
		this.datumUplate = datumUplate;
	}

	public double getIznosUplate() {
		return iznosUplate;
	}

	public void setIznosUplate(double iznosUplate) {
		this.iznosUplate = iznosUplate;
	}

	public String getRacunPrimaoca() {
		return racunPrimaoca;
	}

	public void setRacunPrimaoca(String racunPrimaoca) {
		this.racunPrimaoca = racunPrimaoca;
	}

	public String getPozivNaBroj() {
		return pozivNaBroj;
	}

	public void setPozivNaBroj(String pozivNaBroj) {
		this.pozivNaBroj = pozivNaBroj;
	}

	public String getSvrhaUplate() {
		return svrhaUplate;
	}

	public void setSvrhaUplate(String svrhaUplate) {
		this.svrhaUplate = svrhaUplate;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	

}
