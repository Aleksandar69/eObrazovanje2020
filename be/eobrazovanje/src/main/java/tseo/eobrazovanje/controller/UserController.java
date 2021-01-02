package tseo.eobrazovanje.controller;

import static tseo.eobrazovanje.constant.SecurityConstant.JWT_TOKEN_HEADER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tseo.eobrazovanje.dto.StudentDto;
import tseo.eobrazovanje.enumeration.Role;
import tseo.eobrazovanje.exception.UserNotFoundException;
import tseo.eobrazovanje.exception.UsernameExistException;
import tseo.eobrazovanje.model.RegistracijaZahtev;
import tseo.eobrazovanje.model.Student;
import tseo.eobrazovanje.model.User;
import tseo.eobrazovanje.security.JWTTokenProvider;
import tseo.eobrazovanje.security.UserPrincipal;
import tseo.eobrazovanje.service.StudentServiceInterface;
import tseo.eobrazovanje.service.UserServiceInterface;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
	
    private AuthenticationManager authenticationManager;
    private UserServiceInterface userService;
    private JWTTokenProvider jwtTokenProvider;
	StudentServiceInterface studentService;

    @Autowired
    public UserController(AuthenticationManager authenticationManager, UserServiceInterface userService, JWTTokenProvider jwtTokenProvider,
    		StudentServiceInterface ssi) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        studentService = ssi;
    }


	
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        User loginUser = userService.findByUsername(user.getUsername());
        UserPrincipal userPrincipal = new UserPrincipal(loginUser);
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(userPrincipal));
        return new ResponseEntity<>(loginUser, headers, HttpStatus.OK);
    }
    
    @PostMapping("/register")
    public ResponseEntity<RegistracijaZahtev> register(@RequestBody Student user) throws UserNotFoundException, UsernameExistException {
    	System.out.println("ADRESA:" + user.getIme());
    	System.out.println("JMBG:" + user.getJmbg());
    	System.out.println("ADRESA:" + user.getAdresa());
    	System.out.println("TEKUCI RACUN: " + user.getTekuciRacun());
    	RegistracijaZahtev newUser = userService.register(user.getIme(), user.getPrezime(), user.getUsername(), user.getPassword(), user.getJmbg(), user.getAdresa(), user.getTekuciRacun(), user.getBrojIndexa(), user.getStanje(), user.getBrojTelefona());
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }
    
	@GetMapping()
	public ResponseEntity getAll() {
		return new ResponseEntity(userService.findAll(), HttpStatus.OK);
	}
 
	@GetMapping("/{id}")
	public ResponseEntity getOne(@PathVariable("id") long id) {
		User user = userService.findOne(id);
		if (user != null) {
			return new ResponseEntity(user, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	
	public ResponseEntity deleteOne(@PathVariable("id") long id) {
		User user = userService.findOne(id);
		if (user != null) {
			userService.delete(user.getId());
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/username-check")
	public ResponseEntity<Void> checkUsername(@RequestParam(value = "username", defaultValue = "") String username) {
		User user = userService.findByUsername(username);
		if (user != null) {
			return new ResponseEntity<>(HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/user/{username}")
	public ResponseEntity getUserByUsername(@PathVariable("username") String username) {
		User user = userService.findByUsername(username);
		
		if (user != null) {
			if(user.getRole().equals(Role.STUDENT)){
				Student student = studentService.findOne(user.getId());
				StudentDto studentdto = studentService.studentDtoMaker(student);
				
				return ResponseEntity.ok(studentdto);
			}else{
				return ResponseEntity.ok(user);
				}
			
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
