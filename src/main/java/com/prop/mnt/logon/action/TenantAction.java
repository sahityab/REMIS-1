package com.prop.mnt.logon.action;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.prop.mnt.property.user.relation.PropUserVO;
import com.prop.mnt.property.user.relation.PropertyUserRelationInterface;
import com.prop.mnt.property.user.relation.PropertyUserRelationService;
import com.prop.mnt.vo.PropRentInfoVo;

public class TenantAction extends ActionSupport implements ModelDriven<PropRentInfoVo> {
	
	private static final long serialVersionUID = 1L;
	private PropRentInfoVo propRentInfoVo = new PropRentInfoVo();
	private PropertyUserRelationInterface propUserRelService=null;//new PropertyUserRelationService();;
	
	Integer userId=null;
	@Override
	   public PropRentInfoVo getModel() {
	      return propRentInfoVo;
	   }
	
	
	public TenantAction(){
		System.out.println("in TenantAction constructor");
		propUserRelService=new PropertyUserRelationService();
		 userId=(Integer)ActionContext.getContext().getSession().get("userid");
	}
	
	public String rentChange(){
		System.out.println("in rentChange");
		this.setPropList(propUserRelService.findProperties(userId));
		if(this.getPropList()!=null&&this.getPropList().size()>0){
		PropRentInfoVo propRentVo=propUserRelService.getRentTntInfo(this.getPropList().get(0).getPropertyID());
			if(propRentVo!=null){
				propRentInfoVo.setId(propRentVo.getId());
				propRentInfoVo.setDuedate(propRentVo.getDuedate());
				propRentInfoVo.setRentterms(propRentVo.getRentterms());
				propRentInfoVo.setRentamount(propRentVo.getRentamount());
				propRentInfoVo.setTntName(propRentVo.getTntName());
				propRentInfoVo.setPropUserID(propRentVo.getPropUserID());
			}
		}
		return "success";
	}
	//savepropertyrent
	public String execute(){
		if(validate1()){
			System.out.println("propId="+propRentInfoVo.getPropertyID());
			try{
				int i=0;
				i=propUserRelService.saveRent(propRentInfoVo);
				if (i>0){
					PropRentInfoVo propRentVo=propUserRelService.getRentTntInfo(propRentInfoVo.getPropertyID());
					if(propRentVo!=null){
						propRentInfoVo.setId(propRentVo.getId());
						propRentInfoVo.setDuedate(propRentVo.getDuedate());
						propRentInfoVo.setRentterms(propRentVo.getRentterms());
						propRentInfoVo.setRentamount(propRentVo.getRentamount());
						propRentInfoVo.setTntName(propRentVo.getTntName());
						propRentInfoVo.setPropUserID(propRentVo.getPropUserID());
					}
					if (i==1)this.addActionMessage("Record Saved Successfully");
					if (i==2)this.addActionMessage("Record Updated Successfully");
				}
			}catch(Exception e){
				e.printStackTrace();
			     return "error_page";
			     }
			 this.setPropList(propUserRelService.findProperties(userId));
			return "success";
			}
		else {
			this.setPropList(propUserRelService.findProperties(userId));
			return "success";
		}
	}
	
	 public boolean validate1(){
	  
		 //boolean flag=true;
	        if(propRentInfoVo.getDuedate()!=null){
	        		if(propRentInfoVo.getDuedate()<1 || propRentInfoVo.getDuedate()>30){
	                 this.addFieldError("propRentInfoVo.duedate","Due Date Between 0 and 30");
	        		}
	        }else this.addFieldError("propRentInfoVo.duedate","Due Date Mandatory");
	       

	        if(propRentInfoVo.getRentamount()!=null){
	        	
	        }else this.addFieldError("propRentInfoVo.rentamount","Please Enter Rent Amount");  
	        
           if(propRentInfoVo.getRentterms()!=null){
        	   if(propRentInfoVo.getRentterms().trim().length()<5)
        	   this.addFieldError("propRentInfoVo.rentterms",getText("message.rentterms.required")); 
	        }else this.addFieldError("propRentInfoVo.rentterms",getText("message.rentterms.required"));  
	        
	       if(this.hasFieldErrors()) return false;
	       else return true;
	        
	    }
	
	
	public String propTenant(){
		try{
			System.out.println("propTenantPrID="+propRentInfoVo.getPropertyID());
			PropRentInfoVo propRentVo=propUserRelService.getRentTntInfo(propRentInfoVo.getPropertyID());{
				if(propRentVo!=null){
					propRentInfoVo.setId(propRentVo.getId());
					propRentInfoVo.setDuedate(propRentVo.getDuedate());
					propRentInfoVo.setRentterms(propRentVo.getRentterms());
					propRentInfoVo.setRentamount(propRentVo.getRentamount());
					propRentInfoVo.setTntName(propRentVo.getTntName());
					propRentInfoVo.setPropUserID(propRentVo.getPropUserID());
				}
			}
			this.setPropList(propUserRelService.findProperties(userId));
		}catch(Exception e){
			e.printStackTrace();
		     return "error_page";
		     }
		return "success";
	}
	
	
	public String rentCollect(){
		try{
		this.setPropRentList(propUserRelService.findCurrnetTNTPayments(userId));
		}catch(Exception e){
			e.printStackTrace();
		     return "error_page";
		     }
		return "success";
	}
	
	public String payment(){
		String st="success";
		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("payrentid="+request.getParameter("payrentid"));
		if(request.getParameter("payrentid")!=null){
		Integer rentinfoId=Integer.parseInt(request.getParameter("payrentid"));
		int i=propUserRelService.payRentPayment(rentinfoId, userId);
		System.out.println("record status="+i);
		
		 if(i==0){
			this.setPropRentList(propUserRelService.findMonthRentals(userId));
			this.addActionMessage("Record Not Exist"); 
			st="input";
		}else if(i==2){
			this.setPropRentList(propUserRelService.findMonthRentals(userId));
			this.addActionMessage("Payment already done"); 
			st="input";
		}
		
		}
		
		}catch(Exception e){
			e.printStackTrace();
		     return "error_page";
		     }
		return st;
	}
	
	/*public String tasks(){
		return "success";
	}
	*/
	public String rentPay(){
		try{
		this.setPropRentList(propUserRelService.findMonthRentals(userId));
		}catch(Exception e){
			e.printStackTrace();
		     return "error_page";
		     }
		return "success";
	}

	public PropRentInfoVo getPropRentInfoVo() {
		return propRentInfoVo;
	}


	public void setPropRentInfoVo(PropRentInfoVo propRentInfoVo) {
		this.propRentInfoVo = propRentInfoVo;
	}


	public PropertyUserRelationInterface getPropUserRelService() {
		return propUserRelService;
	}


	public void setPropUserRelService(
			PropertyUserRelationInterface propUserRelService) {
		this.propUserRelService = propUserRelService;
	}

	private List<PropUserVO> propList;
	public List<PropUserVO> getPropList() {
		return propList;
	}

	public void setPropList(List<PropUserVO> propList) {
		this.propList = propList;
	}
	
	private List<PropRentInfoVo> propRentList;
	public List<PropRentInfoVo> getPropRentList() {
		return propRentList;
	}


	public void setPropRentList(List<PropRentInfoVo> propRentList) {
		this.propRentList = propRentList;
	}

}
