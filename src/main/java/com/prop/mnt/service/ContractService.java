package com.prop.mnt.service;

import java.util.List;

import com.prop.mnt.property.user.relation.PropUserVO;
import com.prop.mnt.vo.ContractVo;

public interface ContractService {

	public Integer  saveContract(ContractVo contract);
	
	public List<ContractVo> getCurrentOwnerContracts(Integer ownerId);
	
	public List<ContractVo> getCurrentContractorContracts(Integer contractorId);
	
	public List<PropUserVO> findProperties(int  userId);
	
	public List<ContractVo> findPropContrctors(int propid);
	
	public ContractVo viewContract(int contractid);
	
	public Integer  saveWorkProgress(ContractVo contractVo);
	 
	public List<ContractVo> getContractWorkProgress(Integer contrctId,int limit);
	
	  public ContractVo viewWorkProgress(int progressId);
	  
		public Integer updateNotification(int notifyId);
	
}
