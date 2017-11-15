package com.prop.mnt.property.user.relation;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name="PropUserRelDO") 
@Table(name="Prop_User_Rel")
public class PropUserRelDO implements Serializable {
	
	@Column(name="RELATION_CODE")
	private String relationCode;
	
	@Column(name="RELATION_NAME")
	private String relationName;

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
