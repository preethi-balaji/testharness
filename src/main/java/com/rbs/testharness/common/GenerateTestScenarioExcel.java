package com.rbs.testharness.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.rbs.testharness.model.PricingTestCaseResponse;
@Component
public class GenerateTestScenarioExcel {
   

    public ByteArrayInputStream generateExcelReport(List<PricingTestCaseResponse> pricingTestCaseResponse) {
    	 ByteArrayOutputStream out = new ByteArrayOutputStream();
    	 String createdBy=null;
    	 String environment=null;
    	
        if(null!=pricingTestCaseResponse && pricingTestCaseResponse.size()>0) {
			
        	createdBy=pricingTestCaseResponse.get(0).getCreatedBy();
        	environment=pricingTestCaseResponse.get(0).getEnvironment();
        	
        }
    	//Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook(); 
         
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Generated_Scenarios");
                
        int rowKey=1;
        String[] datasets = null;
        //This data needs to be written (Object[])
        Map<Integer, String[]> data = new TreeMap<Integer, String[]>();
       
        if(null!=pricingTestCaseResponse && pricingTestCaseResponse.size()>0) {
        
        	if(pricingTestCaseResponse.get(0).getProductName().equals(THConstant.Small_Business_Loans)) {
        		
        		data.put(1, new String[] {"Test Case Number", "Test Set Id","Application Identity", "Bank Division","Product Family", "Product Name", "Borrowing Amount","Term(Months)", "Risk Band", "Expected AIR","Expected APR","Environment","Created By"});
        	}
        	else if(pricingTestCaseResponse.get(0).getProductName().equals(THConstant.Overdraft_Sheet)) {
        		
        		data.put(1, new String[] {"Test Case Number","Test Set Id", "Application Identity", "Bank Division","Product Family", "Product Name", "Borrowing Amount","Term(Months)", "Risk Band", "Expected Margin Fee","Expected Arrangement Fee", "Environment","Created By"});
        	}
        	else if(pricingTestCaseResponse.get(0).getProductName().equals(THConstant.Agri_Sheet)) {
        		
        		data.put(1, new String[] {"Test Case Number", "Test Set Id","Application Identity", "Bank Division","Product Family", "Product Name", "Borrowing Amount","Term(Months)", "Risk Band", "Expected Margin Fee","Expected Arrangement Fee","Start Margin","Term Margin Premium","Environment","Created By"});
        	}
        }
        
       
       // for(List<PricingTestCaseResponse> totalTestCases : totalTestCaseList) {
        	for(PricingTestCaseResponse testCases : pricingTestCaseResponse) {
        		if(testCases.getProductName().equals(THConstant.Small_Business_Loans) ||testCases.getProductName().equals(THConstant.Overdraft_Sheet) ) {
        			datasets=new String[13];
        		}
        		else if(testCases.getProductName().equals(THConstant.Agri_Sheet)) {
        			datasets=new String[15];
        		}
        		
    			datasets[0]=String.valueOf(testCases.getTestTransactionNo());
    			datasets[1]=String.valueOf(testCases.getTestSetId());
    			datasets[2]=String.valueOf(testCases.getApplicationIdentity());
    			datasets[3]=String.valueOf(testCases.getBankDivision());
    			datasets[4]=String.valueOf(testCases.getProductFamily());
    			datasets[5]=String.valueOf(testCases.getProductName());
    			datasets[6]=String.valueOf(testCases.getBorrowingAmount());
    			datasets[7]=String.valueOf(testCases.getTermFactor());
    			datasets[8]=String.valueOf(testCases.getRiskBand());
    			if(testCases.getProductName().equals(THConstant.Small_Business_Loans)) {
    				datasets[9]=String.valueOf(testCases.getExpectetAir());
        			datasets[10]=String.valueOf(testCases.getExpectetApr());
        			datasets[11]=String.valueOf(testCases.getEnvironment());
        			datasets[12]=String.valueOf(testCases.getCreatedBy());
    			}
    			else if(testCases.getProductName().equals(THConstant.Overdraft_Sheet)) {
    				datasets[9]=String.valueOf(testCases.getExpectedMarginFee());
        			datasets[10]=String.valueOf(testCases.getExpectedArrangementFee());
        			datasets[11]=String.valueOf(testCases.getEnvironment());
        			datasets[12]=String.valueOf(testCases.getCreatedBy());
    			}
    			else if(testCases.getProductName().equals(THConstant.Agri_Sheet)) {
    				
    				datasets[9]=String.valueOf(testCases.getExpectedMarginFee());
        			datasets[10]=String.valueOf(testCases.getExpectedArrangementFee());
        			datasets[11]=String.valueOf(testCases.getStartMarginFee());
        			datasets[12]=String.valueOf(testCases.getTermMarginPremium());
        			datasets[13]=String.valueOf(testCases.getEnvironment());
        			datasets[14]=String.valueOf(testCases.getCreatedBy());
    			}
    			
    			
    	        data.put(++rowKey, datasets);
        	}
      //  }
      
        //Iterate over data and write to sheet
        Set<Integer> keyset = data.keySet();
        int rownum = 0;
        for (Integer key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
               Cell cell = row.createCell(cellnum++);
               if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        try
        {
            //Write the workbook in file system
            workbook.write(out);
            out.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
	}
}
