package aiims.vishram_sadan.config;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import aiims.vishram_sadan.entities.User;
import aiims.vishram_sadan.repositories.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

	private static final Logger LOGGER = LogManager.getLogger(MyLogoutSuccessHandler.class); 
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
			String ipAddress = request.getHeader("X-Forwarded-For");
			if(ipAddress == null || ipAddress.isEmpty()) {
				ipAddress = request.getHeader("X-Real-IP");
			}
			if(ipAddress == null || ipAddress.isEmpty()) { 
				ipAddress = request.getRemoteAddr();
			}
			if (authentication != null && authentication.getPrincipal() != null) {
	            String username = authentication.getName();
	            LOGGER.info("USER:{} Logout successfully from IP:{}",username,ipAddress);
	            User currentUser = userRepository.findByUsername(username).orElse(null);
	            if(currentUser!=null) {
	            	currentUser.setInvalidAttempt(0);
	            	userRepository.save(currentUser);
	            }
	        }else {
	        	LOGGER.info("USER Logout successfully from IP:{}",request.getRemoteAddr());
	        }
		handle(request, response, authentication);
	}
	
	private void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		StringBuffer targetUrl =  new StringBuffer("/otp_auth");
        if (response.isCommitted()) {
        	return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl.toString());
	}

}
