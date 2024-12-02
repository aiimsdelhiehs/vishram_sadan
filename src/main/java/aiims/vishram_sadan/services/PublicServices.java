package aiims.vishram_sadan.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import aiims.vishram_sadan.config.AppConstants;
import aiims.vishram_sadan.dto.MasterData;
import aiims.vishram_sadan.dto.PasswordReset;
import aiims.vishram_sadan.dto.UserWithNewAndCurrentPassword;
import aiims.vishram_sadan.entities.Category;
import aiims.vishram_sadan.entities.User;
import aiims.vishram_sadan.entities.VishramSadan;
import aiims.vishram_sadan.exceptions.ApiException;
import aiims.vishram_sadan.repositories.CategoryRepository;
import aiims.vishram_sadan.repositories.UserRepository;
import aiims.vishram_sadan.repositories.VishramSadanRepository;
import aiims.vishram_sadan.util.ApplicationConstant;
import aiims.vishram_sadan.util.ApplicationUtility;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PublicServices {

	private static final Logger logger = LogManager.getLogger(PublicServices.class);
	@Autowired
	private VishramSadanRepository vishramSadanRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
    @Autowired
    private UserRepository userRepository;
    
	@Autowired
	private TextMessageServices textMessageServices;
	
    @Autowired
    private PasswordEncoder encoder;

    
	
	public MasterData getMasterData() {
		return new MasterData(vishramSadanRepository.findAll(),categoryRepository.findAll());
	}
	

	public User getPasswordResetOTP(String employeeId) {
		User existingUser = userRepository.findByUsername(employeeId).orElseThrow(()->new ApiException("No user found with employee id: "+employeeId+"."));
		int randomOTP = ApplicationUtility.generateOTP(5);
		existingUser.setUser_otp(randomOTP);
		existingUser.setOtpSentOn(new Date());
		existingUser = userRepository.save(existingUser);
		try {
			textMessageServices.sendMessage(existingUser.getContactNo(), randomOTP+" is the OTP to reset your password of AIIMS Vishram Sadan account");
		    return existingUser;
		} catch (Exception e) {
			throw new ApiException("Text Message services is not working");
		}
	}
	
	public User resetPasswordByOTP(PasswordReset passwordReset) {
		
		System.out.println("sahilll"+passwordReset.getContactNo());
		User existingUser = userRepository.findByUsername(passwordReset.getContactNo()).orElseThrow(()->new ApiException("No user found with employee id: "+passwordReset.getContactNo()+"."));
		String oldPassword1 = (existingUser.getOldPassword1()!=null)?existingUser.getOldPassword1():"";
	    String oldPassword2 = (existingUser.getOldPassword2()!=null)?existingUser.getOldPassword2():"";
		String oldPassword3 = (existingUser.getOldPassword3()!=null)?existingUser.getOldPassword3():"";
		String rawPassword =  passwordReset.getNewPassword();
		
		if( (new Date().getTime() - existingUser.getOtpSentOn().getTime())/60000 > 10 ) {
			 throw new ApiException("Your OTP has been expired");
		}
		else if(encoder.matches(rawPassword, existingUser.getPassword()) ) {
		    throw new ApiException("You can't update your new password as same as your current password");
	    }
		else if(existingUser.getUser_otp() != passwordReset.getOtp()) {
			throw new ApiException("Invalid OTP");
		}
		else if(encoder.matches(rawPassword, oldPassword1) || encoder.matches(rawPassword, oldPassword2) || encoder.matches(rawPassword, oldPassword3) ) {
		    throw new ApiException("You can't update your password as same as your last 3 history of password");
	    }
		else  {
			existingUser.setOldPassword3(existingUser.getOldPassword2());
			existingUser.setOldPassword2(existingUser.getOldPassword1());
			existingUser.setOldPassword1(existingUser.getPassword());
			existingUser.setPassword(encoder.encode(passwordReset.getNewPassword()));
			existingUser.setUser_otp(0);
			existingUser.setOtpSentOn(null);
			existingUser.setStatus(AppConstants.ACTIVE);
			//existingUser.setEnabled(true);
			existingUser = userRepository.save(existingUser);
			return existingUser;
		}
	}
	
	public User updatePassword(UserWithNewAndCurrentPassword user, String username) throws Exception {
		User loggedInUser = userRepository.findByUsername(username).orElseThrow(()->new Exception("User not found"));
    	String oldPassword1 = (loggedInUser.getOldPassword1()!=null)?loggedInUser.getOldPassword1():"";
	    String oldPassword2 = (loggedInUser.getOldPassword2()!=null)?loggedInUser.getOldPassword2():"";
		String oldPassword3 = (loggedInUser.getOldPassword3()!=null)?loggedInUser.getOldPassword3():"";
		   
		String rawPassword =  user.getNewPassword();
		String currentPwd = user.getCurrentPassword();
		
		if(encoder.matches(rawPassword, loggedInUser.getPassword()) ) {
		    throw new Exception("You can't update your new password as same as your current password");
	    }
		else if(encoder.matches(rawPassword, oldPassword1) || encoder.matches(rawPassword, oldPassword2) || encoder.matches(rawPassword, oldPassword3) ) {
		    throw new Exception("You can't update your password as same as your last 3 history of password");
	    }
		else if(encoder.matches(currentPwd, loggedInUser.getPassword()) || loggedInUser.getPassword() == null) { 
			loggedInUser.setOldPassword3(loggedInUser.getOldPassword2());
			loggedInUser.setOldPassword2(loggedInUser.getOldPassword1());
			loggedInUser.setOldPassword1(loggedInUser.getPassword());
			loggedInUser.setPassword(encoder.encode(rawPassword));
			
			loggedInUser.setContactNo(user.getContactNo());
			//loggedInUser.setStatus(ApplicationConstant.active);
			loggedInUser.setEnabled(1);
			//loggedInUser.setInvalidLoginAttempt(0);
			return userRepository.save(loggedInUser);	
		}
		else {
			throw new Exception("Your current password doesn't matched!");
		}
	}
	
	public List<Map<String, Object>> getRoomAvailabilityByCategory() {
        List<VishramSadan> sadans = vishramSadanRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        for (VishramSadan sadan : sadans) {
            Map<String, Object> sadanData = new HashMap<>();
            sadanData.put("sadanName", sadan.getName());

            List<Map<String, Object>> categories = new ArrayList<>();
            for (Category category : sadan.getCategoryList()) {
                Map<String, Object> categoryData = new HashMap<>();
                categoryData.put("categoryName", category.getName());

                long availableCount = sadan.getRooms().stream()
                        .filter(room -> "AVAILABLE".equals(room.getStatus()) && room.getCategory().equals(category))
                        .count();
                long notAvailableCount = sadan.getRooms().stream()
                        .filter(room -> "NOT_AVAILABLE".equals(room.getStatus()) && room.getCategory().equals(category))
                        .count();

                categoryData.put("available", availableCount);
                categoryData.put("notAvailable", notAvailableCount);
                categories.add(categoryData);
            }

            sadanData.put("categories", categories);
            result.add(sadanData);
        }

        return result;
    }
}
