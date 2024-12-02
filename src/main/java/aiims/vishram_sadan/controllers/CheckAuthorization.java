package aiims.vishram_sadan.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckAuthorization {

	@GetMapping("/super_admin/home")
	public ResponseEntity<Object> checkAuthFOrSuperAdmin(){
		try {
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
		}
	}
	@GetMapping("/admin/home")
	public ResponseEntity<Object> checkAuthForAdmin(){
		try {
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
		}
	}
}
