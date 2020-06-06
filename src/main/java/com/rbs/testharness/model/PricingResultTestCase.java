package com.rbs.testharness.model;

public class PricingResultTestCase {
	
private String id;
	
	private String applicationIdentity;
	private String bankDivision;
	private String productFamily;
	private String productName;
	private Integer termFactor;
	private Integer riskFactor;
	private Float allInRate;
	private Float annualPercentageRate;
	private Float expectedAllInRate;
	private Float expectedAnnualPercentageRate;
	private Integer borrowingAmount;
	private String status;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApplicationIdentity() {
		return applicationIdentity;
	}
	public void setApplicationIdentity(String applicationIdentity) {
		this.applicationIdentity = applicationIdentity;
	}
	public String getBankDivision() {
		return bankDivision;
	}
	public void setBankDivision(String bankDivision) {
		this.bankDivision = bankDivision;
	}
	public String getProductFamily() {
		return productFamily;
	}
	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getTermFactor() {
		return termFactor;
	}
	public void setTermFactor(Integer termFactor) {
		this.termFactor = termFactor;
	}
	public Integer getRiskFactor() {
		return riskFactor;
	}
	public void setRiskFactor(Integer riskFactor) {
		this.riskFactor = riskFactor;
	}
	public Float getAllInRate() {
		return allInRate;
	}
	public void setAllInRate(Float allInRate) {
		this.allInRate = allInRate;
	}
	public Float getAnnualPercentageRate() {
		return annualPercentageRate;
	}
	public void setAnnualPercentageRate(Float annualPercentageRate) {
		this.annualPercentageRate = annualPercentageRate;
	}
	public Float getExpectedAllInRate() {
		return expectedAllInRate;
	}
	public void setExpectedAllInRate(Float expectedAllInRate) {
		this.expectedAllInRate = expectedAllInRate;
	}
	public Float getExpectedAnnualPercentageRate() {
		return expectedAnnualPercentageRate;
	}
	public void setExpectedAnnualPercentageRate(Float expectedAnnualPercentageRate) {
		this.expectedAnnualPercentageRate = expectedAnnualPercentageRate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getBorrowingAmount() {
		return borrowingAmount;
	}
	public void setBorrowingAmount(Integer borrowingAmount) {
		this.borrowingAmount = borrowingAmount;
	}
	
}
