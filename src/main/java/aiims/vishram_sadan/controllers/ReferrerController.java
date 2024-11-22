package aiims.vishram_sadan.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aiims.vishram_sadan.dto.DemographicDto;
import aiims.vishram_sadan.dto.Response;
import aiims.vishram_sadan.entities.BookingRequest;
import aiims.vishram_sadan.exceptions.ApiException;
import aiims.vishram_sadan.services.NICServices;
import aiims.vishram_sadan.services.ReferrerServices;

@RestController
@RequestMapping("/referrer")
public class ReferrerController {

	@Autowired
	private ReferrerServices referrerServices;
	
	@Autowired
	private NICServices nicservice;
	

	
	@PostMapping("/add-booking-request")
	public ResponseEntity<Object> makeBookingRequest(@RequestBody BookingRequest request,Principal principal){
		try {
			return new ResponseEntity<Object>(referrerServices.addBookingRequest(request,principal.getName()),HttpStatus.CREATED);
		} catch (Exception e) {
			  return new ResponseEntity<Object>(new Response(e.getMessage()),HttpStatus.EXPECTATION_FAILED);
		}
	}
	@GetMapping("/get-all-booking-request")
	public ResponseEntity<Object> getAllBookingRequest(){
		try {
			  return new ResponseEntity<Object>(referrerServices.getAllBookingRequest(),HttpStatus.OK);
		} catch (Exception e) {
			  return new ResponseEntity<Object>(new Response(e.getMessage()),HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	
	@PostMapping("/add-image")
	public ResponseEntity<Object> checkImage(@RequestBody String request){
		String base64Data="";
		try {
			   String[] parts = request.split(",");

		        if (parts.length == 2) {
		             base64Data = parts[1];
		        }
		       
			  return new ResponseEntity<Object>(referrerServices.addImage(base64Data),HttpStatus.CREATED);
		} catch (Exception e) {
			  return new ResponseEntity<Object>(new Response(e.getMessage()),HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	 @GetMapping("/fetchdemographicdata/{uhid}")
	    public ResponseEntity<DemographicDto> getDemographicData(@PathVariable("uhid") String uhid) throws Exception {
		
		 DemographicDto data = nicservice.fetchDemographicDataFromUHID(uhid);
	        if (data != null) {
	            return ResponseEntity.ok(data);
	        } else {
	            return ResponseEntity.noContent().build();
	        }
	    }
	 @PostMapping("/update-priority/{requestId}/{priority}")
	 public ResponseEntity<Object> postMethodName(@PathVariable("requestId")long requestId,@PathVariable("priority")int priority,Principal principal) {
	 	    try {
				return new ResponseEntity<Object>(referrerServices.addPriority(requestId, priority, principal.getName()),HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<Object>(new ApiException(e.getMessage()),HttpStatus.EXPECTATION_FAILED);
			}
	 }
	 
	 
}
