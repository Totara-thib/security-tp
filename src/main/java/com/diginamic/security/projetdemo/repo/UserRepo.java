package com.diginamic.security.projetdemo.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.diginamic.security.projetdemo.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	@Query("select u from User u where u.username = :name")
	Optional<User> findUserWithName(String name);
	
	
	Optional<User> findByUsername(String name);
	
}
