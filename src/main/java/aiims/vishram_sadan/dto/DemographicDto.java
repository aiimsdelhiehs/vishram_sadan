package aiims.vishram_sadan.dto;

public class DemographicDto {

    private String uhid;
    private String fullname;
    private String address;
    private String contactNo;
	
	
	public DemographicDto( String fullname, String contactNo, String address,String uhid) {
		super();
		this.uhid = uhid;
		this.fullname = fullname;
		this.address = address;
		this.contactNo = contactNo;
	}
	public DemographicDto() {
		// TODO Auto-generated constructor stub
	}
	public String getUhid() {
		return uhid;
	}
	public void setUhid(String uhid) {
		this.uhid = uhid;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

    
    
}
