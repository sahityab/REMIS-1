package com.prop.mnt.property.user.relation;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.prop.mnt.admin.user.UserDAO;
import com.prop.mnt.admin.user.UserVO;
import com.prop.mnt.property.property.PropertyDAO;
import com.prop.mnt.property.property.PropertyVO;
import com.prop.mnt.vo.PropRentInfoVo;


public class PropertyUserRelationAction extends ActionSupport{
	
	private static final Logger log = LoggerFactory.getLogger(PropertyUserRelationAction.class);
//	private ClassPathXmlApplicationContext ctx;
	private PropertyDAO propertyBn ;
	private UserDAO usersBn ;
	private PropertyUserRelationInterface propUserRelService;
	//private PropUserVO propUser = new PropUserVO();
	private List<UserVO> userList= new ArrayList<UserVO>();
	private List<PropUserRelVO> relList= new ArrayList<PropUserRelVO>();
	private List<PropUserVO> propList= new ArrayList<PropUserVO>();
	private PropUserVO propUserVO = new PropUserVO();
	//private List<SubPropertyVo> subPropertyList=new ArrayList<SubPropertyVo>();;
	
	Integer userId=null;
	public PropertyUserRelationAction(){
		propertyBn = new PropertyDAO();
		usersBn = new UserDAO();
		propUserRelService=new PropertyUserRelationService();
		 userId=(Integer)ActionContext.getContext().getSession().get("userid");
	}
	

	public String displayRelation(){
		try{
			setDefaultData();
			return "input";
		}catch(Exception e){
			log.error("Exception in display:"+e);
			e.printStackTrace();
			return "error";
		}
	}
	public String execute(){
		return "success";
	}
	
	public void setDefaultData() throws Exception{
		this.setPropList(propUserRelService.findProperties(userId));
		this.setUserList(usersBn.getDefaultData(userId));
		this.setRelList(propUserRelService.getRelationListService());
	}
	
	public String saveRelation(){
		String ret="";
		try{
			System.out.println("UID="+propUserVO.getUserID()+""+"ProID="+propUserVO.getPropertyID()+"RelID="+propUserVO.getRelationCode());
		int i = propUserRelService.saveRelation(propUserVO);
			if (i==1){
				addActionMessage("Record Saved Successfully");
				setDefaultData();
				ret= "success";
			}
			else if(i==2){
				addActionMessage("Record already exists");
				setDefaultData();
				ret= "input";
			}else{
				addActionMessage("Record cannot be saved");
				setDefaultData();
				ret= "input";
			}
			
			setDefaultData();
			//if(propUserVO.getSubProperty().getId()>0)this.setSubPropertyList(propUserRelService.findSubProperties(propUserVO.getPropertyID()));
			
		}catch(Exception e){
			log.error("Exception in display:"+e);
			e.printStackTrace();
			return "error";
		}
		return ret;
	}
	
	public String deleteRelation(){
		try{
		int i = propUserRelService.deleteRelation(propUserVO);
			if (i==1){
				addActionMessage("Record Deleted Successfully");
				setDefaultData();
				return "success";
			}
			else{
				addActionMessage("Record cannot be deleted");
				setDefaultData();
				return "input";
			}
		}catch(Exception e){
			log.error("Exception in display:"+e);
			e.printStackTrace();
			return "error";
		}
		
	}
	
	
/*	
	public List<SubPropertyVo> getSubPropertyList() {
		return subPropertyList;
	}


	public void setSubPropertyList(List<SubPropertyVo> subPropertyList) {
		this.subPropertyList = subPropertyList;
	}*/


	public List<UserVO> getUserList() {
		return userList;
	}
	public void setUserList(List<UserVO> userList) {
		this.userList = userList;
	}
	public List<PropUserRelVO> getRelList() {
		return relList;
	}
	public void setRelList(List<PropUserRelVO> relList) {
		this.relList = relList;
	}


	public List<PropUserVO> getPropList() {
		return propList;
	}


	public void setPropList(List<PropUserVO> propList) {
		this.propList = propList;
	}


	public PropertyDAO getPropertyBn() {
		return propertyBn;
	}


	public void setPropertyBn(PropertyDAO propertyBn) {
		this.propertyBn = propertyBn;
	}


	public UserDAO getUsersBn() {
		return usersBn;
	}


	public void setUsersBn(UserDAO usersBn) {
		this.usersBn = usersBn;
	}


	public PropertyUserRelationInterface getPropUserRelService() {
		return propUserRelService;
	}


	public void setPropUserRelService(
			PropertyUserRelationInterface propUserRelService) {
		this.propUserRelService = propUserRelService;
	}


	public PropUserVO getPropUserVO() {
		return propUserVO;
	}


	public void setPropUserVO(PropUserVO propUserVO) {
		this.propUserVO = propUserVO;
	}
	
	
}
