package com.rbs.testharness.model;

import java.util.List;

public class PricingAttributeRequest {
	
	private List<Integer> borrowingAmount;
	private List<Integer> riskFactor;
	private List<Integer> termFactor;
	private String bankDivision;
	private String productFamily;
	private String productName;
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

	private String applicationIdentity;

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