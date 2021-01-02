package tseo.eobrazovanje.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tseo.eobrazovanje.dto.RegistracijaZahtevDto;
import tseo.eobrazovanje.model.RegistracijaZahtev;
import tseo.eobrazovanje.model.Student;

public interface RegistracijaZahtevServiceInterface {
	
	Page<RegistracijaZahtev> findAll(String ime, String prezime, Pageable pageable);
	
	RegistracijaZahtev findOne(Long id);
	
	RegistracijaZahtev save (RegistracijaZahtev rz);
	
	boolean delete(Long id);
	
	RegistracijaZahtev update(RegistracijaZahtevDto rzdto);
	
	Page<RegistracijaZahtev> findNeodobreni(String ime, String prezime, Pageable pageable);
}
