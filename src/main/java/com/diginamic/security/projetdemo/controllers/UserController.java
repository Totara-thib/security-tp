 package com.diginamic.security.projetdemo.controllers;


import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diginamic.security.projetdemo.entities.User;
import com.diginamic.security.projetdemo.repo.UserRepo;

@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserRepo userRepo;

	@GetMapping("/list")
	public String list(Model model) {
	      model.addAttribute("users", userRepo.findAll());

		return "list";
	}
	
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/edit/{userId}")
	public String modifie(@PathVariable Integer userId,Model model) {
		User user = userRepo.getById(userId);
		 model.addAttribute("user", user);
		return "edit";
	}

	@PostMapping("/edit")
	public String validmodifie(@ModelAttribute User user, Model model) {
		userRepo.save(user);
		return "home";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/add")
	public String add(Model model) {
		return "add";
	}
	
	@PostMapping("/add")
	public String validAdd(@ModelAttribute User user, Model model) {
		userRepo.save(user);
		return "home";
	}
	
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, Model model) {
		userRepo.deleteById(id);
		return "home";
	}
}