package tseo.eobrazovanje.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tseo.eobrazovanje.model.PredispitneObaveze;

@Repository
public interface PredispitneObavezeRepository extends JpaRepository<PredispitneObaveze, Long> {

	
	@Transactional
	@Modifying
	@Query("DELETE FROM PredispitneObaveze u WHERE u.id = :sablonid")
	void obrisiPrepdispitneObaveze(@Param("sablonid") Long sablonid);
	
}
