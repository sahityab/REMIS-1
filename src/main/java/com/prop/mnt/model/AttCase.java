package com.prop.mnt.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ATT_CASE")
public class AttCase implements Serializable {
	
	private static final long serialVersionUID = 1L; 
	
	@Id 
	@GeneratedValue
	@Column(name = "CASE_ID")
	private Integer caseId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PROP_CODE")
	private Property property;
	
	@Column(name="CASE_NAME")
	private String caseName;
	
	@Column(name="COURT_NAME")
	private String courtName;
	
	@Column(name="CASE_DESC",columnDefinition="Text")
	private String caseDesc;
	
	@Column(name="CASE_PETITIONER")
	private String petitioner;
	
	@Column(name="CASE_RESPONDENT")
	private String respondent;
	
	
	@Column(name="START_DATE")
	private Date startDate;
	
	@Column(name="CLOSE_DATE")
	private Date closeDate;
	
	@Column(name="status")
	private String status;
	
	//@Column(name = "CASEATT_ID")
	//private Integer caseattId;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="CASEATT_ID")
	private User attUser;

	@Column(name="isnew")
	private Boolean isnew;
	
	@Column(name="newreason")
	private String newreason;
	
	
	@OneToMany(mappedBy = "attCase", cascade = CascadeType.ALL)
    private Collection<CaseHearing> caseHearing;
	
	public Collection<CaseHearing> getCaseHearing() {
		return caseHearing;
	}

	public void setCaseHearing(Collection<CaseHearing> caseHearing) {
		this.caseHearing = caseHearing;
	}

	public User getAttUser() {
		return attUser;
	}

	public void setAttUser(User attUser) {
		this.attUser = attUser;
	}

	public Integer getCaseId() {
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
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
	
}

