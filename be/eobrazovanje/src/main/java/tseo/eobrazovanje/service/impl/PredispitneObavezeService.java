package tseo.eobrazovanje.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tseo.eobrazovanje.dto.PredispitneObavezeDto;
import tseo.eobrazovanje.model.PredispitneObavezePolaganje;
import tseo.eobrazovanje.model.PredispitneObaveze;
import tseo.eobrazovanje.repo.PredispitneObavezePolaganjeRepository;
import tseo.eobrazovanje.repo.PredispitneObavezeRepository;
import tseo.eobrazovanje.service.PredispitneObavezeServiceInterface;



@Service
public class PredispitneObavezeService implements PredispitneObavezeServiceInterface{
	
	@Autowired
	PredispitneObavezeRepository sablonRepository;

	@Autowired
	NastavnikService nastavnikService;

	@Autowired
	PredispitneObavezePolaganjeService predispitneObavezeService;

	@Autowired
	PredmetService predmetService;

	@Override
	public List<PredispitneObaveze> findAll() {
		return sablonRepository.findAll();
	}

	@Override
	public PredispitneObaveze findOne(Long id) {
		Optional<PredispitneObaveze> pos = sablonRepository.findById(id);
		if(pos.isPresent()) {
			return pos.get();
	} else {
		return null;
		}
	}

	@Override
	public PredispitneObaveze save(PredispitneObaveze predispitneObaveze) {
		sablonRepository.save(predispitneObaveze);
		return predispitneObaveze;
	}

	@Override
	public Boolean delete(Long id) {
		PredispitneObaveze sablon = findOne(id);
		for (PredispitneObavezePolaganje p : sablon.getPolaganja()) {
			predispitneObavezeService.delete(p.getId());
		}
		sablonRepository.deleteById(id);
		sablonRepository.delete(sablon);
		System.out.println("deleted sablon id " + id + "sablon name " + sablon.getNaziv());
		return true;
	}

	@Override
	public PredispitneObaveze save(PredispitneObavezeDto dto) {
		PredispitneObaveze predispitneObavezeSablon = new PredispitneObaveze();
		if (dto.getId() != null)
			predispitneObavezeSablon.setId(dto.getId());
		predispitneObavezeSablon.setMinimumBodova(dto.getMinimumBodova());
		predispitneObavezeSablon.setNaziv(dto.getNaziv());
		predispitneObavezeSablon.setUkupnoBodova(dto.getUkupnoBodova());
		predispitneObavezeSablon.setPredmet(predmetService.findOne(dto.getPredmet()));
		return save(predispitneObavezeSablon);
	}

}
