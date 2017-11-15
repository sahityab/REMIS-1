package com.prop.mnt.model;

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
@Table(name="contract_work_progress")
public class ContractWorkProgress {
	
	@Id 
	@GeneratedValue
	@Column(name = "WORKPROGRESS_ID")
	private int workprogressId;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="CONTRACT_ID")
	private Contract contract;
	
	@Column(name="WORK_PROGRESS",columnDefinition="Text")
	private String workProgress;
	
	@Column(name="WORK_ISSUES",columnDefinition="Text")
	private String issues;
	
	@Column(name = "ACTIONNEED")
	private String actionNeed;
	
	@Column(name = "ISNEW")
	private boolean isnew;

	@Column(name = "create_date")
	private Date created;
	
	public int getWorkprogressId() {
		return workprogressId;
	}

	public void setWorkprogressId(int workprogressId) {
		this.workprogressId = workprogressId;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public String getWorkProgress() {
		return workProgress;
	}

	public void setWorkProgress(String workProgress) {
		this.workProgress = workProgress;
	}

	public String getIssues() {
		return issues;
	}

	public void setIssues(String issues) {
		this.issues = issues;
	}

	public String getActionNeed() {
		return actionNeed;
	}

	public void setActionNeed(String actionNeed) {
		this.actionNeed = actionNeed;
	}

	public boolean isIsnew() {
		return isnew;
	}

	public void setIsnew(boolean isnew) {
		this.isnew = isnew;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}
