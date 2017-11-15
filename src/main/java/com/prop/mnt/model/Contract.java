package com.prop.mnt.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="contract")
public class Contract implements Serializable {
	
	private static final long serialVersionUID = 1L; 
	
	@Id 
	@GeneratedValue
	@Column(name = "CONTRACT_ID")
	private Integer contractId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PROP_CODE")
	private Property property;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="USERID")
    private User user;
	
	
	@Column(name="CONTRACT_NAME")
	private String contractName;
	
	@Column(name="WORK_DESC",columnDefinition="Text")
	private String workDesc;
	
	@Column(name="START_DATE")
	private Date startDate;
	
	@Column(name="END_DATE")
	private Date endDate;
	
	@Column(name="PRICE")
	private double price;
	
	@Column(name="CONTRACT_TC",columnDefinition="Text")
	private String contractTC;
	
	@Column(name="status")
	private String status;
	

	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	
	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getWorkDesc() {
		return workDesc;
	}

	public void setWorkDesc(String workDesc) {
		this.workDesc = workDesc;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getContractTC() {
		return contractTC;
	}

	public void setContractTC(String contractTC) {
		this.contractTC = contractTC;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
