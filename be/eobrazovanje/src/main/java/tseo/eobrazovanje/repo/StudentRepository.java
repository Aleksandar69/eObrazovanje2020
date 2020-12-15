package tseo.eobrazovanje.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import tseo.eobrazovanje.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

	
}
