package tseo.eobrazovanje.repo;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tseo.eobrazovanje.model.Dokument;
import tseo.eobrazovanje.model.Student;

@Repository
public interface DokumentRepository extends JpaRepository<Dokument, Long> {
	
	Page<Dokument> findAllByNazivIgnoreCaseContains(String naziv, Pageable pageable);

	Page<Dokument> findByStudent(Student student, Pageable pageable);

}
