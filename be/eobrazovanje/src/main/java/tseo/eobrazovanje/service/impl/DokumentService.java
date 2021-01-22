package tseo.eobrazovanje.service.impl;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static tseo.eobrazovanje.constant.FileConstant.DIRECTORY_CREATED;
import static tseo.eobrazovanje.constant.FileConstant.DOT;
import static tseo.eobrazovanje.constant.FileConstant.FORWARD_SLASH;
import static tseo.eobrazovanje.constant.FileConstant.JPG_EXTENSION;
import static tseo.eobrazovanje.constant.FileConstant.USER_FOLDER;
import static tseo.eobrazovanje.constant.FileConstant.USER_IMAGE_PATH;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional.TxType;

import org.apache.catalina.User;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.common.net.MediaType;

import tseo.eobrazovanje.constant.FileConstant;
import tseo.eobrazovanje.dto.DokumentDto;
import tseo.eobrazovanje.model.Dokument;
import tseo.eobrazovanje.model.Student;
import tseo.eobrazovanje.repo.DokumentRepository;
import tseo.eobrazovanje.service.DokumentServiceInterface;
import tseo.eobrazovanje.service.StudentServiceInterface;
import tseo.eobrazovanje.service.UserServiceInterface;

@Service
public class DokumentService implements DokumentServiceInterface {
	
	@Autowired
	DokumentRepository dokumentRepository;
	
	@Autowired
	StudentServiceInterface userService;


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
	public Dokument update(Dokument dokument) {
		if (dokument == null) {
			return null;
		} else {
			return save(dokument);
		}
	}

//	@Override
//	public Dokument save(MultipartFile file, String naziv, Student student) {
//		try {
//			File fileSave = new File("c:/tseoDocs/", System.currentTimeMillis() + file.getOriginalFilename());
//			if (!fileSave.exists()) {
//				fileSave.createNewFile();
//			}
//			file.transferTo(fileSave);
//			Dokument document = new Dokument();
//			document.setDatumDokumenta(new Date());
//			document.setNaziv(naziv);
//			document.setStudent(student);
//			document.setSadrzaj("http://127.0.0.1:8887/" + fileSave.getName());
//			student.getDokumenti().add(document);
//			return save(document);
//		} catch (Exception e) {
//			return null;
//		}
//	}
	
	@Override
	public Dokument save(MultipartFile file, String naziv, long studentId) throws Exception {
		Dokument dokument = new Dokument();
		Student student = userService.findOne(studentId);
		dokument.setNaziv(naziv);
		dokument.setDatumDokumenta(new Date());
		dokument.setStudent(student);
		Student user = userService.findOne(studentId);
		if(file != null) {		
            Path userFolder = Paths.get(USER_FOLDER + user.getUsername()).toAbsolutePath().normalize();
            if(!Files.exists(userFolder)) {
                Files.createDirectories(userFolder);
            }
            
            String extension = null;
           try {
            extension = FilenameUtils.getExtension(file.getOriginalFilename());
           } catch(Exception e){
        	   e.printStackTrace();
           }
           if(extension!= null) {
            //Files.copy(file.getInputStream(), userFolder, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            Files.copy(file.getInputStream(), userFolder.resolve(naziv + DOT + extension), REPLACE_EXISTING);
            dokument.setSadrzaj(setDokumentUrl(user.getUsername(),naziv, extension));
           }
           else {
        	   throw new Exception("Extension Not Found");
           }
		}
        return dokument;
	}
	
    private String setDokumentUrl(String username, String dokName, String extension) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(FileConstant.USER_DOCUMENT_PATH + username + FORWARD_SLASH
        + dokName+ DOT + extension).toUriString();
    }

	public Page<Dokument> getByStudent(Student student, Pageable pageable) {
		return dokumentRepository.findByStudent(student, pageable);
	}
}
