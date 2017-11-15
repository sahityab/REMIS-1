package com.prop.mnt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="NOTIFICATION")
public class Notification {
	
	    @Id
	    @GeneratedValue
	    @Column(name = "NOTIFICATION_ID") 
	    private Integer notificationId;
	 
	     @Column(name="USER_ID")
	     private Integer userId;
	     
	     @Column(name="NOTIFICATION_FROM")
	     private String notiFrom;  
	     
	     @Column(name="NOTIFICATION_DESC")
	     private String notifDesc;  
	     
	     
	     @Column(name="STATUS")
	     private String status;

	     @Column(name="CREATED_DATE")
	     private Date createdDate;
	     

	     
		public Date getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}


		public Integer getNotificationId() {
			return notificationId;
		}


		public void setNotificationId(Integer notificationId) {
			this.notificationId = notificationId;
		}

		public Integer getUserId() {
			return userId;
		}
		public void setUserId(Integer userId) {
			this.userId = userId;
		}


		public String getNotiFrom() {
			return notiFrom;
		}


		public void setNotiFrom(String notiFrom) {
			this.notiFrom = notiFrom;
		}


		public String getNotifDesc() {
			return notifDesc;
		}


		public void setNotifDesc(String notifDesc) {
			this.notifDesc = notifDesc;
		}


		public String getStatus() {
			return status;
		}


		public void setStatus(String status) {
			this.status = status;
		}
	 
	     
	     
	     
}
