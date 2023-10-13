package com.orbitalyards.landwar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orbitalyards.landwar.jpa.repository.UnitInfoRepository;
import com.orbitalyards.landwar.mvc.model.data.UnitInfoEntry;
import com.orbitalyards.landwar.mvc.model.data.UserModel;
import com.orbitalyards.landwar.service.UnitInfoService;

@Service
public class UnitInfoServiceImpl implements UnitInfoService {

	@Autowired
	private UnitInfoRepository unitInfoRepository;
	
	@Override
	public UnitInfoEntry createUnit(UnitInfoEntry unitInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UnitInfoEntry> getUnitsByUser(UserModel userModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateUnit(UnitInfoEntry unitInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteUnit(UnitInfoEntry unitInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteUnits(List<UnitInfoEntry> unitList) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
