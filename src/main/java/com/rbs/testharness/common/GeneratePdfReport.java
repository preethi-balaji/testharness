package com.rbs.testharness.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.rbs.testharness.model.PricingTestCaseResponse;

@Component
public class GeneratePdfReport{

    private static final Logger logger = LoggerFactory.getLogger(GeneratePdfReport.class);

    public ByteArrayInputStream testCaseResultReport(List<PricingTestCaseResponse> pricingTestCaseResponse) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
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
        
        //Set the items display in header part of page
        String userName="Goutam";
        String environmentName="Dev";
        String testSetId="123";
        
        try {
        	            
            //Font for Test Case table heading and cell values                   
            Font lableFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8.5f);
            lableFont.setColor(BaseColor.WHITE);
            Font valueFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 7.5f);

            //Test Case summary table
        	PdfPTable resultSummaryTable = new PdfPTable(3);
        	resultSummaryTable.setWidthPercentage(30);
         	resultSummaryTable.setWidths(new float[]{4f, 2f, 2f});
         	resultSummaryTable.setSpacingAfter(10f);
         	resultSummaryTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
        	
        	PdfPCell summarycell;
        	summarycell = new PdfPCell(new Phrase("Total Test Case", lableFont));
         	summarycell.setBackgroundColor(new BaseColor(43, 56,176));
        	summarycell.setHorizontalAlignment(Element.ALIGN_CENTER);
        	resultSummaryTable.addCell(summarycell);

            summarycell = new PdfPCell(new Phrase("Passed", lableFont));
         	summarycell.setBackgroundColor(new BaseColor(43, 56,176));
            summarycell.setHorizontalAlignment(Element.ALIGN_CENTER);
            resultSummaryTable.addCell(summarycell);

            summarycell = new PdfPCell(new Phrase("Failed", lableFont));
         	summarycell.setBackgroundColor(new BaseColor(43, 56,176));
            summarycell.setHorizontalAlignment(Element.ALIGN_CENTER);
            resultSummaryTable.addCell(summarycell);
            
            PdfPCell scell;
            scell = new PdfPCell(new Phrase(String.valueOf((int)totalTestCase),valueFont));
            scell.setBackgroundColor(BaseColor.ORANGE);
            scell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            scell.setHorizontalAlignment(Element.ALIGN_CENTER);
            resultSummaryTable.addCell(scell);

            scell = new PdfPCell(new Phrase(String.valueOf((int)passedCaseSize),valueFont));
            scell.setPaddingLeft(5);
            scell.setBackgroundColor(BaseColor.GREEN);
            scell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            scell.setHorizontalAlignment(Element.ALIGN_CENTER);
            resultSummaryTable.addCell(scell);

            scell = new PdfPCell(new Phrase(String.valueOf((int)failedCaseSize),valueFont));
            scell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            scell.setBackgroundColor(BaseColor.RED);
            scell.setHorizontalAlignment(Element.ALIGN_CENTER);
            scell.setPaddingRight(5);
            resultSummaryTable.addCell(scell);  
            
            //Opening the PdfWriter object
            PdfWriter writer = PdfWriter.getInstance(document, out);
            //Even calling for header and footer
            GeneratePdfHeaderFooterEvent event = new GeneratePdfHeaderFooterEvent(userName,environmentName,testSetId);
            writer.setPageEvent(event);
            
            //Setting up the page margin
            document.setPageSize(PageSize.A4);
            document.setMargins(50, 50, 120, 70);
            document.setMarginMirroring(false);
            document.open();
            
            //Adding the summary table to document
            document.add(resultSummaryTable);
            
            //Test Case Table and Cell
            
            PdfPTable table=null;
            PdfPCell cell=null;
            for(List<PricingTestCaseResponse> testCasesList : totalTestCaseList) {
            	if(testCasesList!=null && testCasesList.size()>0) {
	            	for(PricingTestCaseResponse cases : testCasesList) {
	            		if(null!=cases.getTestTransactionFlag())
	                        if("Y".equalsIgnoreCase(cases.getTestTransactionFlag().toString())) {
	                        	Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 10.5f, Font.BOLD);
	                            Chunk passedChunk = new Chunk("Passed :"+dformat.format(passedPercentage) +"%",boldFont);
	                            passedChunk.setBackground(BaseColor.ORANGE);
	                            Paragraph passedPara = new Paragraph(passedChunk);
	                            passedPara.setAlignment(Element.ALIGN_LEFT);
	                            passedPara.add(" \n ");
	                            //Adding to document.
	                            document.add(passedPara);
	                            break;
	                        }else {
	                            Chunk spaceChunk = new Chunk("\n\n");
	                        	Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 10.5f, Font.BOLD);
	                            Chunk failedChunk = new Chunk("Failed :"+dformat.format(failedPercentage) +"%",boldFont);
	                            failedChunk.setBackground(BaseColor.ORANGE);
	                            Paragraph failedPara = new Paragraph(spaceChunk);
	                            failedPara.add(failedChunk);
	                            failedPara.setAlignment(Element.ALIGN_LEFT);
	                            failedPara.add(" \n ");
	                            //Adding to document.
	                            document.add(failedPara);
	                            break;
	                        }
	            	}
	            	//Test cases table for records/cells
	            	
	            	table=new PdfPTable(13);
	                table.setWidthPercentage(100);
	                table.setWidths(new float[]{1f, 1.5f, 1.5f,1.5f, 1.5f,1.5f, 1.5f,1.5f, 1.5f,1.5f, 1.5f,1.5f, 1.5f});
	                table.setHeaderRows(1);
	                
	                PdfPCell hcell;
	                hcell = new PdfPCell(new Phrase("Id", lableFont));
	                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                hcell.setBackgroundColor(new BaseColor(43, 56,176));
	                table.addCell(hcell);
	
	                hcell = new PdfPCell(new Phrase("Application Identity", lableFont));
	                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                hcell.setBackgroundColor(new BaseColor(43, 56,176));
	                table.addCell(hcell);
	
	                hcell = new PdfPCell(new Phrase("Bank Division", lableFont));
	                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                hcell.setBackgroundColor(new BaseColor(43, 56,176));
	                table.addCell(hcell);
	
	                hcell = new PdfPCell(new Phrase("Product Family", lableFont));
	                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                hcell.setBackgroundColor(new BaseColor(43, 56,176));
	                table.addCell(hcell);
	
	                hcell = new PdfPCell(new Phrase("Product Name", lableFont));
	                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                hcell.setBackgroundColor(new BaseColor(43, 56,176));
	                table.addCell(hcell);
	
	                hcell = new PdfPCell(new Phrase("Borrowing Amount", lableFont));
	                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                hcell.setBackgroundColor(new BaseColor(43, 56,176));
	                table.addCell(hcell);
	                
	                hcell = new PdfPCell(new Phrase("Term(Months)", lableFont));
	                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                hcell.setBackgroundColor(new BaseColor(43, 56,176));
	                table.addCell(hcell);
	
	                hcell = new PdfPCell(new Phrase("Risk Band", lableFont));
	                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                hcell.setBackgroundColor(new BaseColor(43, 56,176));
	                table.addCell(hcell);
	                
	                hcell = new PdfPCell(new Phrase("Expected AIR", lableFont));
	                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                hcell.setBackgroundColor(new BaseColor(43, 56,176));
	                table.addCell(hcell);
	
	                hcell = new PdfPCell(new Phrase("Excepted APR", lableFont));
	                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                hcell.setBackgroundColor(new BaseColor(43, 56,176));
	                table.addCell(hcell);
	                
	                hcell = new PdfPCell(new Phrase("Actual AIR", lableFont));
	                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                hcell.setBackgroundColor(new BaseColor(43, 56,176));
	                table.addCell(hcell);
	
	                hcell = new PdfPCell(new Phrase("Actual APR", lableFont));
	                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                hcell.setBackgroundColor(new BaseColor(43, 56,176));
	                table.addCell(hcell);
	
	                hcell = new PdfPCell(new Phrase("Pass/Fail", lableFont));
	                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                hcell.setBackgroundColor(new BaseColor(43, 56,176));
	                table.addCell(hcell);
	            	
	                //Generate records/columns/cells	
	                generatePassedFailedReport(cell,table,testCasesList,valueFont);
	                document.add(table);
            	}
            }
            //Document close
            document.close();
        } catch (Exception ex) {
            logger.error("Error occurred: {0}", ex);
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
    /*
     * This method will execute for both pass and failed case column/cell creation
     */
    private void generatePassedFailedReport(PdfPCell cell,PdfPTable table,List<PricingTestCaseResponse> testCaseList,Font valueFont) {
        int i=2;
        BaseColor recordCol=null;
        for (PricingTestCaseResponse testCases : testCaseList) {
        	if(i%2==0) {
        		recordCol=new BaseColor(238, 238,238);
        	}
        	else{
        		recordCol=new BaseColor(255, 255,255);
        	}
        	cell = new PdfPCell(new Phrase(String.valueOf(testCases.getTestTransactionId()),valueFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(recordCol);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(testCases.getApplicationIdentity()),valueFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(recordCol);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(testCases.getBankDivision()),valueFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(recordCol);
            cell.setBorder(0);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(String.valueOf(testCases.getProductFamily()),valueFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(recordCol);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(testCases.getProductName()),valueFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(recordCol);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(testCases.getBorrowingAmount()),valueFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(recordCol);
            cell.setBorder(0);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(String.valueOf(testCases.getTermFactor()),valueFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(recordCol);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(testCases.getRiskBand()),valueFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(recordCol);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(testCases.getExpectetAir()),valueFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(recordCol);
            cell.setBorder(0);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(String.valueOf(testCases.getExpectetApr()),valueFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(recordCol);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(testCases.getActualAir()),valueFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(recordCol);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(testCases.getActualApr()),valueFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(recordCol);
            cell.setBorder(0);
            table.addCell(cell);
            
            if(null!=testCases.getTestTransactionFlag())
            if("Y".equalsIgnoreCase(testCases.getTestTransactionFlag().toString())) {
                cell = new PdfPCell(new Phrase(String.valueOf("Pass"),valueFont));
            }else {
                cell = new PdfPCell(new Phrase(String.valueOf("Fail"),valueFont));
            }
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(recordCol);
            cell.setBorder(0);
            table.addCell(cell);
            i++;
        }
    }
}