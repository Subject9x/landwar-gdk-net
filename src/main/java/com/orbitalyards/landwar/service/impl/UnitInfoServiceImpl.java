package com.orbitalyards.landwar.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.orbitalyards.landwar.jpa.model.AppUser;
import com.orbitalyards.landwar.jpa.model.UnitInfo;
import com.orbitalyards.landwar.jpa.repository.UnitInfoRepository;
import com.orbitalyards.landwar.jpa.repository.UserRepository;
import com.orbitalyards.landwar.mvc.model.body.AppResponse;
import com.orbitalyards.landwar.mvc.model.body.UnitResponse;
import com.orbitalyards.landwar.mvc.model.data.UnitInfoEntry;
import com.orbitalyards.landwar.mvc.model.dto.UnitInfoEntryDTO;
import com.orbitalyards.landwar.service.UnitInfoService;
import com.orbitalyards.landwar.service.exception.UserServiceException;

import jakarta.transaction.Transactional;


@Service
public class UnitInfoServiceImpl implements UnitInfoService {
	
	private static Logger logger = LoggerFactory.getLogger(UnitInfoServiceImpl.class);
	
	@Autowired
	private UnitInfoRepository unitInfoRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UnitInfoEntryDTO unitInfoEntryDTO;
	
	@Override
	public AppResponse createUnits(UnitResponse unitInfoPack) {
		
		AppResponse response = validateUnitRequest(unitInfoPack.getUnits());
		
		if(response == null) {
			Optional<AppUser> checkUser = null;
			try {
				checkUser = userRepository.findByUserName(unitInfoPack.getUser().getUserName());
			}
			catch(Exception e){
				logger.error(e.getMessage());
				return new UnitResponse.Builder()
						.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
						.setMsg(UserServiceException.errors.JPA_GENERAL.msg())
						.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.build();
			}
			
			if(checkUser.isEmpty() || !checkUser.isPresent()) {
				return new UnitResponse.Builder()
								.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
								.setMsg(UserServiceException.errors.JPA_GENERAL.msg())
								.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
								.build();
			}
			
			AppUser user  = checkUser.get();
			
			try {
				updateExistingUnits(unitInfoPack.getUnits(), user);
			}
			catch(Exception e) {
				logger.error(e.getMessage());
				return new UnitResponse.Builder()
						.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
						.setMsg("Problem saving units to database.")
						.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.setUnits(unitInfoPack.getUnits())
						.build();
			}
			
			Optional<AppUser> usah = userRepository.findById(user.getId());

			return new UnitResponse.Builder()
					.setHttpStatus(HttpStatus.OK)
					.setMsg("Units have been saved successfully..")
					.setStatusCode(HttpStatus.OK.value())
					.setUnits(unitInfoPack.getUnits())
					.build();
		}
		
		return response;
	}

	@Override
	public AppResponse getUnitsByUser(String userName, String userCode, int count, String sortBy) {
		// TODO Auto-generated method stub
		
		
		Optional<AppUser> checkUser = userRepository.findByUserName(userName);
		
		List<UnitInfo> findUnits = unitInfoRepository.findByAppUserOrderById(checkUser.get());
		
		
		
		AppResponse dbg = new UnitResponse.Builder()
							.setHttpStatus(HttpStatus.OK)
							.setStatusCode(HttpStatus.OK.value())
							.setMsg("yup")
							.build();
		
		return dbg;
	}

	@Override
	public AppResponse updateUnit(UnitResponse unitInfoPack) {
		AppResponse response = validateUnitRequest(unitInfoPack.getUnits());
		
		if(response == null) {
			
		}
		
		return response;
	}

	@Override
	public AppResponse deleteUnits(UnitResponse unitInfoList) {
		AppResponse response = validateUnitRequest(unitInfoList.getUnits());
		
		if(response == null) {
			
		}
		
		// TODO Auto-generated method stub
		return null;
	}

	
	private UnitResponse validateUnitRequest(List<UnitInfoEntry> units) {
		
		if(units == null || units.size() == 0 || units.isEmpty()) {
			UnitResponse resp = new UnitResponse.Builder()
								.setHttpStatus(HttpStatus.BAD_REQUEST)
								.setMsg("Unit list was empty.")
								.build();
			return resp;
		}
		
		return null;
	}
	
	
	@Transactional
	private List<UnitInfoEntry> updateExistingUnits(List<UnitInfoEntry> updateList, AppUser user) throws Exception{
		
		
		List<UnitInfo> existingUnits = unitInfoRepository.findByAppUserOrderById(user);
		List<UnitInfo> createUnits = new ArrayList<UnitInfo>();
		for(UnitInfoEntry updated : updateList) {
			boolean isNew = true;
			for(UnitInfo exist : existingUnits) {
				if(exist.getId().equals(updated.getId())) {
					exist = unitInfoEntryDTO.toPersistFromModel(updated, exist);
					isNew = false;
				}
			}
			if(isNew) {
				UnitInfo newUnit = new UnitInfo();
				newUnit = unitInfoEntryDTO.toPersistFromModel(updated, newUnit);
				newUnit.setAppUser(user);
				user.getUnits().add(newUnit);
				createUnits.add(newUnit);
			}
		}
		existingUnits.addAll(createUnits);
		
		existingUnits = unitInfoRepository.saveAllAndFlush(existingUnits);
		
		return unitInfoEntryDTO.listModelFromPersist(existingUnits);
	}
	
	@Transactional
	private List<UnitInfoEntry> deleteUnits(List<UnitInfoEntry> deleteList, AppUser user) throws Exception{
		
		return null;
	}	
}
