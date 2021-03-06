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

import tseo.eobrazovanje.dto.PrijavaDto;
import tseo.eobrazovanje.model.Prijava;
import tseo.eobrazovanje.model.Student;
import tseo.eobrazovanje.service.IspitServiceInterface;
import tseo.eobrazovanje.service.NastavnikServiceInterface;
import tseo.eobrazovanje.service.PrijavaServiceInterface;
import tseo.eobrazovanje.service.StudentServiceInterface;

@RestController
@RequestMapping("/prijave")
public class PrijavaController {

	@Autowired
	PrijavaServiceInterface prijavaService;

	@Autowired
	IspitServiceInterface ispitService;

	@Autowired
	NastavnikServiceInterface nastavnikService;

	@Autowired
	StudentServiceInterface studentService;
	
	
	@GetMapping
	public ResponseEntity getAll(Pageable pageable) {

		Page<Prijava> prijave = prijavaService.findAll(pageable);
		HttpHeaders headers = new HttpHeaders();
		headers.set("total", String.valueOf(prijave.getTotalPages()));

		return ResponseEntity.ok().headers(headers).body(prijave.getContent());

	}
	
	@GetMapping("/{id}")
	public ResponseEntity getOne(@PathVariable("id") long id) {
		Prijava prijava = prijavaService.findOne(id);
		if (prijava != null) {
			return new ResponseEntity(prijava, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	
	public ResponseEntity postOne(@Validated @RequestBody PrijavaDto dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		System.out.println(dto);
		Prijava prijava = prijavaService.save(dto);

		if (prijava == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(prijava, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteOne(@PathVariable("id") long id) {
		if (prijavaService.delete(id)) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	

	@PutMapping("/{id}")
	public ResponseEntity putOne(@PathVariable("id") long id, @Validated @RequestBody PrijavaDto dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (dto.getId() != id) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} else {
			Prijava prijava = prijavaService.update(dto);
			if (prijava == null) {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			} else {
				if(prijava.isOcenjeno()){
					Student student = studentService.findOne(dto.getStudent());					
				}
				return new ResponseEntity(prijava, HttpStatus.CREATED);
			}
		}
	}
}
