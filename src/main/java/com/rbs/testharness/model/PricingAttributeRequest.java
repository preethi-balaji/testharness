package com.rbs.testharness.model;

import java.util.List;

public class PricingAttributeRequest {
	
	private List<Integer> borrowingAmount;
	private List<Integer> riskFactor;
	private List<Integer> termFactor;
	private Integer bankDivision;
	private Integer productFamily;
	private Integer productName;
	private Integer applicationIdentity;
	private String userId;
	
	
	public List<Integer> getRiskFactor() {
		return riskFactor;
	}

	public void setRiskFactor(List<Integer> riskFactor) {
		this.riskFactor = riskFactor;
	}

	public List<Integer> getTermFactor() {
		return termFactor;
	}

	public void setTermFactor(List<Integer> termFactor) {
		this.termFactor = termFactor;
	}

	public Integer getBankDivision() {
		return bankDivision;
	}

	public void setBankDivision(Integer bankDivision) {
		this.bankDivision = bankDivision;
	}

	public Integer getProductFamily() {
		return productFamily;
	}

	public void setProductFamily(Integer productFamily) {
		this.productFamily = productFamily;
	}

	public Integer getProductName() {
		return productName;
	}

	public void setProductName(Integer productName) {
		this.productName = productName;
	}

	public Integer getApplicationIdentity() {
		return applicationIdentity;
	}

	public void setApplicationIdentity(Integer applicationIdentity) {
		this.applicationIdentity = applicationIdentity;
	}

	

	public List<Integer> getBorrowingAmount() {
		return borrowingAmount;
	}

	public void setBorrowingAmount(List<Integer> borrowingAmount) {
		this.borrowingAmount = borrowingAmount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
