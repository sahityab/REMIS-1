package com.prop.mnt.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.prop.mnt.model.Notification;
import com.prop.mnt.util.HibernateUtil;

public class TasksDao {

	public List<Notification> notifyList(int userId){
	
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	List<Notification> notifiList=null;
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Notification.class, "ch").
		                          add(Restrictions.eq("ch.userId",userId)).add(Restrictions.eq("ch.status","A"));
			criteria.addOrder(Order.desc("createdDate"));
			notifiList=criteria.list();
			   
		}catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        finally{
        	session.getTransaction().commit();
        }
	return notifiList;
	
	}
}
