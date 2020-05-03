package com.personal.mocktail.ingredients.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.mocktail.ingredients.domain.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long>{

	List<User> findByfirstName(String firstName);
	
}
