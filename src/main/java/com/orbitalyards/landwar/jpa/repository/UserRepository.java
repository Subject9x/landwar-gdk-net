package com.orbitalyards.landwar.jpa.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.orbitalyards.landwar.jpa.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByUserName(String userName);
}
