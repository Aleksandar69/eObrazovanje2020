package tseo.eobrazovanje.service.impl;

import static org.springframework.http.MediaType.IMAGE_GIF_VALUE;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
import static tseo.eobrazovanje.constant.FileConstant.DIRECTORY_CREATED;
import static tseo.eobrazovanje.constant.FileConstant.DOT;
import static tseo.eobrazovanje.constant.FileConstant.FILE_SAVED_IN_FILE_SYSTEM;
import static tseo.eobrazovanje.constant.FileConstant.JPG_EXTENSION;
import static tseo.eobrazovanje.constant.FileConstant.NOT_AN_IMAGE_FILE;
import static tseo.eobrazovanje.constant.FileConstant.USER_FOLDER;
import static tseo.eobrazovanje.constant.UserImplConstant.FOUND_USER_BY_USERNAME;
import static tseo.eobrazovanje.constant.UserImplConstant.NO_USER_FOUND_BY_USERNAME;
import static tseo.eobrazovanje.constant.UserImplConstant.USERNAME_ALREADY_EXISTS;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tseo.eobrazovanje.dto.PasswordDto;
import tseo.eobrazovanje.enumeration.Role;
import tseo.eobrazovanje.exception.NotAnImageFileException;
import tseo.eobrazovanje.exception.UserNotFoundException;
import tseo.eobrazovanje.exception.UsernameExistException;
import tseo.eobrazovanje.model.RegistracijaZahtev;
import tseo.eobrazovanje.model.User;
import tseo.eobrazovanje.repo.StudentRepository;
import tseo.eobrazovanje.repo.UserRepository;
import tseo.eobrazovanje.security.UserPrincipal;
import tseo.eobrazovanje.service.RegistracijaZahtevServiceInterface;
import tseo.eobrazovanje.service.UserServiceInterface;
import static tseo.eobrazovanje.constant.FileConstant.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


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
	
    @Override
    public User updateProfileImage(String username, MultipartFile profileImage) throws UserNotFoundException, UsernameExistException, IOException, NotAnImageFileException {
        User user = validateNewUsername(username, null);
        saveProfileImage(user, profileImage);
        return user;
    }
    
    private void saveProfileImage(User user, MultipartFile profileImage) throws IOException, NotAnImageFileException {
        if (profileImage != null) {
            if(!Arrays.asList(IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE, IMAGE_GIF_VALUE).contains(profileImage.getContentType())) {
                throw new NotAnImageFileException(profileImage.getOriginalFilename() + NOT_AN_IMAGE_FILE);
            }
            Path userFolder = Paths.get(USER_FOLDER + user.getUsername()).toAbsolutePath().normalize();
            if(!Files.exists(userFolder)) {
                Files.createDirectories(userFolder);
                LOGGER.info(DIRECTORY_CREATED + userFolder);
            }
            Files.deleteIfExists(Paths.get(userFolder + user.getUsername() + DOT + JPG_EXTENSION));
            Files.copy(profileImage.getInputStream(), userFolder.resolve(user.getUsername() + DOT + JPG_EXTENSION), REPLACE_EXISTING);
            user.setProfileImageUrl(setProfileImageUrl(user.getUsername()));
            userRepository.save(user);
            LOGGER.info(FILE_SAVED_IN_FILE_SYSTEM + profileImage.getOriginalFilename());
        }
    }
    
    private String setProfileImageUrl(String username) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(USER_IMAGE_PATH + username + FORWARD_SLASH
        + username + DOT + JPG_EXTENSION).toUriString();
    }
}
