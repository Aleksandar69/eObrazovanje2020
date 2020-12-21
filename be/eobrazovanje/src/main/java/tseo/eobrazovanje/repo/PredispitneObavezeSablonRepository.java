package tseo.eobrazovanje.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tseo.eobrazovanje.model.PredispitneObavezeSablon;

@Repository
public interface PredispitneObavezeSablonRepository extends JpaRepository<PredispitneObavezeSablon, Long> {

}
