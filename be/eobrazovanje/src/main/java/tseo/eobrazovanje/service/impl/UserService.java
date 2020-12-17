package tseo.eobrazovanje.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tseo.eobrazovanje.enumeration.Role;
import tseo.eobrazovanje.exception.UserNotFoundException;
import tseo.eobrazovanje.exception.UsernameExistException;
import tseo.eobrazovanje.model.User;
import tseo.eobrazovanje.repo.UserRepository;
import tseo.eobrazovanje.security.UserPrincipal;
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
    public User register(String firstName, String lastName, String username) throws UserNotFoundException, UsernameExistException {
    	validateNewUsername(StringUtils.EMPTY, username);
        User user = new User();
        String password = generatePassword();
        user.setIme(firstName);
        user.setPrezime(lastName);
        user.setUsername(username);
        user.setPassword(encodePassword(password));
        user.setRole(Role.STUDENT.name());
        user.setAuthorities(Role.STUDENT.getAuthorities());
        userRepository.save(user);
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
	
}
