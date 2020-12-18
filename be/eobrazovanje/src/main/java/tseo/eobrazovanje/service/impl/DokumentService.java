package tseo.eobrazovanje.service.impl;

import java.io.File;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tseo.eobrazovanje.dto.DokumentDto;
import tseo.eobrazovanje.model.Dokument;
import tseo.eobrazovanje.model.Student;
import tseo.eobrazovanje.repo.DokumentRepository;
import tseo.eobrazovanje.service.DokumentServiceInterface;

@Service
public class DokumentService implements DokumentServiceInterface {
	
	@Autowired
	DokumentRepository dokumentRepository;

	@Override
	public Page<Dokument> findAll(String naziv, Pageable pageable) {
		return dokumentRepository.findAllByNazivIgnoreCaseContains(naziv, pageable);
	}

	@Override
	public Dokument findOne(Long id) {
		Optional<Dokument> dokument = dokumentRepository.findById(id);
		if(dokument.isPresent()) {
			return dokument.get();
		}
		else {
			return null;
		}
		
	}

	@Override
	public Dokument save(Dokument dokument) {
		dokumentRepository.save(dokument);
		return dokument;
	}

	@Override
	public Boolean delete(Long id) {
		Dokument doc = findOne(id);
		if (doc != null) {
			File file = new File(doc.getSadrzaj());
			file.delete();
		}
		dokumentRepository.deleteById(id);
		return true;
	}

	@Override
	public Dokument update(DokumentDto dto) {
		Dokument dokument = findOne(dto.getId());
		if (dokument == null) {
			return null;
		} else {
			dokument.setNaziv(dto.getNaziv());
			return save(dokument);
		}
	}

	@Override
	public Dokument save(MultipartFile file, String naziv, Student student) {
		try {
			File fileSave = new File("c:/tseoDocs/", System.currentTimeMillis() + file.getOriginalFilename());
			if (!fileSave.exists()) {
				fileSave.createNewFile();
			}
			file.transferTo(fileSave);
			Dokument document = new Dokument();
			document.setDatumDokumenta(new Date());
			document.setNaziv(naziv);
			document.setStudent(student);
			document.setSadrzaj("http://127.0.0.1:8887/" + fileSave.getName());
			student.getDokumenti().add(document);
			return save(document);
		} catch (Exception e) {
			return null;
		}
	}

	public Page<Dokument> getByStudent(Student student, Pageable pageable) {
		return dokumentRepository.findByStudent(student, pageable);
	}
}
