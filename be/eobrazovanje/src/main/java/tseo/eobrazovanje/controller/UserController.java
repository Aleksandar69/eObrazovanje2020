package tseo.eobrazovanje.controller;

import static tseo.eobrazovanje.constant.SecurityConstant.JWT_TOKEN_HEADER;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.core.status.Status;
import tseo.eobrazovanje.constant.FileConstant;
import tseo.eobrazovanje.dto.PasswordDto;
import tseo.eobrazovanje.dto.StudentDto;
import tseo.eobrazovanje.enumeration.Role;
import tseo.eobrazovanje.exception.NotAnImageFileException;
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
	
    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
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
	
	@PutMapping("/password")
	public ResponseEntity<Void> updatePassword(@RequestBody PasswordDto passwordDto, Errors errors) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(passwordDto.getUsername(), passwordDto.getOldPassword()));
		User userFound = userService.findByUsername(passwordDto.getUsername());
		if (userFound == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return userService.updatePassword(userFound, passwordDto);
	}
	
    @PostMapping("/updateProfileImage")
    public ResponseEntity<User> updateProfileImage(@RequestParam("username") String username, @RequestParam(value = "profileImage") MultipartFile profileImage) throws UserNotFoundException, UsernameExistException,  IOException, NotAnImageFileException {
        User user = userService.updateProfileImage(username, profileImage);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    @GetMapping(path = "/image/{username}/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getProfileImage(@PathVariable("username") String username, @PathVariable("fileName") String fileName) throws IOException {
    	System.out.println("U ZAHTJEVU SLIKE");
        return Files.readAllBytes(Paths.get(FileConstant.USER_FOLDER + username + FileConstant.FORWARD_SLASH + fileName));
    }
    
    @GetMapping(path = "/image/profile/{username}", produces =  MediaType.IMAGE_JPEG_VALUE)
    public byte[] getTempProfileImage(@PathVariable("username") String username) throws IOException {
        URL url = new URL(FileConstant.TEMP_PROFILE_IMAGE_BASE_URL + username);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (InputStream inputStream = url.openStream()) {
            int bytesRead;
            byte[] chunk = new byte[1024];
            while((bytesRead = inputStream.read(chunk)) > 0) {
                byteArrayOutputStream.write(chunk, 0, bytesRead);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }
}
