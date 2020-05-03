package com.personal.mocktail.ingredients.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.mocktail.ingredients.domain.Permission;
import com.personal.mocktail.ingredients.repo.PermissionRepo;

@Service
public class PermissionsService {
	@Autowired
	PermissionRepo permissionRepo;

	public Permission getPermissionByName(String permissionName) {
		return permissionRepo.findByname(permissionName);
	}
}
