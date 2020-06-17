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
public class GenerateExcelReport {
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    public ByteArrayInputStream generateExcelReport(List<PricingTestCaseResponse> pricingTestCaseResponse) {
    	List<PricingTestCaseResponse> passedList=new ArrayList<>();
        List<PricingTestCaseResponse> failedList=new ArrayList<>();
        float totalTestCase=0;
        if(null!=pricingTestCaseResponse && pricingTestCaseResponse.size()>0) {
        	totalTestCase=pricingTestCaseResponse.size();
        	for(PricingTestCaseResponse testCases : pricingTestCaseResponse) {
        		if(null!=testCases.getTestTransactionFlag() && "Y".equalsIgnoreCase((testCases.getTestTransactionFlag().toString()))) {
        			passedList.add(testCases);
        		}else {
        			failedList.add(testCases);
        		}
        	}
        }
        //Calculation of failed and passed %
        float passedCaseSize=passedList!=null && passedList.size()>0?passedList.size():0;
        float failedCaseSize=failedList!=null && failedList.size()>0?failedList.size():0;
        
        float passedPercentage=passedCaseSize!=0?passedCaseSize*100/totalTestCase:0;
        float failedPercentage=failedCaseSize!=0?failedCaseSize*100/totalTestCase:0;
        DecimalFormat dformat=new DecimalFormat("0.00");
        List<List<PricingTestCaseResponse>> totalTestCaseList=new ArrayList<>();
        totalTestCaseList.add(passedList);
        totalTestCaseList.add(failedList);
    	
    	
    	//Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook(); 
         
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Test_Case_Result_Data");
        
        int rowKey=1;
        String[] datasets;
        //This data needs to be written (Object[])
        Map<Integer, String[]> data = new TreeMap<Integer, String[]>();
        data.put(1, new String[] {"ID", "Application Identity", "Bank Division","Product Family", "Product Name", "Borrowing Amount","Term(Months)", "Risk Band", "Expected AIR","Excepted APR", "Actual AIR", "Actual APR","Pass/Fail"});
       
        for(List<PricingTestCaseResponse> totalTestCases : totalTestCaseList) {
        	for(PricingTestCaseResponse testCases : totalTestCases) {
        		datasets=new String[13];
    			datasets[0]=String.valueOf(testCases.getTestTransactionId());
    			datasets[1]=String.valueOf(testCases.getApplicationIdentity());
    			datasets[2]=String.valueOf(testCases.getBankDivision());
    			datasets[3]=String.valueOf(testCases.getProductFamily());
    			datasets[4]=String.valueOf(testCases.getProductName());
    			datasets[5]=String.valueOf(testCases.getBorrowingAmount());
    			datasets[6]=String.valueOf(testCases.getTermFactor());
    			datasets[7]=String.valueOf(testCases.getRiskBand());
    			datasets[8]=String.valueOf(testCases.getExpectetAir());
    			datasets[9]=String.valueOf(testCases.getExpectetApr());
    			datasets[10]=String.valueOf(testCases.getActualAir());
    			datasets[11]=String.valueOf(testCases.getActualApr());
    			if(null!=testCases.getTestTransactionFlag()) {
    	            if("Y".equalsIgnoreCase(testCases.getTestTransactionFlag().toString())) {
    	            	datasets[12]=String.valueOf("Pass");
    	            }else {
    	            	datasets[12]=String.valueOf("Fail");
    	            }
    			}
    	        data.put(++rowKey, datasets);
        	}
        }
      
        //Iterate over data and write to sheet
        Set<Integer> keyset = data.keySet();
        int rownum = 5;
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
