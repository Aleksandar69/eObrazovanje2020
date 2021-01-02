package tseo.eobrazovanje.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tseo.eobrazovanje.model.RegistracijaZahtev;

@Repository
public interface RegistracijaZahtevRepository extends JpaRepository<RegistracijaZahtev, Long> {

	
	Page<RegistracijaZahtev> findAllByImeIgnoreCaseContainsAndPrezimeIgnoreCaseContains(String ime, String prezime,
			Pageable pageable);
	
	Page<RegistracijaZahtev> findAllByImeIgnoreCaseContainsAndPrezimeIgnoreCaseContainsAndOdobrenFalse(String ime, String prezime,
			Pageable pageable);
}
