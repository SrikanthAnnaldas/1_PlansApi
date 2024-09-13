package com.plans.service;

import java.util.List;
import java.util.Map;

import com.plans.model.PlansModel;

public interface PlansService {

    Map<Integer, String> getPlanCategories();

    PlansModel savePlan(PlansModel plansModel);

    List<PlansModel> getAllPlans();

    PlansModel getPlanById(Integer planId);

    PlansModel updatePlan(Integer planId, PlansModel plansModel);

    String deletePlan(Integer planId);

    boolean planStatusChange(Integer planId, String status);
    
    
}
