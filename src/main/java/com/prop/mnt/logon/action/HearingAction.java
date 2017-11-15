package com.prop.mnt.logon.action;

import java.util.ArrayList;
import java.util.Date;
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

public class HearingAction  extends BaseActionSupport implements Preparable {
	private static final Logger log = LoggerFactory.getLogger(HearingAction.class);	
	private static final long serialVersionUID = 1L;
	private AttorneyVo hearingVo=new AttorneyVo();
	private AttorneyService attorneyService;
	
	
	private List<AttorneyVo> hearingList=new ArrayList<AttorneyVo>();
	
	private Integer userId;
	
	
	public void prepare(){
		try{
			System.out.println("prepare construct");
			 userId=(Integer)ActionContext.getContext().getSession().get("userid");
			 attorneyService=new AttorneyServiceImpl();
		   }catch(Exception e){
			log.error("Exception in prepare:"+e);
		}
		System.out.println(" hear date="+hearingVo.getCaseHearingDate());
		
	  }
		
	 @SkipValidation
	 public String viewHearing(){
			log.info("viewHearing method="+userId);
			 String st="success";
				/*try{
					HttpServletRequest request = ServletActionContext.getRequest();
					System.out.println("hearingId="+request.getParameter("casehearId"));
					if(request.getParameter("casehearId")!=null){
					Integer casehearId=Integer.parseInt(request.getParameter("casehearId"));
					
		*/			
					try{
						Integer caseid=hearingVo.getCaseId();
						Integer casehearId=hearingVo.getCasehearId();
						if(caseid!=null && casehearId!=null){
							System.out.println(casehearId+"==caseid="+caseid+" new="+hearingVo.getIsnew());
							if(hearingVo.getIsnew()){
								attorneyService.resetHearingIsnew(caseid, casehearId);
							}
					   this.setHearingVo(attorneyService.viewCaseHear(casehearId,userId));
					}
				 }catch(Exception e){
						e.printStackTrace();
						 return "error_page";
				 }
				return st;
      }
	 
	 
	  @SkipValidation
	  public String getCaseHearings(){
			log.info("viewCase method="+userId);
			 String st="success";
				try{
					HttpServletRequest request = ServletActionContext.getRequest();
					System.out.println("caseid="+request.getParameter("caseid"));
					if(request.getParameter("caseid")!=null){
					Integer caseid=Integer.parseInt(request.getParameter("caseid"));
					this.setHearingList(attorneyService.getCaseHearings(caseid, 5,hearingVo));
					}
				 }catch(Exception e){
						e.printStackTrace();
						 return "error_page";
				 }
				return st;
           }
	  
	  @SkipValidation
	  public String createCaseHearing(){
			log.info("viewCase method="+userId);
			 String st="success";
			 try{
					HttpServletRequest request = ServletActionContext.getRequest();
					System.out.println("caseid="+request.getParameter("caseid"));
					
					if(request.getParameter("caseid")!=null){
					Integer caseid=Integer.parseInt(request.getParameter("caseid"));
					this.setHearingList(attorneyService.getCaseHearings(caseid, 2,hearingVo));
					if(this.getHearingList()==null || this.getHearingList().size()==0){
						this.setHearingVo(attorneyService.viewAttCase(caseid,userId));
					}
					}
					
				 }catch(Exception e){
						e.printStackTrace();
						 return "error_page";
				 }
			 
				return st;
           }
	  
	  public String execute(){
			log.info("execute method="+userId);
			
			try{
				System.out.println("case Id="+hearingVo.getCaseId());
				hearingVo.setUserId(userId);
				int i=attorneyService.saveCaseHearing(hearingVo);
				if (i==1)this.addActionMessage("Case Hearing Created Successfully");
				this.setHearingList(attorneyService.getCaseHearings(hearingVo.getCaseId(), 2,hearingVo));
			   }catch(Exception e){
				e.printStackTrace();
				
				this.addActionMessage("You are refreshing page/ Some thing went wrong ");
				//this.setPropList(attorneyService.getAttProperties(userId));
		       return "success";
		     }
			return "success";
		}

	  public String updateHearing(){
			log.info("execute method="+userId);
			
			try{
				System.out.println("case Id="+hearingVo.getCaseId());
				hearingVo.setUserId(userId);
				int i=attorneyService.updateCaseHearing(hearingVo);
				if (i==1)this.addActionMessage("Case Hearing Updated Successfully");
			   }catch(Exception e){
				e.printStackTrace();
				
				this.addActionMessage("You are refreshing page/ Some thing went wrong ");
				//this.setPropList(attorneyService.getAttProperties(userId));
		       return "success";
		     }
			return "success";
		}

	public AttorneyVo getHearingVo() {
		return hearingVo;
	}

	public void setHearingVo(AttorneyVo hearingVo) {
		this.hearingVo = hearingVo;
	}

	public List<AttorneyVo> getHearingList() {
		return hearingList;
	}


	public void setHearingList(List<AttorneyVo> hearingList) {
		this.hearingList = hearingList;
	}
	
	
}