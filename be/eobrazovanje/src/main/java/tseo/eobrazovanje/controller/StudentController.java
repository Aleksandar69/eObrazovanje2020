package tseo.eobrazovanje.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.multipart.MultipartFile;

import tseo.eobrazovanje.dto.StudentDto;
import tseo.eobrazovanje.model.Dokument;
import tseo.eobrazovanje.model.Ispit;
import tseo.eobrazovanje.model.Prijava;
import tseo.eobrazovanje.model.Student;
import tseo.eobrazovanje.model.Uplata;
import tseo.eobrazovanje.service.IspitServiceInterface;
import tseo.eobrazovanje.service.PredmetServiceInterface;
import tseo.eobrazovanje.service.PrijavaServiceInterface;
import tseo.eobrazovanje.service.StudentServiceInterface;
import tseo.eobrazovanje.service.UplataServiceInterface;
import tseo.eobrazovanje.service.impl.DokumentService;

@RestController
@RequestMapping("/studenti")
public class StudentController {

	@Autowired
	DokumentService dokumentService;

	@Autowired
	StudentServiceInterface studentService;

	@Autowired
	PredmetServiceInterface predmetiService;

	@Autowired
	IspitServiceInterface ispitService;

	@Autowired
	PrijavaServiceInterface prijavaService;

	@Autowired
	UplataServiceInterface uplataService;

	@GetMapping
	public ResponseEntity getAll(@RequestParam(value = "ime", defaultValue = "") String ime,
			@RequestParam(value = "prezime", defaultValue = "") String prezime, Pageable pageable) {

		Page<Student> studenti = studentService.findAll(ime, prezime, pageable);
		HttpHeaders headers = new HttpHeaders();
		headers.set("total", String.valueOf(studenti.getTotalPages()));

		return ResponseEntity.ok().headers(headers).body(studenti.getContent());

	}

	@GetMapping("/{id}")
	public ResponseEntity getOne(@PathVariable("id") long id) {
		Student student = studentService.findOne(id);
		if (student != null) {
			return new ResponseEntity(student, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}/dokumenti")
	public ResponseEntity getDokumenti(@PathVariable("id") long id, Pageable pageable) {
		Student student = studentService.findOne(id);
		if (student != null) {

			Page<Dokument> dokumenti = dokumentService.getByStudent(student, pageable);
			HttpHeaders headers = new HttpHeaders();
			headers.set("total", String.valueOf(dokumenti.getTotalPages()));

			return ResponseEntity.ok().headers(headers).body(dokumenti.getContent());
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}/predispitne-obaveze")
	public ResponseEntity getPredispitneObaveze(@PathVariable("id") long id,
			@RequestParam(value = "predmet", required = false) Long predmetId) {
		Student student = studentService.findOne(id);
		if (student != null) {
			if (predmetId == null) {
				return new ResponseEntity(student.getPredispitneObaveze(), HttpStatus.OK);
			} else {
				return new ResponseEntity(studentService.getLatestPredispitneObaveze(student, predmetId, new Date()),
						HttpStatus.OK);
			}
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}/predmeti")
	
	public ResponseEntity getPredmeti(@PathVariable("id") long id) {
		Student student = studentService.findOne(id);
		if (student != null) {

			return new ResponseEntity(student.getPredmeti(), HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	
	@PostMapping("/{id}/dokumenti")
	public ResponseEntity postOne(HttpServletRequest req, @PathVariable Long id,
			@RequestParam("dokument") MultipartFile file, @RequestParam("naziv") String naziv) {
		Student student = studentService.findOne(id);
		if (student != null) {
			return new ResponseEntity(dokumentService.save(file, naziv, student), HttpStatus.CREATED);
		} else {
			return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
		}
	}

	@GetMapping("/{id}/prijave")
//	
	public ResponseEntity getPrijave(@PathVariable("id") long id, Pageable pageable) {
		Student student = studentService.findOne(id);
		if (student != null) {
			Page<Prijava> prijave = prijavaService.getPrijavaByStudent(student, pageable);
			HttpHeaders headers = new HttpHeaders();
			headers.set("total", String.valueOf(prijave.getTotalPages()));

			return ResponseEntity.ok().headers(headers).body(prijave.getContent());

		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}/prijava-ispita")
	
	public ResponseEntity getIspitiZaPrijavu(@PathVariable("id") long id) {
		Student student = studentService.findOne(id);
		if (student != null) {
			System.out.println(student);
			List<Ispit> ispitiZaPrijavu = ispitService.ispitiZaPrijavu(student);
			return new ResponseEntity(ispitiZaPrijavu, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}/polozeni-ispiti")
	public ResponseEntity getPolozeniPredmeti(@PathVariable("id") long id) {
		Student student = studentService.findOne(id);
		if (student != null) {
			return new ResponseEntity(prijavaService.getPolozeniPredmeti(student), HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}/nepolozeni-ispiti")
	public ResponseEntity getNepolozeniPredmeti(@PathVariable("id") long id) {
		Student student = studentService.findOne(id);
		if (student != null) {

			return new ResponseEntity(predmetiService.getNepolozeniPredmeti(student), HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}/uplate")
	public ResponseEntity getUplate(@PathVariable("id") long id, Pageable pageable) {
		Student student = studentService.findOne(id);
		if (student != null) {
			Page<Uplata> uplate = uplataService.findByStudent(student, pageable);
			HttpHeaders headers = new HttpHeaders();
			headers.set("total", String.valueOf(uplate.getTotalPages()));

			return ResponseEntity.ok().headers(headers).body(uplate.getContent());

		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	
	public ResponseEntity postOne(@Validated @RequestBody Student student, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(studentService.create(student), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	
	public ResponseEntity deleteOne(@PathVariable("id") long id) {
		Student student = studentService.findOne(id);
		if (student != null) {
			studentService.delete(student.getId());
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{id}")
	
	public ResponseEntity putOne(@PathVariable("id") long id, @Validated @RequestBody StudentDto student,
			Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (student.getId() != id) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity(studentService.update(student), HttpStatus.OK);

		}
	}

	@PutMapping("/{id}/broj-telefona")
	
	public ResponseEntity changePhoneNumber(@PathVariable("id") long id,
			@Validated @RequestBody StudentDto studentdto) {

		if (studentdto.getId() != id) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} else {

			Student student = studentService.findOne(studentdto.getId());

			StudentDto dto = studentService.studentDtoMaker(studentService.update(studentdto));
			return new ResponseEntity(dto, HttpStatus.OK);

		}
	}

}
