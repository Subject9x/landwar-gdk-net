package com.orbitalyards.landwar.jpa.dao;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.orbitalyards.landwar.jpa.model.UnitInfo;
import com.orbitalyards.landwar.jpa.model.User;
import com.orbitalyards.landwar.jpa.model.ref.Role;
import com.orbitalyards.landwar.jpa.repository.UnitInfoRepository;
import com.orbitalyards.landwar.jpa.repository.UserRepository;
import com.orbitalyards.landwar.jpa.repository.UserRoleRepository;

@DataJpaTest
public class UserDaoTest extends JPATestRig{

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private UnitInfoRepository unitInfoRepository;
	
	
	@Test
	public void createUser() {
		User u = createMockUser(entityManager.getEntityManager());
		
		User saved = entityManager.persistAndFlush(u);
		
		System.out.println(saved);
	}
	
	@Test
	public void updateUser() {
		User u = createMockUser(entityManager.getEntityManager());
		
		User saved = entityManager.persistAndFlush(u);
		
		String t = generateRandoString();
		saved.setUserName(t);
		
		entityManager.merge(saved);
		entityManager.flush();
		
		Optional<User> change = userRepository.findById(saved.getId());
		
		User test = change.get();
	
		System.out.println(test.toString());
	}
	
	@Test
	public void deleteUser() {
		User u = createMockUser(entityManager.getEntityManager());
		
		User saved = userRepository.save(u);
		
		userRepository.delete(saved);
		
		Optional<User> delete = userRepository.findById(saved.getId());
		
		User isgone = delete.get();
		
		assertEquals(delete.get(), Optional.empty());
	}	
	
	@Test
	public void assignRole() {
		initMockRoles();
		userRoleRepository.saveAll(getRoleTable());
		
		User u = createMockUser(entityManager.getEntityManager());
		Set<Role> dummy = new HashSet<Role>(); 
		u.setRoles(dummy);
		
		User saved = userRepository.save(u);
		
		saved.getRoles().add(entityManager.find(Role.class, 2L));
		
		userRepository.save(saved);
		
		Optional<User> opCheck = userRepository.findById(saved.getId());
		
		assertNotEquals(opCheck.get(), Optional.empty());
		assertEquals(opCheck.get().getRoles(), saved.getRoles());
		
	}
	
	@Test
	public void userCreateUnit() {
		initMockRoles();
		initMockTags(entityManager.getEntityManager());
		
		
		UnitInfo m = createMockUnitInfo();
		
		User u = createMockUser(entityManager.getEntityManager());
		u = userRepository.save(u);
		
		m.setAppUser(u);
		u.getUnits().add(m);
		
		unitInfoRepository.save(m);
//		userRepository.save(u);
		
		Optional<User> test = userRepository.findById(u.getId());
		
		User check = test.get();

		assertNotEquals(check, null);
		
	}
}