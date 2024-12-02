package aiims.vishram_sadan.config;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import aiims.vishram_sadan.util.ApplicationConstant;

@Aspect
@Component
public class LoggingAspect {
	    
	    private static final Logger logs = LogManager.getLogger(LoggingAspect.class);
	
	    @AfterThrowing(
	        pointcut = "execution(* aiims.vishram_sadan.services.*.*(..))",
	        throwing = "ex"
	    )
	    public void logException(JoinPoint joinPoint, Exception ex) {
	    	String methodName = joinPoint.getSignature().getName();
	        Object[] args = joinPoint.getArgs();
	        logs.error("User:{}, got Exception from:{}, with Input parameter values:{}, and getting exception as:{}",getLoggedInUsername(), methodName, Arrays.toString(args), ex.getMessage());
	    }
	    
	    @Before("execution(* aiims.vishram_sadan.controllers.*.*(..))")
	    public void logBefore(JoinPoint joinPoint) {
	    	String methodName = joinPoint.getSignature().toShortString();
	        String ipAddress = getClientIpAddress();
	    	if (isPostRequest(joinPoint)) {
	    		Object[] args = joinPoint.getArgs();
		        logs.info("User:{} is calling:{}, from IP:{}, with Input parameter values:{}",getLoggedInUsername(), methodName,ipAddress, Arrays.toString(args));
	        }
	    }

	    @After("execution(* aiims.vishram_sadan.controllers.*.*(..))")
	    public void logAfter(JoinPoint joinPoint) {
	        String methodName = joinPoint.getSignature().toShortString();
	        String ipAddress = getClientIpAddress();
	        if (isPostRequest(joinPoint)) {
	        	logs.info("User:{} calling of:{} has been completed, from IP:{}",getLoggedInUsername(), methodName,ipAddress);
	        }
	    }
	    private boolean isPostRequest(JoinPoint joinPoint) {
	        try {
	        	jakarta.servlet.http.HttpServletRequest request =  ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	            String httpMethod = request.getMethod();
	            return (httpMethod.equals(ApplicationConstant.postMethod))?true:false;
			} catch (SecurityException e) {
				return false;
			}
	    }
	    private String getClientIpAddress() {
	        jakarta.servlet.http.HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	        String ipAddress = request.getHeader("X-Forwarded-For");
			if(ipAddress == null || ipAddress.isEmpty()) {
				ipAddress = request.getHeader("X-Real-IP");
			}
			if(ipAddress == null || ipAddress.isEmpty()) { 
				ipAddress = request.getRemoteAddr();
			}
	        return ipAddress;
	    }
	    private String getLoggedInUsername() {
	    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    	if (authentication != null && authentication.isAuthenticated()) { 
	    		return authentication.getName();
	    	}else {
	    		return "Anonymous";
	    	}
	    }
}
