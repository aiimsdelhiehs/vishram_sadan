package aiims.vishram_sadan.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import aiims.vishram_sadan.entities.Payment;

public class PaymentReceipt {
       
	   private BookingRequestDTO request;
       private String type;
       private long txnId;
       private int rate;
   	   private Date txnDate;
       private Date fromDate;
       private Date toDate;
       private Set<PaymentDTO> payments;
       
       public PaymentReceipt(List<Payment> payments) {
    	      if(payments.size()>0) {
    	    	 Payment payment = payments.get(0);
    	    	 this.type = payment.getReceiptType();
    	    	 this.txnDate = payment.getTxnDate();
    	    	 this.txnId = payment.getTxnId();
    	    	 this.fromDate = payment.getFromDate();
    	    	 this.toDate = payment.getToDate();
    	    	 this.rate = payment.getRate();
    	    	 this.request = new BookingRequestDTO(payment.getBooking());
    	    	 this.payments = new HashSet<>();
    	    	 for(Payment pay : payments) {
    	    		 if(pay.getBooking()==null) {
    	    			 System.out.println(pay.getId());
    	    		 }else {
    	    			 System.out.println("rq"+pay.getBooking().getRequest());
    	    		 }
    	    		 this.payments.add(new PaymentDTO(pay));
    	    	 }
    	      }
       }

	public BookingRequestDTO getRequest() {
		return request;
	}

	public void setRequest(BookingRequestDTO request) {
		this.request = request;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getTxnId() {
		return txnId;
	}

	public void setTxnId(long txnId) {
		this.txnId = txnId;
	}

	public Date getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(Date txnDate) {
		this.txnDate = txnDate;
	}

	public Set<PaymentDTO> getPayments() {
		return payments;
	}

	public void setPayments(Set<PaymentDTO> payments) {
		this.payments = payments;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
       
}
