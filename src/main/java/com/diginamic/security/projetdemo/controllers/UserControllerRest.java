package com.diginamic.security.projetdemo.controllers;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diginamic.security.projetdemo.entities.User;
import com.diginamic.security.projetdemo.repo.UserRepo;

@RestController
@CrossOrigin
@RequestMapping("api/users")
public class UserControllerRest {

	@Autowired
	UserRepo userRepo;
	
	@GetMapping
	public List<User> getAll() {
		return userRepo.findAll();
	}
	@GetMapping("/{id}")
	public User getOne(@PathVariable Integer id) {
		return userRepo.findById(id).get();
	}
	

}
