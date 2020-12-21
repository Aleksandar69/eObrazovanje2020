package tseo.eobrazovanje.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tseo.eobrazovanje.dto.UplataDto;
import tseo.eobrazovanje.model.Student;
import tseo.eobrazovanje.model.Uplata;

public interface UplataServiceInterface {
	
	Uplata findOne(Long id);

	Uplata save(Uplata uplata);

	Boolean delete(Long id);

	Uplata save(UplataDto dto);

	Page<Uplata> findAll(Pageable pageable);

	Page<Uplata> findByStudent(Student student, Pageable pageable);

}
