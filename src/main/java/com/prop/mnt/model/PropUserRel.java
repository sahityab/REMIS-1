package com.prop.mnt.model;


import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="Prop_User_Rel")
public class PropUserRel implements Serializable {
	

	/*@Id 
	@GeneratedValue
	@Column(name = "RELATION_ID")
	private int id;*/
	
	@Id
	@Column(name="RELATION_CODE")
	private String relationCode;
	
	@Column(name="RELATION_NAME")
	private String relationName;

	
	/*	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Pages.class)
    @JoinTable(name = "ROLE_PAGE", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "PAGE_ID") })
    private List<Pages> pages;

	
	
	
	
	public List<Pages> getPages() {
		return pages;
	}

	public void setPages(List<Pages> pages) {
		this.pages = pages;
	}*/

/*	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}*/

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
}