package com.prop.mnt.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.prop.mnt.model.Contract;
import com.prop.mnt.model.ContractWorkProgress;
import com.prop.mnt.model.PropUser;
import com.prop.mnt.model.Property;
import com.prop.mnt.model.User;
import com.prop.mnt.util.HibernateUtil;
import com.prop.mnt.vo.ContractVo;

public class ContractDao {

	
	public Integer  saveContract(Contract contract){
		    int i=0;
		   Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		        try {
		            session.beginTransaction();
		            
		            session.save(contract);
		            i++;
		            ContractWorkProgress work=new ContractWorkProgress();
		            work.setContract(contract);
		            work.setIsnew(true);
		            work.setWorkProgress("Contract Created");
		            work.setCreated(new Date());
		            session.save(work);
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
	
	public Integer  updateContract(Contract contract){
	    int i=0;
	   Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        try {
	            session.beginTransaction();
	            session.save(contract);
	            i++;
	            ContractWorkProgress work=new ContractWorkProgress();
	            work.setContract(contract);
	            work.setIsnew(true);
	            work.setWorkProgress("Contract Updated");
	            work.setCreated(new Date());
	            session.save(work);
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
	public Integer  saveWorkProgress(ContractWorkProgress work){
	    int i=0;
	   Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        try {
	               session.beginTransaction();
	               session.save(work);
	               updateContractStatus(work.getContract().getContractId(), "Started");
	               i++;
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
	
	public Integer updateContractStatus(int contractId,String status)  
	{
		int i=0;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			System.out.println("session created");
			String sql=" update contract set status=? where CONTRACT_ID=?" ;
			Query query = session.createSQLQuery(sql);
			query.setString(0,status);
			query.setInteger(1,contractId);
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
	
	
	public Integer updateWorkProgress(int contractId,int workprogId)
	{
		int i=0;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			System.out.println("session created");
			String sql="update contract_work_progress set ISNEW=false where  CONTRACT_ID=? and WORKPROGRESS_ID=?" ;
			Query query = session.createSQLQuery(sql);
			query.setInteger(0,contractId);
			query.setInteger(1,workprogId);
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
	
	public Integer updateWorkProgress(int contractId)
	{
		int i=0;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			System.out.println("session created");
			String sql="update contract_work_progress set ISNEW=false where  CONTRACT_ID=?" ;
			Query query = session.createSQLQuery(sql);
			query.setInteger(0,contractId);
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
	
	
	public Integer updateWorkProgressActionNeed(int contractId)  throws Exception
	{
		int i=0;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			System.out.println("session created");
			String sql="update contract_work_progress set ACTIONNEED=NO, ISNEW=false where  CONTRACT_ID=?" ;
			Query query = session.createSQLQuery(sql);
			query.setInteger(0,contractId);
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
	
	public ContractWorkProgress  viewWorkProgress(int workId){
	    int i=0;
	    ContractWorkProgress wp=null;
		   Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		        try {
		            session.beginTransaction();
		             
		            wp = (ContractWorkProgress) session.get(ContractWorkProgress.class, workId);
		           
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
	
	public Contract  viewContract(int contractId){
	    int i=0;
	    Contract contract=null;
		   Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		        try {
		            session.beginTransaction();
		             
		            contract = (Contract) session.get(Contract.class, contractId);
		           
		        }
		        catch (HibernateException e) {
		            e.printStackTrace();
		            session.getTransaction().rollback();
		        }
		        finally{
		        	 session.getTransaction().commit();
		        }
		  return contract;
    }
	
	public List<ContractWorkProgress> getContractWorkProgress(Integer contractId, int limit){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<ContractWorkProgress> workList=null;
			try {
				session.beginTransaction();
				Criteria criteria = session.createCriteria(ContractWorkProgress.class, "wp").
			                          add(Restrictions.eq("wp.contract.contractId",contractId));
				criteria.addOrder(Order.desc("workprogressId"));
				criteria.setMaxResults(limit);
				
				workList=criteria.list();
				   
			}catch (HibernateException e) {
	            e.printStackTrace();
	            session.getTransaction().rollback();
	        }
	        finally{
	        	session.getTransaction().commit();
	        }
		return workList;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ContractVo> getContractUsers(int propId)
	{
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<ContractVo> rentPropList=new ArrayList<ContractVo>();
		try {
			//System.out.println("session created");
			List<PropUser> propUserRelList = (List<PropUser>)session.createQuery("from PropUser pu join fetch pu.property join fetch pu.user where pu.status='A' and pu.property.propCode="+propId+" and pu.propUserRel.id='CTR'").list();
			ContractVo propuservo=null;
			for(PropUser propuser:propUserRelList){
				propuservo=new ContractVo();
				propuservo.setContratorId(propuser.getUser().getId());
				propuservo.setContractorName(propuser.getUser().getFirstName()+", "+propuser.getUser().getLastName());
				rentPropList.add(propuservo);
			}
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
	   System.out.println("propUserRelList size="+rentPropList.size());
		return rentPropList;
	}
	
	
		public List<Contract> getCurrentOwnerContracts(Integer ownerId){
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			List<Contract> contractList=null;
				try {
					session.beginTransaction();
					Criteria criteria = session.createCriteria(Contract.class, "ct").
					createAlias("ct.property", "cp").createAlias("cp.user", "cu").add(Restrictions.eq("cu.id",ownerId)).add(Restrictions.ne("ct.status","closed"));
					criteria.addOrder(Order.desc("contractId"));
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
		
		public List<Contract> getCurrentContractorContracts(Integer contractorId){
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			List<Contract> contractList=null;
				try {
					session.beginTransaction();
					Criteria criteria = session.createCriteria(Contract.class, "ct").
					createAlias("ct.user", "cu").add(Restrictions.eq("cu.id",contractorId)).add(Restrictions.ne("ct.status","closed"));
					criteria.addOrder(Order.desc("contractId"));
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
	
		
		public List<PropUser> getPropertyContractors(Integer propId){
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			List<PropUser> propUserList=null;
				try {
					Criteria criteria = session.createCriteria(PropUser.class, "pu")
							.createAlias("pu.User", "ppu")
							//.createCriteria("ppu.id").createCriteria("ppu.firstName").createCriteria("ppu.firstName")
						    .add(Restrictions.eq("pu.property.propCode",propId)).add(Restrictions.eq("pu.status","A"));
					
					   propUserList=criteria.list();
				}catch (HibernateException e) {
		            e.printStackTrace();
		            session.getTransaction().rollback();
		        }
		        finally{
		        	session.getTransaction().commit();
		        }
			return propUserList;
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
		
		public Integer updateNotification(int notifyId)  
		{
			int i=0;
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			try {
				System.out.println("session created");
				String sql=" update notification set status=? where NOTIFICATION_ID=?" ;
				Query query = session.createSQLQuery(sql);
				query.setString(0,"D");
				query.setInteger(1,notifyId);
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
		
		
		
		
}
