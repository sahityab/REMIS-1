package com.prop.mnt.property.user.relation;

import java.io.Serializable;

import javax.persistence.Column;

public class PropUserRelVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String relationCode;
	private String relationName;
	private String userName;
	private String propName;
	//private String divisionInfo;
	private Float rentamount;
	public Float getRentamount() {
		return rentamount;
	}

	public void setRentamount(Float rentamount) {
		this.rentamount = rentamount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRelationCode() {
		return relationCode;
	}

	public void setRelationCode(String relationCode) {
		this.relationCode = relationCode;
	}

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	/*public String getDivisionInfo() {
		return divisionInfo;
	}

	public void setDivisionInfo(String divisionInfo) {
		this.divisionInfo = divisionInfo;
	}*/

}
