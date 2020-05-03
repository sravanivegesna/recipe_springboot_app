package com.personal.mocktail.ingredients.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.mocktail.ingredients.bean.ApiError;
import com.personal.mocktail.ingredients.bean.ApiResponse;
import com.personal.mocktail.ingredients.bean.ApiResponseStatus;
import com.personal.mocktail.ingredients.bean.InputPermissionRequest;
import com.personal.mocktail.ingredients.domain.Mocktail;
import com.personal.mocktail.ingredients.domain.Permission;
import com.personal.mocktail.ingredients.domain.User;
import com.personal.mocktail.ingredients.exception.InvalidInputDataException;
import com.personal.mocktail.ingredients.service.PermissionsService;
import com.personal.mocktail.ingredients.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService userService;

	@Autowired
	PermissionsService permissionService;

	@GetMapping
	public ApiResponse getUsers() {
		return new ApiResponse(ApiResponseStatus.SUCCESS, null, userService.getUsers());
	}

	@GetMapping(path = "/{firstname}")
	public ApiResponse getUsers(@PathVariable String firstname) {
		logger.info(firstname);
		logger.info("User details by Firstname {}", userService.getUserByName(firstname));
		return new ApiResponse(ApiResponseStatus.SUCCESS, null, userService.getUserByName(firstname));
	}

	@PostMapping
	public ApiResponse saveUser(@RequestBody User inputUser) throws InvalidInputDataException {
		logger.info(inputUser.toString());
		// validations
		if (StringUtils.isEmpty(inputUser.getEmail()) || StringUtils.isEmpty(inputUser.getFirstName())
				|| StringUtils.isEmpty(inputUser.getLastName())) {
			throw new InvalidInputDataException("Empty fields should not be allowed");
		}
		// saveuser
		User savedUser = userService.save(inputUser);
		return new ApiResponse(ApiResponseStatus.SUCCESS, null, savedUser);
	}

	@GetMapping(path = "/{userid}/mocktails")
	public ApiResponse getUserMocktail(@PathVariable long userid) {
		logger.info("input Userid {}", userid);
		User userInfo = userService.getUserByUserId(userid);
		if (userInfo == null) {
			List<ApiError> apiErrorList = new ArrayList<ApiError>();
			apiErrorList.add(new ApiError("User Not Found", "Input info doesn't exists with userid : " + userid));
			return new ApiResponse(ApiResponseStatus.FAILURE, apiErrorList, null);
		}
		if (userInfo.getMocktails().size() <= 0) {
			return new ApiResponse(ApiResponseStatus.SUCCESS, null, "No Mocktails are available");
		}
		;
		return new ApiResponse(ApiResponseStatus.SUCCESS, null, userInfo.getMocktails());
	}

	@PostMapping(path = "/{userid}/mocktails")
	public ApiResponse saveMockatil(@PathVariable long userid, @RequestBody Mocktail inputMocktail)
			throws InvalidInputDataException {
		logger.info("Mocktail inputdata {}", inputMocktail);
		if (StringUtils.isEmpty(inputMocktail.getDuration()) || StringUtils.isEmpty(inputMocktail.getName())
				|| StringUtils.isEmpty(inputMocktail.getServing())) {
			throw new InvalidInputDataException("Empty fields should not be allowed");
		}
		User userInfo = userService.getUserByUserId(userid);
		if (userInfo == null) {
			List<ApiError> apiErrorList = new ArrayList<ApiError>();
			apiErrorList.add(new ApiError("User Not Found", "User doesn't exist to create Mocktail" + userid));
			return new ApiResponse(ApiResponseStatus.FAILURE, apiErrorList, null);
		}
		userInfo.getMocktails().add(inputMocktail);
		User savedUser = userService.save(userInfo);
		return new ApiResponse(ApiResponseStatus.SUCCESS, null, savedUser.getMocktails());
	}

	@PostMapping(path = "/{userid}/permissions")
	public ApiResponse saveUserPermission(@PathVariable long userid,
			@RequestBody InputPermissionRequest inputPermissions) throws InvalidInputDataException {
		logger.info("InputPermission Bean {}", inputPermissions.toString());

		User userInfo = userService.getUserByUserId(userid);
		if (userInfo == null) {
			List<ApiError> apiErrorList = new ArrayList<ApiError>();
			apiErrorList.add(new ApiError("User Not Found", "User doesn't exist to create permissions" + userid));
			return new ApiResponse(ApiResponseStatus.FAILURE, apiErrorList, null);
		}
		if (inputPermissions == null || (inputPermissions != null && inputPermissions.getName() == null)
				|| (inputPermissions != null && inputPermissions.getName() != null
						&& inputPermissions.getName().size() <= 0)) {
			throw new InvalidInputDataException("Permission values are empty");
		}
		User savedUser = null;
		Set<Permission> savePermissions = new HashSet<>();
		for (String permission : inputPermissions.getName()) {
			logger.info(permission);
			Permission retrievePermission = permissionService.getPermissionByName(permission);
			if (retrievePermission == null)
				throw new InvalidInputDataException(
						"Provided Invalid Permissions. " + "Use CREATE, EDIT, DELTE VIEW ONly");
			savePermissions.add(retrievePermission);
		}
		userInfo.setPermissions(savePermissions);
		savedUser = userService.save(userInfo);
		return new ApiResponse(ApiResponseStatus.SUCCESS, null, savedUser);
	}

}
