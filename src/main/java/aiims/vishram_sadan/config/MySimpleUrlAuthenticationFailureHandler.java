package aiims.vishram_sadan.config;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class MySimpleUrlAuthenticationFailureHandler implements AuthenticationFailureHandler {

	private static final Logger LOGGER = LogManager.getLogger(MySimpleUrlAuthenticationFailureHandler.class); 
	 
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	 
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		    System.err.println("login fail");
		    LOGGER.info("USER with userid:"+request.getParameter("j_username")+" attempt invalid login from IP:"+request.getRemoteAddr());
		    handle(request, response, exception);
	}
	
	 protected void handle(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
	
		    String targetUrl = "/login?error=true";
	        if (response.isCommitted()) {
	        	LOGGER.debug("Response has already been committed. Unable to redirect to "+ targetUrl);
	            return;
	        }
	        HttpSession session = request.getSession();
	        session.setAttribute("login-error", "Login fail:Bad Credential "+exception.getMessage());
	        session.setAttribute("j_username", request.getParameter("username"));
	        session.setAttribute("password", request.getParameter("password"));
	        session.setAttribute("errorLoginMessage", "<span style='color:red;'>Invalid userid or password!</span>");
	        redirectStrategy.sendRedirect(request, response, targetUrl);
	        
	    }
}
