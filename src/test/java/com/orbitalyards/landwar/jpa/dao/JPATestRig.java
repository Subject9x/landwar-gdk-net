package com.orbitalyards.landwar.jpa.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.orbitalyards.landwar.jpa.model.ArmyList;
import com.orbitalyards.landwar.jpa.model.UnitInfo;
import com.orbitalyards.landwar.jpa.model.User;
import com.orbitalyards.landwar.jpa.model.map.UserRoleMap;
import com.orbitalyards.landwar.jpa.model.ref.Role;
import com.orbitalyards.landwar.jpa.model.ref.UnitTag;

import jakarta.persistence.EntityManager;

/***
 * Test class that includes mockup utility functions, extend this for unit tests.
 */
public abstract class JPATestRig {

	private List<Role> roleTable = new ArrayList<Role>();
	private int userTestTotal = 0;
	
	
	public void initMockTags(EntityManager em) {
		for(int i=0; i<31; i++) {
			UnitTag t = new UnitTag();
			t.setTagId(i);
			em.persist(t);
		}
		em.flush();
	}
	
	public void initMockRoles() {
		
		Role admin = new Role();
		admin.setRole("ADMIN");
		this.roleTable.add(admin);
		
		Role dev = new Role();
		dev.setRole("DEV");
		this.roleTable.add(dev);
		
		Role regular = new Role();
		regular.setRole("REGULAR");
		this.roleTable.add(regular); 
	}
	
	public void initRandomUsers(EntityManager em) {
		
		int tote = ThreadLocalRandom.current().nextInt(1, 50);
		setUserTestTotal(tote);
		
		for(int i=0; i < tote; i++) {
			User u = createMockUser(em);
			em.persist(u);
			
		}
		em.flush();
	}
	
	
	public User createMockUser(EntityManager em) {
		
		User u = new User();
		u.setUserName(generateRandoString());
		
		Set<Role> roles = new HashSet<Role>();
		
		for(int i=1; i < roles.size(); i++) {
			if(ThreadLocalRandom.current().nextInt(0, 1) == 1) {
				roles.add(em.find(Role.class, Long.valueOf(i)));
			}
		}
		
		u.setRoles(roles);
		
		return u;
	}
	
	public UnitInfo createMockUnitInfo() {
		
		UnitInfo u = new UnitInfo();
		u.setArmor(ThreadLocalRandom.current().nextInt(0, 15 + 1));
		u.setDesc(generateRandoString());
		u.setDmgMelee(ThreadLocalRandom.current().nextInt(0, 8));
		u.setDmgRange(ThreadLocalRandom.current().nextInt(0, 8));
		u.setEvade(ThreadLocalRandom.current().nextInt(0, 3));
		u.setMove(ThreadLocalRandom.current().nextInt(2, 24));
		u.setPointsCost(ThreadLocalRandom.current().nextInt(0, 750));
		u.setRange(ThreadLocalRandom.current().nextInt(0, 24));
		u.setSize(ThreadLocalRandom.current().nextInt(1, 8));
		u.setUnitName(generateRandoString());
		
		Set<UnitTag> tags = new HashSet<UnitTag>();
		
		int t = ThreadLocalRandom.current().nextInt(0, 6);
		
		for(int i=0; i < t; i++) {
			UnitTag junk = new UnitTag();
			junk.setTagId(ThreadLocalRandom.current().nextInt(0, 30));
			junk.setRulesId(1);
			tags.add(junk);
		}
		u.setTags(tags);
		
		return u;
	}
	
	public ArmyList createMockArmyList(){

		int rand = ThreadLocalRandom.current().nextInt(0, 5);
		
		ArmyList army = new ArmyList();
		Set<UnitInfo> list = new HashSet<UnitInfo>();
	
		army.setListName(generateRandoString());
		army.setDesc(generateRandoString());
		
		for(int i=0; i < rand; i++) {
			list.add(createMockUnitInfo());
		}
		
		army.setUnits(list);
		
		return army;
	}

	public UnitInfo insertUnitInfo(EntityManager em, UnitInfo crud) throws Exception{
		UnitInfo sent = (UnitInfo)((TestEntityManager)em).persistAndGetId(crud);
		em.flush();
		
		return sent;
	}
	
	public String generateRandoString() {

	    int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	    
	    return generatedString;
	}

	public int getUserTestTotal() {
		return userTestTotal;
	}

	public void setUserTestTotal(int userTestTotal) {
		this.userTestTotal = userTestTotal;
	}
	
	public List<Role> getRoleTable() {
		return roleTable;
	}

	public void setRoleTable(List<Role> roleTable) {
		this.roleTable = roleTable;
	}

}
