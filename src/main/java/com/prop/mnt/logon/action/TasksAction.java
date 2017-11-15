package com.prop.mnt.logon.action;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;
import com.prop.mnt.common.BaseActionSupport;
import com.prop.mnt.service.TasksService;
import com.prop.mnt.service.TasksServiceImpl;
import com.prop.mnt.vo.NotificationVo;

public class TasksAction extends BaseActionSupport implements Preparable {
	private static final Logger log = LoggerFactory.getLogger(TasksAction.class);
	Integer userId;
	private TasksService tasksService;
	private NotificationVo notiVo;
	private List<NotificationVo> notifiList=new ArrayList<NotificationVo>();
	
	
	public void prepare(){
		try{
			System.out.println("TasksAction prepare construct");
			 userId=(Integer)ActionContext.getContext().getSession().get("userid");
			 tasksService=new TasksServiceImpl();
		}catch(Exception e){
			log.error("Exception in prepare:"+e);
		}
  	}
	
	public	 String ownerTasks(){ 
		
		String role=(String)ActionContext.getContext().getSession().get("role");
		if(role!=null && role.equals("OWN")){
			this.setNotifiList(tasksService.notifyList(userId));
			System.out.println("userId = "+userId+" role="+role+"  noti size="+notifiList.size());
		}
		else return "error_page";
		
		return "success";
	}

	public List<NotificationVo> getNotifiList() {
		return notifiList;
	}

	public void setNotifiList(List<NotificationVo> notifiList) {
		this.notifiList = notifiList;
	}

	public NotificationVo getNotiVo() {
		return notiVo;
	}

	public void setNotiVo(NotificationVo notiVo) {
		this.notiVo = notiVo;
	}
		
}
