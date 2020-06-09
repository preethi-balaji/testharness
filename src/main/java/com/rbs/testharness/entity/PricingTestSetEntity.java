package com.rbs.testharness.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "THS_PRC_TESTSET_TB")
public class PricingTestSetEntity extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TEST_SET_ID")
	private Integer testSetId;
	@Column(name = "APPL_IDENTITY")
	private String applicationIdentity;
	@Column(name = "BANK_DIVISION")
	private String bankDivision;
	@Column(name = "PROD_FAMILY")
	private String productFamily;
	@Column(name = "PROD_NAME")
	private String productName;
	@Column(name = "RISK_BAND")
	private String riskBand;
	@Column(name = "BORROWING_AMT")
	private String borrowingAmount;
	@Column(name = "TERM")
	private String termFactor;
	@Column(name = "PROCESSED_FLAG")
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
	public String getBorrowingAmount() {
		return borrowingAmount;
	}
	public void setBorrowingAmount(String borrowingAmount) {
		this.borrowingAmount = borrowingAmount;
	}
}
