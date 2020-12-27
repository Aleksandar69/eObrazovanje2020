package tseo.eobrazovanje.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tseo.eobrazovanje.dto.NastavnikDto;
import tseo.eobrazovanje.model.Nastavnik;
import tseo.eobrazovanje.repo.NastavnikRepository;
import tseo.eobrazovanje.service.NastavnikServiceInterface;

@Service
public class NastavnikService implements NastavnikServiceInterface{
	
	@Autowired
	NastavnikRepository nastavnikRepository;

	@Override
	public Page<Nastavnik> findAll(String ime, String prezime, Pageable pageable) {
		return nastavnikRepository.findAllByImeIgnoreCaseContainsAndPrezimeIgnoreCaseContains(ime, prezime, pageable);
	}
	

	@Override
	public List<Nastavnik> findAllLowercaseList(String ime, String prezime) {
		return nastavnikRepository.findAllByImeIgnoreCaseContainsAndPrezimeIgnoreCaseContains(ime, prezime);
	}


	@Override
	public Nastavnik changePassword(Nastavnik nastavnik) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		nastavnik.setPassword(passwordEncoder.encode(nastavnik.getPassword()));
		return save(nastavnik);
	}

	@Override
	public Nastavnik findOne(Long id) {
		Optional<Nastavnik> nastavnik = nastavnikRepository.findById(id);
		if(nastavnik.isPresent()) {
			return nastavnik.get();
		}
		else {
			return null;
		}
	}

	@Override
	public Nastavnik save(Nastavnik nastavnik) {
		nastavnikRepository.save(nastavnik);
		return nastavnik;
	}

	@Override
	public Boolean delete(Long id) {
		nastavnikRepository.deleteById(id);
		return true;
	}

	@Override
	public Nastavnik save(NastavnikDto dto) {
		Nastavnik nastavnik = new Nastavnik();
		nastavnik.setIme(dto.getIme());
		nastavnik.setJmbg(dto.getJmbg());
		nastavnik.setPrezime(dto.getPrezime());
		nastavnik.setUsername(dto.getUsername());
	//	nastavnik.setPassword(passwordEncoder.encode(dto.getPassword()));
		return save(nastavnik);
	}

	@Override
	public Nastavnik update(NastavnikDto dto) {
		Nastavnik nastavnik = findOne(dto.getId());
		if (nastavnik == null) {
			return null;
		} else {
			if (!dto.getIme().equals(""))
				nastavnik.setIme(dto.getIme());
			if (!dto.getPrezime().equals(""))
				nastavnik.setPrezime(dto.getPrezime());
			if (!dto.getUsername().equals(""))
				nastavnik.setUsername(dto.getUsername());
			if (!dto.getAdresa().equals(""))
				nastavnik.setAdresa(dto.getAdresa());
			return save(nastavnik);
		}
	}




}
