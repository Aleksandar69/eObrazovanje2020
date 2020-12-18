package tseo.eobrazovanje.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import tseo.eobrazovanje.dto.PasswordDto;
import tseo.eobrazovanje.exception.UserNotFoundException;
import tseo.eobrazovanje.exception.UsernameExistException;
import tseo.eobrazovanje.model.User;


public interface UserServiceInterface {
	
	List<User> findAll();
	User findOne(Long id);
    User register(String firstName, String lastName, String username) throws UserNotFoundException, UsernameExistException;
	User findByUsername(String username);
	Boolean delete(Long id);
	ResponseEntity<Void> updatePassword(User user, PasswordDto passwordDto);
	User save(User user);
		


}
