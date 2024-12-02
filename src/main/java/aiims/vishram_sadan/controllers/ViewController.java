package aiims.vishram_sadan.controllers;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ViewController {

	
	@GetMapping({"/","/index" })
	public String getLogin() {
		
		return "home";
	}
	
	@GetMapping({"/landingpage" })
	public String getPage() {
		
		return "landingpage";
	}
	
	@GetMapping("/referrer/home")
	public String getAdminHome(Principal p) {
		
		return "superAdmin/home";
	}
	
	@GetMapping("/referrer/changepassword")
	public String getchangePassword() {
		
		return "superAdmin/changepassword";
	}
	
	@GetMapping("/aboutus")
	public String getaboutus() {
		
		return "aboutus";
	}
	
	@GetMapping("/rate")
	public String getratelist() {
		
		return "rateList";
	}
	
	@GetMapping("/contact")
	public String getcontactUs() {
		
		return "contactus";
	}
	
	@GetMapping("/term")
	public String termAndcondition() {
		
		return "terms";
	}
	
	@GetMapping("/reception/home")
	public String getreceptionHome(Principal p) {
		
		return "receptionist/home";
	}
	@GetMapping("/reception/booking")
	public String getmanageBooking(Principal p) {
		
		return "receptionist/manageBooking";
	}
	
	@GetMapping("/reception/checkout")
	public String getReceptionCheckout(Principal p) {
		
		return "receptionist/checkout";
	}
	
	@GetMapping("/reception/changepassword")
	public String getreceptionchangePassword() {
		
		return "receptionist/changepassword";
	}
	
	@GetMapping("/reception/contacts")
	public String getconnectPage() {
		
		return "receptionist/contact";
	}
	
	@GetMapping("/reception/report")
	public String getreport() {
		
		return "receptionist/report";
	}
	
	@GetMapping("/reception/room_check")
	public String getcheckRoom() {
		
		return "receptionist/checkRoom";
	}
	
	@GetMapping("/reception/booking_list")
	public String getbookingList() {
		
		return "receptionist/BookingList";
	}
	
	@GetMapping("/reception/closereport")
	public String getcloseList() {
		
		return "receptionist/closeReport";
	}
	
	@GetMapping("/reception/bookingspdf")
	public String getPDf() {
		
		return "receptionist/PdfReceipt";
	}
	

	@GetMapping("/reception/extenndbookingspdf")
	public String getextendPDf() {
		
		return "receptionist/ExtendPdf";
	}
	
	@GetMapping("/reception/checkOutpdf")
	public String getvhekPDf() {
		
		return "receptionist/checkOutPdf";
	}
}
