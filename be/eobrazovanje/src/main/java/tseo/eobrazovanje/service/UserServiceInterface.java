package tseo.eobrazovanje.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import tseo.eobrazovanje.dto.PasswordDto;
import tseo.eobrazovanje.exception.UserNotFoundException;
import tseo.eobrazovanje.exception.UsernameExistException;
import tseo.eobrazovanje.model.RegistracijaZahtev;
import tseo.eobrazovanje.model.Student;
import tseo.eobrazovanje.model.User;


public interface UserServiceInterface {
	
	List<User> findAll();
	User findOne(Long id);
	RegistracijaZahtev register(String firstName, String lastName, String username, String password, String jmbg, String adresa, String tekuciRacun, String brojIndexa, double stanje, String brojTelefona) throws UserNotFoundException, UsernameExistException;
	User findByUsername(String username);
	Boolean delete(Long id);
	ResponseEntity<Void> updatePassword(User user, PasswordDto passwordDto);
	User save(User user);
		


}
