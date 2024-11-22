package aiims.vishram_sadan.config;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import aiims.vishram_sadan.entities.User;
import aiims.vishram_sadan.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Component
public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private static final Logger LOGGER = LogManager.getLogger(MySimpleUrlAuthenticationSuccessHandler.class); 

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	
	@Autowired
	private UserRepository userRepo;
	
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)throws IOException {
            LOGGER.info("USER with userid:"+request.getParameter("j_username")+" Logged in successfully from IP:"+request.getRemoteAddr());
            HttpSession session = request.getSession();
            System.out.println("s1"+session.getId());
            System.out.println("s2"+request.getSession(true));
            User loggedInUser = userRepo.findByUsername(request.getParameter("j_username")).orElse(null);
            if(loggedInUser != null && AppConstants.NEW_USER_STATUS.equals(loggedInUser.getStatus())) {
               loggedInUser.setStatus(AppConstants.ACTIVE_USER_STATUS);
               loggedInUser = userRepo.save(loggedInUser);
            }else if(loggedInUser == null) {
            	loggedInUser = new User();
            }
            session.setAttribute("user", loggedInUser);
            System.out.println("Form success "+authentication);
            if(authentication != null) {
                 System.out.println("val "+authentication.getName()+" "+authentication.getPrincipal());
                 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                 System.out.println("auth "+auth);
                 SecurityContextHolder.getContext().setAuthentication(authentication);
                 
            }else {
            	System.out.println("auth null");
            }
            handle(request, response, authentication);
            
    }
  	    
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication ) throws IOException {
    	  StringBuffer targetUrl =  new StringBuffer("/landingpage");
	        if (response.isCommitted()) {
	        	return;
	        }
       redirectStrategy.sendRedirect(request, response, targetUrl.toString());
    }

}
