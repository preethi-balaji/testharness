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
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.rbs.testharness.model.PricingTestCaseResponse;

@Component
public class GeneratePdfReport {

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
        try {
        	       	
            Font lableFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 9.5f);
            lableFont.setColor(BaseColor.WHITE);
            Font valueFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8.5f);


        	PdfPTable resultSummaryTable = new PdfPTable(3);
        	resultSummaryTable.setWidthPercentage(30);
         	resultSummaryTable.setWidths(new float[]{4f, 2f, 2f});
         	resultSummaryTable.setSpacingAfter(30f);
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
            
            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12.5f, Font.BOLD);
            Chunk chunk = new Chunk("Passed : "+dformat.format(passedPercentage)+ "%  Failed :"+dformat.format(failedPercentage) +"%",boldFont);
            chunk.setBackground(BaseColor.ORANGE);
            Paragraph para = new Paragraph(chunk);
            para.add(" \n ");
            
            PdfPTable table = new PdfPTable(13);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{1f, 1.5f, 1.5f,1.5f, 1.5f,1.5f, 1.5f,1.5f, 1.5f,1.5f, 1.5f,1.5f, 1.5f});


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
            PdfPCell cell=null;
            
            for(List<PricingTestCaseResponse> testCasesList : totalTestCaseList) {
                    generatePassedFailedReport(cell,table,testCasesList,valueFont);
            }
       
            PdfWriter.getInstance(document, out);
            document.open();
            Rectangle rect= new Rectangle(577,825,18,15);
            rect.setBorder(Rectangle.BOX);
            rect.setBorderWidth(2);
            document.add(rect);
            document.add(resultSummaryTable);
            document.add(para);
            document.add(table);
            //Creating boarder 
            rect.setBorder(Rectangle.BOX);
            rect.setBorderWidth(2);
            document.add(rect);
            document.close();

        } catch (DocumentException ex) {

            logger.error("Error occurred: {0}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
    private void generatePassedFailedReport(PdfPCell cell,PdfPTable table,List<PricingTestCaseResponse> testCaseList,Font valueFont) {
        
        for (PricingTestCaseResponse testCases : testCaseList) {
        	cell = new PdfPCell(new Phrase(String.valueOf(testCases.getTestTransactionId()),valueFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(testCases.getApplicationIdentity()),valueFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(testCases.getBankDivision()),valueFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(String.valueOf(testCases.getProductFamily()),valueFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(testCases.getProductName()),valueFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(testCases.getBorrowingAmount()),valueFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(String.valueOf(testCases.getTermFactor()),valueFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(testCases.getRiskBand()),valueFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(testCases.getExpectetAir()),valueFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(String.valueOf(testCases.getExpectetApr()),valueFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(testCases.getActualAir()),valueFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(testCases.getActualApr()),valueFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            if(null!=testCases.getTestTransactionFlag())
            if("Y".equalsIgnoreCase(testCases.getTestTransactionFlag().toString())) {
                cell = new PdfPCell(new Phrase(String.valueOf("Pass"),valueFont));
            }else {
                cell = new PdfPCell(new Phrase(String.valueOf("Fail"),valueFont));
            }
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }
    }
}