package tseo.eobrazovanje.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tseo.eobrazovanje.dto.StudentDto;
import tseo.eobrazovanje.enumeration.Role;
import tseo.eobrazovanje.model.PredispitneObaveze;
import tseo.eobrazovanje.model.Student;
import tseo.eobrazovanje.repo.StudentRepository;
import tseo.eobrazovanje.service.StudentServiceInterface;

@Service
public class StudentService implements StudentServiceInterface {

	@Autowired
	StudentRepository studentRepository;

	@Override
	public Page<Student> findAll(String ime, String prezime, Pageable pageable) {
		return studentRepository.findAllByImeIgnoreCaseContainsAndPrezimeIgnoreCaseContains(ime, prezime, pageable);
	}

	@Override
	public List<Student> findAllList() {
		System.out.println("dosao sam dovde findalllist");
		return studentRepository.findAll();
	}

	@Override
	public Student findOne(Long id) {
		return studentRepository.findById(id).get();
	}

	@Override
	public Student save(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Student changePassword(Student student) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		student.setPassword(passwordEncoder.encode(student.getPassword()));
		return save(student);
	}

	@Override
	public Student update(StudentDto dto) {
		Student student = findOne(dto.getId());
		System.out.println("dto student id:" + dto.getId());
		System.out.println("dto broj indeksa: " + dto.getBrojIndexa());
		System.out.println("dto broj telefona: " + dto.getBrojTelefona());
		if (student == null) {
			System.out.println("student null");
			return null;
		} else {
			if (!dto.getIme().equals(""))
				student.setIme(dto.getIme());
			if (!dto.getPrezime().equals(""))
				student.setPrezime(dto.getPrezime());
			if (!dto.getUsername().equals(""))
				student.setUsername(dto.getUsername());
			if (!dto.getAdresa().equals(""))
				student.setAdresa(dto.getAdresa());
			if (!dto.getBrojIndexa().equals(""))
				student.setBrojIndexa(dto.getBrojIndexa());
//			if (!dto.getBrojTelefona().equals("") && !dto.getBrojTelefona().equals(student.getBrojTelefona())) {
//
//				student.setBrojTelefona(dto.getBrojTelefona());
//			}

			return save(student);
		}
	}

	@Override
	public Boolean delete(Long id) {
		studentRepository.deleteById(id);
		return true;
	}

	@Override
	public Student findByTekuciRacun(String tekuciRacun) {
		return studentRepository.findByTekuciRacun(tekuciRacun);
	}

	@Override
	public List<PredispitneObaveze> getLatestPredispitneObaveze(Student student, Long predmetId, Date datum) {
		
		Set<PredispitneObaveze> result = student.getPredispitneObaveze();	
		
		ArrayList<PredispitneObaveze> predObaveze = new ArrayList<PredispitneObaveze>();
		
		for (PredispitneObaveze po : result) {
			if(po.getDatum().before(datum)) {
				if(po.getSablon().getPredmet().getId() == predmetId) {
					predObaveze.add(po);
				}
			
			}
		}

		
		return predObaveze;
	}
//	
//	@Override
//	public List<PredispitneObaveze> getLatestPredispitneObaveze(Student student, Long predmetId, Date datum) {
//		List<PredispitneObaveze> result = student.getPredispitneObaveze().stream()
//				.filter(p -> p.getSablon().getPredmet().getId() == predmetId && p.getDatum().before(datum))
//				.collect(Collectors.toList());
//		ArrayList<PredispitneObaveze> ret = new ArrayList<>();
//		result.forEach(po -> {
//			boolean isMatch = false;
//			for (int i = 0; i < result.size(); i++)
//				if (po.getSablon().getId() == result.get(i).getSablon().getId()) {
//					isMatch = true;
//					if ((po.getDatum().after(result.get(i).getDatum()))
//							|| (po.getDatum().equals(result.get(i).getDatum()) && po.getId() > result.get(i).getId())) {
//						result.remove(result.get(i));
//						result.add(po);
//					}
//				}
//			if (!isMatch) {
//				ret.add(po);
//			}
//		});
//
//		// VRACA I NEPOLOZENE, NAKON REFINEMENTA MORAMO PROMENITI LINIJU 71!
//		return ret;
//	}

	@Override
	public Student create(Student student) {
		return changePassword(student);
	}

	@Override
	public Student findOneByBrojTelefona(String broj) {

		System.out.println("RADI REPO" + broj);
		List<Student> studenti = studentRepository.findAll();
		for (Student student : studenti) {
			System.out.println(student.getIme() + "STUDENT");
		}

		Student student = studentRepository.findOneByBrojTelefona(broj);
		System.out.println(student + broj);
		return student;
	}

	@Override
	public StudentDto studentDtoMaker(Student student) {

		return dtoBasic(student);
	}

	public StudentDto dtoBasic(Student student) {

		StudentDto dto = new StudentDto();
		dto.setId(student.getId());
		dto.setAdresa(student.getAdresa());
		dto.setIme(student.getIme());
		dto.setPrezime(student.getPrezime());
		dto.setBrojTelefona(student.getBrojTelefona());
		dto.setJmbg(student.getJmbg());
		dto.setUsername(student.getUsername());
		dto.setBrojIndexa(student.getBrojIndexa());
		dto.setRole(Role.STUDENT.name());
		dto.setStanje(student.getStanje());

		return dto;
	}

}
