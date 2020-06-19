package com.rbs.testharness.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "THS_PRC_LOOKUP_REF")
public class PricingLookUpEntity extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	
	@Column(name = "MARGIN_FEE")
	private Double marginFee;
	@Column(name = "ARRANGEMENT_FEE")
	private Double arrangementFee;
	@Column(name = "START_MARGIN_FEE")
	private Double startMarginFee;
	@Column(name = "TERM_MARGIN_PREMIUM")
	private Double termMarginPremium;
	@Column(name = "THS_PRC_ATTRIBUTE_ID")
	private Integer attributeId;
	

	public Integer getAttributeId() {
		return attributeId;
	}
	public void setAttributeId(Integer attributeId) {
		this.attributeId = attributeId;
	}
	public Double getMarginFee() {
		return marginFee;
	}
	public void setMarginFee(Double marginFee) {
		this.marginFee = marginFee;
	}
	public Double getArrangementFee() {
		return arrangementFee;
	}
	public void setArrangementFee(Double arrangementFee) {
		this.arrangementFee = arrangementFee;
	}
	public Double getStartMarginFee() {
		return startMarginFee;
	}
	public void setStartMarginFee(Double startMarginFee) {
		this.startMarginFee = startMarginFee;
	}
	public Double getTermMarginPremium() {
		return termMarginPremium;
	}
	public void setTermMarginPremium(Double termMarginPremium) {
		this.termMarginPremium = termMarginPremium;
	}
	
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
