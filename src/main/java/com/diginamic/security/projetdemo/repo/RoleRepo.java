package com.diginamic.security.projetdemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diginamic.security.projetdemo.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
}