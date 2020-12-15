package tseo.eobrazovanje.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tseo.eobrazovanje.model.User;
import tseo.eobrazovanje.repo.UserRepository;
import tseo.eobrazovanje.service.UserServiceInterface;

public class UserService implements UserServiceInterface{
	
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

	
	
}
