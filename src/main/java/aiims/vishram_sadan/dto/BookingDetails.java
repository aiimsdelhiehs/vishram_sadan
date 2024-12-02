package aiims.vishram_sadan.dto;

import java.util.Set;

import aiims.vishram_sadan.entities.Patient;
import aiims.vishram_sadan.entities.Payment;
import aiims.vishram_sadan.entities.Room;

public class BookingDetails {

	private Room room;
	private Patient patient;
	private long requestId;
	private Set<Payment> paymentHistory; 
	private String status;
	public BookingDetails() {
		
	}
	public BookingDetails(Room room, Patient patient, long requestId,Set<Payment> paymentHistory, String status) {
		super();
		this.room = room;
		this.patient = patient;
		this.requestId = requestId;
		this.paymentHistory = paymentHistory;
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public long getRequestId() {
		return requestId;
	}
	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}
	public Set<Payment> getPaymentHistory() {
		return paymentHistory;
	}
	public void setPaymentHistory(Set<Payment> paymentHistory) {
		this.paymentHistory = paymentHistory;
	}
	
}
