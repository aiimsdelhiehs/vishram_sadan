package aiims.vishram_sadan.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class PasswordReset {
	
	@NotEmpty(message="Please provide a Contact Number.")
	@Pattern(regexp = "^[0-9]{10}$",message = "Contact Number must be of 10 length with no special characters and alphabet.")	
	private String contactNo;
	
	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	@NotEmpty(message="Please provide a Concern.")
	private String newPassword;
	
	@Min(value = 1, message = "Please select a Department.")
	private int otp;

	

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}
}
