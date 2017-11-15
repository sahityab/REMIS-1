package com.prop.mnt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prop.mnt.common.DBConnection;
import com.prop.mnt.common.PasswordEncryption;
import com.prop.mnt.model.Modules;
import com.prop.mnt.model.Pages;
import com.prop.mnt.model.User;
import com.prop.mnt.util.HibernateUtil;

public class LoginDao extends HibernateUtil {
	private static final Logger log = LoggerFactory.getLogger(LoginDao.class);	
	  @SuppressWarnings("unchecked")
	 public User checkLogon(String userid, String pw)throws Exception
	  {
		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		 session.beginTransaction();
		String hql = "from User where USERID=:userid and STATUS=:status and PASSWORD=:pass ";
		Query query = session.createQuery(hql);
		query.setString("userid",userid);
		query.setString("status","A");
		query.setString("pass",PasswordEncryption.getObject().getEncriptedData(pw));
		List<User> uList=query.list();
		
		session.getTransaction().commit();
		//System.out.println("uList=="+uList);
		if(uList!=null && !uList.isEmpty()){
			System.out.println("city=="+uList.get(0).getCity());
			return uList.get(0);
		}
		else return null;
    }
	
	 
   /* @SuppressWarnings("unchecked")
	public List<Modules> geModulesList(Set<String> roles)  throws Exception
		{
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			List<Modules> modules = null;
			try {
				//modules = (List<Modules>)session.createQuery("from Modules  where role=:role").list();
				Query query = session.createQuery("from Modules  where role in (:roles)");
				query.setParameterList("roles",roles);
				modules=query.list();
			} catch (HibernateException e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}
			session.getTransaction().commit();
			System.out.println(roles+" ==roles propUserRelList size="+modules.size());
			return modules;
		}*/
    
    
        public  Map<Integer,Modules> selectPropRels(String qry) throws Exception{
        	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
     		session.beginTransaction();
     		String sql="SELECT module_name, p.page_id,p.module_id,p.page_name,path FROM role_pages r,pages p,modules m where role_code in ("+qry+") and  p.page_id=r.page_id and p.module_id=m.module_id order by p.module_id";
        	System.out.println("sql=="+sql);
     		Query query = session.createSQLQuery(sql) ;
        	//query.setParameterList("roles",roles);
        	List<?> result = query.list();
        	System.out.println("pages size=="+result.size());
        	Iterator<?> iterator = result.iterator();
        	Map<Integer,Modules> moduleMap=new TreeMap<Integer,Modules>();
        	while (iterator.hasNext()) {
        	    Object[] row = (Object[])iterator.next();
        	    if(moduleMap.containsKey(Integer.parseInt(row[2].toString()))){
        	    	moduleMap.get(Integer.parseInt(row[2].toString())).getPages().add(new Pages(Integer.parseInt(row[1].toString()),row[3].toString(),row[4].toString()));
    				//moduleMap.get(page.getModule().getModuleName());
    			}else {
    			List<Pages>	pages=new ArrayList<Pages>();
    				pages.add(new Pages(Integer.parseInt(row[1].toString()),row[3].toString(),row[4].toString()));
    				
    				Modules m=new Modules();
    				m.setModuleName(row[0].toString());
    				m.setModuleID(Integer.parseInt(row[2].toString()));
    				m.setPages(pages);
    				moduleMap.put(Integer.parseInt(row[2].toString()), m);
    			}
        	}
        	//System.out.println("size==M== "+moduleMap);
			session.getTransaction().commit();
			return moduleMap;
	}
        
        public void updateLogin(String userid)
        {
        	Connection con=null;
        	PreparedStatement pstmt=null;
	        // String query = "UPDATE USERS SET LOGIN_TIME = now(),LOGOUT_TIME = null WHERE USERID= ?";
	         try{
		          con = DBConnection.getConnection();
		          pstmt = con.prepareStatement("UPDATE USERS SET LOGIN_TIME = now(),LOGOUT_TIME = null WHERE USERID= ?");
		          pstmt.setString(1, userid);
		          pstmt.executeUpdate();
		        }
	         catch(Exception e){e.printStackTrace();}
	        finally{
	        	try{
	        		closeConnectionObj(con,pstmt);
	        	}catch(NullPointerException n){n.getMessage();}
	        } 
        }
        public String changePassword(String userid, String pw)
        {
        	Connection con=null;
        	PreparedStatement pstmt=null;
	        // String query = "UPDATE USERS SET LOGIN_TIME = now(),LOGOUT_TIME = null WHERE USERID= ?";
	         try{
	        	  con = DBConnection.getConnection();
      	          pstmt = con.prepareStatement("UPDATE USERS SET PASSWORD = ? WHERE USERID = ?");
		      	  pstmt.setBytes(1,PasswordEncryption.getObject().getEncriptedData(pw).getBytes());
		      	  pstmt.setString(2, userid);
		      	  pstmt.executeUpdate();
		      	log.info("Password Updated");
		        }
	         catch(Exception e){e.printStackTrace();}
	        finally{
	        	try{
	        		closeConnectionObj(con,pstmt);
	        	}catch(NullPointerException n){n.getMessage();}
	        } 
      	    return "Password changed successfully.";
        }  
        
        public void updateLogout(Object userid)
        {
        	Connection con=null;
        	PreparedStatement pstmt=null;
	        // String query = "UPDATE USERS SET LOGIN_TIME = now(),LOGOUT_TIME = null WHERE USERID= ?";
	         try{ 
	        	 con = DBConnection.getConnection();
      	          pstmt = con.prepareStatement("UPDATE USERS SET LOGOUT_TIME = now() WHERE USERID= ?");
	      	      pstmt.setString(1, userid!=null?userid.toString():"");
	      	      pstmt.executeUpdate();
	      	      log.info("LOGOUT_TIME set status= "+ pstmt.toString());
	           }
	         catch(Exception e){e.printStackTrace();}
        
        finally{
        	try{
        		closeConnectionObj(con,pstmt);
        	}catch(NullPointerException n){n.getMessage();}
        } 
        }  
        
        private void closeConnectionObj(Connection c,PreparedStatement ps){
        	try{
    			if(ps != null)
    				ps.close();
    			if(c != null)
    				c.close();
    		}catch(SQLException e)
    		{
    			log.warn("connection exception while closing :"+e);
    		}
        }
        
}