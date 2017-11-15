package com.prop.mnt.logout.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prop.mnt.common.BaseActionSupport;
import com.prop.mnt.dao.LogonDAO;

public class LogoutAction extends BaseActionSupport {
	private static final long serialVersionUID = 5515593704788890868L;
	private static final Logger log = LoggerFactory.getLogger(LogoutAction.class);
	private LogonDAO logonBn = new LogonDAO();
	
	public String logout(){
		try{
			//logonBn.updateLogout();
			return "success";
		}catch(Exception e){
			return "error";
		}        		
	}

}
