package com.orbitalyards.landwar.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.orbitalyards.landwar.jpa.model.UnitInfo;
import com.orbitalyards.landwar.jpa.model.User;
import com.orbitalyards.landwar.jpa.repository.UnitInfoRepository;
import com.orbitalyards.landwar.jpa.repository.UserRepository;
import com.orbitalyards.landwar.mvc.model.body.AppResponse;
import com.orbitalyards.landwar.mvc.model.body.UnitResponse;
import com.orbitalyards.landwar.mvc.model.data.UnitInfoEntry;
import com.orbitalyards.landwar.mvc.model.dto.UnitInfoEntryDTO;
import com.orbitalyards.landwar.service.UnitInfoService;
import com.orbitalyards.landwar.service.exception.UserServiceException;


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
			Optional<User> checkUser = null;
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
			
			User user  = checkUser.get();
			
			Set<UnitInfo> units = user.getUnits();
			for(UnitInfoEntry e : unitInfoPack.getUnits()) {
				UnitInfo newUnit = new UnitInfo();
				newUnit.setAppUser(user);
				units.add(unitInfoEntryDTO.toPersistFromModel(e, newUnit));
			}
			
			
			try {
				userRepository.save(user);
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
		
		
		Optional<User> checkUser = userRepository.findByUserName(userName);
		List<UnitInfo> findUnits = unitInfoRepository.findAllByAppUser(checkUser.get());
		
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
}
