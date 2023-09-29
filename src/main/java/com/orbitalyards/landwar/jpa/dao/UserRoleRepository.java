package com.orbitalyards.landwar.jpa.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orbitalyards.landwar.jpa.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
	Optional<UserRole> findByName(String userName);
}
