package com.rbs.testharness.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rbs.testharness.common.THConstant;
import com.rbs.testharness.common.THException;
import com.rbs.testharness.entity.PricingBusinessAttributeEntity;
import com.rbs.testharness.entity.PricingLookUpEntity;
import com.rbs.testharness.entity.PricingTestCaseResponseEntity;
import com.rbs.testharness.entity.PricingTestSetEntity;
import com.rbs.testharness.helper.PricingHelper;
import com.rbs.testharness.model.PricingAttributeRequest;
import com.rbs.testharness.model.PricingBusinessAttribute;
import com.rbs.testharness.model.PricingTestCaseResponse;
import com.rbs.testharness.repository.PricingBusinessAttributeRepository;
import com.rbs.testharness.repository.PricingLookUpRepository;
import com.rbs.testharness.repository.PricingTestCaseResponseRepository;
import com.rbs.testharness.repository.PricingTestSetRepository;

@Service
public class PricingService {
	
	@Autowired
	private PricingHelper pricingHelper;
	
	@Autowired
	private PricingBusinessAttributeRepository parameterAttributeRepository;
	
	@Autowired
	PricingTestSetRepository pricingTestSetRepository;
	
	@Autowired
	PricingTestCaseResponseRepository pricingTestCaseResponseRepository;
	
	@Autowired
	PricingLookUpRepository pricingLookUpRepository;
	
	/*
	 * 
	 */
	public List<PricingBusinessAttribute> businessAttributes(){
		List<PricingBusinessAttributeEntity> parameterAttributeEntityList=parameterAttributeRepository.findAll();
		List<PricingBusinessAttribute> parameterAttributeList=new ArrayList<>();
		if(parameterAttributeEntityList!=null && parameterAttributeEntityList.size()>0) {
			parameterAttributeEntityList.forEach(page->{
				PricingBusinessAttribute testCase=new PricingBusinessAttribute();
				BeanUtils.copyProperties(page, testCase);
				parameterAttributeList.add(testCase);
			});
		}else {
			throw new THException(HttpStatus.NOT_FOUND,"Parameter attribute not found","Not found");
		}
		return parameterAttributeList;
	}
	
	
	/*
	 * This method will generate the Test case Combination based on the attribute inpputs
	 */
	public List<PricingTestCaseResponse> generateTestCaseCombination(PricingAttributeRequest attributeInput) {
		final List<List<Integer>> lists = new ArrayList<List<Integer>>();
		lists.add(new ArrayList<>(attributeInput.getBorrowingAmount()));
		lists.add(new ArrayList<>(attributeInput.getRiskFactor()));
		lists.add(new ArrayList<>(attributeInput.getTermFactor()));
		//Logic to add the record in the Test_Set Table
		String borrowingAmount = attributeInput.getBorrowingAmount().stream().map(String::valueOf).collect(Collectors.joining(","));
		String riskBand = attributeInput.getRiskFactor().stream().map(String::valueOf).collect(Collectors.joining(","));
		String termFactor = attributeInput.getTermFactor().stream().map(String::valueOf).collect(Collectors.joining(","));
		PricingTestSetEntity testSetEntity=new PricingTestSetEntity();
		BeanUtils.copyProperties(attributeInput, testSetEntity);
		testSetEntity.setBorrowingAmount(borrowingAmount);
		testSetEntity.setRiskBand(riskBand);
		testSetEntity.setTermFactor(termFactor);
		testSetEntity=pricingTestSetRepository.save(testSetEntity);
		//Logic to create test case combination
		final List<Integer> list4 = new ArrayList<Integer>();
		PricingHelper.permute(lists, (permutation -> list4.addAll(permutation)));
		System.out.println("Total testcase size::"+list4.size());
		int j = 0;
		int transactionId=1;
		List<PricingTestCaseResponseEntity> pricingTestCaseResponseEntityList = new ArrayList<>();

		for (int i = 0; i < list4.size() / 3; i++) {
			PricingTestCaseResponseEntity pricingTestCaseResponseEntity = new PricingTestCaseResponseEntity();
			pricingTestCaseResponseEntity.setTestSetId(testSetEntity.getTestSetId());
			String counter = String.format("%06d", transactionId++); 
			pricingTestCaseResponseEntity.setTestTransactionNo("TH_"+testSetEntity.getTestSetId()+"_"+ counter);
			pricingTestCaseResponseEntity.setTestTransactionFlag(THConstant.TestCase_Processed_N);
			pricingTestCaseResponseEntity.setApplicationIdentity(attributeInput.getApplicationIdentity());
			pricingTestCaseResponseEntity.setBankDivision(attributeInput.getBankDivision());
			pricingTestCaseResponseEntity.setProductFamily(attributeInput.getProductFamily());
			pricingTestCaseResponseEntity.setProductName(attributeInput.getProductName());
			pricingTestCaseResponseEntity.setBorrowingAmount(list4.get(j++));
			pricingTestCaseResponseEntity.setRiskBand(list4.get(j++));
			pricingTestCaseResponseEntity.setTermFactor(list4.get(j++));
			pricingTestCaseResponseEntityList.add(pricingTestCaseResponseEntity);
		}
		//Saving to Transaction Testcase DB
		pricingTestCaseResponseEntityList=pricingTestCaseResponseRepository.saveAll(pricingTestCaseResponseEntityList);
		List<PricingTestCaseResponse> pricingTestCaseResponseList=new ArrayList<>();
		if(pricingTestCaseResponseEntityList!=null && pricingTestCaseResponseEntityList.size()>0) {
			pricingTestCaseResponseEntityList.forEach(pricingTestCaseResponseEntity->{
				PricingTestCaseResponse testCase=new PricingTestCaseResponse();
				BeanUtils.copyProperties(pricingTestCaseResponseEntity, testCase);
				pricingTestCaseResponseList.add(testCase);
			});
		}else {
			throw new THException(HttpStatus.NOT_FOUND,"Test Case not found","Not found");
		}
		return pricingTestCaseResponseList;
	}
	/*
	 * This method will generate the Test case AIR & APR from the lookup table data
	 */
	public List<PricingTestCaseResponse> generateTestCaseAirApr(Integer testSetId) {
		List<PricingTestCaseResponse> pricingTestCaseResponseList=new ArrayList<>();
		List<PricingTestCaseResponseEntity> pricingTestCaseResponseEntityList=new ArrayList<>();

		if(null!=testSetId) {
			Optional<List<PricingTestCaseResponseEntity>> pricingTestCaseResponseEntityLists=pricingTestCaseResponseRepository.findByTestSetId(testSetId);
			if(pricingTestCaseResponseEntityLists.isPresent()) {
				pricingTestCaseResponseEntityLists.get().forEach(pricingTestCaseResponses->{
					PricingTestCaseResponseEntity pricingTestCaseResponseEntity=new PricingTestCaseResponseEntity();
					BeanUtils.copyProperties(pricingTestCaseResponses, pricingTestCaseResponseEntity);
					Integer term=pricingTestCaseResponses.getTermFactor();
					Integer borrowingAmount=pricingTestCaseResponses.getBorrowingAmount();
					Integer risk=pricingTestCaseResponses.getRiskBand();
					//Forming the Term Factor Range
					String termRange=THConstant.Empty_String;
					if(term>=12 && term<=35){
						termRange=THConstant.Term_Range_12to35;
					}
					else if(term>=36 && term<=59){
						termRange=THConstant.Term_Range_36to59;
					}
					else if(term>=60 && term<=120){
						termRange=THConstant.Term_Range_60to120;
					}
					//Forming the Risk Band Range
					String borrowingAmountRange=THConstant.Empty_String;
					if(borrowingAmount>=1000 && borrowingAmount<5000) {
						borrowingAmountRange=THConstant.BorrowingAmount_Range_1000to5000;
					}
					else if(borrowingAmount>=5000 && borrowingAmount<7500) {
						borrowingAmountRange=THConstant.BorrowingAmount_Range_5000to7500;
					}
					else if(borrowingAmount>=6500 && borrowingAmount<10000) {
						borrowingAmountRange=THConstant.BorrowingAmount_Range_7500to10000;
					}
					else if(borrowingAmount>=10000 && borrowingAmount<15000) {
						borrowingAmountRange=THConstant.BorrowingAmount_Range_10000to15000;
					}
					else if(borrowingAmount>=15000 && borrowingAmount<20000) {
						borrowingAmountRange=THConstant.BorrowingAmount_Range_15000to20000;
					}
					else if(borrowingAmount>=20000 && borrowingAmount<25000) {
						borrowingAmountRange=THConstant.BorrowingAmount_Range_20000to25000;
					}
					else if(borrowingAmount>=25000 && borrowingAmount<30000) {
						borrowingAmountRange=THConstant.BorrowingAmount_Range_25000to30000;
					}
					else if(borrowingAmount>=30000 && borrowingAmount<35000) {
						borrowingAmountRange=THConstant.BorrowingAmount_Range_30000to35000;
					}
					else if(borrowingAmount>=35000 && borrowingAmount<40000) {
						borrowingAmountRange=THConstant.BorrowingAmount_Range_35000to40000;
					}
					else if(borrowingAmount>=40000 && borrowingAmount<45000) {
						borrowingAmountRange=THConstant.BorrowingAmount_Range_40000to45000;
					}
					else if(borrowingAmount>=45000 && borrowingAmount<50000) {
						borrowingAmountRange=THConstant.BorrowingAmount_Range_45000to50000;
					}
					//Making DB call to retrive the AIR & APR
					Optional<PricingLookUpEntity> pricingLookUp=pricingLookUpRepository.findByRiskBandAndTermFactorAndBorrowingAmount(risk,termRange,borrowingAmountRange);
					if(pricingLookUp.isPresent()) {
						pricingTestCaseResponseEntity.setExpectetAir(pricingLookUp.get().getAirRate());
						pricingTestCaseResponseEntity.setExpectetApr(pricingLookUp.get().getAprRate());
					}
					else {
						pricingTestCaseResponseEntity.setExpectetAir(THConstant.Default_Air);
						pricingTestCaseResponseEntity.setExpectetApr(THConstant.Default_Apr);
					}
					pricingTestCaseResponseEntityList.add(pricingTestCaseResponseEntity);
				});
				List<PricingTestCaseResponseEntity> pricingTestCaseResponseWithAirApr=pricingTestCaseResponseRepository.saveAll(pricingTestCaseResponseEntityList);
				if(null!=pricingTestCaseResponseWithAirApr && pricingTestCaseResponseWithAirApr.size()>0) {
					pricingTestCaseResponseWithAirApr.forEach(pricingTestCaseResponseAirApr->{
						PricingTestCaseResponse pricingTestCaseResponse=new PricingTestCaseResponse();
						BeanUtils.copyProperties(pricingTestCaseResponseAirApr, pricingTestCaseResponse);
						pricingTestCaseResponse.setTotalRecord(Long.valueOf(pricingTestCaseResponseWithAirApr.size()));
						pricingTestCaseResponseList.add(pricingTestCaseResponse);
					});
				}
			}else {
				throw new THException(HttpStatus.NOT_FOUND,"Test Case not found","Not found");
			}
		}
		return pricingTestCaseResponseList;
	}
	
	/*
	 * This method used for retrieving pagination Test cases pages by page
	 */
	
	public List<PricingTestCaseResponse> findByPageNo(Integer testSetId,Integer pageNo){
		Pageable pageRequest=PageRequest.of(pageNo-1, THConstant.pageSize, Sort.by("testTransactionId"));
		Page<PricingTestCaseResponseEntity> pageList= pricingTestCaseResponseRepository.findByTestSetId(testSetId,pageRequest);
		List<PricingTestCaseResponse> testCaseList = new ArrayList<>();
		if(pageList!=null && pageList.getSize()>0) {
			pageList.forEach(page->{
				PricingTestCaseResponse testCase=new PricingTestCaseResponse();
				BeanUtils.copyProperties(page, testCase);
				testCase.setTotalRecord(pageList.getTotalElements());
				testCaseList.add(testCase);
			});
		}
		return testCaseList;
	}
}
