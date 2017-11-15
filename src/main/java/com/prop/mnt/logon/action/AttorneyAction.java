package com.prop.mnt.logon.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;
import com.prop.mnt.common.BaseActionSupport;
import com.prop.mnt.service.AttorneyService;
import com.prop.mnt.service.AttorneyServiceImpl;
import com.prop.mnt.vo.AttorneyVo;

public class AttorneyAction extends BaseActionSupport implements Preparable {
	private static final Logger log = LoggerFactory.getLogger(AttorneyAction.class);	
	private static final long serialVersionUID = 1L;
	private AttorneyVo attorneyVo=new AttorneyVo();
	private AttorneyService attorneyService;
	
	private List<AttorneyVo> propList=new ArrayList<AttorneyVo>();
	
	private Integer userId;
	
	
	public void prepare(){
		try{
			System.out.println("prepare construct");
			 userId=(Integer)ActionContext.getContext().getSession().get("userid");
			 attorneyService=new AttorneyServiceImpl();
			 this.setPropList(attorneyService.getAttProperties(userId));
		}catch(Exception e){
			log.error("Exception in prepare:"+e);
		}
  	}
	//createcase
	@SkipValidation
	public String createCase(){
		return "success";
	}
	//savecase
	//@SkipValidation
	public String execute(){
		log.info("execute method="+userId);
		try{
			System.out.println("Att="+attorneyVo.getCaseName()+"propID="+attorneyVo.getPropertyId());
			attorneyVo.setUserId(userId);
			int i=attorneyService.saveAttCase(attorneyVo);
			if (i==1)this.addActionMessage("Case Created Successfully");
		   }catch(Exception e){
			e.printStackTrace();
			System.out.println("refresh attempt");
			this.addActionMessage("You are refreshing page/ Some thing went wrong ");
			//this.setPropList(attorneyService.getAttProperties(userId));
	       return "success";
	     }
		return "success";
	}
	
	public String updateCase(){
		log.info("updateCase method="+userId);
		 String st="success";
			try{
				if(attorneyVo.getCaseId()!=null){
				int i=attorneyService.updateAttCase(attorneyVo);
				if (i==1)this.addActionMessage("Case Updated Successfully");
				}
			 }catch(Exception e){
					e.printStackTrace();
					 return "error_page";
			 }
			return st;
	 }
	
	
	//viewcase
	   @SkipValidation
		public String viewCase(){
			log.info("viewCase method="+userId);
			 String st="success";
				/*try{
					HttpServletRequest request = ServletActionContext.getRequest();
					System.out.println("caseid="+request.getParameter("caseid"));
					if(request.getParameter("caseid")!=null){
					Integer caseid=Integer.parseInt(request.getParameter("caseid"));
					this.setAttorneyVo(attorneyService.viewAttCase(caseid,userId));
					}*/
					
					try{
						Integer caseid=attorneyVo.getCaseId();
						if(caseid!=null){
							System.out.println("caseid="+caseid+" new="+attorneyVo.getIsnew());
							if(attorneyVo.getIsnew()){
								attorneyService.resetIsnew(caseid);
							}
						this.setAttorneyVo(attorneyService.viewAttCase(caseid,userId));
						}
					
				 }catch(Exception e){
						e.printStackTrace();
						 return "error_page";
				 }
				return st;
		 }
	
		//viewattcases
	  
	    
	  /* public void validate() {  
		   System.out.println("in validate");
		    if(attorneyVo.getPropertyId()<1)  
		        addFieldError("attorneyVo.","Name can't be blank");  
		    if(attorneyVo.getCaseName().length()<3)  
		        addFieldError("attorneyVo.caseName","Case ID/NO must be greater than 2");  
		}  
	   
	   */
	   
	public AttorneyVo getAttorneyVo() {
		return attorneyVo;
	}

	public void setAttorneyVo(AttorneyVo attorneyVo) {
		this.attorneyVo = attorneyVo;
	}

	public List<AttorneyVo> getPropList() {
		return propList;
	}

	public void setPropList(List<AttorneyVo> propList) {
		this.propList = propList;
	}

}