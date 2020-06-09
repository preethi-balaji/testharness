package com.rbs.testharness.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "THS_PRC_TEST_TXN_TB")
public class PricingTestCaseResponseEntity extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TEST_TXN_ID")
	private Integer testTransactionId;
	@Column(name = "TEST_SET_ID")
	private Integer testSetId;
	@Column(name = "TEST_TXN_NO")
	private String testTransactionNo;
	@Column(name = "APPL_IDENTITY")
	private String applicationIdentity;
	@Column(name = "BANK_DIVISION")
	private String bankDivision;
	@Column(name = "PROD_FAMILY")
	private String productFamily;
	@Column(name = "PROD_NAME")
	private String productName;
	@Column(name = "RISK_BAND")
	private Integer riskBand;
	@Column(name = "BORROWING_AMT")
	private Integer borrowingAmount;
	@Column(name = "TERM")
	private Integer termFactor;
	@Column(name = "ACT_AIR")
	private Double actualAir;
	@Column(name = "ACT_APR")
	private Double actualApr;
	@Column(name = "EXCPT_AIR")
	private Double expectetAir;
	@Column(name = "EXCPT_APR")
	private Double expectetApr;
	@Column(name = "TEST_TXN_FLAG")
	private Character testTransactionFlag;
	@Column(name = "XML_DIFF")
	private String xmlDifference;
	
	public Integer getTestTransactionId() {
		return testTransactionId;
	}
	public void setTestTransactionId(Integer testTransactionId) {
		this.testTransactionId = testTransactionId;
	}
	public Integer getTestSetId() {
		return testSetId;
	}
	public void setTestSetId(Integer testSetId) {
		this.testSetId = testSetId;
	}
	public String getTestTransactionNo() {
		return testTransactionNo;
	}
	public void setTestTransactionNo(String testTransactionNo) {
		this.testTransactionNo = testTransactionNo;
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
	
	public Integer getRiskBand() {
		return riskBand;
	}
	public void setRiskBand(Integer riskBand) {
		this.riskBand = riskBand;
	}
	public Integer getBorrowingAmount() {
		return borrowingAmount;
	}
	public void setBorrowingAmount(Integer borrowingAmount) {
		this.borrowingAmount = borrowingAmount;
	}
	public Integer getTermFactor() {
		return termFactor;
	}
	public void setTermFactor(Integer termFactor) {
		this.termFactor = termFactor;
	}
	public Double getActualAir() {
		return actualAir;
	}
	public void setActualAir(Double actualAir) {
		this.actualAir = actualAir;
	}
	public Double getActualApr() {
		return actualApr;
	}
	public void setActualApr(Double actualApr) {
		this.actualApr = actualApr;
	}
	public Double getExpectetAir() {
		return expectetAir;
	}
	public void setExpectetAir(Double expectetAir) {
		this.expectetAir = expectetAir;
	}
	public Double getExpectetApr() {
		return expectetApr;
	}
	public void setExpectetApr(Double expectetApr) {
		this.expectetApr = expectetApr;
	}
	public Character getTestTransactionFlag() {
		return testTransactionFlag;
	}
	public void setTestTransactionFlag(Character testTransactionFlag) {
		this.testTransactionFlag = testTransactionFlag;
	}
	public String getXmlDifference() {
		return xmlDifference;
	}
	public void setXmlDifference(String xmlDifference) {
		this.xmlDifference = xmlDifference;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
}
