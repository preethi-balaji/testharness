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
	private Integer applicationIdentity;
	@Column(name = "BANK_DIVISION")
	private Integer bankDivision;
	@Column(name = "PROD_FAMILY")
	private Integer productFamily;
	@Column(name = "PROD_NAME")
	private Integer productName;
	@Column(name = "RISK_BAND")
	private Integer riskBand;
	@Column(name = "BORROWING_AMT")
	private Integer borrowingAmount;
	@Column(name = "TERM")
	private Integer termFactor;
	@Column(name = "ACT_AIR")
	private Integer actualAir;
	@Column(name = "ACT_APR")
	private Integer actualApr;
	@Column(name = "EXCPT_AIR")
	private Integer expectetAir;
	@Column(name = "EXCPT_APR")
	private Integer expectetApr;
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
	public Integer getApplicationIdentity() {
		return applicationIdentity;
	}
	public void setApplicationIdentity(Integer applicationIdentity) {
		this.applicationIdentity = applicationIdentity;
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
	public Integer getActualAir() {
		return actualAir;
	}
	public void setActualAir(Integer actualAir) {
		this.actualAir = actualAir;
	}
	public Integer getActualApr() {
		return actualApr;
	}
	public void setActualApr(Integer actualApr) {
		this.actualApr = actualApr;
	}
	public Integer getExpectetAir() {
		return expectetAir;
	}
	public void setExpectetAir(Integer expectetAir) {
		this.expectetAir = expectetAir;
	}
	public Integer getExpectetApr() {
		return expectetApr;
	}
	public void setExpectetApr(Integer expectetApr) {
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
	public Integer getProductName() {
		return productName;
	}
	public void setProductName(Integer productName) {
		this.productName = productName;
	}
}

