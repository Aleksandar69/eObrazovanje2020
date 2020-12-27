package tseo.eobrazovanje.controller;

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
import org.springframework.web.bind.annotation.RestController;

import tseo.eobrazovanje.dto.IspitDto;
import tseo.eobrazovanje.model.Ispit;
import tseo.eobrazovanje.model.Prijava;
import tseo.eobrazovanje.service.impl.IspitService;
import tseo.eobrazovanje.service.impl.NastavnikService;
import tseo.eobrazovanje.service.impl.PredmetService;
import tseo.eobrazovanje.service.impl.PrijavaService;
import tseo.eobrazovanje.service.impl.StudentService;

@RestController
@RequestMapping("/ispit")
public class IspitController {

	
	@Autowired
	IspitService ispitService;

	@Autowired
	NastavnikService nastavnikService;

	@Autowired
	StudentService studentService;

	@Autowired
	PredmetService predmetService;

	@Autowired
	PrijavaService prijavaService;

	@GetMapping
	public ResponseEntity getAll() {

		return new ResponseEntity(ispitService.findAll(), HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity getOne(@PathVariable("id") long id) {
		Ispit ispit = ispitService.findOne(id);
		if (ispit != null) {
			return new ResponseEntity(ispit, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}/prijave")
	public ResponseEntity getPrijave(@PathVariable("id") long id, Pageable pageable) {
		Ispit ispit = ispitService.findOne(id);
		if (ispit != null) {
			Page<Prijava> prijave = prijavaService.getByIspit(ispit, pageable);
			HttpHeaders headers = new HttpHeaders();
			headers.set("total", String.valueOf(prijave.getTotalPages()));
			
			return ResponseEntity.ok().headers(headers).body(prijave.getContent());
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public ResponseEntity postOne(@Validated @RequestBody IspitDto dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (dto != null) {
			
			Ispit ispit = ispitService.save(dto);
			
			return new ResponseEntity(ispit, HttpStatus.CREATED);
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteOne(@PathVariable("id") long id) {
		Ispit ispit = ispitService.findOne(id);
		if (ispit != null) {
			ispitService.delete(ispit.getId());
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity putOne(@PathVariable("id") long id, @Validated @RequestBody IspitDto dto, Errors errors) {

		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (dto.getId() != id) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} else {
			Ispit ispit = ispitService.update(dto);
			if (ispit == null) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity(ispit, HttpStatus.OK);
			}
		}
	}
	
}
