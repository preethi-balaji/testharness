package com.rbs.testharness.model;

import java.util.List;

public class PricingResultResponse {
	
	private Integer totaltestcases;
	private Integer passed;
	private Integer failed;
	private List<PricingResultTestCase> testcasesResultList;
	
	public Integer getTotaltestcases() {
		return totaltestcases;
	}
	public void setTotaltestcases(Integer totaltestcases) {
		this.totaltestcases = totaltestcases;
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
	public List<PricingResultTestCase> getTestcasesResultList() {
		return testcasesResultList;
	}
	public void setTestcasesResultList(List<PricingResultTestCase> testcasesResultList) {
		this.testcasesResultList = testcasesResultList;
	}
}
