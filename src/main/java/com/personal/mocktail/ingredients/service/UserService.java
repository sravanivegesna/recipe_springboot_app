package com.personal.mocktail.ingredients.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.mocktail.ingredients.domain.Mocktail;
import com.personal.mocktail.ingredients.domain.User;
import com.personal.mocktail.ingredients.repo.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;

	public User save(User user) {
		return userRepo.save(user);
	}

	public List<User> getUsers() {
		return userRepo.findAll();
	}

	public List<User> getUserByName(String username) {
		return userRepo.findByfirstName(username);
	}

	public User getUserByUserId(Long inputUserId) {
		// TODO Auto-generated method stub
		Optional<User> retrievedOptionalUser = userRepo.findById(inputUserId);
		if (retrievedOptionalUser.isPresent() == false) {
			return null;
		} 
		return retrievedOptionalUser.get();
	}

	public User saveMocktail(Mocktail inputMocktail) {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	
}
