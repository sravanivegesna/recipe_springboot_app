package com.personal.mocktail.ingredients.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.mocktail.ingredients.domain.Permission;

@Repository
@Transactional
public interface PermissionRepo extends JpaRepository<Permission, Long>{
	
	Permission findByname(String firstName);

}
