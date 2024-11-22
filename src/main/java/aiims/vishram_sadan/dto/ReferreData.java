package aiims.vishram_sadan.dto;

import java.util.List;

import aiims.vishram_sadan.entities.BookingRequest;

public class ReferreData {
	
	private List<BookingRequest> closeBooking;
	private List<BookingRequest> confirmBooking;
	private List<BookingRequest> pendingBooking;
	public ReferreData() {
		
	}
	public ReferreData(List<BookingRequest> closeBooking, List<BookingRequest> confirmBooking,
			List<BookingRequest> pendingBooking) {
		super();
		this.closeBooking = closeBooking;
		this.confirmBooking = confirmBooking;
		this.pendingBooking = pendingBooking;
	}
	public List<BookingRequest> getCloseBooking() {
		return closeBooking;
	}
	public void setCloseBooking(List<BookingRequest> closeBooking) {
		this.closeBooking = closeBooking;
	}
	public List<BookingRequest> getConfirmBooking() {
		return confirmBooking;
	}
	public void setConfirmBooking(List<BookingRequest> confirmBooking) {
		this.confirmBooking = confirmBooking;
	}
	public List<BookingRequest> getPendingBooking() {
		return pendingBooking;
	}
	public void setPendingBooking(List<BookingRequest> pendingBooking) {
		this.pendingBooking = pendingBooking;
	}
	
	

}
