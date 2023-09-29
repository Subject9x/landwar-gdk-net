package com.orbitalyards.landwar.jpa.dao;

import com.orbitalyards.landwar.jpa.model.UnitInfo;

public interface UnitInfoDao {

	public UnitInfo loadByName(String unitName);
}
