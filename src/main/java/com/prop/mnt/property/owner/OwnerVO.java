package com.prop.mnt.property.owner;


//@Entity
//@Table(name="OWNER")
public class OwnerVO {
	
	//@Id 
		//@GeneratedValue
		//@Column(name = "id")
		//private int id;
	//@Column(name = "OWN_Code") 
	private Integer ownCode;
	//@Column(name = "OWN_F_Name") 
	 private String ownFName;
	//@Column(name = "OWN_M_Name") 
	 private String ownMName;
	//@Column(name = "OWN_L_Name") 
	 private String ownLName;
	//@Column(name = "OWN_TYPE") 
	 private String ownType;
	//@Column(name = "OWN_SSN") 
	 private Integer ownSsn;
	//@Column(name = "ADDRESS1") 
	 private String address1;
	//@Column(name = "ADDRESS2") 
	 private String address2;
	//@Column(name = "CITY") 
	 private String city;
	//@Column(name = "STATE_CD") 
	 private String stateCd;
	//@Column(name = "COUNTRY") 
	 private String country;
	//@Column(name = "ZIP") 
	 private String zip;
	//@Column(name = "PHONE") 
	 private Long phone;
	//@Column(name = "FAX") 
	 private Long fax;
	//@Column(name = "email") 
	 private String email;
	//@Column(name = "STATUS") 
	 private String status;
	 
	 private String fullName;
	public Integer getOwnCode() {
		return ownCode;
	}
	public void setOwnCode(Integer ownCode) {
		this.ownCode = ownCode;
	}
	public String getOwnFName() {
		return ownFName;
	}
	public void setOwnFName(String ownFName) {
		this.ownFName = ownFName;
	}
	public String getOwnMName() {
		return ownMName;
	}
	public void setOwnMName(String ownMName) {
		this.ownMName = ownMName;
	}
	public String getOwnLName() {
		return ownLName;
	}
	public void setOwnLName(String ownLName) {
		this.ownLName = ownLName;
	}
	public String getOwnType() {
		return ownType;
	}
	public void setOwnType(String ownType) {
		this.ownType = ownType;
	}
	public Integer getOwnSsn() {
		return ownSsn;
	}
	public void setOwnSsn(Integer ownSsn) {
		this.ownSsn = ownSsn;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getFullName() {
		return ownFName+" "+ownMName+" "+ownLName;
	}
	public void setFullName(String fullName) {
		this.fullName = ownFName+" "+ownMName+" "+ownLName;
	}
	public String getStateCd() {
		return stateCd;
	}
	public void setStateCd(String stateCd) {
		this.stateCd = stateCd;
	}
	
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public Long getFax() {
		return fax;
	}
	public void setFax(Long fax) {
		this.fax = fax;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OwnerVO [ownCode=");
		builder.append(ownCode);
		builder.append(", ownFName=");
		builder.append(ownFName);
		builder.append(", ownMName=");
		builder.append(ownMName);
		builder.append(", ownLName=");
		builder.append(ownLName);
		builder.append(", ownType=");
		builder.append(ownType);
		builder.append(", ownSsn=");
		builder.append(ownSsn);
		builder.append(", address1=");
		builder.append(address1);
		builder.append(", address2=");
		builder.append(address2);
		builder.append(", city=");
		builder.append(city);
		builder.append(", stateCd=");
		builder.append(stateCd);
		builder.append(", country=");
		builder.append(country);
		builder.append(", zip=");
		builder.append(zip);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", fax=");
		builder.append(fax);
		builder.append(", email=");
		builder.append(email);
		builder.append(", status=");
		builder.append(status);
		builder.append(", fullName=");
		builder.append(fullName);
		builder.append("]");
		return builder.toString();
	}
	 
}
