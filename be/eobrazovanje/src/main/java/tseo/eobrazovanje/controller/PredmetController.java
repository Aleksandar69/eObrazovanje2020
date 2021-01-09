package tseo.eobrazovanje.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tseo.eobrazovanje.dto.IspitDto;
import tseo.eobrazovanje.model.Ispit;
import tseo.eobrazovanje.model.Predmet;
import tseo.eobrazovanje.service.IspitServiceInterface;
import tseo.eobrazovanje.service.NastavnikServiceInterface;
import tseo.eobrazovanje.service.PredmetServiceInterface;
import tseo.eobrazovanje.service.StudentServiceInterface;

@RestController
@RequestMapping("/predmeti")
public class PredmetController {
	
	@Autowired
	PredmetServiceInterface predmetService;

	@Autowired
	NastavnikServiceInterface nastavnikService;

	@Autowired
	StudentServiceInterface studentService;

	@Autowired
	IspitServiceInterface ispitService;

	@GetMapping
	public ResponseEntity getAll(@RequestParam(value = "naziv", defaultValue = "") String naziv, Pageable pageable) {

		Page<Predmet> predmeti = predmetService.findAll(naziv, pageable);
		HttpHeaders headers = new HttpHeaders();
		headers.set("total", String.valueOf(predmeti.getTotalPages()));

		return ResponseEntity.ok().headers(headers).body(predmeti.getContent());

	}

	@GetMapping("/{id}")
	public ResponseEntity getOne(@PathVariable("id") long id) {
		Predmet predmet = predmetService.findOne(id);
		if (predmet != null) {
			return new ResponseEntity(predmet, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}/ispiti")
	public ResponseEntity getIspiti(@PathVariable("id") long id,
			@RequestParam(value = "nastavnik", required = false) Long nastavnikId, Pageable pageable) {
		Predmet predmet = predmetService.findOne(id);
		if (predmet != null) {
			if (nastavnikId != null) {
				List<Ispit> ispiti1 = ispitService.findByPredmetAndNastavnikId(predmet, nastavnikId);
				System.out.println("Prije petlje ispit");
				for (Ispit ispit : ispiti1) {
					System.out.println("ISPIT!: "+ ispit);
				}
				Page<Ispit> ispiti = ispitService.findByPredmetAndNastavnikId(predmet, nastavnikId, pageable);
				HttpHeaders headers = new HttpHeaders();
				headers.set("total", String.valueOf(ispiti.getTotalPages()));
				return ResponseEntity.ok().headers(headers).body(ispiti.getContent());
			} else {
				List<Ispit> ispiti1 = ispitService.findByPredmet(predmet);
				System.out.println("Prije petlje ispit");
				for (Ispit ispit : ispiti1) {
					System.out.println("ISPIT!: " + ispit);
				}
				Page<Ispit> ispiti = ispitService.findByPredmet(predmet, pageable);
				HttpHeaders headers = new HttpHeaders();
				headers.set("total", String.valueOf(ispiti.getTotalPages()));
				return ResponseEntity.ok().headers(headers).body(ispiti.getContent());
			}
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/{id}/ispiti")
	public ResponseEntity postIspit(@PathVariable("id") long id, @RequestBody IspitDto dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (dto != null) {
			dto.setPredmet(id);
			Ispit ispit = ispitService.save(dto);
			return new ResponseEntity(ispit, HttpStatus.CREATED);
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}/predispitne-obaveze")
	public ResponseEntity getSabloni(@PathVariable("id") long id) {
		Predmet predmet = predmetService.findOne(id);
		if (predmet != null) {
			return new ResponseEntity(predmet.getPredispitneObavezeSabloni(), HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}/predispitne-obaveze-polaganje")
	public ResponseEntity getPredispitneObaveze(@PathVariable("id") long id) {
		Predmet predmet = predmetService.findOne(id);
		if (predmet != null) {
			return new ResponseEntity(predmet.getPredispitneObaveze(), HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity postOne(@Validated @RequestBody Predmet predmet, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(predmetService.save(predmet), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteOne(@PathVariable("id") long id) {
		Predmet predmet = predmetService.findOne(id);
		if (predmet != null) {
			predmetService.delete(predmet.getId());
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity putOne(@PathVariable("id") long id, @Validated @RequestBody Predmet predmet, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (predmet.getId() != id) {
			
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity(predmetService.save(predmet), HttpStatus.OK);

		}
	}

	@GetMapping("/{id}/nastavnici")
	public ResponseEntity getNastavnici(@PathVariable("id") long id) {
		Predmet predmet = predmetService.findOne(id);
		if (predmet != null) {
			return new ResponseEntity(predmet.getNastavnici(), HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/{id}/nastavnici")
	public ResponseEntity postNastavnik(@PathVariable("id") long id, @RequestBody List<Long> nastavnici) {
		Predmet predmet = predmetService.findOne(id);
		if (predmet == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity(predmetService.addNastavnici(predmet, nastavnici), HttpStatus.OK);
		}
	}

	@GetMapping("/{id}/studenti")
	public ResponseEntity getStudenti(@PathVariable("id") long id) {
		Predmet predmet = predmetService.findOne(id);
		if (predmet != null) {
			return new ResponseEntity(predmet.getStudenti(), HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/{id}/studenti")
	public ResponseEntity postStudent(@PathVariable("id") long id, @RequestBody List<Long> studenti) {
		Predmet predmet = predmetService.findOne(id);
		if (predmet == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity(predmetService.addStudenti(predmet, studenti), HttpStatus.OK);
		}
	}

	@GetMapping("/oznaka-check")
	public ResponseEntity checkOznaka(@RequestParam(value = "oznaka", defaultValue = "") String oznaka) {
		Predmet predmet = predmetService.findByOznaka(oznaka);
		if (predmet != null) {
			return new ResponseEntity<>(HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
