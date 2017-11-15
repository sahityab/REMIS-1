package com.prop.mnt.property.user.relation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.prop.mnt.model.PropRentInfo;
import com.prop.mnt.model.PropUser;
import com.prop.mnt.model.PropUserRel;
import com.prop.mnt.model.User;
import com.prop.mnt.util.HibernateUtil;
import com.prop.mnt.vo.PropRentInfoVo;

public class PropertyUserRelationDAO extends HibernateUtil {

	public PropertyUserRelationDAO(){}
	
	@SuppressWarnings("unchecked")
	public List<PropUserRel> getRelationList()  throws Exception
	{
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<PropUserRel> propUserRelList = null;
		try {
			System.out.println("session created");
			propUserRelList = (List<PropUserRel>)session.createQuery("from PropUserRel where relationCode!='OWN' ").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		System.out.println("propUserRelList size="+propUserRelList.size());
		return propUserRelList;
	}
	
	@SuppressWarnings("unchecked")
	public PropRentInfoVo getPropertyTenantRentInfo(int propId)
	{
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		PropRentInfoVo propRentInfoVo=null;
		try {
			
		    String sql="SELECT pu.prop_code,u.first_name,u.last_name,prop_rentamount,prop_rentterms,duedate,ri.id  FROM prop_user pu "+
                       "left join prop_rentinfo ri on pu.prop_code=ri.prop_user_id join users u on u.id=pu.userid  where pu.status='A' and pu.relation_code='TNT' and prop_code="+propId;
			Query query = session.createSQLQuery(sql) ;
			List<?> result = query.list();
        	//System.out.println("pages size=="+result.size());
        	Iterator<?> iterator = result.iterator();
        	if (iterator.hasNext()) {
        	    Object[] row = (Object[])iterator.next();
        	    propRentInfoVo=new PropRentInfoVo();
        	    propRentInfoVo.setPropUserID(row[0]==null?null:Integer.parseInt(row[0].toString()));
        	    propRentInfoVo.setTntName(row[1]==null?null:row[1].toString()+", "+row[2].toString());
        	    propRentInfoVo.setRentamount(row[3]==null?null:Float.parseFloat(row[3].toString()));
        	    propRentInfoVo.setRentterms(row[4]==null?null:row[4].toString());
        	    propRentInfoVo.setDuedate(row[5]==null?null:Integer.parseInt(row[5].toString()));
        	   
        	    propRentInfoVo.setId(row[6]==null?null:Integer.parseInt(row[6].toString()));
        	   
        	}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return propRentInfoVo;
	}
	
	@SuppressWarnings("unchecked")
	public List<PropRentInfoVo> getTNTRentalss(int userid)
	{
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<PropRentInfoVo> propRentList=new ArrayList<PropRentInfoVo>();
		try {
			
		    String sql="SELECT ri.id,p.prop_code,p.prop_name, u.first_name,u.last_name,ri.prop_rentamount,ri.duedate,pay_date  FROM property p "+
			   " join prop_user pu on pu.prop_code=p.prop_code join prop_rentinfo ri on pu.prop_code=ri.prop_user_id "+
			   " join users tn on tn.id=pu.userid left join prop_ten_payment ptp on ptp.prop_rentinfo_id=ri.id and MONTH(pay_date) = MONTH(CURDATE()) "+
			   " join users u on u.id=p.userid where pu.status='A' and pu.relation_code='TNT' and pu.userid="+userid;
			Query query = session.createSQLQuery(sql) ;
			List<?> result = query.list();
        	//System.out.println("pages size=="+result.size());
        	Iterator<?> iterator = result.iterator();
        	PropRentInfoVo propRentInfoVo=null;
        	while (iterator.hasNext()) {
        	    Object[] row = (Object[])iterator.next();
        	    propRentInfoVo=new PropRentInfoVo();
        	    propRentInfoVo.setId(row[0]==null?null:Integer.parseInt(row[0].toString()));
        	    propRentInfoVo.setPropName(row[2].toString());
        	    propRentInfoVo.setTntName(row[3]==null?null:row[3].toString()+", "+row[4].toString());
        	    propRentInfoVo.setRentamount(row[5]==null?null:Float.parseFloat(row[5].toString()));
        	   // propRentInfoVo.setRentterms(row[7]==null?null:row[7].toString());
        	    propRentInfoVo.setDuedate(row[6]==null?null:Integer.parseInt(row[6].toString()));
        	    propRentInfoVo.setRentterms(row[7]==null?null:row[7].toString());
        	    propRentInfoVo.setStatus(row[7]==null?"Not Paid":"Paid");
        	    propRentList.add(propRentInfoVo);
        	}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		System.out.println("propRentList"+propRentList.size());
		return propRentList;
	}
	
	public int payRentPayment(Integer rentinfoid,int userid){
		int i=0;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
		 String sql="SELECT ri.prop_rentamount,pay_date  FROM property p "+
			   " join prop_user pu on pu.prop_code=p.prop_code join prop_rentinfo ri on pu.prop_code=ri.prop_user_id "+
			   " join users tn on tn.id=pu.userid left join prop_ten_payment ptp on ptp.prop_rentinfo_id=ri.id and MONTH(pay_date) = MONTH(CURDATE()) "+
			   " join users u on u.id=p.userid where pu.status='A' and pu.relation_code='TNT' and ri.id="+rentinfoid+" and tn.id="+userid;
		 Query query = session.createSQLQuery(sql) ;
		 List<?> result =  query.list();
		 if(result!=null && !result.isEmpty()){
			 Object[] row = (Object[])result.get(0);
			 float amount=row[0]==null?null:Float.parseFloat(row[0].toString());
			 if(row[1]!=null){
				 i=2;
			 }else {
				 String sql1="insert into prop_ten_payment (pay_date, prop_rentamount, prop_rentinfo_id) values (CURDATE(), ?, ?)";
					Query query1 = session.createSQLQuery(sql1);
					query1.setFloat(0,amount);
					query1.setInteger(1,rentinfoid);
					i=query1.executeUpdate();
			 }
		 }
		 
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return i;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<PropRentInfoVo> getTNTPayments(int userid)
	{
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<PropRentInfoVo> propRentList=new ArrayList<PropRentInfoVo>();
		try {
			
		    String sql="SELECT p.prop_code,p.prop_name, tn.first_name,tn.last_name,ri.prop_rentamount,ri.duedate,pay_date  FROM property p "+
			   " join prop_user pu on pu.prop_code=p.prop_code join prop_rentinfo ri on pu.prop_code=ri.prop_user_id "+
			   " join users tn on tn.id=pu.userid left join prop_ten_payment ptp on ptp.prop_rentinfo_id=ri.id and MONTH(pay_date) = MONTH(CURDATE()) "+
			   " join users u on u.id=p.userid where pu.status='A' and pu.relation_code='TNT' and u.id="+userid;
			Query query = session.createSQLQuery(sql) ;
			List<?> result = query.list();
        	//System.out.println("pages size=="+result.size());
        	Iterator<?> iterator = result.iterator();
        	PropRentInfoVo propRentInfoVo=null;
        	while (iterator.hasNext()) {
        	    Object[] row = (Object[])iterator.next();
        	    propRentInfoVo=new PropRentInfoVo();
        	    propRentInfoVo.setPropName(row[1].toString());
        	    propRentInfoVo.setTntName(row[2]==null?null:row[2].toString()+", "+row[3].toString());
        	    propRentInfoVo.setRentamount(row[4]==null?null:Float.parseFloat(row[4].toString()));
        	   // propRentInfoVo.setRentterms(row[7]==null?null:row[7].toString());
        	    propRentInfoVo.setDuedate(row[5]==null?null:Integer.parseInt(row[5].toString()));
        	    propRentInfoVo.setRentterms(row[6]==null?null:row[6].toString());
        	    propRentInfoVo.setStatus(row[6]==null?"Not Paid":"Paid");
        	    propRentList.add(propRentInfoVo);
        	}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		System.out.println("propRentList"+propRentList.size());
		return propRentList;
	}
	
	
	/*@SuppressWarnings("unchecked")
	public List<PropertyDivision> getSubPropertyList(int propId)
	{
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<PropertyDivision> propUserRelList = null;
		try {
			System.out.println("session created");
			propUserRelList = (List<PropertyDivision>)session.createQuery("from PropertyDivision where prop_id="+propId).list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		System.out.println("propUserRelList size="+propUserRelList.size());
		return propUserRelList;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<PropUserRelVO> getUserSubPropertyList(int userId)
	{
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<PropUserRelVO> rentPropList=new ArrayList<PropUserRelVO>();
		try {
			//System.out.println("session created");
			List<PropUser> propUserRelList = (List<PropUser>)session.createQuery("from PropUser pu join fetch pu.property join fetch pu.user join fetch pu.propertyDivision where pu.status='A' and pu.property.user.id="+userId+" and pu.propUserRel.id=3").list();
			PropUserRelVO propuservo=null;
			for(PropUser propuser:propUserRelList){
				propuservo=new PropUserRelVO();
				propuservo.setId(propuser.getId());
				propuservo.setPropName(propuser.getProperty().getPropName());
				if(propuser.getPropertyDivision()!=null)propuservo.setDivisionInfo(propuser.getPropertyDivision().getDivId()+", "+propuser.getPropertyDivision().getDivTitle());
				propuservo.setUserName(propuser.getUser().getFullName());
				propuservo.setRentamount(propuser.getRentamount());
				rentPropList.add(propuservo);
			}
			 propUserRelList.clear();
			 propUserRelList = (List<PropUser>)session.createQuery("from PropUser pu join fetch pu.property join fetch pu.user  where pu.status='A' and pu.propertyDivision.id=0 and  pu.property.user.id="+userId+" and pu.propUserRel.id=3").list();
			//PropUserRelVO propus=null;
			for(PropUser propuser:propUserRelList){
				propuservo=new PropUserRelVO();
				propuservo.setId(propuser.getId());
				propuservo.setPropName(propuser.getProperty().getPropName());
				//if(propuser.getPropertyDivision()!=null)propuservo.setDivisionInfo(propuser.getPropertyDivision().getDivId()+", "+propuser.getPropertyDivision().getDivTitle());
				propuservo.setUserName(propuser.getUser().getFullName());
				propuservo.setRentamount(propuser.getRentamount());
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
	*/
	
	@SuppressWarnings("unchecked")
	public PropUserRel getRelation(int id)  throws Exception
	{
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		PropUserRel propUserRelList = null;
		try {
			Query query = session.createQuery("from PropUserRel where id=:id");
			query.setInteger("id",id);
			propUserRelList=(PropUserRel)query.list().get(0);
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return propUserRelList;
	}
	
	@SuppressWarnings("unchecked")
	public PropUser getPropUser(int propCode)
	{
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		PropUser propUser = null;
		try {
			Query query = session.createQuery("from PropUser where id=:propCode");
			query.setInteger("propCode",propCode);
			propUser=(PropUser)query.list().get(0);
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return propUser;
	}
	
	
	@SuppressWarnings("unchecked")
	public User getUser(int USERID)  throws Exception
	{
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		User user = null;
		try {
			Query query = session.createQuery("from User where id=:USERID");
			query.setInteger("USERID",USERID);
			user=(User)query.list().get(0);
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return user;
	}
	
	
	
	public Integer savePropRent(PropUserVO propUserVO)  throws Exception
	{
		int i=0;
		
		PropUser puDO =null;
		 puDO = selectRecord(propUserVO);
		 System.out.println(puDO);
		if(puDO!=null)
			return 2;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			System.out.println("session created");
			String sql="insert into prop_rent (RELATION_CODE, PROP_CODE, STATUS, USERID) values (?, ?, ?, ?)";
			Query query = session.createSQLQuery(sql);
			query.setInteger(0,propUserVO.getProUserRelID());
			query.setInteger(1,propUserVO.getPropertyID());
			query.setString(2,"A");
			query.setInteger(3,propUserVO.getUserID());
			query.executeUpdate();
			//session.save(puDO);
				i=1;//session.getTransaction().rollback();	
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return i;
		}
		session.getTransaction().commit();
		return i;
	}	
	
	@SuppressWarnings("unchecked")
	public PropRentInfo propRentInfo(int id)
	{
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		PropRentInfo propUser = null;
		try {
			Query query = session.createQuery("from PropRentInfo where id=:id");
			query.setInteger("id",id);
			propUser=(PropRentInfo)query.list().get(0);
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return propUser;
	}
	
	public Integer updateRent(PropRentInfoVo propRentInfoVo){
		PropRentInfo proprent=propRentInfo(propRentInfoVo.getId());	
		 proprent.setRentamount(propRentInfoVo.getRentamount());
		 proprent.setRentterms(propRentInfoVo.getRentterms());
		 proprent.setDuedate(propRentInfoVo.getDuedate());
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
				session.update(proprent);
		   } catch (HibernateException e)
		            {
			         e.printStackTrace();
			         session.getTransaction().rollback();
			  return 0;
		} 
		session.getTransaction().commit();
		return 2;
	}
	
	public Integer saveRent(PropRentInfoVo propRentInfoVo){
		PropRentInfo proprent=new PropRentInfo();
		 proprent.setPropUser(getPropUser(propRentInfoVo.getPropUserID()));
		proprent.setRentamount(propRentInfoVo.getRentamount());
		proprent.setRentterms(propRentInfoVo.getRentterms());
		proprent.setDuedate(propRentInfoVo.getDuedate());
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
				session.save(proprent);
		   } catch (HibernateException e)
		            {
			         e.printStackTrace();
			         session.getTransaction().rollback();
			  return 0;
		} 
		session.getTransaction().commit();
		return 1;
	}

	
	public Integer saveRelation(PropUserVO propUserVO)  throws Exception
	{
		int i=0;
		
		PropUser puDO =null;
		 puDO = selectRecord(propUserVO);
		 //System.out.println(puDO);
		if(puDO!=null)
			return 2;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			System.out.println("session created");
			String sql="insert into prop_user (RELATION_CODE, PROP_CODE, STATUS, USERID) values (?, ?, ?, ?)";
			Query query = session.createSQLQuery(sql);
			query.setString(0,propUserVO.getRelationCode());
			query.setInteger(1,propUserVO.getPropertyID());
			query.setString(2,"A");
			query.setInteger(3,propUserVO.getUserID());
			//query.setInteger(4,propUserVO.getSubProperty().getId()==0?null:propUserVO.getSubProperty().getId());
			//query.setString(5,propUserVO.getSubProperty().getRentamount()==null?null:propUserVO.getSubProperty().getRentamount().toString());
			query.executeUpdate();
			//session.save(puDO);
				i=1;//session.getTransaction().rollback();	
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return i;
		}
		session.getTransaction().commit();
		return i;
	}	
	
	
	
	public Integer deleteRelation(PropUser puDO) throws Exception
	  {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			try {
				System.out.println("session created");
				//List<PropUser> list = selectRecord(puDO);
				//if(list == null || list.size() == 0){
					//session.getTransaction().rollback();
					//return 0;
				//}
				//for(PropUser pu : list){
					session.save(puDO);
				//}
			} catch (HibernateException e) {
				e.printStackTrace();
				session.getTransaction().rollback();
				return 0;
			} 
			session.getTransaction().commit();
			return 1;
	  }
	public PropUser selectRecord(PropUserVO puDO) throws Exception{
		
		String select_Hql="FROM PropUser WHERE USERID = "+ puDO.getUserID()+""
					+" and PROP_CODE = "+ puDO.getPropertyID()
					+" AND RELATION_CODE = '"+ puDO.getRelationCode()+"'";
 		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
 		session.beginTransaction();
 		PropUser propUser=null;
		try {
			System.out.println("session created");
			Query q = session.createQuery(select_Hql);
			List<PropUser> list = q.list();
			if(list!=null && !list.isEmpty()){
				propUser= list.get(0);
			}
			else {
				session.getTransaction().commit();
			return null;
			}
		}catch(HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		} 
		session.getTransaction().commit();
		return propUser;
	}
}
