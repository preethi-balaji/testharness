package com.rbs.testharness.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "THS_PRC_ATTRIBUTE_REF")
public class PricingBusinessAttributeEntity extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "THS_PRC_ATTRIBUTE_ID")
	private int attributeId;
	@Column(name = "REF_DATA_KEY")
	private String refDataKey;
	@Column(name = "DESCRIPTION")
	private String refDataDesc;
	@Column(name = "IS_ACTIVE")
	private Character isActive;
	
	
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
	
}
