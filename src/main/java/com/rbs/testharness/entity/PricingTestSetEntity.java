package com.rbs.testharness.entity;

import java.time.LocalDateTime;

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
	private Integer applicationIdentity;
	@Column(name = "BANK_DIVISION")
	private Integer bankDivision;
	@Column(name = "PROD_FAMILY")
	private Integer productFamily;
	@Column(name = "PROD_NAME")
	private Integer productName;
	@Column(name = "RISK_BAND")
	private String riskBand;
	@Column(name = "BORROWING_AMT")
	private String borrowingAmount;
	@Column(name = "TERM")
	private String termFactor;
	@Column(name = "PROCESSED_FLAG")
	private Character processedFlag;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "CREATED_TS")
	private LocalDateTime createdTs;
		
	@Column(name = "ENVIRONMENT")
	private String environment;
	
	public Integer getTestSetId() {
		return testSetId;
	}
	public void setTestSetId(Integer testSetId) {
		this.testSetId = testSetId;
	}
	public Integer getBankDivision() {
		return bankDivision;
	}
	public void setBankDivision(Integer bankDivision) {
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
	public Integer getApplicationIdentity() {
		return applicationIdentity;
	}
	public void setApplicationIdentity(Integer applicationIdentity) {
		this.applicationIdentity = applicationIdentity;
	}
	public String getTermFactor() {
		return termFactor;
	}
	public void setTermFactor(String termFactor) {
		this.termFactor = termFactor;
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
	public String getBorrowingAmount() {
		return borrowingAmount;
	}
	public void setBorrowingAmount(String borrowingAmount) {
		this.borrowingAmount = borrowingAmount;
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
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
}

