package aiims.vishram_sadan.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

@Configuration
public class AppConfig {

	@Bean
    PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    AuthenticationProvider customAuthenticationProvider() {
	        return new CustomAuthenticationProvider();
	}

	 @Bean
	    public RestTemplate restTemplate(RestTemplateBuilder builder) {
	        return builder.build();
	    }
	 
	 @Bean
	    public RestTemplate restTemplate2() {
	        return new RestTemplate();
	    }
	 
	 @Bean
	 public ModelMapper getModalMapper() {
		 return new ModelMapper();
	 }
	
	    
	    @Bean
	    public HttpSessionEventPublisher httpSessionEventPublisher() {
	        return new HttpSessionEventPublisher();
	    } 
	    
	    @Bean
	    public ServletContextInitializer servletContextInitializer() {
	        return new ServletContextInitializer() {
	            @Override
	            public void onStartup(ServletContext servletContext) throws ServletException {
	            	// Configure session cookie properties
	                //servletContext.getSessionCookieConfig().setHttpOnly(true);
	                //servletContext.getSessionCookieConfig().setSecure(true);
	            }
	        };
	    }
}
