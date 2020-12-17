package tseo.eobrazovanje.service;

import java.util.List;

import tseo.eobrazovanje.exception.UserNotFoundException;
import tseo.eobrazovanje.exception.UsernameExistException;
import tseo.eobrazovanje.model.User;


public interface UserServiceInterface {
	
	List<User> findAll();
	User findOne(Long id);
    User register(String firstName, String lastName, String username) throws UserNotFoundException, UsernameExistException;
	User findByUsername(String username);


}
