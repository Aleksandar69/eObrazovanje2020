package tseo.eobrazovanje.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import tseo.eobrazovanje.dto.PredispitneObavezeSablonDto;
import tseo.eobrazovanje.model.PredispitneObavezeSablon;
import tseo.eobrazovanje.service.impl.NastavnikService;
import tseo.eobrazovanje.service.impl.PredispitneObavezeSablonService;
import tseo.eobrazovanje.service.impl.PredmetService;

@RestController
@RequestMapping("/predispitne-obaveze/sabloni")
public class PredispitneObavezeSablonController {
	
	@Autowired
	PredispitneObavezeSablonService sablonService;

	@Autowired
	NastavnikService nastavnikService;

	@Autowired
	PredmetService predmetService;

	@GetMapping
	public ResponseEntity getAll() {

		return new ResponseEntity(sablonService.findAll(), HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity getOne(@PathVariable("id") long id) {
		PredispitneObavezeSablon sablon = sablonService.findOne(id);
		if (sablon != null) {
			return new ResponseEntity(sablon, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}/polaganja")
	public ResponseEntity getPredispitneObaveze(@PathVariable("id") long id) {
		PredispitneObavezeSablon sablon = sablonService.findOne(id);
		if (sablon != null) {
			return new ResponseEntity(sablon.getPolaganja(), HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteOne(@PathVariable("id") long id) {
		PredispitneObavezeSablon sablon = sablonService.findOne(id);
		if (sablon != null) {
			sablonService.delete(sablon.getId());
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping
	public ResponseEntity postOne(@Validated @RequestBody PredispitneObavezeSablonDto dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (dto != null) {
			PredispitneObavezeSablon sablon = sablonService.save(dto);
			return new ResponseEntity(sablon, HttpStatus.CREATED);
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity putOne(@PathVariable("id") Long id, @Validated @RequestBody PredispitneObavezeSablonDto dto,
			Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (dto != null && dto.getId() == id) {
			PredispitneObavezeSablon sablon = sablonService.save(dto);
			return new ResponseEntity(sablon, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}


}