package com.prop.mnt.property.user.relation;

import java.io.Serializable;
import java.util.List;

import com.prop.mnt.admin.user.UserVO;

public class PropUserVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String relationCode;
	
	private Integer propCode;
	
	private String userId;
	
	private String status;
	private Integer userID;
	private Integer proUserRelID;
	
	private Integer propertyID;
	private String propertyName;
	
	private Float rentAmt;
	
	private List<UserVO> userList;
	private List<PropUserRelVO> relList;
	private List<PropUserVO> propList;
	
	
	
	
	
	//private SubPropertyVo subProperty;
	
	//private List<SubPropertyVo> subPropertyList;
	
	/*public List<SubPropertyVo> getSubPropertyList() {
		return subPropertyList;
	}
	public void setSubPropertyList(List<SubPropertyVo> subPropertyList) {
		this.subPropertyList = subPropertyList;
	}

	public SubPropertyVo getSubProperty() {
		return subProperty;
	}
	public void setSubProperty(SubPropertyVo subProperty) {
		this.subProperty = subProperty;
	}*/
	public String getPropertyName() {
		return propertyName;
	}


	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}


	public PropUserVO(){}
	
	
	public PropUserVO(Integer propertyID, String propertyName) {
		super();
		this.propertyID = propertyID;
		this.propertyName = propertyName;
	}

	public List<UserVO> getUserList() {
		return userList;
	}

	public void setUserList(List<UserVO> userList) {
		this.userList = userList;
	}

	public List<PropUserRelVO> getRelList() {
		return relList;
	}

	public void setRelList(List<PropUserRelVO> relList) {
		this.relList = relList;
	}

	public List<PropUserVO> getPropList() {
		return propList;
	}

	public void setPropList(List<PropUserVO> propList) {
		this.propList = propList;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getProUserRelID() {
		return proUserRelID;
	}

	public void setProUserRelID(Integer proUserRelID) {
		this.proUserRelID = proUserRelID;
	}

	public Integer getPropertyID() {
		return propertyID;
	}

	public void setPropertyID(Integer propertyID) {
		this.propertyID = propertyID;
	}

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

	public Float getRentAmt() {
		return rentAmt;
	}

	public void setRentAmt(Float rentAmt) {
		this.rentAmt = rentAmt;
	}
	
	
	
}
