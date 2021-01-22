package tseo.eobrazovanje.repo;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tseo.eobrazovanje.model.Ispit;
import tseo.eobrazovanje.model.Predmet;
import tseo.eobrazovanje.model.Student;

@Repository
public interface IspitRepository extends JpaRepository<Ispit, Long>  {
	
	@Query("Select i from Ispit i where i.rokZaPrijavu > :datum " + "and :student member of i.predmet.studenti "
			+ "and not exists (select p from Prijava p where p.ispit = i and p.student = :student) "
			+ "and not exists(select p from Prijava p where p.student = :student and p.ispit.predmet = i.predmet and p.polozio = true)")
	List<Ispit> findByStudentAndDatum(@Param("student") Student student, @Param("datum") Date date);

	Page<Ispit> findByPredmet(Predmet predmet, Pageable pageable);
	
	List<Ispit> findByPredmet(Predmet predmet);

	Page<Ispit> findByPredmetAndNastavnikId(Predmet predmet, Long nastavnikId, Pageable pageable);

	List<Ispit> findByPredmetAndNastavnikId(Predmet predmet, Long nastavnikId);
	
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Ispit u WHERE u.id = :ispitid")
	void obrisiIspit(@Param("ispitid") Long ispitid);

}
