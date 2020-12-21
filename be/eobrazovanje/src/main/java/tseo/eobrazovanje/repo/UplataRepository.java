package tseo.eobrazovanje.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tseo.eobrazovanje.model.Student;
import tseo.eobrazovanje.model.Uplata;

@Repository
public interface UplataRepository extends JpaRepository<Uplata, Long> {

	Page<Uplata> findAll(Pageable pageable);

	Page<Uplata> findByStudent(Student student, Pageable pageable);


}
