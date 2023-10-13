package com.orbitalyards.landwar.service.impl;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orbitalyards.landwar.jpa.model.User;
import com.orbitalyards.landwar.jpa.model.ref.Role;
import com.orbitalyards.landwar.jpa.repository.UserRepository;
import com.orbitalyards.landwar.jpa.repository.UserRoleRepository;
import com.orbitalyards.landwar.mvc.model.data.UserModel;
import com.orbitalyards.landwar.mvc.model.dto.UserModelDTO;
import com.orbitalyards.landwar.service.AppUserService;
import com.orbitalyards.landwar.service.exception.UserServiceException;

@Service(value = "svcAppUser")
public class AppUserServiceImpl implements AppUserService {

	private static Logger logger = LoggerFactory.getLogger(AppUserServiceImpl.class);
	
	@Autowired
	private UserModelDTO userModelDTO;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	
	@Override
	public UserModel registerUser(String userName, String userCode) throws Exception, UserServiceException {
		
		//TODO - replace with validators at some point.
		if(userName == null || userName.isEmpty() || userName.isBlank()) {
			throw new UserServiceException(UserServiceException.errors.EMPTY_USERNAME.msg());
		}
		
		if(userCode == null || userCode.isEmpty() || userCode.isBlank()) {
			throw new UserServiceException(UserServiceException.errors.EMPTY_USERCODE.msg());
		}
		
		Optional<User> exists = userRepository.findByUserName(userName);
		
		if(!exists.isEmpty() || exists.isPresent()) {
			throw new UserServiceException(UserServiceException.errors.EXIST_USERNAME.msg());
		}
		
		UserModel newUserModel = new UserModel();
		newUserModel.setUserName(userName);
		newUserModel.setUserCode(userCode);
		
		User newUser = null;
		try {
			newUser = userModelDTO.toPersistFromModel(newUserModel, new User());
			newUser = userRepository.save(newUser);
		}
		catch(Exception e) {
			logger.error(e.getMessage());
			throw new UserServiceException(UserServiceException.errors.JPA_GENERAL.msg());
		}
		
		if(newUser == null || newUser.getId() == 0L) {
			logger.error("User failed to persis: {}", newUser);
			throw new UserServiceException(UserServiceException.errors.JPA_GENERAL.msg());
		}
		
		return userModelDTO.toModelFromPersist(newUserModel, newUser);
	}
	
	@Override
	public boolean loginUser(String userName, String userCode) throws Exception, UserServiceException {
		return updateUserLogin(userName, userCode, true);
	}
	

	@Override
	public boolean logoutUser(String userName, String userCode) throws Exception, UserServiceException {
		return updateUserLogin(userName, userCode, false);
	}
	
	@Override
	public void deleteUser(String userName, String userCode) throws Exception, UserServiceException {
				
		validateUserInput(userName, userCode);
		
		Optional<User> exists = userRepository.findByUserName(userName);
		
		if(exists.isEmpty() || !exists.isPresent()) {
			throw new UserServiceException(UserServiceException.errors.EMPTY_USERNAME.msg());
		}
		
		User user = exists.get();
		
		if(!user.getPassCode().equals(userCode)) {
			throw new UserServiceException(UserServiceException.errors.USER_BAD_CODE.msg());
		}
		
		try {
			userRepository.delete(exists.get());	
		}
		catch(Exception e) {
			throw new UserServiceException(UserServiceException.errors.JPA_GENERAL.msg());
		}
	}
	
	@Override
	public void adminDeleteUser(String userName, String adminUser, String adminCode) throws Exception, UserServiceException{
		
		validateUserRole(adminUser, adminCode, Arrays.asList("ADMIN"));
		
		Optional<User> toDelete = userRepository.findByUserName(userName);
		
		if(toDelete.isEmpty() || !toDelete.isPresent()) {
			throw new UserServiceException(UserServiceException.errors.EMPTY_USERNAME.msg());
		}
		
		try {
			userRepository.delete(toDelete.get());
		}
		catch(Exception e) {
			logger.error(e.getMessage());
			throw new UserServiceException(UserServiceException.errors.JPA_GENERAL.msg());
		}
	}
	
	@Override
	public String updateUser(UserModel userModel)  throws Exception, UserServiceException{
		
		Optional<User> exists = userRepository.findById(0l);
		
		if(exists.isEmpty() || !exists.isPresent()) {
			throw new UserServiceException(UserServiceException.errors.EMPTY_USERNAME.msg());
		}
		
		
		
		return null;
	}

	
	@Override
	public void updateUserRole( String userName, String userCode, List<String> roles) throws Exception, UserServiceException{
		
		validateUserInput(userName, userCode);
		
		Optional<User> exists = userRepository.findByUserName(userName);
		
		if(exists.isEmpty() || !exists.isPresent()) {
			throw new UserServiceException(UserServiceException.errors.EMPTY_USERNAME.msg());
		}
		
		User user = exists.get();
		
		Iterable<Role> jpaRoles = userRoleRepository.findAll();
		Set<Role> updateRoles = new HashSet<Role>();
		
		jpaRoles.forEach(t -> {
			if(roles.contains(t.getRole())) {
				updateRoles.add(t);
			}
		});
		
		user.setRoles(updateRoles);
		
		try {
			userRepository.save(user);
		}
		catch(Exception e) {
			logger.error(e.getMessage());
			throw new UserServiceException(UserServiceException.errors.JPA_GENERAL.msg());
		}
	}

	private void validateUserInput(String userName, String userCode) throws Exception, UserServiceException{
		//TODO - replace with validators at some point.
		if(userName == null || userName.isEmpty() || userName.isBlank()) {
			throw new UserServiceException(UserServiceException.errors.EMPTY_USERNAME.msg());
		}
		
		if(userCode == null || userCode.isEmpty() || userCode.isBlank()) {
			throw new UserServiceException(UserServiceException.errors.EMPTY_USERCODE.msg());
		}
	}
	
	private void validateUserRole(String userName, String userCode, List<String> roles) throws Exception, UserServiceException{
		
		//validate admin user
		validateUserInput(userName, userCode);
		
		Optional<User> exists = userRepository.findByUserName(userName);
		
		if(!Optional.empty().isEmpty() || !Optional.empty().isPresent()) {
			throw new UserServiceException(UserServiceException.errors.EXIST_USERNAME.msg());
		}
		
		if(!exists.get().getPassCode().equals(userCode)) {
			throw new UserServiceException(UserServiceException.errors.USER_BAD_CODE.msg());
		}
		
		User user = exists.get();
		
		if(user.getRoles() == null || user.getRoles().isEmpty()) {
			throw new UserServiceException(UserServiceException.errors.USER_ROLE_MATCH.msg());
		}
		
		List<String> userRoles = user.getRoles().stream().map(Role::getRole).collect(Collectors.toList());
		if(!userRoles.containsAll(roles)) {
			throw new UserServiceException(UserServiceException.errors.USER_ROLE_MATCH.msg());
		}
	}
	
	private boolean updateUserLogin(String userName, String userCode, boolean loginVal) throws Exception, UserServiceException{
		
		validateUserInput(userName, userCode);

		Optional<User> exists = userRepository.findByUserName(userName);
		
		if(!Optional.empty().isEmpty() || !Optional.empty().isPresent()) {
			throw new UserServiceException(UserServiceException.errors.EXIST_USERNAME.msg());
		}
		
		if(!exists.get().getPassCode().equals(userCode)) {
			throw new UserServiceException(UserServiceException.errors.USER_BAD_CODE.msg());
		}
		
		if(exists.get().getLogIn()) {
			return exists.get().getLogIn();
		}
		
		User user = exists.get();
		
		user.setLogIn(true);
		user.setLogInTime(new Timestamp(System.currentTimeMillis()));

		try {
			user = userRepository.save(user);	
		}
		catch(Exception e) {
			logger.error(e.getMessage());
			throw new UserServiceException(UserServiceException.errors.JPA_GENERAL.msg());
		}
		
		if(exists.get().getLogIn() != loginVal) {
			logger.error("Uncaught JPA exception on user {}", exists.get().getUserName());
			throw new UserServiceException(UserServiceException.errors.JPA_GENERAL.msg());
		}
		
		return user.getLogIn();
	}

}
