package com.prop.mnt.vo;

import java.util.ArrayList;
import java.util.List;

import com.prop.mnt.property.user.relation.PropUserVO;



public class PropRentInfoVo {
	
	private Integer id;
	private Integer propertyID;
	private String propName;
	
	private Integer propUserID;
	private String tntName;
	
	private Integer duedate;
	
	private String rentterms;
	
	private Float rentamount;

	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PropRentInfoVo(){}
	
	public PropRentInfoVo(Integer propertyID, String propName) {
		super();
		this.propertyID = propertyID;
		this.propName = propName;
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPropertyID() {
		return propertyID;
	}

	public void setPropertyID(Integer propertyID) {
		this.propertyID = propertyID;
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	
	
	public String getTntName() {
		return tntName;
	}

	public void setTntName(String tntName) {
		this.tntName = tntName;
	}

	public Integer getPropUserID() {
		return propUserID;
	}

	public void setPropUserID(Integer propUserID) {
		this.propUserID = propUserID;
	}

	public Integer getDuedate() {
		return duedate;
	}

	public void setDuedate(Integer duedate) {
		this.duedate = duedate;
	}

	public String getRentterms() {
		return rentterms;
	}

	public void setRentterms(String rentterms) {
		this.rentterms = rentterms;
	}

	public Float getRentamount() {
		return rentamount;
	}

	public void setRentamount(Float rentamount) {
		this.rentamount = rentamount;
	}
	
	
}
