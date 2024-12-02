package aiims.vishram_sadan.dto;

import aiims.vishram_sadan.entities.VishramSadan;

public class VishramSadanDTO {
	private long id;
	private String name;
	private String fullname;
	public VishramSadanDTO() {
		
	}
	public VishramSadanDTO(VishramSadan sadan) {
		this.id = sadan.getId();
		this.name = sadan.getName();
		this.fullname = sadan.getFullname();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
    
}
