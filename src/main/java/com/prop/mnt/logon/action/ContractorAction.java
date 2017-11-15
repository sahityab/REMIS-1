package com.prop.mnt.logon.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.prop.mnt.common.BaseActionSupport;
import com.prop.mnt.property.user.relation.PropUserVO;
import com.prop.mnt.service.ContractService;
import com.prop.mnt.service.ContractServiceImpl;
import com.prop.mnt.vo.ContractVo;

public class ContractorAction extends BaseActionSupport {

	private static final long serialVersionUID = 1L;
	private ContractService contractService=null;
	private ContractVo contractVo=new ContractVo();
	private List<PropUserVO> propList;
	private List<ContractVo> contractorList=new ArrayList<ContractVo>();
	
	//List<PropUserVO> findProperties
	Integer  userId;
	String role;
	
	
	public ContractorAction(){
		contractService=new ContractServiceImpl();
		 userId=(Integer)ActionContext.getContext().getSession().get("userid");
		 role=(String)ActionContext.getContext().getSession().get("role");
	}
	
	
	
	//assigncontract default page
	public String display(){
		
		if(!checkRole()) return "error_page";
		
		
		this.setPropList(contractService.findProperties(userId));
		if(propList!=null && !propList.isEmpty() && propList.size()>0){
			int propId=propList.get(0).getPropertyID();
		    this.setContractorList(contractService.findPropContrctors(propId));
		    contractVo.setPropertyId(propId);
		}else  this.setContractorList(getContractorList());
		return "success";
	}
	
	/*//find contractors based on Property
	public String propContractors(){
		try{
			this.setContractorList(contractService.findPropContrctors(contractVo.getPropertyId()));
			this.setPropList(contractService.findProperties(userId));
		}catch(Exception e){
			e.printStackTrace();
		     return "error_page";
		     }
		return "success";
	}*/
	
	
	//createnewcontract  page
		public String execute(){
			//int i=0;
			try{
				System.out.println("contractorID="+contractVo.getContratorId()+"propID="+contractVo.getPropertyId());
			int i=contractService.saveContract(contractVo);
			if (i==1)this.addActionMessage("Contract Created Successfully");
			this.setPropList(contractService.findProperties(userId));
			this.setContractorList(contractService.findPropContrctors(contractVo.getPropertyId()));
		    }catch(Exception e){
			e.printStackTrace();
			System.out.println("refresh attempt");
			this.addActionMessage("You are refreshing page/ Some thing went wrong ");
			this.setPropList(contractService.findProperties(userId));
			this.setContractorList(contractService.findPropContrctors(contractVo.getPropertyId()));
		     return "success";
		     }
			return "success";
		}
	
	//manageownercontracts
	public String manageOwnerContrcts(){
		if(!checkRole()) return "error_page";
		
		this.setContractorList(contractService.getCurrentOwnerContracts(userId));
		return "success";
	}
	
	//manamycontracts
    public String manageContractorContracts(){
    	this.setContractorList(contractService.getCurrentContractorContracts(userId));
		return "success";
	}
    
    
    public String viewContract(){
    String st="success";
	try{
		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("contractid="+request.getParameter("contractid"));
		if(request.getParameter("contractid")!=null){
		Integer contractid=Integer.parseInt(request.getParameter("contractid"));
		this.setContractVo(contractService.viewContract(contractid));
		//if(this.contractVo.getContratorId()==userId || )
		
		}
	 }catch(Exception e){
			e.printStackTrace();
			 return "error_page";
	 }
	return st;
	}
	
    //createprogress
    public String createProgress(){
        String st="success";
    	try{
    		HttpServletRequest request = ServletActionContext.getRequest();
    		System.out.println("contractid="+request.getParameter("contractid"));
    		if(request.getParameter("contractid")!=null){
    		Integer contractid=Integer.parseInt(request.getParameter("contractid"));
    		this.setContractVo(contractService.viewContract(contractid));
    		this.contractVo.setWorkDesc("");
    		this.setContractorList(contractService.getContractWorkProgress(contractid, 2));
    		}
    	 }catch(Exception e){
    			e.printStackTrace();
    			 return "error_page";
    	 }
    	return st;
    	}
    
  //saveprogress
    public String saveProgress(){
    	if(!validateworkprogress())   return "error_page";
    	
    	 String st="success";
    	 try{
    	    int i=contractService.saveWorkProgress(contractVo);
			if (i==1)this.addActionMessage("Work Progress Created Successfully");
		    }catch(Exception e){
			e.printStackTrace();
			 return "error_page";
		    }
    	 return st;
    }
    
    //viewContractprogress
    public String viewContractProgress(){
    	 String st="success";
                 HttpServletRequest request = ServletActionContext.getRequest();
    		      System.out.println("contractid="+request.getParameter("contractid"));
	    		try{
	    			if(request.getParameter("contractid")!=null){
	    		    Integer contractid=Integer.parseInt(request.getParameter("contractid"));
	    		    this.setContractorList(contractService.getContractWorkProgress(contractid, 5));
	    			}
	    
	    		 }catch(Exception e){
     			   e.printStackTrace();
     			 return "error_page";
     	 }
     	return st;
    }
    
    //viewcompleteprogress
    public String viewSingleProgress(){
   	 String st="success";
                HttpServletRequest request = ServletActionContext.getRequest();
   		      System.out.println("workProgId="+request.getParameter("workProgId"));
	    		try{
	    			if(request.getParameter("workProgId")!=null){
	    		    Integer progressId=Integer.parseInt(request.getParameter("workProgId"));
	    		    this.setContractVo(contractService.viewWorkProgress(progressId));
	    			}
	    
	    		 }catch(Exception e){
    			   e.printStackTrace();
    			 return "error_page";
    	 }
    	return st;
   }
    
    private boolean checkRole(){
    	if(role!=null && role.equals("OWN"))
    		return true;
    	else return false;
    }
    
    public boolean validateworkprogress(){
  	  
		 //boolean flag=true;
	        if(contractVo.getContractId()==0){
	                 this.addFieldError("contractVo.contractId","No Contract Found");
	        }
	       

	     if(contractVo.getWorkDesc()!=null){
       	   if(contractVo.getWorkDesc().trim().length()<20)
       	   this.addFieldError("contractVo.workDesc",getText("Please Enter Detailed Description")); 
	        }else this.addFieldError("contractVo.workDesc",getText("message.rentterms.required"));  
	        
	       if(this.hasFieldErrors()) return false;
	       else return true;
	        
	    } 
    
    
    
    
	public String reports(){
		return "success";
	}
	
	public String tasks(){
		return "success";
	}
	
	public String rentPay(){
		return "success";
	}


	public ContractVo getContractVo() {
		return contractVo;
	}

	public void setContractVo(ContractVo contractVo) {
		this.contractVo = contractVo;
	}



	public List<PropUserVO> getPropList() {
		return propList;
	}



	public void setPropList(List<PropUserVO> propList) {
		this.propList = propList;
	}
	
	public List<ContractVo> getContractorList() {
		return contractorList;
	}
	public void setContractorList(List<ContractVo> contractorList) {
		this.contractorList = contractorList;
	}	
	
	
}