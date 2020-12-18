package tseo.eobrazovanje.service;

import java.util.List;

import tseo.eobrazovanje.dto.AdminDto;
import tseo.eobrazovanje.model.Admin;

public interface AdministratorServiceInterface {
	
	List<Admin> findAll();

	Admin findOne(Long id);

	Admin save(Admin admin);

	Boolean delete(Long id);

	Admin save(AdminDto adminDto);

	Admin update(AdminDto adminDto);

	Admin changePassword(Admin admin);

}
