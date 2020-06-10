package com.rbs.testharness.model;

import java.util.List;

public class PricingTestCaseResult {
	
	private Integer totalTestCases;
	private Integer passed;
	private Integer failed;
	private List<PricingTestCaseResponse> testcasesResultList;
	
	public Integer getTotalTestCases() {
		return totalTestCases;
	}
	public void setTotalTestCases(Integer totalTestCases) {
		this.totalTestCases = totalTestCases;
	}
	public Integer getPassed() {
		return passed;
	}
	public void setPassed(Integer passed) {
		this.passed = passed;
	}
	public Integer getFailed() {
		return failed;
	}
	public void setFailed(Integer failed) {
		this.failed = failed;
	}
	public List<PricingTestCaseResponse> getTestcasesResultList() {
		return testcasesResultList;
	}
	public void setTestcasesResultList(List<PricingTestCaseResponse> testcasesResultList) {
		this.testcasesResultList = testcasesResultList;
	}
	
}
