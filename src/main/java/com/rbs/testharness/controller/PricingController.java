package com.rbs.testharness.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rbs.testharness.model.PricingAttributeRequest;
import com.rbs.testharness.model.PricingResultResponse;
import com.rbs.testharness.model.PricingTestCaseAirApr;
import com.rbs.testharness.model.PricingTestCaseResponse;
import com.rbs.testharness.service.PricingService;

@RestController
@RequestMapping("/com/rcb/th")
public class PricingController {
	
	@Autowired
	private PricingService pricingService;
	
	@RequestMapping(value="/generate/testcase-scenarios", method=RequestMethod.POST)
	private List<PricingTestCaseResponse> generateTestCaseCombination(@RequestBody PricingAttributeRequest attributeInputList) {
		return pricingService.generateTestCaseCombination(attributeInputList);
	}
	
	@RequestMapping(value="/generate/testcase-airapr", method=RequestMethod.POST)
	private List<PricingTestCaseAirApr> generateTestCaseAirApr(@RequestBody List<PricingTestCaseResponse> testCaseOutputList) {
		return pricingService.generateTestCaseAirApr(testCaseOutputList);
	}
	
	@RequestMapping(value="/generate/testcase-result", method=RequestMethod.POST)
	private PricingResultResponse generateTestCaseResult(@RequestBody List<PricingTestCaseAirApr> testCaseAirAprList) {
		return pricingService.generateTestCaseResult(testCaseAirAprList);
	}
	
	@RequestMapping(value="testcase/pagination/pageno/{pageno}", method=RequestMethod.POST)
	private List<PricingTestCaseResponse> findTestCasesByPageNo(@PathVariable Integer pageno) {
		return pricingService.findByPageNo(pageno);
	}
}
