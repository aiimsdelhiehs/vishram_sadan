package aiims.vishram_sadan.services;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import aiims.vishram_sadan.entities.BookingRequest;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfGenerator {

	public static void createReceiptPdf(ByteArrayOutputStream out, BookingRequest bookingRequest) throws DocumentException, IOException {
        // Initialize the document
    
       
        
        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, out);
        
        document.open();

        PdfContentByte canvas = writer.getDirectContent();
        
        Image imageverified=Image.getInstance("\\vishram_sadan\\vishram_sadan\\src\\main\\resources\\static\\assets\\images\\verified.png");
        // Create a PdfContentByte object for absolute positioning
        
        imageverified.scaleToFit(100, 100); 
        
        float xImagePosition = 50; // X position for the image
        float yPosition = 400;
        BaseFont baseFont = BaseFont.createFont(BaseFont.TIMES_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
        canvas.beginText();
        canvas.setFontAndSize(baseFont, 20);
        canvas.showTextAligned(Element.ALIGN_LEFT, "Vishram Sadan, Ansari Nagar, New Delhi - 110029", 100, 800, 0);
        canvas.endText();

        // Add Receipt label
        canvas.beginText();
        canvas.setFontAndSize(BaseFont.createFont(), 16);
        canvas.showTextAligned(Element.ALIGN_CENTER, "RECEIPT", 290, 780, 0);
        canvas.endText();
        
      
        // Add Registration No
        canvas.beginText();
        canvas.setFontAndSize(BaseFont.createFont(), 14);
        canvas.showTextAligned(Element.ALIGN_LEFT, "Reg. No:"+bookingRequest.getRequestId(), 350, 720, 0);
        canvas.endText();
        float yOffset = 20;
        canvas.beginText();
        canvas.setFontAndSize(BaseFont.createFont(), 14);
        canvas.showTextAligned(Element.ALIGN_LEFT, "Date:", 350, 720 - yOffset, 0);
        canvas.endText();

        // Add Date
        canvas.beginText();
        canvas.setFontAndSize(BaseFont.createFont(), 14);
        canvas.showTextAligned(Element.ALIGN_RIGHT, "Receipt No: ", 120, 720, 0);
        canvas.endText();
        
        canvas.beginText();
        canvas.setFontAndSize(BaseFont.createFont(), 14);
        canvas.showTextAligned(Element.ALIGN_RIGHT, "Receipt Type: ",134, 720 - yOffset, 0);
        canvas.endText();

        canvas.beginText();
        canvas.setFontAndSize(baseFont, 16);
        canvas.showTextAligned(Element.ALIGN_RIGHT, "Patient Details", 140, 650, 0);
        canvas.endText();
        
        canvas.beginText();
        canvas.setFontAndSize(BaseFont.createFont(), 14);
        canvas.showTextAligned(Element.ALIGN_RIGHT, "Patient Name :"+bookingRequest.getPatient().getFullname(),135, 620 , 0);
        canvas.endText();
        canvas.beginText();
        canvas.setFontAndSize(BaseFont.createFont(), 14);
        canvas.showTextAligned(Element.ALIGN_RIGHT, "Address :"+bookingRequest.getPatient().getAddress(),100, 610- yOffset , 0);
        canvas.endText();
        canvas.beginText();
        canvas.setFontAndSize(BaseFont.createFont(), 14);
        canvas.showTextAligned(Element.ALIGN_LEFT, "UHID :"+bookingRequest.getPatient().getUhid(),350, 620, 0);
        canvas.endText();
        canvas.beginText();
        canvas.setFontAndSize(BaseFont.createFont(), 14);
        canvas.showTextAligned(Element.ALIGN_LEFT, "Contact No :"+bookingRequest.getPatient().getContactNo(),350, 610- yOffset, 0);
        canvas.endText();

        canvas.beginText();
        canvas.setFontAndSize(baseFont, 16);
        canvas.showTextAligned(Element.ALIGN_RIGHT, "Booking Description", 180,550, 0);
        canvas.endText();
       
        canvas.beginText();
        canvas.setFontAndSize(BaseFont.createFont(), 14);
        canvas.showTextAligned(Element.ALIGN_RIGHT, "Sadan-Room&Category:",190, 520 , 0);
        canvas.endText();
        canvas.beginText();
        canvas.setFontAndSize(BaseFont.createFont(), 14);
        canvas.showTextAligned(Element.ALIGN_RIGHT, "Check-In:",100, 510- yOffset , 0);
        canvas.endText();
        
        canvas.beginText();
        canvas.setFontAndSize(BaseFont.createFont(), 14);
        canvas.showTextAligned(Element.ALIGN_LEFT, "Guests:",350, 520, 0);
        canvas.endText();
        canvas.beginText();
        canvas.setFontAndSize(BaseFont.createFont(), 14);
        canvas.showTextAligned(Element.ALIGN_LEFT, "Check-Out:",350, 510- yOffset, 0);
        canvas.endText();
        canvas.beginText();
        canvas.setFontAndSize(BaseFont.createFont(), 14);
        canvas.showTextAligned(Element.ALIGN_RIGHT, "Charges/day:",125, 460 , 0);
        canvas.endText();
        canvas.beginText();
        canvas.setFontAndSize(BaseFont.createFont(), 14);
        canvas.showTextAligned(Element.ALIGN_LEFT, "Total day:",350, 460 , 0);
        canvas.endText();
        canvas.beginText();
        canvas.setFontAndSize(BaseFont.createFont(), 14);
        canvas.showTextAligned(Element.ALIGN_RIGHT, "Amount:",95, 430 , 0);
        canvas.endText();
        
        canvas.beginText();
        canvas.setFontAndSize(BaseFont.createFont(), 14);
        canvas.showTextAligned(Element.ALIGN_LEFT, "Total Amount:",350, 430 , 0);
        canvas.endText();
        
        canvas.beginText();
        canvas.setFontAndSize(BaseFont.createFont(), 14);
        canvas.showTextAligned(Element.ALIGN_LEFT, "Signature ____________________",320, 370, 0);
        canvas.endText();
        
        canvas.beginText();
        canvas.setFontAndSize(BaseFont.createFont(), 14);
        canvas.showTextAligned(Element.ALIGN_LEFT, "(Manager)",400, 370- yOffset, 0);
        canvas.endText();
        
        canvas.beginText();
        canvas.setFontAndSize(BaseFont.createFont(), 14);
        canvas.showTextAligned(Element.ALIGN_LEFT, "Stamp",320, 320, 0);
        canvas.endText();
        
        imageverified.setAbsolutePosition(xImagePosition, yPosition - imageverified.getScaledHeight());
        canvas.addImage(imageverified);
        canvas.beginText();
        canvas.setFontAndSize(BaseFont.createFont(), 14);
        canvas.showTextAligned(Element.ALIGN_RIGHT, "Generated Date & Time:",200, 270 , 0);
        canvas.endText();
        
        canvas.beginText();
        canvas.setFontAndSize(BaseFont.createFont(), 14);
        canvas.showTextAligned(Element.ALIGN_RIGHT, "IP Address:",120, 240 , 0);
        canvas.endText();
        // Close the document
        document.close();
    }
	
	 public byte[] generatePdf(BookingRequest bookingRequest) throws IOException, DocumentException {
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        createReceiptPdf(baos, bookingRequest); // Pass the bookingRequest to your PDF generation method
	        return baos.toByteArray();
	    }
}
