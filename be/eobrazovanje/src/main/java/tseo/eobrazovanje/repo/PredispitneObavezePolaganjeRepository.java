package tseo.eobrazovanje.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tseo.eobrazovanje.model.PredispitneObavezePolaganje;

@Repository
public interface PredispitneObavezePolaganjeRepository extends JpaRepository<PredispitneObavezePolaganje, Long> {

}
