package com.rbs.testharness.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rbs.testharness.model.PricingAttributeRequest;
import com.rbs.testharness.model.PricingBusinessAttribute;
import com.rbs.testharness.model.PricingTestCaseResponse;
import com.rbs.testharness.model.PricingTestCaseResult;
import com.rbs.testharness.service.PricingService;

@RestController
@RequestMapping("/rbs/th")
public class PricingController {
	
	@Autowired
	private PricingService pricingService;
	
	@RequestMapping(value="/businessAttributes", method=RequestMethod.GET)
	public List<PricingBusinessAttribute> businessAttributes(){
		return pricingService.businessAttributes();
	}
	
	@RequestMapping(value="/testdata", method=RequestMethod.POST)
	private List<PricingTestCaseResponse> generateTestCaseCombination(@RequestBody PricingAttributeRequest attributeInputList) {
		return pricingService.generateTestCaseCombination(attributeInputList);
	}
	
	@RequestMapping(value="/testdata/airapr/{testsetid}", method=RequestMethod.GET)
	private List<PricingTestCaseResponse> generateTestCaseAirApr(@PathVariable Integer testsetid) {
		return pricingService.generateTestCaseAirApr(testsetid);
	}
	
	@RequestMapping(value="/testdata/result/{testsetid}", method=RequestMethod.GET)
	private PricingTestCaseResult generateTestCaseResult(@PathVariable Integer testsetid) {
		return pricingService.generateTestCaseResult(testsetid);
	}
	
	@RequestMapping(value="testdata/{testsetid}/pagination/pageno/{pageno}", method=RequestMethod.GET)
	private List<PricingTestCaseResponse> findTestCasesByPageNo(@PathVariable Integer testsetid,@PathVariable Integer pageno) {
		return pricingService.findByPageNo(testsetid,pageno);
	}
	
	@RequestMapping(value="testdata/generatepdf/{testsetid}", method=RequestMethod.GET , produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> testCasePDFReport(@PathVariable Integer testsetid) {
        ByteArrayInputStream bis = pricingService.generatePDF(testsetid);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=TestCaseResult.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
}
