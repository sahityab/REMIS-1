package com.prop.mnt.changepwd.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prop.mnt.common.BaseActionSupport;
import com.prop.mnt.dao.LogonDAO;
import com.prop.mnt.logon.action.LoginAction;

public class ChangePasswordAction extends BaseActionSupport {
	
	private static final long serialVersionUID = 5515593704788890868L;
	private static final Logger log = LoggerFactory.getLogger(ChangePasswordAction.class);

	private String userid;
	private String passwrd;
	private String newpasswrd;
	private String confirmPasswrd;
	private LogonDAO logonBn = new LogonDAO();
	
	public String display(){
		return "success";
	}
	public String execute(){
		try{
	        String msg = logonBn.checkLogon(userid, passwrd);
	        if(msg != null){
	        	addActionError(msg);
	        	return "invalid";
	        }
	        msg = logonBn.changePassword(userid, newpasswrd);		
	        addActionMessage(msg);
	        return "success";
		}catch(Exception e){
			return "error";
		}        
	}
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPasswrd() {
		return passwrd;
	}

	public void setPasswrd(String passwrd) {
		this.passwrd = passwrd;
	}

	public String getNewpasswrd() {
		return newpasswrd;
	}
	public void setNewpasswrd(String newpasswrd) {
		this.newpasswrd = newpasswrd;
	}
	public String getConfirmPasswrd() {
		return confirmPasswrd;
	}
	public void setConfirmPasswrd(String confirmPasswrd) {
		this.confirmPasswrd = confirmPasswrd;
	}

}
