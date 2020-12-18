package tseo.eobrazovanje.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import tseo.eobrazovanje.model.Predmet;

public interface PredmetRepository extends JpaRepository<Predmet, Long> {

	Page<Predmet> findAllByNazivIgnoreCaseContains(String naziv, Pageable pageable);

	Predmet findByOznaka(String oznaka);

}
