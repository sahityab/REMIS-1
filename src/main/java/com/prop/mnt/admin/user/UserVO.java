package com.prop.mnt.admin.user;

import java.io.Serializable;
import java.sql.Timestamp;



//@Entity
//@Table(name="users")
public class UserVO implements Serializable {
	private static final long serialVersionUID = 323951818107991022L;
	//@Id 
	//@GeneratedValue
	//@Column(name = "id")
	private Integer id;
	
	//@Column(name = "USERID")
	private String userId;
	//@Column(name = "FIRST_NAME")
	private String firstName;
	//@Column(name = "LAST_NAME") 
	private String lastName;
	//@Column(name = "PASSWORD") 
	private String password;
	private String confirmPassword;
	//@Column(name = "PASSWORD_DATE")
	private String passwordDate;
	//@Column(name = "CONT_NO")
	private String contNo;
	//@Column(name = "ADDRESS1")  
	private String address1;
	//@Column(name = "ADDRESS2") 
	 private String address2;
	//@Column(name = "CITY") 
	 private String city;
	//@Column(name = "STATE") 
	 private String state;
	//@Column(name = "COUNTRY") 
	 private String country;
	//@Column(name = "ZIP") 
	 private String zip;
	//@Column(name = "EMAIL") 
	 private String email;
	//@Column(name = "LOGIN_TIME") 
	 private Timestamp loginTime;
	//@Column(name = "LOGOUT_TIME") 
	 private Timestamp logoutTime;
	//@Column(name = "STATUS") 
	 private String status;
	 private String relation;
	 
	 private String newUserId;
	
	 private String fullName;
	 
	 
	 public UserVO(){}
	 
	 
	 
	public UserVO(Integer id, String fullName) {
		//super();
		this.id = id;
		this.fullName = fullName;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordDate() {
		return passwordDate;
	}
	public void setPasswordDate(String passwordDate) {
		this.passwordDate = passwordDate;
	}
	public String getContNo() {
		return contNo;
	}
	public void setContNo(String contNo) {
		this.contNo = contNo;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Timestamp getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}
	public Timestamp getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(Timestamp logoutTime) {
		this.logoutTime = logoutTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNewUserId() {
		return newUserId;
	}
	public void setNewUserId(String newUserId) {
		this.newUserId = newUserId;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getFullName() {
		return firstName+" "+lastName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserVO [userId=");
		builder.append(userId);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", password=");
		builder.append(password);
		builder.append(", passwordDate=");
		builder.append(passwordDate);
		builder.append(", contNo=");
		builder.append(contNo);
		builder.append(", address1=");
		builder.append(address1);
		builder.append(", address2=");
		builder.append(address2);
		builder.append(", city=");
		builder.append(city);
		builder.append(", state=");
		builder.append(state);
		builder.append(", country=");
		builder.append(country);
		builder.append(", zip=");
		builder.append(zip);
		builder.append(", email=");
		builder.append(email);
		builder.append(", loginTime=");
		builder.append(loginTime);
		builder.append(", logoutTime=");
		builder.append(logoutTime);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}
	}
