package tseo.eobrazovanje.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tseo.eobrazovanje.model.User;
import tseo.eobrazovanje.service.UserServiceInterface;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserServiceInterface userService;

	
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
}
