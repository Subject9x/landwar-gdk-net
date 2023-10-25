package com.orbitalyards.landwar.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.orbitalyards.landwar.jpa.model.ref.UnitTag;

@Repository
public interface UnitTagRepository extends CrudRepository<UnitTag, Long> {

}
