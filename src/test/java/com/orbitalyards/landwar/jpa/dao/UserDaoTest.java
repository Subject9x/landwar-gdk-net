package com.orbitalyards.landwar.jpa.dao;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.orbitalyards.landwar.jpa.model.UnitInfo;
import com.orbitalyards.landwar.jpa.model.AppUser;
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
		AppUser u = createMockUser(entityManager.getEntityManager());
		
		AppUser saved = entityManager.persistAndFlush(u);
		
		System.out.println(saved);
	}
	
	@Test
	public void updateUser() {
		AppUser u = createMockUser(entityManager.getEntityManager());
		
		AppUser saved = entityManager.persistAndFlush(u);
		
		String t = generateRandoString();
		saved.setUserName(t);
		
		entityManager.merge(saved);
		entityManager.flush();
		
		Optional<AppUser> change = userRepository.findById(saved.getId());
		
		AppUser test = change.get();
	
		System.out.println(test.toString());
	}
	
	@Test
	public void deleteUser() {
		AppUser u = createMockUser(entityManager.getEntityManager());
		
		AppUser saved = userRepository.save(u);
		
		userRepository.delete(saved);
		
		Optional<AppUser> delete = userRepository.findById(saved.getId());
		
		AppUser isgone = delete.get();
		
		assertEquals(delete.get(), Optional.empty());
	}	
	
	@Test
	public void assignRole() {
		initMockRoles();
		userRoleRepository.saveAll(getRoleTable());
		
		AppUser u = createMockUser(entityManager.getEntityManager());
		Set<Role> dummy = new HashSet<Role>(); 
		u.setRoles(dummy);
		
		AppUser saved = userRepository.save(u);
		
		saved.getRoles().add(entityManager.find(Role.class, 2L));
		
		userRepository.save(saved);
		
		Optional<AppUser> opCheck = userRepository.findById(saved.getId());
		
		assertNotEquals(opCheck.get(), Optional.empty());
		assertEquals(opCheck.get().getRoles(), saved.getRoles());
		
	}
	
	@Test
	public void userCreateUnit() {
		initMockRoles();
		initMockTags(entityManager.getEntityManager());
		
		
		UnitInfo m = createMockUnitInfo();
		
		AppUser u = createMockUser(entityManager.getEntityManager());
		u = userRepository.save(u);
		
		m.setAppUser(u);
		u.getUnits().add(m);
		
		unitInfoRepository.save(m);
//		userRepository.save(u);
		
		Optional<AppUser> test = userRepository.findById(u.getId());
		
		AppUser check = test.get();

		assertNotEquals(check, null);
		
	}
}