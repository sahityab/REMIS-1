package com.prop.mnt.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="modules")
public class Modules implements Serializable{
	@Id 
	@GeneratedValue
	@Column(name = "MODULE_ID")
	private Integer moduleID;
	
	@Column(name = "MODULE_NAME")
	private String moduleName;
	
	@Column(name = "IMAGE")
	private String image ;
	
	@Column(name = "ALT")
	private String alt ;
	
	@Column(name = "role")
	private String role ;

	//@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="module")
	@Transient
	List<Pages> pages;
   
	public List<Pages> getPages() {
		return pages;
	}

	public void setPages(List<Pages> pages) {
		this.pages = pages;
	}

	public Integer getModuleID() {
		return moduleID;
	}

	public void setModuleID(Integer moduleID) {
		this.moduleID = moduleID;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((moduleID == null) ? 0 : moduleID.hashCode());
		result = prime * result
				+ ((moduleName == null) ? 0 : moduleName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Modules other = (Modules) obj;
		if (moduleID == null) {
			if (other.moduleID != null)
				return false;
		} else if (!moduleID.equals(other.moduleID))
			return false;
		if (moduleName == null) {
			if (other.moduleName != null)
				return false;
		} else if (!moduleName.equals(other.moduleName))
			return false;
		return true;
	}
	
	
	
	
}
