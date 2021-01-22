package tseo.eobrazovanje.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tseo.eobrazovanje.model.Ispit;
import tseo.eobrazovanje.model.PredispitneObaveze;
import tseo.eobrazovanje.model.PredispitneObavezePolaganje;

@Repository
public interface PredispitneObavezePolaganjeRepository extends JpaRepository<PredispitneObavezePolaganje, Long> {
	
	@Transactional
	@Modifying
	@Query("DELETE FROM PredispitneObavezePolaganje u WHERE u.sablon = :sablonid")
	void obrisiPredispitneObavezePolaganje(@Param("sablonid") PredispitneObaveze sablonid);

}
 