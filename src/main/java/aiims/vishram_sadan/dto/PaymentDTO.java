package aiims.vishram_sadan.dto;

import java.util.Date;

import aiims.vishram_sadan.entities.Payment;

public class PaymentDTO {
	
	private int amount;
	private int rate;
	
    
    private String txnType;
	private BookingDTO booking;
	
	public PaymentDTO() {
		
	}
	public PaymentDTO(Payment payment) {
		
	   this.amount = payment.getAmount();
	   this.rate = payment.getRate();
	  
	   this.txnType = payment.getTxnType();
	   this.booking = new BookingDTO(payment.getBooking());
	}
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	
	public String getTxnType() {
		return txnType;
	}
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}
	public BookingDTO getBooking() {
		return booking;
	}
	public void setBooking(BookingDTO booking) {
		this.booking = booking;
	}
	
	
}
