package com.rbs.testharness.helper;

import java.io.File;
import java.io.FileInputStream;
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

import com.rbs.testharness.common.THConstant;

import com.rbs.testharness.entity.PricingLookUpEntity;


@Component
public class ReferenceDataHelper {

	@Autowired
	private PricingHelper pricingHelper;
	public List<PricingLookUpEntity> generateReferenceData(File file) throws InvalidFormatException, IOException,NumberFormatException {
		
	FileInputStream file1 = new FileInputStream(file.getAbsolutePath());
	XSSFWorkbook workbook = new XSSFWorkbook(file1);
	List<PricingLookUpEntity> pricingLookUpEntityList =new ArrayList<PricingLookUpEntity>();
     int sheetNum= workbook.getNumberOfSheets();
     Map<String, Integer> businessAttributeMap = pricingHelper.findBusinessAttributeId();
     for (int i = 0; i < sheetNum; i++) {
    	 XSSFSheet sheet = workbook.getSheetAt(i); 
    	 Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
             Row row = rowIterator.next();
             int rowIndex = row.getRowNum();
             if (rowIndex < 1) {
                continue;
          }
             Iterator<Cell> cellIterator = row.cellIterator();
             PricingLookUpEntity pricingLookUpEntity= new PricingLookUpEntity();
             while (cellIterator.hasNext()) {
                 Cell cell = cellIterator.next();
                 int columnIndex = cell.getColumnIndex();
                 String columnName = "";
                 switch (columnIndex) {
                     case 0:
                         columnName = THConstant.Risk_Band;
                         break;
                     case 1:
                         columnName = THConstant.Term_Factor;
                         break;
                     case 2:
                         columnName = THConstant.Borrowing_Amount;
                         break;
                     case 3:
                         columnName = THConstant.Air_Rate+"|"+THConstant.Margin_Fee;
                         break;
                     case 4:
                         columnName = THConstant.Apr_Rate+"|"+THConstant.Arrangement_Fee;
                         break;
                     case 5:
                         columnName = THConstant.Term_Margin_Premium;
                         break;
                     case 6:
                         columnName = THConstant.Start_Margin;
                         break;
               
             }
                 String value = ReferenceDataHelper.getValue(cell);
                 String sheetCol1=null;
                 String sheetCol2=null;
                 if(columnName.contains("|")) {
                	 String[] columnArg=columnName.split("\\|");
                	 sheetCol1=columnArg[0];
                	 sheetCol2=columnArg[1];
                 }
                 
                
                 if((THConstant.Risk_Band).equalsIgnoreCase(columnName)) {
                 	 pricingLookUpEntity.setRiskBand(Integer.valueOf(value));
                 }
                 else if((THConstant.Term_Factor).equalsIgnoreCase(columnName)) {
                	 pricingLookUpEntity.setTermFactor(value);
                }
                 else if((THConstant.Borrowing_Amount).equalsIgnoreCase(columnName)) {
                	 pricingLookUpEntity.setBorrowingAmount(value);
                }
                                
                 else if(sheetCol1!=null && sheetCol1.equalsIgnoreCase(THConstant.Air_Rate) 
                		 && sheet.getSheetName().equalsIgnoreCase(THConstant.SBL_Sheet)) {
                	 pricingLookUpEntity.setAirRate(Double.valueOf(value));
                }
               
                 else if(sheetCol1!=null && sheetCol1.equalsIgnoreCase(THConstant.Apr_Rate) 
                		 && sheet.getSheetName().equalsIgnoreCase(THConstant.SBL_Sheet)) {
                	 pricingLookUpEntity.setAprRate(Double.valueOf(value));
                }
                 else if(sheetCol2!=null && sheetCol2.equalsIgnoreCase(THConstant.Margin_Fee) 
                		 && (sheet.getSheetName().equalsIgnoreCase(THConstant.Overdraft_Sheet) 
                				 ||  sheet.getSheetName().equalsIgnoreCase(THConstant.Agri_Sheet))) {
                	 pricingLookUpEntity.setMarginFee(Double.valueOf(value));
                }
                 else if(sheetCol2!=null && sheetCol2.equalsIgnoreCase(THConstant.Arrangement_Fee) 
                		 && (sheet.getSheetName().equalsIgnoreCase(THConstant.Overdraft_Sheet) 
                				 || sheet.getSheetName().equalsIgnoreCase(THConstant.Agri_Sheet))) {
                	 pricingLookUpEntity.setArrangementFee(Double.valueOf(value));
                }
                 else if((THConstant.Term_Margin_Premium).equalsIgnoreCase(columnName)) {
                	 pricingLookUpEntity.setTermMarginPremium(Double.valueOf(value));
                }
                 else if((THConstant.Start_Margin).equalsIgnoreCase(columnName)) {
                	 pricingLookUpEntity.setStartMarginFee(Double.valueOf(value));
                }
                 if(sheet.getSheetName().equalsIgnoreCase(THConstant.SBL_Sheet)) {
                	 pricingLookUpEntity.setAttributeId(1);
                 }
                 else {
                	 pricingLookUpEntity.setAttributeId(businessAttributeMap.get(sheet.getSheetName()));
                 }
                
                 boolean valid =true;
                 if (valid) {
                     continue;
                 }
                
          }
             pricingLookUpEntityList.add(pricingLookUpEntity);
             
  }
    
    }

     return pricingLookUpEntityList;
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
