package com.rbs.testharness.model;

public class PricingTestSet {
	
	private Integer testSetId;
	private String applicationIdentity;
	private String bankDivision;
	private String productFamily;
	private String productName;
	private String riskBand;
	private String borrowingAmt;
	private String termFactor;
	private Character processedFlag;
	
	public Integer getTestSetId() {
		return testSetId;
	}
	public void setTestSetId(Integer testSetId) {
		this.testSetId = testSetId;
	}
	public String getBankDivision() {
		return bankDivision;
	}
	public void setBankDivision(String bankDivision) {
		this.bankDivision = bankDivision;
	}
	public String getRiskBand() {
		return riskBand;
	}
	public void setRiskBand(String riskBand) {
		this.riskBand = riskBand;
	}
	public String getBorrowingAmt() {
		return borrowingAmt;
	}
	public void setBorrowingAmt(String borrowingAmt) {
		this.borrowingAmt = borrowingAmt;
	}
	public Character getProcessedFlag() {
		return processedFlag;
	}
	public void setProcessedFlag(Character processedFlag) {
		this.processedFlag = processedFlag;
	}
	public String getApplicationIdentity() {
		return applicationIdentity;
	}
	public void setApplicationIdentity(String applicationIdentity) {
		this.applicationIdentity = applicationIdentity;
	}
	public String getTermFactor() {
		return termFactor;
	}
	public void setTermFactor(String termFactor) {
		this.termFactor = termFactor;
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
}
