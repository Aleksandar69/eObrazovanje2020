package tseo.eobrazovanje.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tseo.eobrazovanje.dto.IspitDto;
import tseo.eobrazovanje.model.Ispit;
import tseo.eobrazovanje.model.Predmet;
import tseo.eobrazovanje.model.Student;

public interface IspitServiceInterface {
	List<Ispit> findAll();

	Ispit findOne(Long id);

	Ispit save(Ispit ispit);

	Boolean delete(Long id);

	Ispit save(IspitDto dto);

	Ispit update(IspitDto dto);

	List<Ispit> ispitiZaPrijavu(Student student);

	List<Ispit> nepolozeniIspiti(Student student);

	Page<Ispit> findByPredmet(Predmet predmet, Pageable pageable);

	Page<Ispit> findByPredmetAndNastavnikId(Predmet predmet, Long nastavnikId, Pageable pageable);
}
