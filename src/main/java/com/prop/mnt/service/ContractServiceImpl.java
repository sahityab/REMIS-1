package com.prop.mnt.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.prop.mnt.admin.user.UserDAO;
import com.prop.mnt.dao.ContractDao;
import com.prop.mnt.model.AttCase;
import com.prop.mnt.model.Contract;
import com.prop.mnt.model.ContractWorkProgress;
import com.prop.mnt.property.user.relation.PropUserVO;
import com.prop.mnt.vo.AttorneyVo;
import com.prop.mnt.vo.ContractVo;

public class ContractServiceImpl implements ContractService {
	private ContractDao contractDao;
	private UserDAO userDao;
	
	public ContractServiceImpl(){
		contractDao=new ContractDao();
		userDao=new UserDAO();
	}
	
   public Integer  saveContract(ContractVo contractVo){
	      Contract contract=new Contract();
	      contract.setContractName(contractVo.getContractName());
	      contract.setContractTC(contractVo.getContractTC());
	      contract.setEndDate(contractVo.getEndDate());
	      contract.setPrice(contractVo.getPrice());
	      contract.setStartDate(contractVo.getStartDate());
	      contract.setWorkDesc(contractVo.getWorkDesc());
	      contract.setProperty(contractDao.findByPropertyId(contractVo.getPropertyId()) );
	      contract.setUser(contractDao.findByUserId(contractVo.getContratorId()));
	      contract.setStatus("Created");
	      return contractDao.saveContract(contract);
	   
    }
   
    public Integer  saveWorkProgress(ContractVo contractVo){
    	
	   ContractWorkProgress work=new ContractWorkProgress();
	   work.setContract(contractDao.viewContract(contractVo.getContractId()));
	   work.setWorkProgress(contractVo.getWorkDesc());
	   work.setIssues(contractVo.getIssues());
	   work.setActionNeed(contractVo.getActionneeded());
	   work.setIsnew(true);
	   work.setCreated(new Date());
	   return contractDao.saveWorkProgress(work);
    }
    
    public ContractVo viewWorkProgress(int progressId){
    	ContractWorkProgress work=contractDao.viewWorkProgress(progressId);
    	if(work==null)
    		return null;
		ContractVo contrvo= new ContractVo();
		contrvo= new ContractVo();
		contrvo.setContractName(work.getContract().getContractName());
		contrvo.setWorkProgId(work.getWorkprogressId());
		contrvo.setWorkDesc(work.getWorkProgress());
		contrvo.setIssues(work.getIssues());
		contrvo.setStartDate(work.getCreated());
		contrvo.setActionneeded(work.getActionNeed());
		contrvo.setIsnew(work.isIsnew());
		return contrvo;
	}
   
    public List<ContractVo> getContractWorkProgress(Integer contrctId,int limit){
		List<ContractVo> conList =new ArrayList<ContractVo>();
		 List<ContractWorkProgress> workList=contractDao.getContractWorkProgress(contrctId,limit);
		ContractVo contrvo=null;
		for(ContractWorkProgress contr:workList){
			contrvo= new ContractVo();
			contrvo.setWorkProgId(contr.getWorkprogressId());
			contrvo.setWorkDesc(trimString(contr.getWorkProgress(),60));
			contrvo.setIssues(trimString(contr.getIssues(),30));
			contrvo.setStartDate(contr.getCreated());
			contrvo.setActionneeded(contr.getActionNeed());
			contrvo.setIsnew(contr.isIsnew());
			conList.add(contrvo);
		}
		return conList;
	}
    
    private String trimString(final String input,int size){
    	if(input!=null && input.length()>size){
    		return input.substring(0, size)+" ...";
    	}
    	return input;
    }
    
	public List<ContractVo> getCurrentOwnerContracts(Integer ownerId){
		List<ContractVo> conList =new ArrayList<ContractVo>();
		List<Contract> woncontrList=contractDao.getCurrentOwnerContracts(ownerId);
		ContractVo contrvo=null;
		for(Contract contr:woncontrList){
			contrvo= new ContractVo();
			contrvo.setContractId(contr.getContractId());
			contrvo.setContractName(contr.getContractName());
			contrvo.setContractorName(contr.getUser().getFirstName()+", "+contr.getUser().getLastName());
			contrvo.setContractTC(contr.getContractTC());
			contrvo.setEndDate(contr.getEndDate());
			contrvo.setPrice(contr.getPrice());
			contrvo.setPropertyId(contr.getProperty().getPropCode());
			contrvo.setPropertyName(contr.getProperty().getPropName());
			contrvo.setStartDate(contr.getStartDate());
			contrvo.setStatus(contr.getStatus());
			contrvo.setWorkDesc(contr.getWorkDesc());
			conList.add(contrvo);
		}
		System.out.println(ownerId+"conList of owner="+conList.size());
		return conList;
	}
	
	public List<ContractVo> getCurrentContractorContracts(Integer contractorId){
		List<Contract> contrList=contractDao.getCurrentContractorContracts(contractorId);
		List<ContractVo> contratsList =new ArrayList<ContractVo>();
		ContractVo contrvo=null;
		for(Contract contr:contrList){
			contrvo= new ContractVo();
			contrvo.setContractId(contr.getContractId());
			contrvo.setContractName(contr.getContractName());
			contrvo.setContractorName(contr.getProperty().getUser().getFirstName()+", "+contr.getProperty().getUser().getLastName());
			contrvo.setContractTC(contr.getContractTC());
			contrvo.setEndDate(contr.getEndDate());
			contrvo.setPrice(contr.getPrice());
			contrvo.setPropertyId(contr.getProperty().getPropCode());
			contrvo.setPropertyName(contr.getProperty().getPropName());
			contrvo.setStartDate(contr.getStartDate());
			contrvo.setStatus(contr.getStatus());
			contrvo.setWorkDesc(contr.getWorkDesc());
			contratsList.add(contrvo);
		}
		return contratsList;
	}	
	
	public ContractVo viewContract(int contractid){
		Contract contr=contractDao.viewContract(contractid);
		ContractVo contrvo= new ContractVo();
		contrvo= new ContractVo();
		contrvo.setContractId(contr.getContractId());
		contrvo.setContractName(contr.getContractName());
		contrvo.setContractorName(contr.getProperty().getUser().getFirstName()+", "+contr.getProperty().getUser().getLastName());
		contrvo.setContractTC(contr.getContractTC());
		contrvo.setEndDate(contr.getEndDate());
		contrvo.setPrice(contr.getPrice());
		contrvo.setPropertyId(contr.getProperty().getPropCode());
		contrvo.setPropertyName(contr.getProperty().getPropName());
		contrvo.setStartDate(contr.getStartDate());
		contrvo.setStatus(contr.getStatus());
		contrvo.setWorkDesc(contr.getWorkDesc());
		return contrvo;
	}
	
	public Integer updateContract(ContractVo conVo){
		Contract contr=contractDao.viewContract(conVo.getContractId());
		contr.setStatus(conVo.getStatus());
		contr.setStartDate(conVo.getStartDate());
		contr.setEndDate(conVo.getEndDate());
		if(contr.getContractId()!=conVo.getContratorId())
		contr.setUser(contractDao.findByUserId(conVo.getContratorId()));
		
		contr.setWorkDesc(conVo.getWorkDesc());
		contr.setContractTC(conVo.getContractTC());
		return contractDao.updateContract(contr);
	}
	
	
	public List<PropUserVO> findProperties(int  userId){
	      return userDao.getPropList(userId);
	}
	
	public List<ContractVo> findPropContrctors(int propid){
		return contractDao.getContractUsers(propid);
		
		/* List<PropUser> propUList= contractDao.getPropertyContractors(propid);
		 List<ContractVo> contList=new ArrayList<ContractVo>();
		 ContractVo contr=null;
	      for(PropUser propuser:propUList){
	    	  contr=new ContractVo();
	    	  contr.setUserId(propuser.getUser().getId());
	    	  contr.setContractorName(propuser.getUser().getFirstName()+", "+propuser.getUser().getLastName());
	    	  contList.add(contr) ;
	      }
	      System.out.println(propid+ " this prop contractorList size="+contList.size());
	 return contList;   */ 
	}
	
	public Integer updateNotification(int notifyId){
		return contractDao.updateNotification(notifyId);
	}
	
}
