package tseo.eobrazovanje.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tseo.eobrazovanje.dto.PredispitneObavezeDto;
import tseo.eobrazovanje.model.PredispitneObaveze;
import tseo.eobrazovanje.repo.PredispitneObavezeRepository;
import tseo.eobrazovanje.service.PredispitneObavezeServiceInterface;
import tseo.eobrazovanje.service.PredmetServiceInterface;

@Service
public class PredispitneObavezeService implements PredispitneObavezeServiceInterface{

	
	@Autowired
	PredispitneObavezeRepository predispitneObavezeRepository;

	@Autowired
	PredispitneObavezeSablonService sablonService;

	@Autowired
	StudentService studentService;

	@Override
	public List<PredispitneObaveze> findAll() {
		return predispitneObavezeRepository.findAll();
	}

	@Override
	public PredispitneObaveze findOne(Long id) {
		Optional<PredispitneObaveze> po = predispitneObavezeRepository.findById(id);
		if(po.isPresent()) {
			return po.get();
		}
		else {
			return null;
		}
	}

	@Override
	public PredispitneObaveze save(PredispitneObaveze predispitneObaveze) {
		predispitneObavezeRepository.save(predispitneObaveze);
		return predispitneObaveze;
	}

	@Override
	public Boolean delete(Long id) {
		predispitneObavezeRepository.deleteById(id);
		return true;
	}

	@Override
	public PredispitneObaveze save(PredispitneObavezeDto dto) {
		PredispitneObaveze pObaveze = new PredispitneObaveze();
		pObaveze.setDatum(new Date());
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

	@Override
	public PredispitneObaveze update(PredispitneObavezeDto dto) {
		PredispitneObaveze pObaveze = findOne(dto.getId());
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
}
