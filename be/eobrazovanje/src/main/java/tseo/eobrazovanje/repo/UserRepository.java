package tseo.eobrazovanje.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import tseo.eobrazovanje.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
