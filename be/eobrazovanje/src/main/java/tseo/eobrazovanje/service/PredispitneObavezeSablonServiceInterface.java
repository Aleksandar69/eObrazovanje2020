package tseo.eobrazovanje.service;

import java.util.List;

import tseo.eobrazovanje.dto.PredispitneObavezeSablonDto;
import tseo.eobrazovanje.model.PredispitneObavezeSablon;

public interface PredispitneObavezeSablonServiceInterface{

	List<PredispitneObavezeSablon> findAll();

	PredispitneObavezeSablon findOne(Long id);

	PredispitneObavezeSablon save(PredispitneObavezeSablon predispitneObavezeSablon);

	Boolean delete(Long id);

	PredispitneObavezeSablon save(PredispitneObavezeSablonDto dto);
	
}
