package tseo.eobrazovanje.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tseo.eobrazovanje.model.PredispitneObaveze;

@Repository
public interface PredispitneObavezeRepository extends JpaRepository<PredispitneObaveze, Long> {

}
