package com.orbitalyards.landwar.jpa.repository.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.orbitalyards.landwar.jpa.model.AppUser;

@NoRepositoryBean
public interface BasicUnitInfoRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

	List<T> findAllByUser(AppUser u);
	
}
