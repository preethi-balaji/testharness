package com.rbs.testharness.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rbs.testharness.entity.PricingTestSetEntity;

@Repository
public interface PricingTestSetRepository extends JpaRepository<PricingTestSetEntity, Integer>{
	
	//Method to get all test sets for the given dates and the environment
	List<PricingTestSetEntity> findByCreatedTsBetweenAndEnvironment(LocalDateTime startDate, LocalDateTime endDate, String environment);

}
