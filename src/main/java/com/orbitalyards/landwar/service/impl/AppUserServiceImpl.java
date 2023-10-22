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
import com.orbitalyards.landwar.mvc.model.body.UserResponse;
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
	public UserResponse registerUser(String userName, String userCode) {
		
		UserResponse validate = validateUserInput(userName, userCode);
		if(validate != null) {
			return validate;
		}
		
		Optional<User> exists = null;
		try {
			exists = userRepository.findByUserName(userName);
		}
		catch(Exception e) {
			logger.error(e.getMessage());
			UserServiceException userError = new UserServiceException(UserServiceException.errors.JPA_GENERAL.msg());
			UserResponse resp = new UserResponse.Builder().setError(true)
									.setMsg(userError.getMessage())
									.setUserException(userError)
									.build();
			return resp;
		}
		
		if(!exists.isEmpty() || exists.isPresent()) {
			UserServiceException userError = new UserServiceException(UserServiceException.errors.EXIST_USERNAME.msg());
			UserResponse resp = new UserResponse.Builder().setError(true)
									.setMsg(userError.getMessage())
									.setUserException(userError)
									.build();
			return resp;
		}
		
		UserModel newUserModel = new UserModel();
		newUserModel.setUserName(userName);
		newUserModel.setUserCode(userCode);
		
		User newUser = null;
		try {
			newUser = new User();
			newUser = userModelDTO.toPersistFromModel(newUserModel, newUser);
			newUser = userRepository.save(newUser);
		}
		catch(Exception e) {
			logger.error(e.getMessage());
			UserServiceException userError = new UserServiceException(UserServiceException.errors.JPA_GENERAL.msg());
			UserResponse resp = new UserResponse.Builder().setError(true)
									.setMsg(userError.getMessage())
									.setUserException(userError)
									.build();
			return resp;
		}
		
		if(newUser == null || newUser.getId() == 0L) {
			logger.error("User failed to persist: {}", newUser);
			UserServiceException userError = new UserServiceException(UserServiceException.errors.JPA_GENERAL.msg());
			UserResponse resp = new UserResponse.Builder().setError(true)
									.setMsg(userError.getMessage())
									.setUserException(userError)
									.build();
			return resp;
		}
		
		UserResponse resp = new UserResponse.Builder().setUser(userModelDTO.toModelFromPersist(newUserModel, newUser))
								.setMsg("User created successfully")
								.build();
		return resp;
	}
	
	@Override
	public UserResponse loginUser(String userName, String userCode) {
		return updateUserLogin(userName, userCode, true);
	}
	

	@Override
	public UserResponse logoutUser(String userName, String userCode) {
		return updateUserLogin(userName, userCode, false);
	}
	
	@Override
	public UserResponse deleteUser(String userName, String userCode)  {
		
		UserResponse validate = validateUserInput(userName, userCode);
		if(validate != null) {
			return validate;
		}
		
		Optional<User> exists = userRepository.findByUserName(userName);
		
		if(exists.isEmpty() || !exists.isPresent()) {
			logger.error(UserServiceException.errors.EMPTY_USERNAME.msg() + " {}", userName);
			UserServiceException userError = new UserServiceException(UserServiceException.errors.EMPTY_USERNAME.msg());
			UserResponse resp = new UserResponse.Builder().setError(true)
									.setMsg(userError.getMessage())
									.setUserException(userError)
									.build();
			return resp;
		}
		
		User user = exists.get();
		
		if(!user.getPassCode().equals(userCode)) {
			logger.error(UserServiceException.errors.USER_BAD_CODE.msg() + " {}", userName);
			UserServiceException userError = new UserServiceException(UserServiceException.errors.USER_BAD_CODE.msg());
			UserResponse resp = new UserResponse.Builder().setError(true)
									.setMsg(userError.getMessage())
									.setUserException(userError)
									.build();
			return resp;
		}
		
		try {
			userRepository.delete(user);	
		}
		catch(Exception e) {
			logger.error(UserServiceException.errors.JPA_GENERAL.msg() + " {}", userName);
			UserServiceException userError = new UserServiceException(UserServiceException.errors.JPA_GENERAL.msg());
			UserResponse resp = new UserResponse.Builder().setError(true)
									.setMsg(userError.getMessage())
									.setUserException(userError)
									.build();
			return resp;
		}

		logger.info("User[{}] self-deleted.", userName);
		UserResponse resp = new UserResponse.Builder()
								.setMsg("User deleted successfully.")
								.build();
		return resp;
	}
	
	@Override
	public UserResponse adminDeleteUser(String userName, String adminUser, String adminCode) {
		
		
		UserResponse validated = validateUserRole(adminUser, adminCode, Arrays.asList("ADMIN")); 
		if(validated != null) {
			return validated;
		}
		
		Optional<User> toDelete = userRepository.findByUserName(userName);
		
		if(toDelete.isEmpty() || !toDelete.isPresent()) {
			logger.error(UserServiceException.errors.EMPTY_USERNAME.msg() + " {}", userName);
			UserServiceException userError = new UserServiceException(UserServiceException.errors.EMPTY_USERNAME.msg());
			UserResponse resp = new UserResponse.Builder().setError(true)
									.setMsg(userError.getMessage())
									.setUserException(userError)
									.build();
			return resp;
		}
		
		try {
			userRepository.delete(toDelete.get());
		}
		catch(Exception e) {
			logger.error(e.getMessage());
			UserServiceException userError = new UserServiceException(UserServiceException.errors.JPA_GENERAL.msg());
			UserResponse resp = new UserResponse.Builder().setError(true)
									.setMsg(userError.getMessage())
									.setUserException(userError)
									.build();
			return resp;
		}
		UserResponse resp = new UserResponse.Builder()
				.setMsg("User deleted successfully.")
				.build();
		return resp;
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
	public UserResponse updateUserRole( String userName, String userCode, List<String> roles){
		
		UserResponse validate = validateUserInput(userName, userCode);
		if(validate != null) {
			return validate;
		}
		
		Optional<User> exists = userRepository.findByUserName(userName);
		
		if(exists.isEmpty() || !exists.isPresent()) {
			logger.error(UserServiceException.errors.EMPTY_USERNAME.msg() + " {}", userName);
			UserServiceException userError = new UserServiceException(UserServiceException.errors.EMPTY_USERNAME.msg());
			UserResponse resp = new UserResponse.Builder().setError(true)
									.setMsg(userError.getMessage())
									.setUserException(userError)
									.build();
			return resp;
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
			UserServiceException userError = new UserServiceException(UserServiceException.errors.JPA_GENERAL.msg());
			UserResponse resp = new UserResponse.Builder().setError(true)
									.setMsg(userError.getMessage())
									.setUserException(userError)
									.build();
			return resp;
		}
		logger.info("User[{}] updated roles successfully.");
		UserResponse resp = new UserResponse.Builder()
								.setMsg("Updated roles successfully.")
								.build();
		return resp;
	}

	private UserResponse validateUserInput(String userName, String userCode){
		//TODO - replace with validators at some point.
		if(userName == null || userName.isEmpty() || userName.isBlank()) {
			UserServiceException userError = new UserServiceException(UserServiceException.errors.EMPTY_USERNAME.msg());
			UserResponse resp = new UserResponse.Builder().setError(true)
									.setMsg(userError.getMessage())
									.setUserException(userError)
									.build();
			return resp;
		}
		
		if(userCode == null || userCode.isEmpty() || userCode.isBlank()) {
			UserServiceException userError = new UserServiceException(UserServiceException.errors.EMPTY_USERCODE.msg());
			UserResponse resp = new UserResponse.Builder().setError(true)
									.setMsg(userError.getMessage())
									.setUserException(userError)
									.build();
			return resp;
		}
		return null;
	}
	
	private UserResponse validateUserRole(String userName, String userCode, List<String> roles){
		
		//validate admin user
		validateUserInput(userName, userCode);
		
		Optional<User> exists = userRepository.findByUserName(userName);
		
		if(!Optional.empty().isEmpty() || !Optional.empty().isPresent()) {
			logger.error("User[{}] doesn't exist in database.", userName);
			UserServiceException userError = new UserServiceException(UserServiceException.errors.EXIST_USERNAME.msg());
			UserResponse resp = new UserResponse.Builder().setError(true)
									.setMsg(userError.getMessage())
									.setUserException(userError)
									.build();
			return resp;
		}
		
		if(!exists.get().getPassCode().equals(userCode)) {
			logger.error("User[{}] failed pass-code match.", userName);
			UserServiceException userError = new UserServiceException(UserServiceException.errors.USER_BAD_CODE.msg());
			UserResponse resp = new UserResponse.Builder().setError(true)
									.setMsg(userError.getMessage())
									.setUserException(userError)
									.build();
			return resp;
		}
		
		User user = exists.get();
		
		if(user.getRoles() == null) {
			logger.info("User[{}] had null role list, adding new list.", userName);
			user.setRoles(new HashSet<Role>());
		}
		
		List<String> userRoles = user.getRoles().stream().map(Role::getRole).collect(Collectors.toList());
		if(!userRoles.containsAll(roles)) {
			logger.error("User[{}] missing roles [" + roles.toString() + "]", userName);
			UserServiceException userError = new UserServiceException(UserServiceException.errors.USER_ROLE_MATCH.msg());
			UserResponse resp = new UserResponse.Builder().setError(true)
									.setMsg(userError.getMessage())
									.setUserException(userError)
									.build();
			return resp;
		}
		return null;
	}
	
	private UserResponse updateUserLogin(String userName, String userCode, boolean loginVal){
		
		UserResponse validate = validateUserInput(userName, userCode);
		if(validate != null) {
			return validate;
		}
		

		Optional<User> exists = userRepository.findByUserName(userName);
		
		if(!Optional.empty().isEmpty() || !Optional.empty().isPresent()) {
			logger.error("User[{}] doesn't exist in database.", userName);
			UserServiceException userError = new UserServiceException(UserServiceException.errors.EXIST_USERNAME.msg());
			UserResponse resp = new UserResponse.Builder().setError(true)
									.setMsg(userError.getMessage())
									.setUserException(userError)
									.build();
			return resp;
		}
		
		if(!exists.get().getPassCode().equals(userCode)) {
			logger.error("User[{}] failed pass-code match.", userName);
			UserServiceException userError = new UserServiceException(UserServiceException.errors.USER_BAD_CODE.msg());
			UserResponse resp = new UserResponse.Builder().setError(true)
									.setMsg(userError.getMessage())
									.setUserException(userError)
									.build();
			return resp;
		}
		
		if(exists.get().getLogIn()) {
			logger.info("User[{}] already logged in", userName);
			UserResponse resp = new UserResponse.Builder()
					.setMsg("You are already logged in.")
					.build();
			return resp;
		}
		
		User user = exists.get();
		
		user.setLogIn(true);
		user.setLogInTime(new Timestamp(System.currentTimeMillis()));

		try {
			user = userRepository.save(user);	
		}
		catch(Exception e) {
			logger.error(e.getMessage());
			UserServiceException userError = new UserServiceException(UserServiceException.errors.JPA_GENERAL.msg());
			UserResponse resp = new UserResponse.Builder().setError(true)
									.setMsg(userError.getMessage())
									.setUserException(userError)
									.build();
			return resp;
		}
		
		if(exists.get().getLogIn() != loginVal) {
			logger.error("Uncaught JPA exception on user {}", exists.get().getUserName());
			UserServiceException userError = new UserServiceException(UserServiceException.errors.JPA_GENERAL.msg());
			UserResponse resp = new UserResponse.Builder().setError(true)
									.setMsg(userError.getMessage())
									.setUserException(userError)
									.build();
			return resp;
		}
		
		logger.info("User[{}] login updated to [" + loginVal + "].", userName);
		UserResponse resp = new UserResponse.Builder()
				.setMsg("Login successful.")
				.build();
		return resp;
	}
}
