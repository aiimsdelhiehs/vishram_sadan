package aiims.vishram_sadan.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import aiims.vishram_sadan.dto.AuthRequest;
import aiims.vishram_sadan.dto.UserInfoDetails;
import aiims.vishram_sadan.entities.User;
import aiims.vishram_sadan.repositories.UserRepository;
import aiims.vishram_sadan.util.ApplicationConstant;
import aiims.vishram_sadan.util.ApplicationUtility;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService implements UserDetailsService{
	
	private static final Logger logger = LogManager.getLogger(UserService.class);
	@Value("${app.resources.path}")
	private String resourcePath;
	
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	Optional<User> userDetail = userRepository.findByUsername(username);
    	System.out.println(userDetail.isPresent());
    	User existingUser = userDetail.orElse(null);
    	if(existingUser != null) {
    		if(existingUser.getAuthorities() != null) {
    			if(existingUser.getAuthorities().size() ==0) {
    			   List<String> roles = new ArrayList<String>();
    			   roles.add(ApplicationConstant.roleUser);
    			   existingUser.setAuthorities(roles);
    			   userRepository.save(existingUser);
    			}
    		}
    	}
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found" + username));
    }
    
    public User getUser(String username) {
    	return userRepository.findByUsername(username).orElse(null);
    }
    
    public User saveUser(User user) {
    	return userRepository.save(user);
    }

	public List<String> getMyroles() {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       User loggedInUser = userRepository.findByUsername(authentication.getName()).orElse(null);
       if(loggedInUser != null)
          return loggedInUser.getAuthorities().stream()
                  .map(GrantedAuthority::getAuthority)
                  .collect(Collectors.toList());
       else 
    	   return new ArrayList<>();
	}

	public AuthRequest getSalt(User user) throws Exception {
		   User requestingUser = userRepository.findByUsername(user.getUsername()).orElseThrow(()->new Exception("Bad Credentials"));
		   AuthRequest auth = new AuthRequest();
		   auth.setSalt(requestingUser.getSalt());
		   return auth;
	}

	public User addAdmin(User userInfo) throws Exception {
		    String randomPassword = ApplicationUtility.generatePassword(6);
		    
		    List<String> authorities = new ArrayList<String>();
		    authorities.add(ApplicationConstant.roleAdmin);
		    authorities.add(ApplicationConstant.roleUser);
		    userInfo.setRegistredOn(new Date());
	    	userInfo.setStatus(ApplicationConstant.statusNew);
	    	userInfo.setEnabled(1);
	    	userInfo.setInvalidAttempt(0);
	    	userInfo.setLastLogin(new Date());
	    	userInfo.setAuthorities(authorities);
	    	userInfo.setPassword(encoder.encode(randomPassword));
	    	userInfo.setSalt(null);
	    	userInfo.setUsername((System.currentTimeMillis()/1000)+"");
	    	userInfo.setProfilePath(writeFileToPath(userInfo.getProfile(), "Profile", userInfo.getUsername()));
	    	User newAdmin = userRepository.save(userInfo);
	    	System.out.println("username: "+newAdmin.getUsername()+"password: "+randomPassword);
	        return newAdmin;	
	}
	
	private String writeFileToPath(String base64String, String folder, String userId) throws IOException {
		    String requiredBase64String = base64String.split(",")[1];
		    byte[] imageBytes = Base64.getDecoder().decode(requiredBase64String);
		    StringBuffer sb = new StringBuffer(resourcePath);
		    sb.append(folder).append("/").append(userId).append(".png");
		    String fileName = sb.toString();
		    Path path = Paths.get(fileName);
		    if (!Files.exists(path.getParent())) {
	            Files.createDirectories(path.getParent());
	        }
		    Files.write(path, imageBytes);
		    return fileName;
	}

}
