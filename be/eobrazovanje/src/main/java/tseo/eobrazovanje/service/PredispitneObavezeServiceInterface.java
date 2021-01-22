package tseo.eobrazovanje.service;

import java.util.List;

import javassist.NotFoundException;
import tseo.eobrazovanje.dto.PredispitneObavezeDto;
import tseo.eobrazovanje.model.PredispitneObaveze;

public interface PredispitneObavezeServiceInterface{

	List<PredispitneObaveze> findAll();

	PredispitneObaveze findOne(Long id);

	PredispitneObaveze save(PredispitneObaveze predispitneObavezeSablon);

	Boolean delete(Long id) throws NotFoundException;

	PredispitneObaveze save(PredispitneObavezeDto dto);
	
	void obrisi(Long id);
}
