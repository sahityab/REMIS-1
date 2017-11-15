package com.prop.mnt.admin.user;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.Preparable;
import com.prop.mnt.common.BaseActionSupport;
import com.prop.mnt.common.StaticDataBean;
import com.prop.mnt.model.User;

public class UserAction  extends BaseActionSupport implements Preparable {

	private static final long serialVersionUID = 5515593704788890868L;
	private static final Logger log = LoggerFactory.getLogger(UserAction.class);
	private UserDAO usersBn = new UserDAO();
	private UserVO userVo;
	private List<UserVO> userList;
	private Map<String, String> stateList;
	private String userId;
	
	public void prepare(){
		try{
			userList = usersBn.getDefaultData();
			stateList = StaticDataBean.getObject().getUSStates();
			System.out.println("action prepare method==");
		}catch(Exception e){
			log.error("Exception in display:"+e);
		}
	}
	
	public String display(){
		//int i=setDefaults("display");
		 userVo = new UserVO();
		System.out.println("action method==display ");
		return "success";
	}
	public String showUser(){
		try{
			userVo = usersBn.findUser(userVo.getUserId());
			System.out.println("action showUser method=="+userVo.getUserId());
			//userList = usersBn.getDefaultData();	
			//stateList = StaticDataBean.getObject().getUSStates();
			//userRoles=usersBn.getUserRoles();
	        return "success";
		}catch(Exception e){
			log.error("Exception in display:"+e);
			return "error";
		}
	}
	public String execute(){
		try{
			User user =null;
			System.out.println("user ID=="+userVo.getId());
			if(userVo.getId()==null){
				user = usersBn.saveUser(userVo);
		        if( user !=null){
		        	addActionMessage("User created successfully.");
		        }
		        else addActionMessage("UserId already exist.");
			}else{
				user = usersBn.updateUserData(userVo);
		        if( user.getId()>0 ){
		        	addActionMessage("User updated successfully.");
		        }				
			}
	        userVo = new UserVO();
	        userList = usersBn.getDefaultData();		
	        stateList = StaticDataBean.getObject().getUSStates();
	        return "success";
		}catch(Exception e){
			e.printStackTrace();
			log.error("Exception in execute:"+e);
			return "error";
		}        
	}
	public String deleteUser(){
		try{
			int i = usersBn.deleteUser(userVo.getUserId());
	        if( i > 0){
	        	addActionMessage("User deleted successfully.");
	        }
	        userVo = new UserVO();
			userList = usersBn.getDefaultData();	
			stateList = StaticDataBean.getObject().getUSStates();
			return "success";
		}catch(Exception e){
			log.error("Exception in deleteUser:"+e);
			return "error";
		}        
	}
	
	private int setDefaults(String action){
		System.out.println("action method=="+action);
		int i=0;
		try{
			userVo = new UserVO();
			userList = usersBn.getDefaultData();
			stateList = StaticDataBean.getObject().getUSStates();
			i=1;
		}catch(Exception e){
			log.error("Exception in display:"+e);
		}
		return i;
	}
	public UserVO getUserVo() {
		return userVo;
	}
	public void setUserVo(UserVO userVo) {
		this.userVo = userVo;
	}
	public List<UserVO> getUserList() {
		return userList;
	}
	public void setUserList(List<UserVO> userList) {
		this.userList = userList;
	}
	public Map<String, String> getStateList() {
		return stateList;
	}
	public void setStateList(Map<String, String> stateList) {
		this.stateList = stateList;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	
	
/*	@Override
	public void validate(){
		if(userVo != null){
			if( "create".equals(userVo.getUserId()) && ( userVo.getNewUserId() == null || "".equals(userVo.getNewUserId().trim()) ) ){
				addActionError(getText("error.user.new.userid.required"));
			}
		}
*/	}
