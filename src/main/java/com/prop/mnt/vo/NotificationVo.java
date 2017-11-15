package com.prop.mnt.vo;

import java.util.Date;


public class NotificationVo {

	    private Integer notificationId;
	 
	     private Integer userId;
	     
	     private String notiFrom;  
	     
	     private String notifDesc;  
	     
	     private String status;

	     private Date createdDate;

	     
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

		public Date getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}
	
}
