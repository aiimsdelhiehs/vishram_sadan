package aiims.vishram_sadan.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class BookingRequest {
	
	@Id
	private long requestId;
	private float priority;
	private int days;
    private String status;
    private Date requestedOn;
    private Date issuedOn;
    private Date closedOn;
    private Date prioritySetOn;
    private Date lastUpdate;
    
    @Lob
    @Transient
    private String patientImage;
    
    @ManyToOne
    private User refferedBy;
    
    @ManyToOne
    private User prioritySetBy;
    
    @ManyToOne
    private User checkInBy;
    
    @ManyToOne
    private User checkoutBy;
    
    @ManyToOne
    private Patient patient;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<Attendants> attendants =new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<Reminder> reminders =new HashSet<>();
    
    private String remarks;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
    	      name = "booking_sadan",
    	      joinColumns = @JoinColumn(name = "booking_id"),
    	      inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<VishramSadan> sadans =new HashSet<>();
    
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Category> preferences =new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Booking> bookings =new HashSet<>();
    
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
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Set<Attendants> getAttendants() {
		return attendants;
	}
	public void setAttendants(Set<Attendants> attendants) {
		this.attendants = attendants;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Set<VishramSadan> getSadans() {
		return sadans;
	}
	public void setSadans(Set<VishramSadan> sadans) {
		this.sadans = sadans;
	}
	public Set<Category> getPreferences() {
		return preferences;
	}
	public void setPreferences(Set<Category> preferences) {
		this.preferences = preferences;
	}
	public Set<Booking> getBookings() {
		return bookings;
	}
	public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}
	public Date getIssuedOn() {
		return issuedOn;
	}
	public void setIssuedOn(Date issuedOn) {
		this.issuedOn = issuedOn;
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
	public Date getClosedOn() {
		return closedOn;
	}
	public void setClosedOn(Date closedOn) {
		this.closedOn = closedOn;
	}
	public Set<Reminder> getReminders() {
		return reminders;
	}
	public void setReminders(Set<Reminder> reminders) {
		this.reminders = reminders;
	}
	public String getPatientImage() {
		return patientImage;
	}
	public void setPatientImage(String patientImage) {
		this.patientImage = patientImage;
	}
	public float getPriority() {
		return priority;
	}
	public void setPriority(float priority) {
		this.priority = priority;
	}
	public Date getPrioritySetOn() {
		return prioritySetOn;
	}
	public void setPrioritySetOn(Date prioritySetOn) {
		this.prioritySetOn = prioritySetOn;
	}
	public User getPrioritySetBy() {
		return prioritySetBy;
	}
	public void setPrioritySetBy(User prioritySetBy) {
		this.prioritySetBy = prioritySetBy;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
}
