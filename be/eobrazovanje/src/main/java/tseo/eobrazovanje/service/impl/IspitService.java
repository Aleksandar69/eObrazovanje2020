package tseo.eobrazovanje.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tseo.eobrazovanje.dto.IspitDto;
import tseo.eobrazovanje.model.Ispit;
import tseo.eobrazovanje.model.PredispitneObavezePolaganje;
import tseo.eobrazovanje.model.Predmet;
import tseo.eobrazovanje.model.Student;
import tseo.eobrazovanje.repo.IspitRepository;
import tseo.eobrazovanje.service.IspitServiceInterface;
import tseo.eobrazovanje.service.PredispitneObavezeServiceInterface;

@Service
public class IspitService implements IspitServiceInterface {

	
	@Autowired
	IspitRepository ispitRepository;

	@Autowired
	NastavnikService nastavnikService;

	@Autowired
	StudentService studentService;

	@Autowired
	PredispitneObavezeServiceInterface sabloniService;

	@Autowired
	PrijavaService prijavaService;

	@Autowired
	PredmetService predmetService;

	@Override
	public List<Ispit> findAll() {
		return ispitRepository.findAll();
	}

	@Override
	public Ispit findOne(Long id) {
		Optional<Ispit> ispit = ispitRepository.findById(id);
		if(ispit.isPresent()) {
			return ispit.get();
		}
		else {
			return null;
		}
	}

	@Override
	public Ispit save(Ispit ispit) {
		ispitRepository.save(ispit);
		return ispit;
	}

	@Override
	public Boolean delete(Long id) {
		ispitRepository.deleteById(id);
		return true;
	}

	@Override
	public Ispit save(IspitDto dto) {
		Ispit ispit = new Ispit();
		ispit.setNastavnik(nastavnikService.findOne(dto.getNastavnik()));
		ispit.setDatum(dto.getDatum());
		ispit.setPredmet(predmetService.findOne(dto.getPredmet()));
		ispit.setRokZaPrijavu(dto.getRokZaPrijavu());
		if (ispit.getUsmeniMinimumBodova() == null && ispit.getUsmeniUkupnoBodova() == null) {
			ispit.setUsmeniMinimumBodova(0F);
			ispit.setUsmeniUkupnoBodova(0F);
		}
		ispit.setUsmeniMinimumBodova(dto.getUsmeniMinimumBodova());
		ispit.setUsmeniUkupnoBodova(dto.getUsmeniUkupnoBodova());
		return save(ispit);
	}

	@Override
	public Ispit update(IspitDto dto) {
		Ispit ispit = findOne(dto.getId());
		if (ispit == null) {
			return null;
		} else {
			ispit.setId(dto.getId());
			ispit.setNastavnik(nastavnikService.findOne(dto.getNastavnik()));
			ispit.setDatum(dto.getDatum());
			ispit.setPredmet(predmetService.findOne(dto.getPredmet()));
			ispit.setRokZaPrijavu(dto.getRokZaPrijavu());
			ispit.setUsmeniMinimumBodova(dto.getUsmeniMinimumBodova());
			ispit.setUsmeniUkupnoBodova(dto.getUsmeniUkupnoBodova());
			return save(ispit);
		}
	}

	@Override
	public List<Ispit> ispitiZaPrijavu(Student student) {

		Student s = studentService.findOne(student.getId());
		List<Ispit> ispiti = new ArrayList<>(ispitRepository.findByStudentAndDatum(s, new Date()));
		List<Ispit> ispitiZaPrijavu = new ArrayList<>();
		for (Ispit ispit : ispiti) {
			List<PredispitneObavezePolaganje> obaveze = studentService.getLatestPredispitneObaveze(student,
					ispit.getPredmet().getId(), new Date());
			if (((ispit.getPredmet().getPredispitneObavezeSabloni().size()) != obaveze.size()))
				continue;
			boolean polozio = true;
			for (PredispitneObavezePolaganje obaveza : obaveze) {
				if (!obaveza.isPolozio()) {
					polozio = false;
					break;
				}
			}
			if (polozio) {
				ispitiZaPrijavu.add(ispit);
			}
		}

		return ispitiZaPrijavu;
	}

	@Override
	public List<Ispit> nepolozeniIspiti(Student student) {

		Student s = studentService.findOne(student.getId());
		List<Ispit> ispiti = new ArrayList<>(ispitRepository.findByStudentAndDatum(s, new Date()));
		List<Ispit> nepolozeni = new ArrayList<>();
		for (Ispit ispit : ispiti) {
			nepolozeni.add(ispit);
		}

		return nepolozeni;
	}

	@Override
	public Page<Ispit> findByPredmet(Predmet predmet, Pageable pageable) {
		return ispitRepository.findByPredmet(predmet, pageable);
	}

	@Override
	public Page<Ispit> findByPredmetAndNastavnikId(Predmet predmet, Long nastavnikId, Pageable pageable) {
		return ispitRepository.findByPredmetAndNastavnikId(predmet, nastavnikId, pageable);
	}

	@Override
	public List<Ispit> findByPredmetAndNastavnikId(Predmet predmet, Long nastavnikId) {
		return ispitRepository.findByPredmetAndNastavnikId(predmet, nastavnikId);
	}

	@Override
	public List<Ispit> findByPredmet(Predmet predmet) {
		return ispitRepository.findByPredmet(predmet);
	}

	
}
