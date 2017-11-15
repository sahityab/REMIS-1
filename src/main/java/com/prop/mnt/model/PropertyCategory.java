package com.prop.mnt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="prop_cat")
public class PropertyCategory {
	@Id
	@Column(name="PRO_CAT_Code")
	private String proCatCode;
	
	@Column(name="PRO_CAT_Des")
	private String proCatDes;
	
	@Column(name="STATUS")
	private char status;

	public String getProCatCode() {
		return proCatCode;
	}

	public void setProCatCode(String proCatCode) {
		this.proCatCode = proCatCode;
	}

	public String getProCatDes() {
		return proCatDes;
	}

	public void setProCatDes(String proCatDes) {
		this.proCatDes = proCatDes;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
	
	
}
