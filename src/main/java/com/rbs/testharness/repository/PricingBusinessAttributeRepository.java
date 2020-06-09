package com.rbs.testharness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rbs.testharness.entity.PricingBusinessAttributeEntity;

@Repository
public interface PricingBusinessAttributeRepository extends JpaRepository<PricingBusinessAttributeEntity, Integer>{

}
