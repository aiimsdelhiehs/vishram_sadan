package aiims.vishram_sadan.controllers;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aiims.vishram_sadan.dto.CustomResponse;
import aiims.vishram_sadan.dto.ErrorResponse;
import aiims.vishram_sadan.dto.FaceMatch;
import aiims.vishram_sadan.dto.PaymentReceipt;
import aiims.vishram_sadan.dto.Response;
import aiims.vishram_sadan.entities.Booking;
import aiims.vishram_sadan.entities.BookingRequest;
import aiims.vishram_sadan.entities.Contact;
import aiims.vishram_sadan.entities.Reminder;
import aiims.vishram_sadan.services.PdfGenerator;
import aiims.vishram_sadan.services.ReceptionistServices;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/reception")
public class ReceptionistController {

	@Autowired
	private ReceptionistServices receptionistServices;
	
	@PostMapping("/verify-date/{date}")
    public ResponseEntity<Object> verifyDate(@PathVariable("date")String date){
    	try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date clientDateTime = sdf.parse(date);	
			Date currentDateTime = new Date();
			LocalDate clientDate = clientDateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		    LocalDate currentDate = currentDateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		    if (clientDate.isEqual(currentDate)) {
				return new ResponseEntity<Object>(HttpStatus.OK);
			}
		    else {
		    	return new ResponseEntity<Object>(new CustomResponse("Server date not matched with your system date"),HttpStatus.EXPECTATION_FAILED);
		    }
		} 
		catch (ParseException e) {
			return new ResponseEntity<>(new CustomResponse("Invalid date"),HttpStatus.BAD_REQUEST);
		}
    	catch (Exception e) {
			return new ResponseEntity<>(new CustomResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
		}
    }

	@GetMapping("/get-booking-request")
	public ResponseEntity<Object> getBookingRequest(HttpServletRequest request, Principal principal) {
		try {

			return new ResponseEntity<Object>(receptionistServices.getBookingRequest(principal.getName()),
					HttpStatus.OK);
		}

		catch (Exception e) {
			return new ResponseEntity<Object>(new ErrorResponse(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping("/get-all-booking-request")
	public ResponseEntity<Object> getAllBookingRequest(HttpServletRequest request, Principal principal) {
		try {

			return new ResponseEntity<Object>(receptionistServices.getAllBookingRequest(principal.getName()),
					HttpStatus.OK);
		}

		catch (Exception e) {
			return new ResponseEntity<Object>(new ErrorResponse(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}

	/*
	@GetMapping
	public ResponseEntity<Object> getPaymentreceipt(){
		
	}*/
	
	@GetMapping("/get-booking-record/{searchBy}/{value}")
	public ResponseEntity<Object> getBookingRecord(HttpServletRequest request,
			@PathVariable("searchBy") String searchBy, @PathVariable("value") Long value, Principal principal) {
		try {

			return new ResponseEntity<Object>(
					receptionistServices.getBookingRecord(principal.getName(), searchBy, value), HttpStatus.OK);
		}

		catch (Exception e) {
			return new ResponseEntity<Object>(new ErrorResponse(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@GetMapping("/get-master-data")
	public ResponseEntity<Object> getMyMasterData(HttpServletRequest request, Principal principal) {
		try {

			return new ResponseEntity<Object>(receptionistServices.getMasterData(principal.getName()), HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<Object>(new ErrorResponse(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PostMapping("/send-waiting-reminder/{requestId}")
	public ResponseEntity<Object> sendWaitingReminder(HttpServletRequest request, @RequestBody Reminder reminder,
			@PathVariable("requestId") long requestId, Principal principal) {
		try {
			BookingRequest book = receptionistServices.sendWaitingReminder(principal.getName(), requestId, reminder);
			if (book != null) {
				request.setAttribute("book", book);
			}
			return new ResponseEntity<Object>(book, HttpStatus.OK);

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<Object>(new Response(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PostMapping("/update-booking-status-not-interested/{requestId}")
	public ResponseEntity<Object> updateBookingStatusNotInterested(HttpServletRequest request,
			@PathVariable("requestId") long requestId, Principal principal) {
		try {

			return new ResponseEntity<Object>(
					receptionistServices.updateBookingStatusToNotInterested(principal.getName(), requestId),
					HttpStatus.OK);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<Object>(new Response(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PostMapping("/proceed-booking-confirmation")
	public ResponseEntity<Object> proceedBookingConfirmation(HttpServletRequest request,
			@RequestBody BookingRequest bookingRequest, Principal principal) {
		try {
			return new ResponseEntity<Object>(
					receptionistServices.proceedBookingConfirmation(principal.getName(), bookingRequest),
					HttpStatus.OK);

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<Object>(new Response(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping("/get-payment-reciept")
	public ResponseEntity<Object> getPaymentReceipt(@RequestParam(name = "fromDate", required = false) String fromDate,
	        @RequestParam(name = "toDate", required = false) String toDate) {
		try {
			long txnId = 0;
		    List<PaymentReceipt> receipts = receptionistServices.getReceiptsBetweenDates(txnId,fromDate, toDate);

	        if (receipts.isEmpty()) {
	            // Return a 404 Not Found response with a message if no records are found
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No records found for the given date range.");
	        }
	        return new ResponseEntity<Object>(receipts,HttpStatus.OK);
	      
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<Object>(new Response(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping("/get-close-request")
	public ResponseEntity<Object> getCloseRequest(HttpServletRequest request) {
		try {
			
		    List<BookingRequest> requested = receptionistServices.getCloseRequestBetweenDates(request.getParameter("fromDate"), request.getParameter("toDate"));

	        if (requested.isEmpty()) {
	            // Return a 404 Not Found response with a message if no records are found
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No records found for the given date range.");
	        }
	        return new ResponseEntity<Object>(requested,HttpStatus.OK);
	      
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<Object>(new Response(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PostMapping("/proceed-booking-check-out/{requestId}")
	public ResponseEntity<Object> proceedBookingCheckOut(HttpServletRequest request,
			@RequestBody List<Booking> bookings, @PathVariable("requestId") long requestId, Principal principal) {
		try {
			
			return new ResponseEntity<Object>(receptionistServices.proceedCheckOut(principal.getName(), bookings, requestId), HttpStatus.OK);
         } catch (Exception e) {
			return new ResponseEntity<Object>(new Response(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PostMapping("/extend-booking/{requestId}")
	public ResponseEntity<Object> extendBooking(HttpServletRequest request,
			@RequestBody List<Booking> bookings, @PathVariable("requestId") long requestId, Principal principal) {
		try {
			
			return new ResponseEntity<Object>(receptionistServices.extendBooking(principal.getName(), bookings, requestId), HttpStatus.OK);
         } catch (Exception e) {
			return new ResponseEntity<Object>(new Response(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@GetMapping("/get-booking-history")
	public ResponseEntity<Object> getBookingHistory(HttpServletRequest request, Principal principal) {
		try {

			return new ResponseEntity<Object>(receptionistServices.getBookingRequestHistory(principal.getName()),
					HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(new Response(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PostMapping("/contact")
	public ResponseEntity<Object> submitContact(@RequestBody Contact contact, Principal principal) {
		try {

			return new ResponseEntity<Object>(receptionistServices.submitContact(contact,principal.getName()),
					HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(new Response(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}
	

	@PostMapping("/match-face")
	public ResponseEntity<Object> matchface(@RequestBody FaceMatch data) {
		try {
			return new ResponseEntity<Object>(receptionistServices.matchImages(data), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new Response(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping("/getConfirmRequest")
	public ResponseEntity<Object> getAllBookingRequest(){
		try {
			  return new ResponseEntity<Object>(receptionistServices.getAllBookingRequest(),HttpStatus.OK);
		} catch (Exception e) {
			  return new ResponseEntity<Object>(new Response(e.getMessage()),HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	
}