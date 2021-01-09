package tseo.eobrazovanje.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tseo.eobrazovanje.dto.StudentDto;
import tseo.eobrazovanje.model.PredispitneObavezePolaganje;
import tseo.eobrazovanje.model.Student;

public interface StudentServiceInterface {
	
	Student findOne(Long id);

	Student save(Student student);

	Boolean delete(Long id);

	Student findByTekuciRacun(String tekuciRacun);

	Student update(StudentDto dto);
	
	List<Student> findAllList();

	List<PredispitneObavezePolaganje> getLatestPredispitneObaveze(Student student, Long predmetId, Date datum);

	Page<Student> findAll(String ime, String prezime, Pageable pageable);

	Student changePassword(Student student);

	Student create(Student student);
	
	Student findOneByBrojTelefona(String brojTelefona);
	
	StudentDto studentDtoMaker(Student student);

}
