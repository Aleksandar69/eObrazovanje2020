package tseo.eobrazovanje.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tseo.eobrazovanje.dto.PredispitneObavezeSablonDto;
import tseo.eobrazovanje.model.PredispitneObaveze;
import tseo.eobrazovanje.model.PredispitneObavezeSablon;
import tseo.eobrazovanje.repo.PredispitneObavezeRepository;
import tseo.eobrazovanje.repo.PredispitneObavezeSablonRepository;
import tseo.eobrazovanje.service.PredispitneObavezeSablonServiceInterface;



@Service
public class PredispitneObavezeSablonService implements PredispitneObavezeSablonServiceInterface{
	
	@Autowired
	PredispitneObavezeSablonRepository sablonRepository;

	@Autowired
	NastavnikService nastavnikService;

	@Autowired
	PredispitneObavezeService predispitneObavezeService;

	@Autowired
	PredmetService predmetService;

	@Override
	public List<PredispitneObavezeSablon> findAll() {
		return sablonRepository.findAll();
	}

	@Override
	public PredispitneObavezeSablon findOne(Long id) {
		Optional<PredispitneObavezeSablon> pos = sablonRepository.findById(id);
		if(pos.isPresent()) {
			return pos.get();
	} else {
		return null;
		}
	}

	@Override
	public PredispitneObavezeSablon save(PredispitneObavezeSablon predispitneObaveze) {
		sablonRepository.save(predispitneObaveze);
		return predispitneObaveze;
	}

	@Override
	public Boolean delete(Long id) {
		PredispitneObavezeSablon sablon = findOne(id);
		for (PredispitneObaveze p : sablon.getPolaganja()) {
			predispitneObavezeService.delete(p.getId());
		}
		sablonRepository.deleteById(id);
		sablonRepository.delete(sablon);
		System.out.println("deleted sablon id " + id + "sablon name " + sablon.getNaziv());
		return true;
	}

	@Override
	public PredispitneObavezeSablon save(PredispitneObavezeSablonDto dto) {
		PredispitneObavezeSablon predispitneObavezeSablon = new PredispitneObavezeSablon();
		if (dto.getId() != null)
			predispitneObavezeSablon.setId(dto.getId());
		predispitneObavezeSablon.setMinimumBodova(dto.getMinimumBodova());
		predispitneObavezeSablon.setNaziv(dto.getNaziv());
		predispitneObavezeSablon.setUkupnoBodova(dto.getUkupnoBodova());
		predispitneObavezeSablon.setPredmet(predmetService.findOne(dto.getPredmet()));
		return save(predispitneObavezeSablon);
	}

}
