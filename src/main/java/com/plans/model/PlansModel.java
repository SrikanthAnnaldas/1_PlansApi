package com.plans.model;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;

public class PlansModel {
    private Integer planId;
    private String planName;
    private LocalDate planStartDate;
    private LocalDate planEndDate;
    private Integer planCategoryId;
    private String planCategoryName;
    private String activeSwitch;
    private String createdBy;
    private String updatedBy;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createdDate;

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDate updatedDate;

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public LocalDate getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(LocalDate planStartDate) {
        this.planStartDate = planStartDate;
    }

    public LocalDate getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(LocalDate planEndDate) {
        this.planEndDate = planEndDate;
    }

    public Integer getPlanCategoryId() {
        return planCategoryId;
    }
    
    
    public String getPlanCategoryName() {
		return planCategoryName;
	}

	public void setPlanCategoryName(String planCategoryName) {
		this.planCategoryName = planCategoryName;
	}

	public void setPlanCategoryId(Integer planCategoryId) {
        this.planCategoryId = planCategoryId;
    }

    public String getActiveSwitch() {
        return activeSwitch;
    }

    public void setActiveSwitch(String activeSwitch) {
        this.activeSwitch = activeSwitch;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }
}
