package tseo.eobrazovanje.repo;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tseo.eobrazovanje.model.Predmet;

public interface PredmetRepository extends JpaRepository<Predmet, Long> {

	Page<Predmet> findAllByNazivIgnoreCaseContains(String naziv, Pageable pageable);

	Predmet findByOznaka(String oznaka);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Predmet u WHERE u.id = :id")
	void obrisiPredmet(@Param("id") Long id);

}
