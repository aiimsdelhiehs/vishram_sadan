package aiims.vishram_sadan.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="tbl_userinfo")
public class User implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true)
	private String username;
	
	@Column(unique = true)
	private String contactNo;
	
	@Column(unique = true)
    private String email;
	
	@Column(name = "status", columnDefinition = "varchar(255) default 'NEW'")
	private String status;
	
	@Column(name = "designation", columnDefinition = "varchar(255) default ''")
	private String designation;
	
	@Column(name="password")
	private String password;
	
	@Column(name="old_password1")
	private String oldPassword1;
	
	@Column(name="old_password2")
	private String oldPassword2;
	
	@Column(name="old_password3")
	private String oldPassword3;
	
	@Column(name="salt")
	private String salt;
	
	@Column(name = "enabled")
	private int enabled;
	
	@Column(name="new_otp")
	private int user_otp;
	
	private int InvalidAttempt;
	
	private Date otpSentOn;
	
	@Column(name = "registred_on")
	private Date registredOn;

	private Date lastLogin; 
	
	private String gender;
	
	private String dob;
	
	@Transient
	private String profile;
	
    private String name;
    
    @ElementCollection(fetch =FetchType.EAGER
    		)
    @CollectionTable(name = "authorities", joinColumns = @JoinColumn(name="user_id"))
    @Column(name = "authority_value")
    private List<String> authorities=new ArrayList<>();
    
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;
    
    private String profilePath;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<VishramSadan> sadans =new HashSet<>();
    
    public User() {
    	
    }
    
	public User(String username, String contactNo, String email, String status, String password,
			String salt, int enabled, int invalidLoginAttempt, Date registredOn, Date lastLogin, String name,
			List<String> authorities) {
		super();
		this.username = username;
		this.contactNo = contactNo;
		this.email = email;
		this.status = status;
		this.password = password;
		this.salt = salt;
		this.enabled = enabled;
		this.InvalidAttempt = invalidLoginAttempt;
		this.registredOn = registredOn;
		this.lastLogin = lastLogin;
		this.name = name;
		this.authorities = authorities;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> granted_authorities = new ArrayList<>();
		for(String authority: this.authorities) {
			granted_authorities.add(new SimpleGrantedAuthority(authority));
		}
     	return granted_authorities;
	}
	@Override
	public String getUsername() {
		return username;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getOldPassword1() {
		return oldPassword1;
	}
	public void setOldPassword1(String oldPassword1) {
		this.oldPassword1 = oldPassword1;
	}
	public String getOldPassword2() {
		return oldPassword2;
	}
	public void setOldPassword2(String oldPassword2) {
		this.oldPassword2 = oldPassword2;
	}
	public String getOldPassword3() {
		return oldPassword3;
	}
	public void setOldPassword3(String oldPassword3) {
		this.oldPassword3 = oldPassword3;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public int getUser_otp() {
		return user_otp;
	}

	public void setUser_otp(int user_otp) {
		this.user_otp = user_otp;
	}

	public int getInvalidAttempt() {
		return InvalidAttempt;
	}
	public void setInvalidAttempt(int invalidAttempt) {
		InvalidAttempt = invalidAttempt;
	}
	public Date getOtpSentOn() {
		return otpSentOn;
	}
	public void setOtpSentOn(Date otpSentOn) {
		this.otpSentOn = otpSentOn;
	}
	public Date getRegistredOn() {
		return registredOn;
	}
	public void setRegistredOn(Date registredOn) {
		this.registredOn = registredOn;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public String getProfilePath() {
		return profilePath;
	}

	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
	}

	public Set<VishramSadan> getSadans() {
		return sadans;
	}

	public void setSadans(Set<VishramSadan> sadans) {
		this.sadans = sadans;
	}
	
	public void addVishramSadan(VishramSadan sadan) {
		sadans.add(sadan);
	}
}
