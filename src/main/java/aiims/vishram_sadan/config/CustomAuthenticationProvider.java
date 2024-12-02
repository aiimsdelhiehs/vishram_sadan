package aiims.vishram_sadan.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import aiims.vishram_sadan.entities.User;
import aiims.vishram_sadan.repositories.UserRepository;

public class CustomAuthenticationProvider  implements AuthenticationProvider {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepo;
	
	@Value("${application.maximum.invalid.login.attempt}")
	private int totalInvalidAttemptAllow;
	
	@Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		    
		    String username = authentication.getName();
		    String password = authentication.getCredentials().toString();
		    User currentUser = userRepo.findByUsername(username).orElse(null);
	        if (currentUser != null) { 
	        	    
	        	    
	        	    if((currentUser.getStatus().contentEquals(AppConstants.newAccount)||currentUser.getStatus().contentEquals(AppConstants.ACTIVE)) && encoder.matches(password, currentUser.getPassword())) {
                    	
                    	
	        	    	List<GrantedAuthority> authorities = new ArrayList<>();
                    	for(GrantedAuthority authority:currentUser.getAuthorities()) {
                    		
                    		authorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
                    	}
	 		        	return new UsernamePasswordAuthenticationToken(currentUser.getUsername(), password, authorities); 
                    }
	        	    else if(currentUser.getStatus().equals(AppConstants.disabled)) {
		        	   throw new InternalAuthenticationServiceException("User account is currently DISABLED. kindly contact to ADMIN.");
		        	}
		        	else {
		        	   throw new InternalAuthenticationServiceException(AppConstants.badCredentials); 		
		        	}
	        }
	        else{
	        	throw new InternalAuthenticationServiceException("User NOT found you may login with OTP");
	        }
	}
	
}
