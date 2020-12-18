package tseo.eobrazovanje.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tseo.eobrazovanje.dto.AdminDto;
import tseo.eobrazovanje.model.Admin;
import tseo.eobrazovanje.repo.AdministratorRepository;
import tseo.eobrazovanje.service.AdministratorServiceInterface;

@Service
public class AdministratorService implements AdministratorServiceInterface {

	@Autowired
	AdministratorRepository adminRepository;
	
	//@Autowired
	//PasswordEncoder passwordEncoder;
	

	@Override
	public List<Admin> findAll() {
		return adminRepository.findAll();
	}

	@Override
	public Admin findOne(Long id) {
		Optional<Admin> admin = adminRepository.findById(id);
		if(admin.isPresent()) {
			return admin.get();
		}
		else {
			return null;
		}
	}

	@Override
	public Admin save(Admin admin) {
		adminRepository.save(admin);
		return admin;
	}

	@Override
	public Admin changePassword(Admin admin) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		return save(admin);
	}

	@Override
	public Boolean delete(Long id) {
		adminRepository.deleteById(id);
		return true;
	}

	@Override
	public Admin save(AdminDto adminDto) {
		Admin admin = new Admin();
		admin.setIme(adminDto.getIme());
		admin.setJmbg(adminDto.getJmbg());
		admin.setPrezime(adminDto.getPrezime());
		admin.setUsername(adminDto.getUsername());
	//	admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
		return save(admin);
	}

	@Override
	public Admin update(AdminDto adminDto) {
		Admin admin = findOne(adminDto.getId());
		if (admin == null) {
			return null;
		} else {
			admin.setIme(adminDto.getIme());
			admin.setJmbg(adminDto.getJmbg());
			admin.setPrezime(adminDto.getPrezime());
			admin.setUsername(adminDto.getUsername());
	//		admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
			return admin;
		}
	}

}
