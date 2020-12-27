package tseo.eobrazovanje.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tseo.eobrazovanje.model.Nastavnik;

@Repository
public interface NastavnikRepository extends JpaRepository<Nastavnik, Long> {

	Page<Nastavnik> findAllByImeIgnoreCaseContainsAndPrezimeIgnoreCaseContains(String ime, String prezime,
			Pageable pageable);
	
	List<Nastavnik> findAllByImeIgnoreCaseContainsAndPrezimeIgnoreCaseContains(String ime, String prezime);

}
