package com.plans.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.plans.entity.Plans;
import com.plans.entity.PlansCategory;
import com.plans.model.PlansModel;
import com.plans.repository.PlansCategoryRepo;
import com.plans.repository.PlansRepo;

@Service
public class PlansServiceImpl implements PlansService {

    private final PlansCategoryRepo plansCategoryRepo;
    private final PlansRepo plansRepo;

    public PlansServiceImpl(PlansCategoryRepo plansCategoryRepo, PlansRepo plansRepo) {
        this.plansCategoryRepo = plansCategoryRepo;
        this.plansRepo = plansRepo;
    }

    @Override
    public Map<Integer, String> getPlanCategories() {
        List<PlansCategory> categories = plansCategoryRepo.findAll();
        Map<Integer, String> categoryMap = new HashMap<>();

        categories.forEach(planCategory ->
            categoryMap.put(planCategory.getCategoryId(), planCategory.getCategoryName())
        );

        return categoryMap;
    }

    @Override
    public PlansModel savePlan(PlansModel plansModel) {
        Plans plans = new Plans();
        plans.setPlanName(plansModel.getPlanName());
        plans.setPlanStartDate(plansModel.getPlanStartDate());
        plans.setPlanEndDate(plansModel.getPlanEndDate());
        plans.setPlanCategoryId(plansModel.getPlanCategoryId());
        plans.setActiveSwitch(plansModel.getActiveSwitch());
        plans.setCreatedBy(plansModel.getCreatedBy());
        plans.setUpdatedBy(plansModel.getUpdatedBy());

        Plans savedPlan = plansRepo.save(plans);

        plansModel.setPlanName(savedPlan.getPlanName());
        plansModel.setPlanStartDate(savedPlan.getPlanStartDate());
        plansModel.setPlanEndDate(savedPlan.getPlanEndDate());
        plansModel.setPlanCategoryId(savedPlan.getPlanCategoryId());
        plansModel.setActiveSwitch(savedPlan.getActiveSwitch());
        plansModel.setCreatedBy(savedPlan.getCreatedBy());
        plansModel.setUpdatedBy(savedPlan.getUpdatedBy());
        
        return plansModel;
    }

    @Override
    public List<PlansModel> getAllPlans() {
        List<Plans> allPlans = plansRepo.findAll();
        Map<Integer, String> categoryMap = getPlanCategories();
        List<PlansModel> plansModels = new ArrayList<>();
        for (Plans plan : allPlans) {
            PlansModel plansModel = new PlansModel();
            plansModel.setPlanId(plan.getPlanId());
            plansModel.setPlanName(plan.getPlanName());
            plansModel.setPlanStartDate(plan.getPlanStartDate());
            plansModel.setPlanEndDate(plan.getPlanEndDate());
            plansModel.setPlanCategoryId(plan.getPlanCategoryId());
            plansModel.setPlanCategoryName(categoryMap.get(plan.getPlanCategoryId()));
            plansModel.setActiveSwitch(plan.getActiveSwitch());
            plansModel.setCreatedBy(plan.getCreatedBy());
            plansModel.setUpdatedBy(plan.getUpdatedBy());
            plansModel.setCreatedDate(plan.getCreatedDate());
            plansModel.setUpdatedDate(plan.getUpdatedDate());
            plansModels.add(plansModel);
        }
        return plansModels;
    }

    @Override
    public PlansModel getPlanById(Integer planId) {
        Optional<Plans> getPlan = plansRepo.findById(planId);
        if (getPlan.isPresent()) {
            Plans p = getPlan.get();
            PlansModel plansModel = new PlansModel();
            Map<Integer, String> categoryMap = getPlanCategories();
            plansModel.setPlanId(p.getPlanId());
            plansModel.setPlanName(p.getPlanName());
            plansModel.setPlanStartDate(p.getPlanStartDate());
            plansModel.setPlanEndDate(p.getPlanEndDate());
            plansModel.setPlanCategoryId(p.getPlanCategoryId());
            plansModel.setPlanCategoryName(categoryMap.get(p.getPlanCategoryId()));
            plansModel.setActiveSwitch(p.getActiveSwitch());
            plansModel.setCreatedBy(p.getCreatedBy());
            plansModel.setUpdatedBy(p.getUpdatedBy());
            plansModel.setCreatedDate(p.getCreatedDate());
            plansModel.setUpdatedDate(p.getUpdatedDate());
            return plansModel;
        } else {
            return null;
        }
    }

    public PlansModel updatePlan(Integer planId, PlansModel plansModel) {
        Optional<Plans> existingPlan = plansRepo.findById(planId);
        if (existingPlan.isPresent()) {
            Plans plan = existingPlan.get();
            

            plan.setPlanName(plansModel.getPlanName());
            plan.setPlanStartDate(plansModel.getPlanStartDate());
            plan.setPlanEndDate(plansModel.getPlanEndDate());
            plan.setPlanCategoryId(plansModel.getPlanCategoryId());
            plan.setActiveSwitch(plansModel.getActiveSwitch());
            plan.setUpdatedBy(plansModel.getUpdatedBy());
            

            Plans updatedPlan = plansRepo.save(plan);

            plansModel.setPlanId(updatedPlan.getPlanId());
            plansModel.setPlanName(updatedPlan.getPlanName());
            plansModel.setPlanStartDate(updatedPlan.getPlanStartDate());
            plansModel.setPlanEndDate(updatedPlan.getPlanEndDate());
            plansModel.setPlanCategoryId(updatedPlan.getPlanCategoryId());
            plansModel.setActiveSwitch(updatedPlan.getActiveSwitch());
            plansModel.setCreatedBy(updatedPlan.getCreatedBy());
            plansModel.setUpdatedBy(updatedPlan.getUpdatedBy());
            plansModel.setCreatedDate(updatedPlan.getCreatedDate());
            plansModel.setUpdatedDate(updatedPlan.getUpdatedDate());


            Map<Integer, String> categoryMap = getPlanCategories();
            plansModel.setPlanCategoryName(categoryMap.get(updatedPlan.getPlanCategoryId()));

            return plansModel;
        } else {
            return null;
        }
    }
    @Override
    public String deletePlan(Integer planId) {
		Optional<Plans> deletePlan = plansRepo.findById(planId);
		if(deletePlan.isPresent()) {
			plansRepo.deleteById(planId);
			return "Plan Delete Successfully";
		}else {
			return "No Plan Found";
		}
		
	}

    @Override
    public boolean planStatusChange(Integer planId, String status) {
		Optional<Plans> plansStatus = plansRepo.findById(planId);
		if(plansStatus.isPresent()) {
			Plans existingPlan = plansStatus.get();
			existingPlan.setActiveSwitch(status);
			plansRepo.save(existingPlan);
			return true;
		}
		return false;
	}

}
