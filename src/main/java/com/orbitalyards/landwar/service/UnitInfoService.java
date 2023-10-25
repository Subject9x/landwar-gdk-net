package com.orbitalyards.landwar.service;

import com.orbitalyards.landwar.mvc.model.body.AppResponse;
import com.orbitalyards.landwar.mvc.model.body.UnitResponse;

public interface UnitInfoService {
	
	public AppResponse createUnits(UnitResponse unitInfoPack);
	
	public AppResponse getUnitsByUser(String userName, String userCode, int count, String sortBy);
	
	public AppResponse updateUnit(UnitResponse unitInfoPack);
	
	public AppResponse deleteUnits(UnitResponse unitInfoList);
	
}
