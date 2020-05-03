package com.personal.mocktail.ingredients.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.mocktail.ingredients.domain.Mocktail;

@Repository
@Transactional
public interface MocktailRepository extends JpaRepository<Mocktail, Long>{

}
