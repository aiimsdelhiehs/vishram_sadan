package aiims.vishram_sadan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aiims.vishram_sadan.dto.AuthRequest;
import aiims.vishram_sadan.dto.ErrorResponse;
import aiims.vishram_sadan.dto.Response;
import aiims.vishram_sadan.entities.User;
import aiims.vishram_sadan.repositories.TokenBlacklistRepository;
import aiims.vishram_sadan.services.SuperAdminServices;
import aiims.vishram_sadan.services.UserService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	
    @Autowired
    private UserService userService;
    
    @Autowired
    private TokenBlacklistRepository tokenBlacklistRepository;
    
    @Autowired
    private SuperAdminServices superAdminServices;
    
    @PostMapping("/get-salt")
    public ResponseEntity<Object> getSalt(@RequestBody User user) {
    	   try {
	    		return new ResponseEntity<Object>(userService.getSalt(user), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<Object>(new Error(e.getMessage()),HttpStatus.EXPECTATION_FAILED);
			}
    }
    
    @GetMapping("/my-roles")
    public ResponseEntity<Object> welcome() {
    	   return new ResponseEntity<Object>(userService.getMyroles(), HttpStatus.OK);
    }
     /*
    @PostMapping("/generateToken")
    public ResponseEntity<Object> authenticateAndGetToken(@RequestBody AuthRequest authRequest, HttpServletRequest request) {
            try {
            	return new ResponseEntity<Object>(authServices.authenticateUser(authRequest,request),HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<Object>(new Response(e.getMessage(),""),HttpStatus.UNAUTHORIZED);
			}
    }*/
    
    @PostMapping("/intialize-super-admin")
    public ResponseEntity<Object> intializeSuperAdmin() {
            try {
            	superAdminServices.intializeSuperAdmin();
            	return new ResponseEntity<Object>(HttpStatus.CREATED);
			} 
            catch (DataIntegrityViolationException ex) {
        		String rootCauseMessage = ex.getRootCause().getMessage();
        	    if (rootCauseMessage.contains("username")) {
                	return new ResponseEntity<Object>(new ErrorResponse("provided username already exist"),HttpStatus.BAD_REQUEST);
                } 
        	    else if (rootCauseMessage.contains("pan_no")) {
                	return new ResponseEntity<Object>(new ErrorResponse("provided PAN No already exist"),HttpStatus.BAD_REQUEST);
                }
                else if (rootCauseMessage.contains("contact_no")) {
                	return new ResponseEntity<Object>(new ErrorResponse("provided Contact No already exist"),HttpStatus.BAD_REQUEST);
                }  
                else {
                	return new ResponseEntity<Object>(new ErrorResponse("provided email Id already exist"),HttpStatus.BAD_REQUEST);
                }
        	}
            catch (Exception e) {
				return new ResponseEntity<Object>(new ErrorResponse(e.getMessage()),HttpStatus.UNAUTHORIZED);
			}
    }
    
    @PostMapping("/logout")
    public ResponseEntity<Object> logout(@RequestHeader("Authorization")String token) {
        try {
        	if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            tokenBlacklistRepository.addToken(token);	
            return new ResponseEntity<Object>(new Response("logout successfully",""),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new Response(e.getMessage(),""),HttpStatus.UNAUTHORIZED);
		}
    }
}
