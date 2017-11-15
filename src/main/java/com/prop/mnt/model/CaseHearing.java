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
@Table(name="CASE_HEARING")
public class CaseHearing implements Serializable {
	
	private static final long serialVersionUID = 1L; 
	@Id 
	@GeneratedValue
	@Column(name = "CASEHEAR_ID")
	private Integer casehearId;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL,optional=false)
	@JoinColumn(name="CASE_ID",updatable=false)
	private AttCase attCase;
	
	@Column(name="HEARING_DATE")
	private Date hearingDate;
	
	@Column(name="HEARING_TASK")
	private String hearingTask;
	
	@Column(name="HEARING_DETAIL")
	private String hearinDetails;
	
	@Column(name = "create_date")
	private Date created;
	
	@Column(name = "updated_date")
	private Date updated;

	
	@Column(name = "isnew")
	private Boolean isnew;
	
	@Column(name="newreason")
	private String newreason;

	public Boolean getIsnew() {
		return isnew;
	}

	public void setIsnew(Boolean isnew) {
		this.isnew = isnew;
	}

	public Integer getCasehearId() {
		return casehearId;
	}

	public void setCasehearId(Integer casehearId) {
		this.casehearId = casehearId;
	}

	public AttCase getAttCase() {
		return attCase;
	}

	public void setAttCase(AttCase attCase) {
		this.attCase = attCase;
	}

	public Date getHearingDate() {
		return hearingDate;
	}

	public void setHearingDate(Date hearingDate) {
		this.hearingDate = hearingDate;
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

	public String getNewreason() {
		return newreason;
	}

	public void setNewreason(String newreason) {
		this.newreason = newreason;
	}
	
}
