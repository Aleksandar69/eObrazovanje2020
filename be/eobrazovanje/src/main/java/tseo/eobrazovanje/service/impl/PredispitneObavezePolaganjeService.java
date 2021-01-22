package tseo.eobrazovanje.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tseo.eobrazovanje.dto.PredispitneObavezePolaganjeDto;
import tseo.eobrazovanje.model.PredispitneObaveze;
import tseo.eobrazovanje.model.PredispitneObavezePolaganje;
import tseo.eobrazovanje.repo.PredispitneObavezePolaganjeRepository;
import tseo.eobrazovanje.service.PredispitneObavezePolaganjeServiceInterface;
import tseo.eobrazovanje.service.PredmetServiceInterface;

@Service
public class PredispitneObavezePolaganjeService implements PredispitneObavezePolaganjeServiceInterface{

	
	@Autowired
	PredispitneObavezePolaganjeRepository predispitneObavezeRepository;

	@Autowired
	PredispitneObavezeService sablonService;

	@Autowired
	StudentService studentService;
	
	@Autowired
	PredmetServiceInterface predmetService;

	@Override
	public List<PredispitneObavezePolaganje> findAll() {
		return predispitneObavezeRepository.findAll();
	}

	@Override
	public PredispitneObavezePolaganje findOne(Long id) {
		Optional<PredispitneObavezePolaganje> po = predispitneObavezeRepository.findById(id);
		if(po.isPresent()) {
			return po.get();
		}
		else {
			return null;
		}
	}

	@Override
	public PredispitneObavezePolaganje save(PredispitneObavezePolaganje predispitneObaveze) {
		predispitneObavezeRepository.save(predispitneObaveze);
		return predispitneObaveze;
	}

	@Override
	public Boolean delete(Long id) {
		predispitneObavezeRepository.deleteById(id);
		return true;
	}

	@Override
	public PredispitneObavezePolaganje save(PredispitneObavezePolaganjeDto dto) {
		PredispitneObavezePolaganje pObaveze = new PredispitneObavezePolaganje();
		pObaveze.setDatum(new Date());
		pObaveze.setSablon(sablonService.findOne(dto.getSablon()));
		pObaveze.setOsvojeniBodovi(dto.getOsvojeniBodovi());
		pObaveze.setStudent(studentService.findOne(dto.getStudent()));
		pObaveze.setPredmet(predmetService.findOne(dto.getPredmet()));
		if (pObaveze.getOsvojeniBodovi() >= pObaveze.getSablon().getMinimumBodova()) {
			pObaveze.setPolozio(true);
		} else {
			pObaveze.setPolozio(false);
		}
		return save(pObaveze);
	}

	@Override
	public PredispitneObavezePolaganje update(PredispitneObavezePolaganjeDto dto) {
		PredispitneObavezePolaganje pObaveze = findOne(dto.getId());
		if (pObaveze == null) {
			return null;
		} else {
			pObaveze.setId(dto.getId());
			pObaveze.setDatum(dto.getDatum());
			pObaveze.setSablon(sablonService.findOne(dto.getSablon()));
			pObaveze.setOsvojeniBodovi(dto.getOsvojeniBodovi());
			pObaveze.setStudent(studentService.findOne(dto.getStudent()));
			if (pObaveze.getOsvojeniBodovi() >= pObaveze.getSablon().getMinimumBodova()) {
				pObaveze.setPolozio(true);
			} else {
				pObaveze.setPolozio(false);
			}
			return save(pObaveze);
		}
	}

	@Override
	public void obrisiPop(PredispitneObaveze po) {
		
		predispitneObavezeRepository.obrisiPredispitneObavezePolaganje(po);
	}
}
