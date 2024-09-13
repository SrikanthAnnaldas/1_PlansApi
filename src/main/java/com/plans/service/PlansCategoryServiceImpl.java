package com.plans.service;

import org.springframework.stereotype.Service;

import com.plans.entity.PlansCategory;
import com.plans.model.PlansCategoryModel;
import com.plans.repository.PlansCategoryRepo;

@Service
public class PlansCategoryServiceImpl implements PlansCategoryService {

    private final PlansCategoryRepo plansCategoryRepo;

    
    public PlansCategoryServiceImpl(PlansCategoryRepo plansCategoryRepo) {
        this.plansCategoryRepo = plansCategoryRepo;
    }

    @Override
    public PlansCategoryModel savePlansCategory(PlansCategoryModel plansCategoryModel) {
        


        PlansCategory plansCategory = new PlansCategory();
        plansCategory.setCategoryName(plansCategoryModel.getCategoryName());
        plansCategory.setActiveSwitch(plansCategoryModel.getActiveSwitch());
        plansCategory.setCreatedBy(plansCategoryModel.getCreatedBy());
        plansCategory.setUpdatedBy(plansCategoryModel.getUpdatedBy()); 

    
        PlansCategory savedPlanCategory = plansCategoryRepo.save(plansCategory);

 
        PlansCategoryModel savedModel = new PlansCategoryModel();
        savedModel.setCategoryId(savedPlanCategory.getCategoryId()); 
        savedModel.setCategoryName(savedPlanCategory.getCategoryName());
        savedModel.setActiveSwitch(savedPlanCategory.getActiveSwitch());
        savedModel.setCreatedBy(savedPlanCategory.getCreatedBy());
        savedModel.setUpdatedBy(savedPlanCategory.getUpdatedBy()); 
        

        return savedModel;
    }
}
