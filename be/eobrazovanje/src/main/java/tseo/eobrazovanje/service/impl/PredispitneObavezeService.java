package tseo.eobrazovanje.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import tseo.eobrazovanje.dto.PredispitneObavezeDto;
import tseo.eobrazovanje.model.PredispitneObavezePolaganje;
import tseo.eobrazovanje.model.Ispit;
import tseo.eobrazovanje.model.PredispitneObaveze;
import tseo.eobrazovanje.repo.IspitRepository;
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
	PredispitneObavezePolaganjeService predispitneObavezePolaganjeService;

	@Autowired
	PredmetService predmetService;

	@Autowired
	IspitRepository ispitRepo;
	
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
	public Boolean delete(Long id) throws NotFoundException {
		try {
		PredispitneObaveze sablon = findOne(id);
		if(sablon == null) {
			throw new NotFoundException("Document not found");
		} 
		
		System.out.println("id: " + id);
		
		PredispitneObaveze po = sablonRepository.getOne(id);
		
		predispitneObavezePolaganjeService.obrisiPop(po);
		sablonRepository.obrisiPrepdispitneObaveze(id);
		
//		for (PredispitneObavezePolaganje p : sablon.getPolaganja()) {
//		predispitneObavezeService.delete(p.getId());
////		repo.deleteInBatch(sablon.getPolaganja());
//		PredispitneObavezePolaganje pop= predispitneObavezeService.findOne(p.getId());
////		sablon.dismissChild(p);
////		pop.dismissParent();
////		sablon.remove(p);
//	}
//		sablonRepository.deleteById(id);
		System.out.println("deleted sablon id " + id + "sablon name " + sablon.getNaziv());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
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

	@Override
	public void obrisi(Long id) {
		sablonRepository.obrisiPrepdispitneObaveze(id);
	}

}
