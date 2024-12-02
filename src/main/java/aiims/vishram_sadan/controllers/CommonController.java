package aiims.vishram_sadan.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aiims.vishram_sadan.dto.UserWithNewAndCurrentPassword;
import aiims.vishram_sadan.entities.User;
import aiims.vishram_sadan.services.PublicServices;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/common")
public class CommonController {

	@Autowired
	private PublicServices publicServices;
	
	@GetMapping(value = "/get-master-data")
	public ResponseEntity<Object> getMasterData(){
		try {
			return new ResponseEntity<Object>(publicServices.getMasterData(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new Error(e.getMessage()),HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PostMapping("/update-password")
	public ResponseEntity<Object> updatePassword(@RequestBody UserWithNewAndCurrentPassword user,  Principal principal, HttpSession session){
		try {
			User updatedUser = publicServices.updatePassword(user,principal.getName());
			if(updatedUser != null) {
				session.setAttribute("user", updatedUser);
			}
			return new ResponseEntity<Object>(updatedUser, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new Exception(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}
	@GetMapping("/room-availability-by-category")
    public ResponseEntity<Object> getRoomAvailabilityByCategory() {
		try {
			return new ResponseEntity<Object>(publicServices.getRoomAvailabilityByCategory(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new Error(e.getMessage()),HttpStatus.EXPECTATION_FAILED);
		}
    }
}
