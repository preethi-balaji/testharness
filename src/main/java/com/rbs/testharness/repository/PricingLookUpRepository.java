package com.rbs.testharness.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rbs.testharness.entity.PricingLookUpEntity;

@Repository
public interface PricingLookUpRepository extends JpaRepository<PricingLookUpEntity, Integer>{
	
	Optional<PricingLookUpEntity> findByRiskBandAndTermFactorAndBorrowingAmount(Integer risk,String termRange,String borrowingAmountRange);
}
