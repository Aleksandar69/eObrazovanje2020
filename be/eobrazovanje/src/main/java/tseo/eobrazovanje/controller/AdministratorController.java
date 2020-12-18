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

import tseo.eobrazovanje.dto.AdminDto;
import tseo.eobrazovanje.model.Admin;
import tseo.eobrazovanje.service.impl.AdministratorService;

@RestController
@RequestMapping("/admin")
public class AdministratorController {

	@Autowired
	AdministratorService adminService;

	

	@GetMapping
	public ResponseEntity getAll() {

		return new ResponseEntity(adminService.findAll(), HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity getOne(@PathVariable("id") long id) {
		Admin admin = adminService.findOne(id);
		if (admin != null) {
			return new ResponseEntity(admin, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}


	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	@PostMapping
	public ResponseEntity postOne(@Validated @RequestBody AdminDto dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (dto != null) {
			Admin admin = adminService.save(dto);
			return new ResponseEntity(admin, HttpStatus.CREATED);
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	@DeleteMapping("/{id}")
	public ResponseEntity deleteOne(@PathVariable("id") long id) {
		Admin admin = adminService.findOne(id);
		if (admin != null) {
			adminService.delete(admin.getId());
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

	}


	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	@PutMapping("/{id}")
	public ResponseEntity putOne(@PathVariable("id") long id, @Validated @RequestBody AdminDto dto, Errors errors) {

		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (dto.getId() != id) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} else {
			Admin admin = adminService.update(dto);
			if (admin == null) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity(admin, HttpStatus.OK);
			}

		}
	}
	
}
