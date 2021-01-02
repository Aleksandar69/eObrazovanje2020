package tseo.eobrazovanje.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tseo.eobrazovanje.dto.RegistracijaZahtevDto;
import tseo.eobrazovanje.dto.StudentDto;
import tseo.eobrazovanje.model.RegistracijaZahtev;
import tseo.eobrazovanje.model.Student;
import tseo.eobrazovanje.service.RegistracijaZahtevServiceInterface;

@RestController
@RequestMapping("/registracija-zahtev")
public class RegistracijaZahtevController {

	@Autowired
	RegistracijaZahtevServiceInterface zahtevService;

	
	@GetMapping
	public ResponseEntity getAll(@RequestParam(value = "ime", defaultValue = "") String ime,
			@RequestParam(value = "prezime", defaultValue = "") String prezime, Pageable pageable) {
		
		Page<RegistracijaZahtev> zahtevi = zahtevService.findAll(ime, prezime, pageable);
		HttpHeaders headers = new HttpHeaders();
		headers.set("total", String.valueOf(zahtevi.getTotalPages()));

		return ResponseEntity.ok().headers(headers).body(zahtevi.getContent());
	}
	
	@GetMapping("/neodobreni")
	public ResponseEntity getNeodobreni(@RequestParam(value = "ime", defaultValue = "") String ime,
			@RequestParam(value = "prezime", defaultValue = "") String prezime, Pageable pageable) {
		
		Page<RegistracijaZahtev> zahtevi = zahtevService.findNeodobreni(ime, prezime, pageable);
		HttpHeaders headers = new HttpHeaders();
		headers.set("total", String.valueOf(zahtevi.getTotalPages()));

		return ResponseEntity.ok().headers(headers).body(zahtevi.getContent());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity getOne(@PathVariable("id") long id) {
		RegistracijaZahtev rz = zahtevService.findOne(id);
		if (rz != null) {
			return new ResponseEntity(rz, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity postOne(@Validated @RequestBody RegistracijaZahtev rz, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(zahtevService.save(rz), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteOne(@PathVariable("id") long id) {
		RegistracijaZahtev rz= zahtevService.findOne(id);
		if (rz != null) {
			zahtevService.delete(rz.getId());
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity putOne(@PathVariable("id") long id, @Validated @RequestBody RegistracijaZahtevDto rzdto,
			Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (rzdto.getId() != id) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity(zahtevService.update(rzdto), HttpStatus.OK);

		}
	}

}
