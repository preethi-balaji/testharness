package com.rbs.testharness.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "THS_PRC_LOOKUP_REF")
public class PricingLookUpEntity extends BaseEntity{
	
	@Id
	@Column(name = "THS_PRC_LOOKUP_ID")
	private Integer lookUpId;
	@Column(name = "RISK_BAND")
	private Integer riskBand;
	@Column(name = "TERM")
	private String termFactor;
	@Column(name = "BORROWING_AMT")
	private String borrowingAmount;
	@Column(name = "AIR_RATE")
	private Double airRate;
	@Column(name = "APR_RATE")
	private Double aprRate;
	
	public Integer getLookUpId() {
		return lookUpId;
	}
	public void setLookUpId(Integer lookUpId) {
		this.lookUpId = lookUpId;
	}
	public Integer getRiskBand() {
		return riskBand;
	}
	public void setRiskBand(Integer riskBand) {
		this.riskBand = riskBand;
	}
	public String getTermFactor() {
		return termFactor;
	}
	public void setTermFactor(String termFactor) {
		this.termFactor = termFactor;
	}
	public String getBorrowingAmount() {
		return borrowingAmount;
	}
	public void setBorrowingAmount(String borrowingAmount) {
		this.borrowingAmount = borrowingAmount;
	}
	public Double getAirRate() {
		return airRate;
	}
	public void setAirRate(Double airRate) {
		this.airRate = airRate;
	}
	public Double getAprRate() {
		return aprRate;
	}
	public void setAprRate(Double aprRate) {
		this.aprRate = aprRate;
	}
}
