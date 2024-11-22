package aiims.vishram_sadan.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import aiims.vishram_sadan.entities.Attendants;
import aiims.vishram_sadan.entities.Booking;

public class BookingDTO {
	
	private long id;
	private String status;
	private Date fromDate;
    private Date toDate;
	private RoomDTO room;
	private VishramSadanDTO sadan;
	private Set<Attendants> attendants= new HashSet<>();
	
	public BookingDTO() {
		
	}
	public BookingDTO(Booking booking) {
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if(booking != null) {
			this.id = booking.getId();
			this.status = booking.getStatus();
			this.attendants = booking.getAttendants();
			this.room = new RoomDTO(booking.getRoom());
			this.sadan = new VishramSadanDTO(booking.getSadan());
			try {
				this.fromDate=formatter.parse(booking.getFromDate());
				this.toDate=formatter.parse(booking.getToDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    }
	}
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
	public RoomDTO getRoom() {
		return room;
	}
	public void setRoom(RoomDTO room) {
		this.room = room;
	}
	public VishramSadanDTO getSadan() {
		return sadan;
	}
	public void setSadan(VishramSadanDTO sadan) {
		this.sadan = sadan;
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
	public Set<Attendants> getAttendants() {
		return attendants;
	}
	public void setAttendants(Set<Attendants> attendants) {
		this.attendants = attendants;
	}
	
}
