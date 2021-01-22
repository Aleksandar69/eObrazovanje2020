package tseo.eobrazovanje.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tseo.eobrazovanje.dto.NastavnikDto;
import tseo.eobrazovanje.model.Nastavnik;

public interface NastavnikServiceInterface {
	
	Nastavnik findOne(Long id);

	Nastavnik save(Nastavnik nastavnik);

	Boolean delete(Long id);

	Nastavnik save(NastavnikDto dto);

	Nastavnik update(NastavnikDto dto);

	Page<Nastavnik> findAll(String ime, String prezime, Pageable pageable);

	Nastavnik changePassword(Nastavnik nastavnik);
	
	List<Nastavnik> findAllLowercaseList(String ime, String prezime);
	
	void obrisiNastavnika(Long id);

}
