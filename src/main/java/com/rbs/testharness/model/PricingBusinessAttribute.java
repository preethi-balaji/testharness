package com.rbs.testharness.model;

import java.time.LocalDateTime;

public class PricingBusinessAttribute {
	
	private int attributeId;
	private String refDataKey;
	private String refDataDesc;
	private Character isActive;
	private String createdBy;
	private LocalDateTime createdTs;
	private String updatedBy;
	private LocalDateTime updatedTs;
	
	public int getAttributeId() {
		return attributeId;
	}
	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}
	public String getRefDataKey() {
		return refDataKey;
	}
	public void setRefDataKey(String refDataKey) {
		this.refDataKey = refDataKey;
	}
	public String getRefDataDesc() {
		return refDataDesc;
	}
	public void setRefDataDesc(String refDataDesc) {
		this.refDataDesc = refDataDesc;
	}
	public Character getIsActive() {
		return isActive;
	}
	public void setIsActive(Character isActive) {
		this.isActive = isActive;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getCreatedTs() {
		return createdTs;
	}
	public void setCreatedTs(LocalDateTime createdTs) {
		this.createdTs = createdTs;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public LocalDateTime getUpdatedTs() {
		return updatedTs;
	}
	public void setUpdatedTs(LocalDateTime updatedTs) {
		this.updatedTs = updatedTs;
	}
	
}
