package aiims.vishram_sadan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private String[] publicURI = new String[]{"/", "/patient_img/**", "/index", "/theme/**","/403", "/terms-and-conditions",  "/WEB-INF/**", "/public/**", "/visitors-count", "/registration","/new_registration","/otp_validation", "/send_otp", "/validate_otp", "/check_otp_validation", "/addingRequest",
									            "/assets/**","/uploaded-files/**", "/font-awesome/**", "/common/**", "/data/**", "/fonts/**","/api/**","/verifyEmail", "/error/**",
									            "/callback/", "/webjars/**", "/error**", "/signup","/aboutus","/term","/rate","/contact", "/landingpage", "/images/**", "/js/**", "/css/**","/local/**", "/vendors/**",
									          };
	@Value("${spring.security.maximum.session}")
	private int maximumSession;
	
	@Autowired
    private AccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	private AuthenticationProvider authProvider;
	
	private final AuthenticationSuccessHandler authSuccessHandler;
    private final AuthenticationFailureHandler authFailureHandler;
    
	public SecurityConfig(AuthenticationSuccessHandler authSuccessHandler,AuthenticationFailureHandler authFailureHandler) {
				this.authSuccessHandler = authSuccessHandler;
				this.authFailureHandler = authFailureHandler;
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		  auth.authenticationProvider(authProvider);
	}

		
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
				http.csrf(csrf->csrf.disable())
					.authorizeHttpRequests((requests) -> requests
						.requestMatchers("/", "/home").permitAll()
						.requestMatchers(publicURI).permitAll()
                        .requestMatchers("/admin/**", "/loginProcess","/swagger-resources/**", "/swagger-ui/**", "/v3/api-docs").hasRole(AppConstants.admin)
                        .requestMatchers("/referrer/**").hasRole(AppConstants.referrer)
                        .requestMatchers("/reception/**").hasRole(AppConstants.receptionist)
                        .requestMatchers("/superadmin/**").hasRole(AppConstants.superadmin)
                        .requestMatchers("/helpdesk/**").hasRole(AppConstants.helpDesk)
                        .requestMatchers("/staff/**").hasRole(AppConstants.staff)
                        .requestMatchers("/common/**", "/attachments/**", "/assets/attachments/images/**", "/assets/attachments/videos/**", "/assets/attachments/audios/**", "/assets/attachments/qr/**").authenticated()
                        .anyRequest().authenticated()
					)
					.formLogin((form) -> form
							  .loginPage("/index").permitAll()
	                          .loginProcessingUrl("/loginProcess")
	                          .permitAll()
	                          .successHandler(authSuccessHandler)
	                          .failureHandler(authFailureHandler)
	                          .usernameParameter("j_username").passwordParameter("j_password")
					)
					.logout((logout) -> logout

							.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	                          .logoutSuccessHandler(myLogoutSuccessHandler())
	                          .invalidateHttpSession(true)
	                          .deleteCookies("JSESSIONID")
	                          .logoutSuccessUrl("/index?logout")
	                          .clearAuthentication(true)
	                )
					.exceptionHandling(handling -> handling.accessDeniedHandler(accessDeniedHandler))
					.sessionManagement(session -> session
			                .sessionFixation().newSession()
			                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			                .invalidSessionUrl("/index?timeout")
			                .maximumSessions(maximumSession)
			                .maxSessionsPreventsLogin(true)
			                .expiredUrl("/index?expired")
			                .sessionRegistry(sessionRegistry())
			        );

		return http.build();
	}
	
	@Bean
	public LogoutSuccessHandler myLogoutSuccessHandler(){
	    return new MyLogoutSuccessHandler();
	}
	
	
	@Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
	
	@Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
