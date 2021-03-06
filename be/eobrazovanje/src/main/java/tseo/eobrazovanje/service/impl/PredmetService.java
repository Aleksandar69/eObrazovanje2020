package tseo.eobrazovanje.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tseo.eobrazovanje.model.Ispit;
import tseo.eobrazovanje.model.Nastavnik;
import tseo.eobrazovanje.model.PredispitneObaveze;
import tseo.eobrazovanje.model.PredispitneObavezePolaganje;
import tseo.eobrazovanje.model.Predmet;
import tseo.eobrazovanje.model.Prijava;
import tseo.eobrazovanje.model.Student;
import tseo.eobrazovanje.repo.PredmetRepository;
import tseo.eobrazovanje.service.PredmetServiceInterface;

@Service
public class PredmetService implements PredmetServiceInterface{

	@Autowired
	NastavnikService nastavnikService;

	@Autowired
	StudentService studentService;

	@Autowired
	PredmetRepository predmetRepository;

	@Autowired
	IspitService ispitiService;
	
	@Autowired
	PrijavaService prijavaService;
	
	@Autowired
	PredispitneObavezeService poService;
	
	@Autowired
	PredispitneObavezePolaganjeService popService;


	@Override
	public Page<Predmet> findAll(String naziv, Pageable pageable) {
		return predmetRepository.findAllByNazivIgnoreCaseContains(naziv, pageable);
	}

	@Override
	public Predmet findOne(Long id) {
		return predmetRepository.findById(id).get();
	}

	@Override
	public Predmet save(Predmet predmet) {
		return predmetRepository.save(predmet);
	}

	@Override
	public Boolean delete(Long id) {
		Predmet predmet = findOne(id);
		for (Ispit ispit : predmet.getIspiti()) {
			prijavaService.obrisiPrijavu(ispit);
			ispitiService.obrisiIspit(ispit.getId());
		}
		
		for(PredispitneObaveze po : predmet.getPredispitneObavezeSabloni()) {
			popService.obrisiPop(po);
			poService.obrisi(po.getId());
		}
		
		obrisiPredmet(id);
		return true;
	}

	@Override
	public Set<Nastavnik> getNastavnici(List<Long> nastavnici) {
		Set<Nastavnik> nastavniciObj = new HashSet<>();
		for (Long l : nastavnici) {
			Nastavnik n = nastavnikService.findOne(l);
			if (n != null) {
				nastavniciObj.add(n);
			}
		}
		return nastavniciObj;
	}

	@Override
	public Set<Student> getStudenti(List<Long> studenti) {
		Set<Student> studentiObj = new HashSet<>();
		for (Long l : studenti) {
			Student s = studentService.findOne(l);
			if (s != null) {
				studentiObj.add(s);
			}
		}
		return studentiObj;
	}

	@Override
	public Predmet addNastavnici(Predmet predmet, List<Long> nastavnici) {
		predmet.getNastavnici().forEach(n -> n.getPredmeti().remove(predmet));
		predmet.setNastavnici(getNastavnici(nastavnici));
		predmet.getNastavnici().forEach(n -> n.getPredmeti().add(predmet));
		return save(predmet);
	}

	@Override
	public Predmet addStudenti(Predmet predmet, List<Long> studenti) {
		predmet.getStudenti().forEach(n -> n.getPredmeti().remove(predmet));
		predmet.setStudenti(getStudenti(studenti));
		predmet.getStudenti().forEach(n -> n.getPredmeti().add(predmet));
		return save(predmet);
	}

	@Override
	public Boolean removeStudent(Predmet predmet, Student student) {
		if (predmet.getStudenti().contains(student)) {
			predmet.getStudenti().remove(student);
			student.getPredmeti().remove(predmet);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean removeNastavnik(Predmet predmet, Nastavnik nastavnik) {
		if (predmet.getNastavnici().contains(nastavnik)) {
			predmet.getNastavnici().remove(nastavnik);
			nastavnik.getPredmeti().remove(predmet);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Set<Predmet> getPolozeniPredmeti(Student student) {
		List<Prijava> prijave = prijavaService.getPrijavaByStudent(student);
		
		Set<Predmet> polozeniPredmeti = new HashSet<>();
		for (Prijava prijava : prijave) {
			if (prijava.isPolozio()) {
				polozeniPredmeti.add(prijava.getIspit().getPredmet());
			}
		}
		return polozeniPredmeti;
	}

	@Override
	public Set<Predmet> getNepolozeniPredmeti(Student student) {
		Set<Prijava> prijave = student.getPrijave();
		Set<Predmet> predmeti = student.getPredmeti();
		Set<Predmet> nepolozeni = new HashSet<>();
		for (Predmet predmet : predmeti) {
			boolean p = false;
			for (Prijava prijava : prijave) {
				if (prijava.getIspit().getPredmet() == predmet) {
					p = true;
					if (!prijava.isPolozio()) {
						nepolozeni.add(predmet);
					}
				}
			}
//			if (!p) {
//				nepolozeni.add(predmet);
//			}
		}
		return nepolozeni;
	}

	@Override
	public Predmet findByOznaka(String oznaka) {
		return predmetRepository.findByOznaka(oznaka);
	}

	@Override
	public void obrisiPredmet(Long id) {
		predmetRepository.obrisiPredmet(id);
	}

}
