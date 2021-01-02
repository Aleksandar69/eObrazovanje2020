package tseo.eobrazovanje.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tseo.eobrazovanje.dto.RegistracijaZahtevDto;
import tseo.eobrazovanje.model.RegistracijaZahtev;
import tseo.eobrazovanje.model.Student;
import tseo.eobrazovanje.repo.RegistracijaZahtevRepository;
import tseo.eobrazovanje.service.RegistracijaZahtevServiceInterface;

@Service
public class RegistracijaZahtevService implements RegistracijaZahtevServiceInterface{
	
	@Autowired
	RegistracijaZahtevRepository regOdobrRepo;

	@Override
	public Page<RegistracijaZahtev> findAll(String ime, String prezime, Pageable pageable) {
	
		return regOdobrRepo.findAllByImeIgnoreCaseContainsAndPrezimeIgnoreCaseContains(ime, prezime, pageable);
	}

	@Override
	public RegistracijaZahtev findOne(Long id) {
		return regOdobrRepo.findById(id).get();
	}


	@Override
	public RegistracijaZahtev save(RegistracijaZahtev rz) {
		return regOdobrRepo.save(rz);
	}

	@Override
	public boolean delete(Long id) {
		regOdobrRepo.deleteById(id);
		return true;
	}

	@Override
	public RegistracijaZahtev update(RegistracijaZahtevDto rzdto) {
		RegistracijaZahtev rz = findOne(rzdto.getId());
		
		if(rz == null) {
			System.out.println("rz null");
			return null;
		}
		else {
			if(StringUtils.isNotEmpty(rzdto.getIme())) {
				rz.setIme(rzdto.getIme());
			}
			if(StringUtils.isNotEmpty(rzdto.getPrezime())) {
				rz.setPrezime(rzdto.getPrezime());
			}
			if(StringUtils.isNotEmpty(rzdto.getUsername())) {
				rz.setIme(rzdto.getUsername());
			}
			if(StringUtils.isNotEmpty(rzdto.getAdresa())) {
				rz.setAdresa(rzdto.getAdresa());
			}
			if(StringUtils.isNotEmpty(rzdto.getBrojIndexa())) {
				rz.setBrojIndexa(rzdto.getBrojIndexa());
			}
			rz.setOdobren(rzdto.isOdobren());
			return save(rz);
		}
	}

	@Override
	public Page<RegistracijaZahtev> findNeodobreni(String ime, String prezime, Pageable pageable) {
		return regOdobrRepo.findAllByImeIgnoreCaseContainsAndPrezimeIgnoreCaseContainsAndOdobrenFalse(ime, prezime, pageable);
	}
	


}
