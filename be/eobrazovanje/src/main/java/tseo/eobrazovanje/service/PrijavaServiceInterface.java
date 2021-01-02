package tseo.eobrazovanje.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tseo.eobrazovanje.dto.PrijavaDto;
import tseo.eobrazovanje.model.Prijava;
import tseo.eobrazovanje.model.Student;

public interface PrijavaServiceInterface {
	
	Page<Prijava> findAll(Pageable pageable);

	Prijava findOne(Long id);

	Prijava save(Prijava prijava);

	Boolean delete(Long id);

	Prijava save(PrijavaDto dto);

	Prijava update(PrijavaDto dto);

	Set<Prijava> getPolozeniPredmeti(Student student);

	Page<Prijava> getPrijavaByStudent(Student student, Pageable pageable);

	List<Prijava> getPrijavljeniIspiti(Student student);

}
