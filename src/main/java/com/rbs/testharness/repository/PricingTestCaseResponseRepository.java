package com.rbs.testharness.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rbs.testharness.entity.PricingTestCaseResponseEntity;


@Repository
public interface PricingTestCaseResponseRepository  extends JpaRepository<PricingTestCaseResponseEntity, Integer>{
	
	Optional<List<PricingTestCaseResponseEntity>> findByTestSetId(Integer testSetId);
	Page<PricingTestCaseResponseEntity> findByTestSetId(Integer testSetId,Pageable pageRequest);
	
	//Method to get all test set transactions for the given test set id and the transaction flag
	List<PricingTestCaseResponseEntity> findByTestSetId(int testSetId);
	
	long deleteByTestSetId(Integer testSetId);
	List<Integer> findDistinctBorrowingAmtByTestSetId(Integer testSetId);

}
