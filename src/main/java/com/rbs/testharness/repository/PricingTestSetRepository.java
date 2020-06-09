package com.rbs.testharness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rbs.testharness.entity.PricingTestSetEntity;

@Repository
public interface PricingTestSetRepository extends JpaRepository<PricingTestSetEntity, Integer>{

}
