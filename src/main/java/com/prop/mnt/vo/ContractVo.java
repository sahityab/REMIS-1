package com.prop.mnt.vo;

import java.util.Date;




import com.prop.mnt.model.Property;
import com.prop.mnt.model.User;

public class ContractVo {
	
	private int contractId;

	private Integer propertyId;
	private String propertyName;
	
	private Integer propUserRelId;
	
	
    private Integer contratorId;
    private String  contractorName;
    
	
	private Date startDate;
	
	private Date endDate;
	
	private double price;
	
	private String contractTC;
	
	private String status;

    private String contractName;
	private String workDesc;
	//progress
	private String issues;
	private String actionneeded;
	private Integer workProgId;
	private boolean isnew;
	
	public ContractVo(){}
	
	
	public ContractVo(Integer contratorId, String propertyName) {
		//super();
		this.contratorId = contratorId;
		this.propertyName = propertyName;
	}

	public ContractVo(Integer contratorId, String contractorName, String contractName) {
		//super();
		this.contratorId = contratorId;
		this.contractorName = contractorName;
		this.contractName = contractName;
	}


	public int getContractId() {
		return contractId;
	}

	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public Integer getPropUserRelId() {
		return propUserRelId;
	}

	public void setPropUserRelId(Integer propUserRelId) {
		this.propUserRelId = propUserRelId;
	}


	public Integer getContratorId() {
		return contratorId;
	}


	public void setContratorId(Integer contratorId) {
		this.contratorId = contratorId;
	}


	public String getContractorName() {
		return contractorName;
	}

	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getWorkDesc() {
		return workDesc;
	}

	public void setWorkDesc(String workDesc) {
		this.workDesc = workDesc;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getContractTC() {
		return contractTC;
	}

	public void setContractTC(String contractTC) {
		this.contractTC = contractTC;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public String getIssues() {
		return issues;
	}
	public void setIssues(String issues) {
		this.issues = issues;
	}


	public String getActionneeded() {
		return actionneeded;
	}
	public void setActionneeded(String actionneeded) {
		this.actionneeded = actionneeded;
	}


	public Integer getWorkProgId() {
		return workProgId;
	}


	public void setWorkProgId(Integer workProgId) {
		this.workProgId = workProgId;
	}


	public boolean isIsnew() {
		return isnew;
	}


	public void setIsnew(boolean isnew) {
		this.isnew = isnew;
	}
	
	
}
