package tseo.eobrazovanje.repo;

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
import tseo.eobrazovanje.model.PredispitneObaveze;
import tseo.eobrazovanje.model.Prijava;
import tseo.eobrazovanje.model.Student;

@Repository
public interface PrijavaRepository extends JpaRepository<Prijava, Long> {

	Page<Prijava> findAll(Pageable pageable);

	Page<Prijava> findByStudent(Student student, Pageable pageable);

	Page<Prijava> findByIspit(Ispit ispit, Pageable pageable);


	List<Prijava> findByStudent(Student student);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Prijava u WHERE u.ispit = :ispit")
	void obrisiPrijavu(@Param("ispit") Ispit ispit);


}
