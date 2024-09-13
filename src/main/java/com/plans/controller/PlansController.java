package com.plans.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plans.binding.PlansBinding;
import com.plans.binding.PlansCategoryBinding;
import com.plans.constants.AppConstants;
import com.plans.model.PlansCategoryModel;
import com.plans.model.PlansModel;
import com.plans.props.AppProperties;
import com.plans.service.PlansCategoryService;
import com.plans.service.PlansServiceImpl;

@RestController
@RequestMapping("/plans")
public class PlansController {
    
    private final PlansServiceImpl plansServiceImpl;
    private final PlansCategoryService plansCategoryService;
    private Map<String, String> messages;

    


	public PlansController(PlansServiceImpl plansServiceImpl, PlansCategoryService plansCategoryService,
			AppProperties appProps) {

		this.plansServiceImpl = plansServiceImpl;
		this.plansCategoryService = plansCategoryService;
		this.messages = appProps.getMessages();
	}

	@PostMapping("/saveCategory")
	public ResponseEntity<String> savePlansCategory(@RequestBody PlansCategoryBinding plansCategoryBinding) {
	    PlansCategoryModel plansCategoryModel = new PlansCategoryModel();
	    plansCategoryModel.setCategoryName(plansCategoryBinding.getCategoryName());
	    plansCategoryModel.setActiveSwitch(plansCategoryBinding.getActiveSwitch());
	    plansCategoryModel.setCreatedBy(plansCategoryBinding.getCreatedBy());
	    plansCategoryModel.setUpdatedBy(plansCategoryBinding.getUpdatedBy());

	    PlansCategoryModel savedModel = plansCategoryService.savePlansCategory(plansCategoryModel);
	    String msg = AppConstants.EMPTY_STR;

	    if (savedModel != null) {
	        plansCategoryBinding.setCategoryId(savedModel.getCategoryId());
	        plansCategoryBinding.setCategoryName(savedModel.getCategoryName());
	        plansCategoryBinding.setActiveSwitch(savedModel.getActiveSwitch());
	        plansCategoryBinding.setCreatedBy(savedModel.getCreatedBy());
	        plansCategoryBinding.setUpdatedBy(savedModel.getUpdatedBy());
	        msg = messages.get(AppConstants.PLAN_CATEGORY_SAVE_SUCCESS);
	    } else {
	        msg = messages.get(AppConstants.PLAN_CATEGORY_SAVE_FAIL);
	    }

	    System.out.println("Response Message: " + msg);

	    return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}

    @PostMapping("/savePlan")
    public ResponseEntity<String> savePlan(@RequestBody PlansBinding plansBinding) {
        PlansModel plansModel = new PlansModel();
        plansModel.setPlanName(plansBinding.getPlanName());
        plansModel.setPlanStartDate(plansBinding.getPlanStartDate());
        plansModel.setPlanEndDate(plansBinding.getPlanEndDate());
        plansModel.setPlanCategoryId(plansBinding.getPlanCategoryId()); 
        plansModel.setActiveSwitch(plansBinding.getActiveSwitch());
        plansModel.setCreatedBy(plansBinding.getCreatedBy()); 
        plansModel.setUpdatedBy(plansBinding.getUpdatedBy()); 
      
        PlansModel savedPlan = plansServiceImpl.savePlan(plansModel);
        String msg = AppConstants.EMPTY_STR;
       if(savedPlan != null) {
    	   plansBinding.setPlanId(savedPlan.getPlanId());
           plansBinding.setPlanName(savedPlan.getPlanName());
           plansBinding.setPlanStartDate(savedPlan.getPlanStartDate());
           plansBinding.setPlanEndDate(savedPlan.getPlanEndDate());
           plansBinding.setPlanCategoryId(savedPlan.getPlanCategoryId()); 
           plansBinding.setActiveSwitch(savedPlan.getActiveSwitch());
           plansBinding.setCreatedBy(savedPlan.getCreatedBy()); 
           plansBinding.setUpdatedBy(savedPlan.getUpdatedBy()); 
           msg = messages.get(AppConstants.PLAN_SAVE_SUCCESS);
       }else {
    	   msg = messages.get(AppConstants.PLAN_SAVE_FAIL);
    			   
       }

        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @GetMapping("/categories")
    public ResponseEntity<Map<Integer, String>> getPlanCategories() {
        Map<Integer, String> categories = plansServiceImpl.getPlanCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/getPlanById/{planId}")
    public ResponseEntity<PlansBinding> getPlanById(@PathVariable Integer planId) {
        PlansModel plan = plansServiceImpl.getPlanById(planId);
        
        
        if (plan != null) {
        	PlansBinding plansBinding = new PlansBinding();
            plansBinding.setPlanId(plan.getPlanId());
            plansBinding.setPlanName(plan.getPlanName());
            plansBinding.setPlanStartDate(plan.getPlanStartDate());
            plansBinding.setPlanEndDate(plan.getPlanEndDate());
            plansBinding.setPlanCategoryId(plan.getPlanCategoryId()); 
            plansBinding.setPlanCategoryName(plan.getPlanCategoryName()); 
            plansBinding.setActiveSwitch(plan.getActiveSwitch());
            plansBinding.setCreatedBy(plan.getCreatedBy()); 
            plansBinding.setUpdatedBy(plan.getUpdatedBy());
            plansBinding.setUpdatedDate(plan.getUpdatedDate());
            plansBinding.setCreatedDate(plan.getCreatedDate());

            return new ResponseEntity<>(plansBinding, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PlansBinding>> getAllPlans() {
        List<PlansModel> plansModels = plansServiceImpl.getAllPlans();

        List<PlansBinding> plansBindings = plansModels.stream().map(pm -> {
            PlansBinding plansBinding = new PlansBinding();
            plansBinding.setPlanId(pm.getPlanId());
            plansBinding.setPlanName(pm.getPlanName());
            plansBinding.setPlanStartDate(pm.getPlanStartDate());
            plansBinding.setPlanEndDate(pm.getPlanEndDate());
            plansBinding.setPlanCategoryId(pm.getPlanCategoryId()); 
            plansBinding.setPlanCategoryName(pm.getPlanCategoryName());
            plansBinding.setActiveSwitch(pm.getActiveSwitch());
            plansBinding.setCreatedBy(pm.getCreatedBy()); 
            plansBinding.setUpdatedBy(pm.getUpdatedBy()); 
            plansBinding.setUpdatedDate(pm.getUpdatedDate());
            plansBinding.setCreatedDate(pm.getCreatedDate());
           
            
            return plansBinding;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(plansBindings, HttpStatus.OK);
    }

    @PutMapping("/update/{planId}")
    public ResponseEntity<String> updatePlan(@PathVariable Integer planId, @RequestBody PlansBinding plansBinding) {
        PlansModel plansModel = new PlansModel();
        plansModel.setPlanId(plansBinding.getPlanId());
        plansModel.setPlanName(plansBinding.getPlanName());
        plansModel.setPlanStartDate(plansBinding.getPlanStartDate());
        plansModel.setPlanEndDate(plansBinding.getPlanEndDate());
        plansModel.setPlanCategoryId(plansBinding.getPlanCategoryId()); 
        plansModel.setActiveSwitch(plansBinding.getActiveSwitch());
        plansModel.setCreatedBy(plansBinding.getCreatedBy()); 
        plansModel.setUpdatedBy(plansBinding.getUpdatedBy());
        

        PlansModel updatedPlan = plansServiceImpl.updatePlan(planId, plansModel);
        String msg = AppConstants.EMPTY_STR;
        if (updatedPlan != null) {
            PlansBinding updatedBinding = new PlansBinding();
            updatedBinding.setPlanId(updatedPlan.getPlanId());
            updatedBinding.setPlanName(updatedPlan.getPlanName());
            updatedBinding.setPlanStartDate(updatedPlan.getPlanStartDate());
            updatedBinding.setPlanEndDate(updatedPlan.getPlanEndDate());
            updatedBinding.setPlanCategoryId(updatedPlan.getPlanCategoryId()); 
            updatedBinding.setPlanCategoryName(updatedPlan.getPlanCategoryName());
            updatedBinding.setActiveSwitch(updatedPlan.getActiveSwitch());
            updatedBinding.setCreatedBy(updatedPlan.getCreatedBy());
            updatedBinding.setUpdatedBy(updatedPlan.getUpdatedBy()); 
            updatedBinding.setCreatedBy(updatedPlan.getCreatedBy()); 
            updatedBinding.setUpdatedBy(updatedPlan.getUpdatedBy());
         
            msg= messages.get(AppConstants.PLAN_UPDATE_SUCCESS);
            
        } else {
        	msg= messages.get(AppConstants.PLAN_UPDATE_FAIL);
        }
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{planId}")
    public ResponseEntity<String> deletePlan(@PathVariable Integer planId) {
        String response = plansServiceImpl.deletePlan(planId);
        String msg = AppConstants.EMPTY_STR;
        if (response.equals("Plan Delete Successfully")) {
        	msg = messages.get(AppConstants.PLAN_DELETE_SUCCESS);
            
        } else {
        	msg = messages.get(AppConstants.PLAN_DELETE_FAIL);
            
        }
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
    
    @PutMapping("/statusChange/{planId}/{status}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId, @PathVariable String status){
		String msg =AppConstants.EMPTY_STR;
		boolean isStatusChanged = plansServiceImpl.planStatusChange(planId, status);
		if(isStatusChanged) {
			msg = messages.get(AppConstants.PLAN_STATUS_CHANGE);
		}else {
			msg = messages.get(AppConstants.PLAN_STATUS_CHANGE_FAIL);
		}
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

}
