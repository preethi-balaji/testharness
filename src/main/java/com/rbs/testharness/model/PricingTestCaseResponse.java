package com.rbs.testharness.model;

public class PricingTestCaseResponse {
	
	private String id;
	private String applicationIdentity;
	private String bankDivision;
	private String productFamily;
	private String productName;
	private Integer borrowingAmount;
	private Integer termFactor;
	private Integer riskFactor;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getApplicationIdentity() {
		return applicationIdentity;
	}
	public void setApplicationIdentity(String applicationIdentity) {
		this.applicationIdentity = applicationIdentity;
	}
	public Integer getBorrowingAmount() {
		return borrowingAmount;
	}
	public void setBorrowingAmount(Integer borrowingAmount) {
		this.borrowingAmount = borrowingAmount;
	}

}
