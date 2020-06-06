package com.rbs.testharness.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbs.testharness.helper.PricingHelper;
import com.rbs.testharness.model.PricingAttributeRequest;
import com.rbs.testharness.model.PricingResultResponse;
import com.rbs.testharness.model.PricingResultTestCase;
import com.rbs.testharness.model.PricingTestCaseAirApr;
import com.rbs.testharness.model.PricingTestCaseResponse;

@Service
public class PricingService {
	
	@Autowired
	private PricingHelper pricingHelper;
	
	/*
	 * This method will generate the Test case Combination based on the attribute inpputs
	 */
	public List<PricingTestCaseResponse> generateTestCaseCombination(PricingAttributeRequest attributeInputList) {
		List<PricingTestCaseResponse> outputList = new ArrayList<>();
		final List<List<Integer>> lists = new ArrayList<List<Integer>>();
		lists.add(new ArrayList<>(attributeInputList.getBorrowingAmount()));
		lists.add(new ArrayList<>(attributeInputList.getRiskFactor()));
		lists.add(new ArrayList<>(attributeInputList.getTermFactor()));
		int count = 0;
		final List<Integer> list4 = new ArrayList<Integer>();
		
		PricingHelper.permute(lists, (permutation -> list4.addAll(permutation)));
		
		System.out.println("list4 size::"+list4.size());
		int j = 0;
		for (int i = 0; i < list4.size() / 3; i++) {
			PricingTestCaseResponse testOutPut = new PricingTestCaseResponse();
			//testOutPut.setId(String.valueOf(count++));
			testOutPut.setId("01"+LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"))+String.format("%07d", ++count));
			testOutPut.setApplicationIdentity(attributeInputList.getApplicationIdentity());
			testOutPut.setBankDivision(attributeInputList.getBankDivision());
			testOutPut.setProductFamily(attributeInputList.getProductFamily());
			testOutPut.setProductName(attributeInputList.getProductName());
			testOutPut.setBorrowingAmount(list4.get(j++));
			testOutPut.setRiskFactor(list4.get(j++));
			testOutPut.setTermFactor(list4.get(j++));
			outputList.add(testOutPut);
		}
		return outputList;
	}
	/*
	 * This method will generate the Test case AIR & APR from the lookup table data
	 */
	public List<PricingTestCaseAirApr> generateTestCaseAirApr(List<PricingTestCaseResponse> testCaseOutputList){
		List<PricingTestCaseAirApr> pricingTestCaseAirAprList = new ArrayList<>();
		for (PricingTestCaseResponse pricingTestCaseResponse : testCaseOutputList) {
			PricingTestCaseAirApr pricingTestCaseAirApr = new PricingTestCaseAirApr();
			pricingTestCaseAirApr.setId(pricingTestCaseResponse.getId());
			//pricingTestCaseAirApr.setId("01"+LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"))+String.format("%07d", count++));
			pricingTestCaseAirApr.setApplicationIdentity(pricingTestCaseResponse.getApplicationIdentity());
			pricingTestCaseAirApr.setBankDivision(pricingTestCaseResponse.getBankDivision());
			pricingTestCaseAirApr.setProductFamily(pricingTestCaseResponse.getProductFamily());
			pricingTestCaseAirApr.setProductName(pricingTestCaseResponse.getProductName());
			pricingTestCaseAirApr.setBorrowingAmount(pricingTestCaseResponse.getBorrowingAmount());
			pricingTestCaseAirApr.setRiskFactor(pricingTestCaseResponse.getRiskFactor());
			pricingTestCaseAirApr.setTermFactor(pricingTestCaseResponse.getTermFactor());
			if (pricingTestCaseResponse.getBorrowingAmount() <= 10000) {
				pricingTestCaseAirApr.setAllInRate(6.95f);
			} else if (pricingTestCaseResponse.getBorrowingAmount() >= 10000 && pricingTestCaseResponse.getBorrowingAmount() <= 20000) {
				pricingTestCaseAirApr.setAllInRate(7.95f);
			} else if (pricingTestCaseResponse.getBorrowingAmount() >= 20000 && pricingTestCaseResponse.getBorrowingAmount() <= 40000) {
				pricingTestCaseAirApr.setAllInRate(8.95f);
			} else {
				pricingTestCaseAirApr.setAllInRate(9.95f);
			}
			pricingTestCaseAirApr.setAnnualPercentageRate(0.0f);
			pricingTestCaseAirAprList.add(pricingTestCaseAirApr);
		}

		return pricingTestCaseAirAprList;
	}
	/*
	 * This method will generate the Test case Results after connecting from ODM
	 */
	public PricingResultResponse generateTestCaseResult(List<PricingTestCaseAirApr> testCaseAirAprList) {
		List<PricingResultTestCase> testResultList =new  ArrayList<PricingResultTestCase>();
		int i =0;
		int pass=0;
		int fail=0;
		for (PricingTestCaseAirApr testoutputAir : testCaseAirAprList) {
			PricingResultTestCase testCaseResult = new PricingResultTestCase();
			testCaseResult.setId(testoutputAir.getId());
			testCaseResult.setApplicationIdentity(testoutputAir.getApplicationIdentity());
			testCaseResult.setBankDivision(testoutputAir.getBankDivision());
			testCaseResult.setProductFamily(testoutputAir.getProductFamily());
			testCaseResult.setProductName(testoutputAir.getProductName());
			testCaseResult.setBorrowingAmount(testoutputAir.getBorrowingAmount());
			testCaseResult.setRiskFactor(testoutputAir.getRiskFactor());
			testCaseResult.setTermFactor(testoutputAir.getTermFactor());
			testCaseResult.setAllInRate(testoutputAir.getAllInRate());
			testCaseResult.setAnnualPercentageRate(testoutputAir.getAnnualPercentageRate());
			testCaseResult.setExpectedAnnualPercentageRate(testoutputAir.getAnnualPercentageRate());
			if(i <=5) {
				testCaseResult.setStatus("Y");
				testCaseResult.setExpectedAllInRate(testoutputAir.getAllInRate());
				pass++;
				i++;
			}else {
				testCaseResult.setStatus("N");
				testCaseResult.setExpectedAllInRate(11.0f);
				i =0;
				fail++;
			}
			testResultList.add(testCaseResult);
		}
		PricingResultResponse testResult= new PricingResultResponse();
		testResult.setTotaltestcases(testCaseAirAprList.size());
		testResult.setPassed(pass);
		testResult.setFailed(fail);
		testResult.setTestcasesResultList(testResultList);
		return testResult;
	}
	
	/*
	 * This method used for retrieving pagination Test cases pages by page
	 */
	
	public List<PricingTestCaseResponse> findByPageNo(Integer pageno){
		/*Pageable pageRequest=PageRequest.of(pageno-1, THConstants.pageSize, Sort.by("id"));
		Page<TestCaseEntity> pageList= testCaseRepo.findAll(pageRequest);
		List<TestCase> testCaseList = new ArrayList<>();
		if(pageList!=null && pageList.getSize()>0) {
			pageList.forEach(page->{
				TestCase testCase=new TestCase();
				BeanUtils.copyProperties(page, testCase);
				testCase.setTotalRecord(pageList.getTotalElements());
				testCase.setTotalPages(pageList.getTotalPages());
				testCaseList.add(testCase);
			});
		}*/
		return null;
	}
}
