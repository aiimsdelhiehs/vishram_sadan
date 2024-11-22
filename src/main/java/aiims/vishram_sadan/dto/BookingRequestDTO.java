package aiims.vishram_sadan.dto;

import java.util.Date;

import aiims.vishram_sadan.entities.Booking;
import aiims.vishram_sadan.entities.BookingRequest;
import aiims.vishram_sadan.entities.Patient;
import aiims.vishram_sadan.entities.User;
import jakarta.persistence.Lob;

public class BookingRequestDTO {

	private long requestId;
	private int days;
    private String status;
    private Date requestedOn;
    private Date issuedOn;
    private Date closedOn;
    
    @Lob
    private String patientImage;
    
    private User refferedBy;
    
    private User checkInBy;
    
    private User checkoutBy;
    
    private Patient patient;
    
	public BookingRequestDTO() {
		
	}
	public BookingRequestDTO(Booking booking) {
		if(booking != null && booking.getRequest() != null) {
		   BookingRequest request = booking.getRequest();
		   this.requestId = request.getRequestId();
		   this.days = request.getDays();
		   this.status = request.getStatus();
		   this.requestedOn = request.getRequestedOn();
		   this.issuedOn = request.getIssuedOn();
		   this.closedOn = request.getClosedOn();
		   this.refferedBy = request.getRefferedBy();
		   this.checkInBy = request.getCheckInBy();
		   this.checkoutBy = request.getCheckoutBy();
		   this.patient = request.getPatient();
				   
		}
	}
	public long getRequestId() {
		return requestId;
	}
	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getRequestedOn() {
		return requestedOn;
	}
	public void setRequestedOn(Date requestedOn) {
		this.requestedOn = requestedOn;
	}
	public Date getIssuedOn() {
		return issuedOn;
	}
	public void setIssuedOn(Date issuedOn) {
		this.issuedOn = issuedOn;
	}
	public Date getClosedOn() {
		return closedOn;
	}
	public void setClosedOn(Date closedOn) {
		this.closedOn = closedOn;
	}
	public String getPatientImage() {
		return patientImage;
	}
	public void setPatientImage(String patientImage) {
		this.patientImage = patientImage;
	}
	public User getRefferedBy() {
		return refferedBy;
	}
	public void setRefferedBy(User refferedBy) {
		this.refferedBy = refferedBy;
	}
	public User getCheckInBy() {
		return checkInBy;
	}
	public void setCheckInBy(User checkInBy) {
		this.checkInBy = checkInBy;
	}
	public User getCheckoutBy() {
		return checkoutBy;
	}
	public void setCheckoutBy(User checkoutBy) {
		this.checkoutBy = checkoutBy;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}
