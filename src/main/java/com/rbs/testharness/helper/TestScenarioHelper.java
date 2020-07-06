package com.rbs.testharness.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.rbs.testharness.common.THConstant;
import com.rbs.testharness.entity.PricingLookUpEntity;
import com.rbs.testharness.entity.PricingTestCaseResponseEntity;
import com.rbs.testharness.repository.PricingTestCaseResponseRepository;
import com.rbs.testharness.repository.PricingTestSetRepository;

@Component
public class TestScenarioHelper {

	
	@Autowired
	private PricingHelper pricingHelper;
	
	
	public List<PricingTestCaseResponseEntity> generateSelectedTestCase(MultipartFile file) throws InvalidFormatException, IOException,NumberFormatException {
		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
	
		List<PricingTestCaseResponseEntity> pricingTestCaseResponseEntityList =new ArrayList<PricingTestCaseResponseEntity>();
		Map<String, Integer> businessAttributeMap = pricingHelper.findBusinessAttributeId();
  
    	 XSSFSheet sheet = workbook.getSheetAt(0); 
    	 Iterator<Row> rowIterator = sheet.iterator();
    	 String productName=null;
    	 while (rowIterator.hasNext()) {
             Row row = rowIterator.next();
             int rowIndex = row.getRowNum();
             if (rowIndex < 1) {
                continue;
          }
             Iterator<Cell> cellIterator = row.cellIterator();
             PricingTestCaseResponseEntity pricingTestCaseResponseEntity= new PricingTestCaseResponseEntity();
             while (cellIterator.hasNext()) {
                 Cell cell = cellIterator.next();
                 int columnIndex = cell.getColumnIndex();
                 String columnName = "";
                 switch (columnIndex) {
                     case 0:
                         columnName = THConstant.Test_Case_Number;
                         break;
                     case 1:
                         columnName = THConstant.Test_Set_Id;
                         break;
                     case 2:
                         columnName = THConstant.Application_Identity;
                         break;
                     case 3:
                         columnName = THConstant.Bank_Division;
                         break;
                     case 4:
                         columnName = THConstant.Product_Family;
                         break;
                     case 5:
                         columnName = THConstant.Product_Name;
                         break;
                     case 6:
                         columnName = THConstant.Borrowing_Amount;
                         break;
                     case 7:
                         columnName = THConstant.Term;
                         break;
                     case 8:
                         columnName = THConstant.Risk_Band;
                         break;
                     case 9:
                         columnName = THConstant.Expected_AIR+"|"+THConstant.Expected_Margin_Fee;
                         break;
                     case 10:
                         columnName = THConstant.Expected_APR+"|"+THConstant.Expected_Arrangement_Fee;
                         break;
                     case 11:
                         columnName = THConstant.Environment+"|"+THConstant.Start_Margin;
                         break;
                     case 12:
                         columnName = THConstant.Created_By+"|"+THConstant.Term_Margin_Premium;
                         break;  
                     case 13:
                         columnName = THConstant.Environment;
                         break;
                     case 14:
                         columnName = THConstant.Created_By;
                         break; 
             }
                 String colName1=null;
                 String colName2=null;
                 String colName3=null;
                 if(columnName.contains("|")) {
                	 String[] columnArg=columnName.split("\\|");
                	 //if(columnArg.length ==3) {
                		 colName1=columnArg[0];
                    	 colName2=columnArg[1];
                    	// colName3=columnArg[2];
                	 //}
                	// else {
                		 //colName1=columnArg[0];
                    	// colName2=columnArg[1];
                	// }
                 }
                 String value = TestScenarioHelper.getValue(cell);
                 
                 if((THConstant.Test_Case_Number).equalsIgnoreCase(columnName)) {
                	 pricingTestCaseResponseEntity.setTestTransactionNo(value);
                 }
                 else if((THConstant.Test_Set_Id).equalsIgnoreCase(columnName)) {
                	 pricingTestCaseResponseEntity.setTestSetId(Integer.valueOf(value));
                 }
                 else if((THConstant.Application_Identity).equalsIgnoreCase(columnName)) {
                	 pricingTestCaseResponseEntity.setApplicationIdentity(businessAttributeMap.get(value));
                 }
                 else if((THConstant.Bank_Division).equalsIgnoreCase(columnName)) {
                	 pricingTestCaseResponseEntity.setBankDivision(businessAttributeMap.get(value));
                 }
                 else if((THConstant.Product_Family).equalsIgnoreCase(columnName)) {
                	 pricingTestCaseResponseEntity.setProductFamily(businessAttributeMap.get(value));
                 }
                 else if((THConstant.Product_Name).equalsIgnoreCase(columnName)) {
                	 productName=value;
                	 pricingTestCaseResponseEntity.setProductName(businessAttributeMap.get(value));
                 }
                 else if((THConstant.Borrowing_Amount).equalsIgnoreCase(columnName)) {
                	 pricingTestCaseResponseEntity.setBorrowingAmount(Integer.valueOf(value));
                 }
                 else if((THConstant.Term).equalsIgnoreCase(columnName)) {
                	 pricingTestCaseResponseEntity.setTermFactor(Integer.valueOf(value));
                 }
                 else if((THConstant.Risk_Band).equalsIgnoreCase(columnName)) {
                	 pricingTestCaseResponseEntity.setRiskBand(Integer.valueOf(value));
                 }
                 else if((THConstant.Expected_AIR).equalsIgnoreCase(colName1)
                		 && productName.equalsIgnoreCase(THConstant.Small_Business_Loans)) {
                	 pricingTestCaseResponseEntity.setExpectetAir(Double.valueOf(value));
                 }
                 else if((THConstant.Expected_Margin_Fee).equalsIgnoreCase(colName2) 
                		 && productName.equalsIgnoreCase(THConstant.Overdraft_Sheet)
                		 ) {
                	 pricingTestCaseResponseEntity.setExpectedMarginFee(Double.valueOf(value));
                 }
                 else if((THConstant.Start_Margin).equalsIgnoreCase(colName2)
                		 && productName.equalsIgnoreCase(THConstant.Agri_Sheet)) {
                	 pricingTestCaseResponseEntity.setStartMargin(Double.valueOf(value));
                 }
                 else if((THConstant.Expected_APR).equalsIgnoreCase(colName1)
                		 && productName.equalsIgnoreCase(THConstant.Small_Business_Loans)) {
                	 pricingTestCaseResponseEntity.setExpectetApr(Double.valueOf(value));
                 }
                 else if((THConstant.Expected_Arrangement_Fee).equalsIgnoreCase(colName2)
                		 && productName.equalsIgnoreCase(THConstant.Overdraft_Sheet)) {
                	 pricingTestCaseResponseEntity.setExpectedArrangementFee(Double.valueOf(value));
                 }
                 else if((THConstant.Term_Margin_Premium).equalsIgnoreCase(colName2)
                		 && productName.equalsIgnoreCase(THConstant.Agri_Sheet)) {
                	 pricingTestCaseResponseEntity.setTermMarginPermium(Double.valueOf(value));
                 }
                 else if((THConstant.Created_By).equalsIgnoreCase(columnName)||(THConstant.Created_By).equalsIgnoreCase(colName1)) {
                	 pricingTestCaseResponseEntity.setCreatedBy(value);
                 }
                 continue;
            
          }
             pricingTestCaseResponseEntityList.add(pricingTestCaseResponseEntity);
             
  }
    
   
     return pricingTestCaseResponseEntityList;
}

private static String getValue(Cell cell) {
	DataFormatter fmt= new DataFormatter();
    switch (cell.getCellType()) {
        case Cell.CELL_TYPE_BLANK:
            return null;
        case Cell.CELL_TYPE_BOOLEAN:
            return "CELL_TYPE_BOOLEAN";
        case Cell.CELL_TYPE_ERROR:
            return "CELL_TYPE_ERROR";
        case Cell.CELL_TYPE_FORMULA:
            return "CELL_TYPE_FORMULA";
        case Cell.CELL_TYPE_NUMERIC:
            return fmt.formatCellValue(cell);
        case Cell.CELL_TYPE_STRING:
            return cell.getStringCellValue();
        default:
            return "none";

    }

 }
}
