package com.prop.mnt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="prop_status")
public class PropertyStatus {
	@Id
	@Column(name="PRO_Status_Code")
	private String proStatusCode;
	
	@Column(name="PRO_Status_Des")
	private String proStatusDes;
	
	@Column(name="STATUS")
	private char status;

	public String getProStatusCode() {
		return proStatusCode;
	}

	public void setProStatusCode(String proStatusCode) {
		this.proStatusCode = proStatusCode;
	}

	public String getProStatusDes() {
		return proStatusDes;
	}

	public void setProStatusDes(String proStatusDes) {
		this.proStatusDes = proStatusDes;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
	
	
	
}
