package tseo.eobrazovanje.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tseo.eobrazovanje.dto.UplataDto;
import tseo.eobrazovanje.model.Student;
import tseo.eobrazovanje.model.Uplata;
import tseo.eobrazovanje.repo.UplataRepository;
import tseo.eobrazovanje.service.UplataServiceInterface;

@Service
public class UplataService implements UplataServiceInterface{

	
	@Autowired
	UplataRepository uplataRepository;

	@Autowired
	StudentService studentService;

	@Override
	public Page<Uplata> findAll(Pageable pageable) {
		return uplataRepository.findAll(pageable);
	}

	@Override
	public Uplata findOne(Long id) {
		return uplataRepository.findById(id).get();
	}

	@Override
	public Uplata save(Uplata uplata) {

		return uplataRepository.save(uplata);
	}

	@Override
	public Boolean delete(Long id) {
		uplataRepository.deleteById(id);
		return true;
	}

	@Override
	public Uplata save(UplataDto dto) {
		Uplata uplata = new Uplata();
		uplata.setDatumUplate(new Date());
		uplata.setIznosUplate(dto.getIznosUplate());
		uplata.setPozivNaBroj(dto.getPozivNaBroj());
		uplata.setRacunPrimaoca(dto.getRacunPrimaoca());
		uplata.setSvrhaUplate(dto.getSvrhaUplate());

		Student student = studentService.findByTekuciRacun(dto.getRacunPrimaoca());
		uplata.setStudent(student);

		if (student != null) {
			if (student.getStanje() == null) {
				student.setStanje(0d);
			}
			student.setStanje(student.getStanje() + uplata.getIznosUplate());
			student.getUplate().add(uplata);
		}

		return save(uplata);
	}

	@Override
	public Page<Uplata> findByStudent(Student student, Pageable pageable) {
		return uplataRepository.findByStudent(student, pageable);
	}
	
}
