package aiims.vishram_sadan.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Patient {
	@Id
	private long uhid;
	private String fullname;
    private long contactNo;
    private String address;
    private String requestedBy;
    
	public long getUhid() {
		return uhid;
	}
	public void setUhid(long uhid) {
		this.uhid = uhid;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public long getContactNo() {
		return contactNo;
	}
	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRequestedBy() {
		return requestedBy;
	}
	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}
    
}
