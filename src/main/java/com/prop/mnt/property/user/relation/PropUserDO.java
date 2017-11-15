package com.prop.mnt.property.user.relation;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name="PropUserDO") 
@Table(name="Prop_User")
public class PropUserDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "RELATION_CODE")   
	private String relationCode;
	
	@Column(name="PROP_CODE")
	private Integer propCode;
	
	@Column(name="USERID")
	private String userId;
	
	@Column(name="STATUS")
	private String status;

	public String getRelationCode() {
		return relationCode;
	}

	public void setRelationCode(String relationCode) {
		this.relationCode = relationCode;
	}

	public Integer getPropCode() {
		return propCode;
	}

	public void setPropCode(Integer propCode) {
		this.propCode = propCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
