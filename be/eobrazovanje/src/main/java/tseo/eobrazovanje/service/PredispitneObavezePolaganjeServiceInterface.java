package tseo.eobrazovanje.service;

import java.util.List;

import tseo.eobrazovanje.dto.PredispitneObavezePolaganjeDto;
import tseo.eobrazovanje.model.PredispitneObavezePolaganje;

public interface PredispitneObavezePolaganjeServiceInterface {

	List<PredispitneObavezePolaganje> findAll();
	
	PredispitneObavezePolaganje findOne(Long id);
	
	PredispitneObavezePolaganje save(PredispitneObavezePolaganje predispitneObaveze);
	
	Boolean delete(Long id);
	
	PredispitneObavezePolaganje save(PredispitneObavezePolaganjeDto dto);
	
	PredispitneObavezePolaganje update(PredispitneObavezePolaganjeDto dto);
}
