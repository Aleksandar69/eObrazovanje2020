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
public class Dokument {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private Date datumDokumenta;
	@NotNull
	private String naziv;
	@NotNull
	private String dokumentLokacija;
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	public Dokument() {
	}

	public Dokument(Long id, Date datumDokumenta, String naziv, String sadrzaj, Student student) {
		this.id = id;
		this.datumDokumenta = datumDokumenta;
		this.naziv = naziv;
		this.dokumentLokacija = sadrzaj;
		this.student = student;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatumDokumenta() {
		return datumDokumenta;
	}

	public void setDatumDokumenta(Date datumDokumenta) {
		this.datumDokumenta = datumDokumenta;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getSadrzaj() {
		return dokumentLokacija;
	}

	public void setSadrzaj(String sadrzaj) {
		this.dokumentLokacija = sadrzaj;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
