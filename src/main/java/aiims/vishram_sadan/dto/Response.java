package aiims.vishram_sadan.dto;

import java.util.List;

public class Response {

	private String token;
	private String message;
	private List<String> roles;
	
	public Response(String message) {
		this.message = message;
	}
	public Response() {
		
	}
	public Response(String message,String token) {
		super();
		this.token = token;
		this.message = message;
	}
	
	public Response(String token, List<String> roles) {
		super();
		this.token = token;
		this.roles = roles;
	}
	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
