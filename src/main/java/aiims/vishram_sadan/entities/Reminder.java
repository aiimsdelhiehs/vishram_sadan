package aiims.vishram_sadan.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Reminder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date sentOn;
	private Date waitingtill;
	
	@Transient
	private int waitingHour;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getSentOn() {
		return sentOn;
	}
	public void setSentOn(Date sentOn) {
		this.sentOn = sentOn;
	}
	public Date getWaitingtill() {
		return waitingtill;
	}
	public void setWaitingtill(Date waitingtill) {
		this.waitingtill = waitingtill;
	}
	public int getWaitingHour() {
		return waitingHour;
	}
	public void setWaitingHour(int waitingHour) {
		this.waitingHour = waitingHour;
	}
	
}
