package aiims.vishram_sadan.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
	private BookingRequest request;
	
    private String status;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    private Set<Attendants> attendants =new HashSet<>();

    private String fromDate;
    private String toDate;
    
    private Date checkIn;
    private Date checkOut;
    
    private int totalDays;
    
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "room_id")
    private Room room;
    
    @ManyToOne
    private VishramSadan sadan;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    private Set<Payment> paymentHistory =new HashSet<>();

    @Transient
    private Payment balanceReceipt;
    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Attendants> getAttendants() {
		return attendants;
	}

	public void setAttendants(Set<Attendants> attendants) {
		this.attendants = attendants;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public int getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public VishramSadan getSadan() {
		return sadan;
	}

	public void setSadan(VishramSadan sadan) {
		this.sadan = sadan;
	}

	public BookingRequest getRequest() {
		return request;
	}

	public void setRequest(BookingRequest request) {
		this.request = request;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	public Payment getBalanceReceipt() {
		return balanceReceipt;
	}

	public void setBalanceReceipt(Payment balanceReceipt) {
		this.balanceReceipt = balanceReceipt;
	}

	public Set<Payment> getPaymentHistory() {
		return paymentHistory;
	}

	public void setPaymentHistory(Set<Payment> paymentHistory) {
		this.paymentHistory = paymentHistory;
	}
    
    
}
