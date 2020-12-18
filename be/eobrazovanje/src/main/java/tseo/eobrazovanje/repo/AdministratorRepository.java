package tseo.eobrazovanje.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tseo.eobrazovanje.model.Admin;

@Repository
public interface AdministratorRepository extends JpaRepository<Admin, Long> {

}
