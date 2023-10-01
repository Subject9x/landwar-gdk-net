package com.orbitalyards.landwar.jpa.dao;

import java.util.List;
import java.util.Optional;

public interface UnitInfoDao<UnitInfo> {

	public Optional<UnitInfo> loadById(Long unitId);
	public Optional<UnitInfo> loadByName(String unitName);
	public Optional<List<UnitInfo>> loadByUser(Long userId);
	
	public void updateUnit(UnitInfo unit);
	public void deleteUnit(UnitInfo unit);
	
	
	
}
