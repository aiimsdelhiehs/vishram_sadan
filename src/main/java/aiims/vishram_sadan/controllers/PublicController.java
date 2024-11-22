package aiims.vishram_sadan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aiims.vishram_sadan.dto.PasswordReset;
import aiims.vishram_sadan.entities.User;
import aiims.vishram_sadan.payloads.ApiResponse;
import aiims.vishram_sadan.services.PublicServices;





@RestController
@RequestMapping("/public")
public class PublicController {
	
	@Autowired
	private PublicServices publicServices;
	
	
	
	@GetMapping("/")
	public ResponseEntity<Object> getStatus(){ 
		return new ResponseEntity<Object>(new ApiResponse("Running",true),HttpStatus.OK);
	}
	@PostMapping("/get-password-reset-otp/{employeeId}")
	public ResponseEntity<Object> getPasswordResetOTP(@PathVariable("employeeId")String employeeId){
		try {
			User user = publicServices.getPasswordResetOTP(employeeId);
			return new ResponseEntity<Object>(new ApiResponse(" OTP sent on ******"+user.getContactNo().substring(6),true), HttpStatus.OK);				
		} catch (Exception e) {
			return new ResponseEntity<Object>(new ApiResponse(e.getMessage(),false),HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PostMapping("/reset-password-by-otp")
	public ResponseEntity<Object> resetPasswordByOTP(@RequestBody PasswordReset passwordReset){
		try {
			User user = publicServices.resetPasswordByOTP(passwordReset);
			return new ResponseEntity<Object>(user, HttpStatus.OK);				
		} catch (Exception e) {
			return new ResponseEntity<Object>(new ApiResponse(e.getMessage(),false),HttpStatus.EXPECTATION_FAILED);
		}
	}
}

