package tseo.eobrazovanje.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tseo.eobrazovanje.dto.PasswordDto;
import tseo.eobrazovanje.enumeration.Role;
import tseo.eobrazovanje.exception.UserNotFoundException;
import tseo.eobrazovanje.exception.UsernameExistException;
import tseo.eobrazovanje.model.RegistracijaZahtev;
import tseo.eobrazovanje.model.Student;
import tseo.eobrazovanje.model.User;
import tseo.eobrazovanje.repo.StudentRepository;
import tseo.eobrazovanje.repo.UserRepository;
import tseo.eobrazovanje.security.UserPrincipal;
import tseo.eobrazovanje.service.RegistracijaZahtevServiceInterface;
import tseo.eobrazovanje.service.UserServiceInterface;
import static tseo.eobrazovanje.constant.UserImplConstant.*;


@Service
@Transactional
@Qualifier("userDetailsService")
public class UserService implements UserServiceInterface, UserDetailsService{
	
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	RegistracijaZahtevServiceInterface zahtevService;

	
	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}

	@Override
	public User findOne(Long id) {
		return userRepo.findById(id).get();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
        if (user == null) {
            LOGGER.error(NO_USER_FOUND_BY_USERNAME + username);
            throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username);
        } else {
            LOGGER.info(FOUND_USER_BY_USERNAME + username);
            UserPrincipal userPrincipal = new UserPrincipal(user);
            return userPrincipal;
        }
	}
	
    @Override
    public RegistracijaZahtev register(String firstName, String lastName, String username, String password, String jmbg, String adresa,
    		String tekuciRacun, String brojIndexa, double stanje, String brojTelefona) throws UserNotFoundException, UsernameExistException {
    	validateNewUsername(StringUtils.EMPTY, username);
    	RegistracijaZahtev user = new RegistracijaZahtev();
       // String password = generatePassword();
        user.setIme(firstName);
        user.setPrezime(lastName);
        user.setUsername(username);
        user.setPassword(encodePassword(password));
        user.setJmbg(jmbg);
        user.setAdresa(adresa);
        user.setRole(Role.STUDENT.name());
        user.setAuthorities(Role.STUDENT.getAuthorities());
        user.setTekuciRacun(tekuciRacun);
        user.setStanje(stanje);
        user.setBrojIndexa(brojIndexa);
        user.setBrojTelefona(brojTelefona);
        zahtevService.save(user);
        LOGGER.info("New user password: " + password);
        return user;
    }
	
	
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }
	
	
    private User validateNewUsername(String currentUsername, String newUsername) throws UserNotFoundException, UsernameExistException {
        User userByNewUsername = userRepository.findByUsername(newUsername);
        if(StringUtils.isNotBlank(currentUsername)) {
            User currentUser = userRepository.findByUsername(currentUsername);
            if(currentUser == null) {
                throw new UserNotFoundException(NO_USER_FOUND_BY_USERNAME + currentUsername);
            }
            if(userByNewUsername != null && !currentUser.getId().equals(userByNewUsername.getId())) {
                throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
            }
            return currentUser;
        } else {
            if(userByNewUsername != null) {
                throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
            }
            return null;
        }
    }

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public Boolean delete(Long id) {
		userRepository.deleteById(id);
		return true;
	}

	@Override
	public ResponseEntity<Void> updatePassword(User user, PasswordDto passwordDto) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (!(passwordEncoder.matches(passwordDto.getOldPassword(), user.getPassword())))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		user.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
		save(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
	
}
