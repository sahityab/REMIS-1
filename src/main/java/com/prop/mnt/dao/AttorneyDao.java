package com.prop.mnt.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.prop.mnt.model.AttCase;
import com.prop.mnt.model.CaseHearing;
import com.prop.mnt.model.Notification;
import com.prop.mnt.model.PropUser;
import com.prop.mnt.model.Property;
import com.prop.mnt.model.User;
import com.prop.mnt.util.HibernateUtil;
import com.prop.mnt.vo.AttorneyVo;

public class AttorneyDao {

	public Integer  saveAttCase(AttCase attCase){
	    int i=0;
	    Notification note=new Notification();
	   Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        try {
	            session.beginTransaction();
	            
	            session.save(attCase);
	            i++;
	            note.setUserId(attCase.getProperty().getUser().getId());
	            note.setCreatedDate(new Date());
	            note.setNotiFrom("ATT");
	            note.setStatus("A");
	            note.setNotifDesc("Case Created On "+attCase.getProperty().getPropName()+" Property By Attorney"+attCase.getAttUser().getFirstName()+", "+attCase.getAttUser().getLastName());
	            createNotification(note,session);
	        }
	        catch (HibernateException e) {
	            e.printStackTrace();
	            session.getTransaction().rollback();
	        }
	        finally{
	        	session.getTransaction().commit();
	        	//if(session!=null)session.close();
	        }
	        return i;
    }
	
	public Integer  updateAttCase(AttCase attCase){
	    int i=0;
	   Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        try {
	            session.beginTransaction();
	            session.update(attCase);
	            i++;
	            Notification note=new Notification();
	            note.setUserId(attCase.getProperty().getUser().getId());
	            note.setCreatedDate(new Date());
	            note.setNotiFrom("ATT");
	            note.setStatus("A");
	            note.setNotifDesc("Case Updated Property# "+attCase.getProperty().getPropName()+"  By Attorney"+attCase.getAttUser().getFirstName()+", "+attCase.getAttUser().getLastName());
	            createNotification(note,session);
	        }
	        catch (HibernateException e) {
	            e.printStackTrace();
	            session.getTransaction().rollback();
	        }
	        finally{
	        	session.getTransaction().commit();
	        	//if(session!=null)session.close();
	        }
	        return i;
    }
	
	private void createNotification(Notification notify,Session session){
		 try {
	            //session.beginTransaction();
	            session.save(notify);
	        }
	        catch (HibernateException e) {
	            e.printStackTrace();
	           // session.getTransaction().rollback();
	        }
	}
	
	public Integer resetIsnew(int caseId)  
	{
		int i=0;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			System.out.println("session created");
			String sql=" update att_case set isnew=?,newreason=? where case_id=?" ;
			Query query = session.createSQLQuery(sql);
			query.setBoolean(0,false);
			query.setString(1, null);
			query.setInteger(2,caseId);
			query.executeUpdate();
				i=1;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return i;
		}
		session.getTransaction().commit();
		return i;
	}

	
	public Integer resetHearingIsnew(int caseId,int hearingId)  
	{
		int i=0;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			System.out.println("session created");
			String sql=" update case_hearing set isnew=?,newreason=? where case_id=? and casehear_id=?" ;
			Query query = session.createSQLQuery(sql);
			query.setBoolean(0,false);
			query.setString(1, null);
			query.setInteger(2,caseId);
			query.setInteger(3,hearingId);
			query.executeUpdate();
				i=1;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return i;
		}
		session.getTransaction().commit();
		return i;
	}

	public Integer  saveCaseHearing(CaseHearing caseHearing){
	    int i=0;
	   Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        try {
	               session.beginTransaction();
	               session.save(caseHearing);
	               i++;
	               
	               Notification note=new Notification();
		            note.setUserId(caseHearing.getAttCase().getProperty().getUser().getId());
		            note.setCreatedDate(new Date());
		            note.setNotiFrom("ATT");
		            note.setStatus("A");
		            note.setNotifDesc("New Hearing On Case#"+caseHearing.getAttCase().getCaseName()+" Property# "+caseHearing.getAttCase().getProperty().getPropName());
		            createNotification(note,session);

	        }
	        catch (HibernateException e) {
	            e.printStackTrace();
	            session.getTransaction().rollback();
	        }
	        finally{
	        	session.getTransaction().commit();
	        }
	        return i;
	 }

	public Integer updateCaseHearing(CaseHearing caseHearing) 
	{
		 int i=0;
		   Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		        try {
		            session.beginTransaction();
		            session.update(caseHearing);
		            i++;
		            
		            Notification note=new Notification();
		            note.setUserId(caseHearing.getAttCase().getProperty().getUser().getId());
		            note.setCreatedDate(new Date());
		            note.setNotiFrom("ATT");
		            note.setStatus("A");
		            note.setNotifDesc("Case Hearing Updated. Case#"+caseHearing.getAttCase().getCaseName()+" Property# "+caseHearing.getAttCase().getProperty().getPropName());
		            createNotification(note,session); 
		        }
		        catch (HibernateException e) {
		            e.printStackTrace();
		            session.getTransaction().rollback();
		        }
		        finally{
		        	session.getTransaction().commit();
		        	//if(session!=null)session.close();
		        }
		        return i;
	}

	public CaseHearing  viewCaseHear(int casehearId){
	    CaseHearing wp=null;
		   Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		        try {
		        	System.out.println("viewCaseHear=="+casehearId);
		            session.beginTransaction();
		            wp = (CaseHearing) session.get(CaseHearing.class, casehearId);
		        }
		        catch (HibernateException e) {
		            e.printStackTrace();
		            session.getTransaction().rollback();
		        }
		        finally{
		        	 session.getTransaction().commit();
		        }
		  return wp;
	}

	public AttCase  viewAttCase(int caseId){
	    AttCase attCase=null;
		   Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		        try {
		            session.beginTransaction();
		             
		            attCase = (AttCase) session.get(AttCase.class, caseId);
		            Collection<CaseHearing> hearList=attCase.getCaseHearing();
		            System.out.println("hearSize="+hearList.size());
		        }
		        catch (HibernateException e) {
		            e.printStackTrace();
		            session.getTransaction().rollback();
		        }
		        finally{
		        	 session.getTransaction().commit();
		        }
		  return attCase;
	}

	public List<CaseHearing> getCaseHearings(Integer caseId, int limit){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<CaseHearing> hearList=null;
			try {
				session.beginTransaction();
				Criteria criteria = session.createCriteria(CaseHearing.class, "ch").
			                          add(Restrictions.eq("ch.attCase.caseId",caseId));
				criteria.addOrder(Order.desc("casehearId"));
				criteria.setMaxResults(limit);
				
				hearList=criteria.list();
				   
			}catch (HibernateException e) {
	            e.printStackTrace();
	            session.getTransaction().rollback();
	        }
	        finally{
	        	session.getTransaction().commit();
	        }
		return hearList;
		}


		@SuppressWarnings("unchecked")
		public List<AttorneyVo> getAttProperties(int attId)
		{
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			List<AttorneyVo> attPropList=new ArrayList<AttorneyVo>();
			try {
				//System.out.println("session created");
				List<PropUser> propUserRelList = (List<PropUser>)session.createQuery("from PropUser pu join fetch pu.property join fetch pu.user where pu.status='A' and pu.property.status='A' and pu.user.id="+attId+" and pu.propUserRel.id='ATT'").list();
				AttorneyVo attovo=null;
				for(PropUser propuser:propUserRelList){
					attovo=new AttorneyVo();
					attovo.setPropertyId(propuser.getProperty().getPropCode());
					attovo.setPropertyName(propuser.getProperty().getPropName()+"#"+ propuser.getProperty().getUser().getFirstName()+", "+propuser.getProperty().getUser().getLastName());
					attPropList.add(attovo);
				}
				
				
			} catch (HibernateException e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}
			session.getTransaction().commit();
		   System.out.println("propUserRelList size="+attPropList.size());
			return attPropList;
		}


	public List<AttCase> getOwnerCases(Integer ownerId){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<AttCase> contractList=null;
			try {
				session.beginTransaction();
				Criteria criteria = session.createCriteria(AttCase.class, "ac").
					createAlias("ac.property", "cp").createAlias("cp.user", "cu").add(Restrictions.eq("cu.id",ownerId)).add(Restrictions.ne("ac.status","closed"));
				contractList=criteria.list();
				   
			}catch (HibernateException e) {
	            e.printStackTrace();
	            session.getTransaction().rollback();
	        }
	        finally{
	        	session.getTransaction().commit();
	        }
		return contractList;
	}
	
	public List<AttCase> getAttorneyCases(Integer attId){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<AttCase> contractList=null;
			try {
				session.beginTransaction();
				Criteria criteria = session.createCriteria(AttCase.class, "ct").add(Restrictions.eq("ct.attUser.id",attId)).add(Restrictions.ne("ct.status","closed"));
				contractList=criteria.list();
				   
			}catch (HibernateException e) {
	            e.printStackTrace();
	            session.getTransaction().rollback();
	        }
	        finally{
	        	session.getTransaction().commit();
	        }
		return contractList;
	}

	public String getUsername(int id)	{
		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
       Criteria crit = session.createCriteria(User.class,"u").add(Restrictions.eq("u.id",id));
       ProjectionList proList = Projections.projectionList();
       proList.add(Projections.property("firstName"));
       proList.add(Projections.property("lastName"));
       crit.setProjection(proList);

       Object[] row = (Object[])crit.uniqueResult();
           return row[0]+""+row[1];
       }
	
	public Property	findByPropertyId(int id){
		   Property property=null;
		   Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		        try {
		            session.beginTransaction();
		             
		            property = (Property) session.get(Property.class, id);
		           
		        }
		        catch (HibernateException e) {
		            e.printStackTrace();
		            session.getTransaction().rollback();
		        }
		        finally{
		        	 session.getTransaction().commit();
		        }
		  return property;
	}
	
	public User	findByUserId(int id){
		   User user=null;
		   Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		        try {
		            session.beginTransaction();
		             
		            user = (User) session.get(User.class, id);
		            session.getTransaction().commit();
		           }
		            catch (HibernateException e) {
		            e.printStackTrace();
		            session.getTransaction().rollback();
		           }
		            finally{
			        	//if(session!=null)session.close();
			        }
		  return user;
	}
	
}
