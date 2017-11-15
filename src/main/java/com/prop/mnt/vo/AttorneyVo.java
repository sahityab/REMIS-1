package com.prop.mnt.vo;

import java.util.Date;

import javax.persistence.Column;


public class AttorneyVo {
	
	//Case
	private Integer caseId;
	private String propertyName;
	private Integer propertyId;
	
	private Integer userId;
	private String userName;
	private String caseName;
	private String courtName;
	private String caseDesc;
	private Date startDate;
	private Date closeDate;
	private String status;
	private String petitioner;
	private String respondent;
	
	//case hearings
	private Integer casehearId;
	private String hearingTask;
	private Date caseHearingDate;
	private String hearinDetails;
	private Date created;
	private Date updated;
	private Boolean updateaccess;
	
	private Boolean isnew;
	private String newreason;
	
	
	public Boolean getIsnew() {
		return isnew;
	}
	public void setIsnew(Boolean isnew) {
		this.isnew = isnew;
	}
	public String getNewreason() {
		return newreason;
	}
	public void setNewreason(String newreason) {
		this.newreason = newreason;
	}
	public Date getCaseHearingDate() {
		return caseHearingDate;
	}
	public void setCaseHearingDate(Date caseHearingDate) {
		this.caseHearingDate = caseHearingDate;
	}
	public Boolean isUpdateaccess() {
		return updateaccess;
	}
	public void setUpdateaccess(Boolean updateaccess) {
		this.updateaccess = updateaccess;
	}
	public Integer getCaseId() {
		return caseId;
	}
	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public Integer getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	public String getCourtName() {
		return courtName;
	}
	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
	public String getCaseDesc() {
		return caseDesc;
	}
	public void setCaseDesc(String caseDesc) {
		this.caseDesc = caseDesc;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getCasehearId() {
		return casehearId;
	}
	public void setCasehearId(Integer casehearId) {
		this.casehearId = casehearId;
	}
	public String getHearingTask() {
		return hearingTask;
	}
	public void setHearingTask(String hearingTask) {
		this.hearingTask = hearingTask;
	}


	
	public String getHearinDetails() {
		return hearinDetails;
	}
	public void setHearinDetails(String hearinDetails) {
		this.hearinDetails = hearinDetails;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public String getPetitioner() {
		return petitioner;
	}
	public void setPetitioner(String petitioner) {
		this.petitioner = petitioner;
	}
	public String getRespondent() {
		return respondent;
	}
	public void setRespondent(String respondent) {
		this.respondent = respondent;
	}
	
}
