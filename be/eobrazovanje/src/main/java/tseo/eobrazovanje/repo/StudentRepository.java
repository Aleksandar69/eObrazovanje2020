package tseo.eobrazovanje.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tseo.eobrazovanje.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	Student findByTekuciRacun(String tekuciRacun);

	Page<Student> findAllByImeIgnoreCaseContainsAndPrezimeIgnoreCaseContains(String ime, String prezime,
			Pageable pageable);

	Student findOneByBrojTelefona(String brojTelefona);
	
}
