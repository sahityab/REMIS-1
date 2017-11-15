package com.prop.mnt.service;

import java.util.ArrayList;
import java.util.List;

import com.prop.mnt.dao.TasksDao;
import com.prop.mnt.model.Notification;
import com.prop.mnt.vo.NotificationVo;

public class TasksServiceImpl implements TasksService{

	TasksDao taskDao;
	public TasksServiceImpl(){
		taskDao= new TasksDao();
	}
	
	public List<NotificationVo> notifyList(int userId){
		List<Notification> notList=taskDao.notifyList(userId);
		 List<NotificationVo> notVoList=new  ArrayList<NotificationVo>();
		 NotificationVo  notvo=null;
		for(Notification noti:notList){
			notvo=new NotificationVo();
			notvo.setNotificationId(noti.getNotificationId());
			notvo.setCreatedDate(noti.getCreatedDate());
			notvo.setNotifDesc(noti.getNotifDesc());
			notVoList.add(notvo);	
		}
		return notVoList;
	}	
}
