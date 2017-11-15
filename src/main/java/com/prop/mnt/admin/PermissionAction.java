package com.prop.mnt.admin;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.prop.mnt.common.BaseActionSupport;

public class PermissionAction extends BaseActionSupport {

	private static final long serialVersionUID = 5515593704788890868L;
	private static final Logger log = LoggerFactory.getLogger(PermissionAction.class);
	private PermissionDAO permBean = null;
	private String userList = "select";
	private HashMap<String, String> usersList;
	
	public String defaultData(){
        try {
        	permBean = (PermissionDAO)ActionContext.getContext().getSession().get("permBean");
        	if(permBean == null){
        		permBean = new PermissionDAO();
    			permBean.setModules();
    			permBean.setPages();        		
        	}
        	usersList = permBean.getUserList();
			ActionContext.getContext().getSession().put("permBean", permBean);
			return "success";
		} catch (Exception e) {
			log.error("Exception in defaultData:"+e);
			return "error";
		}
	}
	public String display(){
		try{
        	permBean = (PermissionDAO)ActionContext.getContext().getSession().get("permBean");
        	if(permBean == null){
        		permBean = new PermissionDAO();
    			permBean.setModules();
    			permBean.setPages();        		
        	}
        	usersList = permBean.getUserList();
	        ActionContext.getContext().getSession().put("permBean", permBean);
	        permBean.setUserPerms(userList);
	        return "success";
		} catch (Exception e) {
			log.error("Exception in display:"+e);
			return "error";
		}
	}
	public String execute(){
		try{
			Map<String, Object> requestMap = ActionContext.getContext().getParameters();
			permBean = (PermissionDAO)ActionContext.getContext().getSession().get("permBean");
			if(userList == null || "select".equals(userList)){
				addActionError("Please select user.");
				return "input";
			}
			permBean.saveData(requestMap, userList);
			usersList = permBean.getUserList();
			userList = "select";
			addActionMessage("Permissions saved successfully.");
			return "success";
		} catch (Exception e) {
			log.error("Exception in execute:"+e);
			return "error";
		}
	}
	public String getUserList() {
		return userList;
	}
	public void setUserList(String userList) {
		this.userList = userList;
	}
	public HashMap<String, String> getUsersList() {
		return usersList;
	}
	public void setUsersList(HashMap<String, String> usersList) {
		this.usersList = usersList;
	}
	
}
