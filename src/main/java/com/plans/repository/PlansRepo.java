package com.plans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plans.entity.Plans;

public interface PlansRepo extends JpaRepository<Plans, Integer> {

}
