package com.orbitalyards.landwar.service;

import java.util.List;

import com.orbitalyards.landwar.mvc.model.data.UnitInfoEntry;
import com.orbitalyards.landwar.mvc.model.data.UserModel;

public interface UnitInfoService {
	
	public UnitInfoEntry createUnit(UnitInfoEntry unitInfo);
	
	public List<UnitInfoEntry> getUnitsByUser(UserModel userModel);
	
	public String updateUnit(UnitInfoEntry unitInfo);
	
	public String deleteUnit(UnitInfoEntry unitInfo);
	
	public String deleteUnits(List<UnitInfoEntry> unitList);
	
}
