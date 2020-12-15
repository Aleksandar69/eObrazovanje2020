package tseo.eobrazovanje.service;

import java.util.List;

import tseo.eobrazovanje.model.User;

public interface UserServiceInterface {
	
	List<User> findAll();
	
	User findOne(Long id);

}
