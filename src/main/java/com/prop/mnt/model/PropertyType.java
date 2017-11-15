package com.prop.mnt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="prop_type")
public class PropertyType {
	
	@Id
	@Column(name="PRO_TYPE_CODE")
	private String proTypeCode;
	
	@Column(name="PRO_TYPE_DES")
	private String proTypeDes;
	
	@Column(name="STATUS")
	private char status;

	public String getProTypeCode() {
		return proTypeCode;
	}

	public void setProTypeCode(String proTypeCode) {
		this.proTypeCode = proTypeCode;
	}

	public String getProTypeDes() {
		return proTypeDes;
	}

	public void setProTypeDes(String proTypeDes) {
		this.proTypeDes = proTypeDes;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
	
	
	
}
