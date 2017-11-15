package com.prop.mnt.logon.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.prop.mnt.common.BaseActionSupport;
import com.prop.mnt.common.SessionListener;
import com.prop.mnt.model.Modules;
import com.prop.mnt.model.User;
import com.prop.mnt.service.LoginService;
import com.prop.mnt.service.LoginServiceImpl;

public class LoginAction extends BaseActionSupport implements SessionAware{

	private static final long serialVersionUID = 5515593704788890868L;
	private static final Logger log = LoggerFactory.getLogger(LoginAction.class);
	private String userid;
	private String passwrd;
	//private LogonDAO logonBn = new LogonDAO();
	private LoginService loginService;
	//private PermissionDAO permBean = new PermissionDAO();
	private ServletContext scontext;
	private String firstPage;
	Map<String,Object> m;
	public void setSession(Map<String,Object> m)
    {
    	this.m=m;
    }
	
	public LoginAction(){
		loginService=new LoginServiceImpl();
	}
	
	
	public String display(){
		return "success";
	}
	public String execute(){
		try{
			 log.info("LoginAction: execute");
			  User u= loginService.checkLogon(userid, passwrd);
			  if(u == null){
				  addActionError("Not A Valid User");
				  return "invalid";
			  }
			  
			  List<Modules> mods=new ArrayList<Modules>();
	          if(u.getRole()!=null && u.getRole().equals("OWN")){	
	        	  mods=loginService.selectPropRels(u.getId(),"OWN");
	           }else mods=loginService.selectPropRels(u.getId(),null);  
	        
	          if(mods!=null && mods.isEmpty()) {
	        	 addActionError("No Role Assigned To This User");
				  return "invalid";
	         }
	         
	          
	      m.put("modules", mods);    
	      m.put("userid", u.getId());
	      m.put("username", u.getUserId());
	      m.put("role", u.getRole()!=null && u.getRole().equals("OWN")?"OWN":null);
	          
	          //if(!roles.isEmpty()) 
	          //modules=logdao.geModulesList(roles);  
	          //else  {
	        //	  addActionError("No Role Found");
			//	  return "invalid";
	         // }
			//logonBn.set	
			//permBean.setModuleList(modules);
			//for(Modules mod:modules){
			//	System.out.println(mod.getModuleName()+"=="+mod.getPages().size());
		//	}
			
			
		//  String msg = logonBn.checkLogon(userid, passwrd);
		 // if(msg != null){
			 // addActionError(msg);
			 // return "invalid";
		  //}
         // permBean.setModules();
         // log.info("Modules:"+permBean.getModules());
         // permBean.setPages();
         // permBean.setUserPerms(userid);
         // permBean.setUserModules();
         // permBean.setUserPages();
         // logonBn.updateLogin(userid);
         // ActionContext.getContext().getSession().put("modules", mods);
         //ActionContext.getContext().getSession().put("logonBn", logonBn);
        //  ActionContext.getContext().getSession().put("userid", u.getId());
         // ActionContext.getContext().getSession().put("sesslistener", new SessionListener(scontext, userid));
          //log.info("in promis:"+permBean.getFirstPage());
          //firstPage = permBean.getPagePath(permBean.getFirstPage());
         // System.out.println("first page=="+modules.get(0).getPages().get(0).getPath());
          firstPage = mods.get(0).getPages().get(0).getPath();
          System.out.println("firstPage=="+firstPage);
          String errpath = "common/showerr.jsp?err=Permissions are not set";
          return "success";
		}catch(Exception e){
			//log.error("Exception in execute:"+e);
			e.printStackTrace();
			return "error";
		}
	}	
	public String logout(){
		try{
			if(m!=null || !m.isEmpty()){
			loginService.updateLogout(m.get("userid"));
			m.clear();
			}
			return "success";
		}catch(Exception e){
			log.error("Exception in logout:"+e);
			return "error";
		}
	}
	
	public String index(){
		
			return "success";
		
	}
	
	
	public void setServletContext(ServletContext ctx) {
		this.scontext = ctx;
		
	}
	public String getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(String firstPage) {
		this.firstPage = firstPage;
	}
	/*public LogonDAO getLogonBn() {
		return logonBn;
	}
	public void setLogonBn(LogonDAO logonBn) {
		this.logonBn = logonBn;
	}
	public PermissionDAO getPermBean() {
		return permBean;
	}
	public void setPermBean(PermissionDAO permBean) {
		this.permBean = permBean;
	}*/
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

}
