package com.plans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plans.entity.PlansCategory;

public interface PlansCategoryRepo extends JpaRepository<PlansCategory, Integer>{

}
