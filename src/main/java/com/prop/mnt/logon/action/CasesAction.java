package com.prop.mnt.logon.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.prop.mnt.common.BaseActionSupport;
import com.prop.mnt.service.AttorneyService;
import com.prop.mnt.service.AttorneyServiceImpl;
import com.prop.mnt.vo.AttorneyVo;

public class CasesAction extends BaseActionSupport{
	
	private static final Logger log = LoggerFactory.getLogger(CasesAction.class);	
	
	private AttorneyService attorneyService;
	Integer userId;
		public CasesAction(){
		 userId=(Integer)ActionContext.getContext().getSession().get("userid");
		 attorneyService=new AttorneyServiceImpl();
		}
	
		private List<AttorneyVo> casesList;
		public List<AttorneyVo> getCasesList() {
			return casesList;
		}
	
		public void setCasesList(List<AttorneyVo> casesList) {
			this.casesList = casesList;
		}
	
		
		
	    public String manageAttorneyCases(){
	    	log.info( " CasesAction manageAttorneyCases method="+userId);
	    	try{
	    	this.setCasesList(attorneyService.getAttorneyCases(userId));
	    	}catch(Exception e){
				e.printStackTrace();
				 return "error_page";
		 }
			return "success";
		}

	    //propattorney
	    public String manageOwnerCases(){
	    	log.info("CasesAction manageOwnerCases method="+userId);
	    	try{
	    	    this.setCasesList(attorneyService.getOwnerCases(userId));
	    	 }catch(Exception e){
				e.printStackTrace();
				 return "error_page";
		 }
			return "success";
		}
	    
}
