package com.orbitalyards.landwar.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.orbitalyards.landwar.jpa.model.ref.Role;

public interface UserRoleRepository extends CrudRepository<Role, Long> {

}
