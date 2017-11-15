package com.prop.mnt.admin.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prop.mnt.common.DBConnection;
import com.prop.mnt.common.PasswordEncryption;
import com.prop.mnt.model.PropUserRel;
import com.prop.mnt.model.User;
import com.prop.mnt.property.user.relation.PropUserVO;
import com.prop.mnt.util.HibernateUtil;

public class UserDAO extends HibernateUtil{
	  private static final Logger log = LoggerFactory.getLogger(UserDAO.class);
	  public UserDAO(){ }
	  public List<UserVO> getDefaultData(Integer userId) throws Exception
	  {
	    String query = "SELECT id,USERID,FIRST_NAME,LAST_NAME FROM USERS WHERE STATUS != 'D' and RELATION_CODE>0 ORDER BY FIRST_NAME,LAST_NAME";
	    Connection con = DBConnection.getInstance().getConnection();
	    PreparedStatement pstmt = con.prepareStatement(query);
	    ResultSet rs = pstmt.executeQuery();
	    ArrayList<UserVO> userList = new ArrayList<UserVO>();
	    while(rs.next())
	    {
	    	if(rs.getInt("id")!=userId){
	    		
	    	UserVO userVo = new UserVO();
	    	userVo.setId(rs.getInt("id"));
	    	userVo.setFirstName(rs.getString("FIRST_NAME"));
	    	userVo.setLastName(rs.getString("LAST_NAME"));
	    	userList.add(userVo);
	    	}
	    }
	    System.out.println("userList=="+userList.size());
	    DBConnection.getInstance().returnConnection(con, pstmt);
	    return userList;
	  }
	  
	  public   List<PropUserVO> getPropList(int userid){
			
			List<PropUserVO> propList = new ArrayList<PropUserVO>();
			try{
				Connection con = DBConnection.getConnection();
				String query = "SELECT P.PROP_CODE, P.PROP_NAME FROM PROPERTY P WHERE P.USERID = '"+userid+"' AND P.STATUS != 'D' ORDER BY PROP_NAME";
				System.out.println("Property query="+query);
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next())
				{
					propList.add(new PropUserVO(rs.getInt(1), rs.getString(2)));
				}

			}catch(Exception w){}
			return propList;
		}
	  
	  
	  public   List<PropUserVO> getPropRelList(int userid){
			
			List<PropUserVO> propList = new ArrayList<PropUserVO>();
			try{
				Connection con = DBConnection.getConnection();
				String query = "SELECT p.prop_code,p.prop_name,u.first_name,u.last_name,pu.Relation_code FROM property p left join prop_user pu on pu.prop_code=p.prop_code "+
			                    " left join users u on pu.userid=u.id  where p.userid='"+userid+"' AND P.STATUS != 'D'";
				System.out.println("Property query="+query);
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next())
				{
					propList.add(new PropUserVO(rs.getInt(1), rs.getString(2)+" # "+rs.getString(3)+", "+rs.getString(4)+" ## "+rs.getString(5)));
				}

			}catch(Exception w){}
			return propList;
		}
	  
	  
	  public List<UserVO> getDefaultData() throws Exception
	  {
	    String query = "SELECT USERID,FIRST_NAME,LAST_NAME FROM USERS WHERE STATUS != 'D' and RELATION_CODE>0 ORDER BY FIRST_NAME,LAST_NAME";
	    Connection con = DBConnection.getInstance().getConnection();
	    PreparedStatement pstmt = con.prepareStatement(query);
	    ResultSet rs = pstmt.executeQuery();
	    ArrayList<UserVO> userList = new ArrayList<UserVO>();
	    while(rs.next())
	    {
	    	UserVO userVo = new UserVO();
	    	userVo.setUserId(rs.getString("USERID"));
	    	userVo.setFirstName(rs.getString("FIRST_NAME"));
	    	userVo.setLastName(rs.getString("LAST_NAME"));
	    	userList.add(userVo);
	    }
	    DBConnection.getInstance().returnConnection(con, pstmt);
	    return userList;
	  }
	  public UserVO getUserInfo(String id) throws Exception
	  {
		  String query = "SELECT id,FIRST_NAME,LAST_NAME,CONT_NO,ADDRESS1,ADDRESS2,CITY,STATE,COUNTRY,ZIP,EMAIL,STATUS FROM USERS WHERE USERID = ?";
		  Connection con = DBConnection.getInstance().getConnection();
		  PreparedStatement pstmt = con.prepareStatement(query);
		  pstmt.setString(1, id);
		  ResultSet rs = pstmt.executeQuery();
		  UserVO user = null;
		  if(rs.next())
		  {      
			  user = new UserVO();
			  user.setId(rs.getInt("id"));
			  user.setUserId(id);
			  user.setFirstName(rs.getString("FIRST_NAME"));
			  user.setLastName(rs.getString("LAST_NAME"));
			  user.setContNo(rs.getString("CONT_NO"));
			  user.setAddress1(rs.getString("ADDRESS1"));
			  user.setAddress2(rs.getString("ADDRESS2"));
			  user.setCity(rs.getString("CITY"));
			  user.setState(rs.getString("STATE"));
			  user.setCountry(rs.getString("COUNTRY"));
			  user.setZip(rs.getString("ZIP"));
			  user.setEmail(rs.getString("EMAIL"));
			  user.setStatus(rs.getString("STATUS"));
		  }
		  DBConnection.getInstance().returnConnection(con, pstmt);
		  return user;
	  }
	  public Integer createUser(UserVO vo) throws Exception
	  {
		    Connection con = DBConnection.getInstance().getConnection();
	        PreparedStatement pstmt = con.prepareStatement("SELECT USERID FROM USERS WHERE USERID = ?");
	        int i = 0;
	        pstmt.setString(1, vo.getUserId());
	        ResultSet rs = pstmt.executeQuery();
	        if(rs.next()){
	        	DBConnection.getInstance().returnConnection(con, pstmt);
	            return i;
	        }
		    con = DBConnection.getInstance().getConnection();
	        pstmt = con.prepareStatement("INSERT INTO USERS(USERID,FIRST_NAME,LAST_NAME,PASSWORD,CONT_NO,ADDRESS1,ADDRESS2,CITY,STATE,COUNTRY,ZIP,EMAIL,STATUS) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
	        String pw = PasswordEncryption.getObject().getEncriptedData(vo.getPassword());
	        pstmt.setString(1, vo.getNewUserId());
	        pstmt.setString(2, vo.getFirstName());
	        pstmt.setString(3, vo.getLastName());
	        pstmt.setBytes(4, pw.getBytes());
	        pstmt.setString(5, vo.getContNo());
	        pstmt.setString(6, vo.getAddress1());
	        pstmt.setString(7, vo.getAddress2());
	        pstmt.setString(8, vo.getCity());
	        pstmt.setString(9, vo.getState());
	        pstmt.setString(10, vo.getCountry());
	        pstmt.setString(11, vo.getZip());
	        pstmt.setString(12, vo.getEmail());
	        pstmt.setString(13, "A");
	        i = pstmt.executeUpdate();
	        DBConnection.getInstance().returnConnection(con, pstmt);
	        return i;
	  }
	  public Integer updateUser(UserVO vo) throws Exception
	  {
		    String query = "UPDATE USERS SET FIRST_NAME = ?,LAST_NAME = ?,";
	        if(vo.getPassword() != null && vo.getPassword().trim().length() > 5){
	        	query += "PASSWORD =?,";
	        }
		    query += "CONT_NO = ?,ADDRESS1 = ?,ADDRESS2 = ?, CITY = ?, STATE = ?, ZIP = ?, EMAIL = ?, STATUS = ? WHERE USERID = ?";
		    Connection con = DBConnection.getInstance().getConnection();
	        PreparedStatement pstmt = con.prepareStatement(query);
	        int i = 0;
	        pstmt.setString(++i, vo.getFirstName());
	        pstmt.setString(++i, vo.getLastName());
	        if(vo.getPassword() != null && vo.getPassword().trim().length() > 5){
	        	pstmt.setBytes(++i, PasswordEncryption.getObject().getEncriptedData(vo.getPassword()).getBytes());
	        }
	        pstmt.setString(++i, vo.getContNo());
	        pstmt.setString(++i, vo.getAddress1());
	        pstmt.setString(++i, vo.getAddress2());
	        pstmt.setString(++i, vo.getCity());
	        pstmt.setString(++i, vo.getState());
	        pstmt.setString(++i, vo.getZip());
	        pstmt.setString(++i, vo.getEmail());
	        pstmt.setString(++i,"A");
	        pstmt.setString(++i, vo.getUserId());
	        int j = pstmt.executeUpdate();
	        DBConnection.getInstance().returnConnection(con, pstmt);
	        return j;
	  }
	  public Integer logout(String userId) throws Exception{
		    Connection con = DBConnection.getInstance().getConnection();
	        PreparedStatement pstmt = con.prepareStatement("UPDATE USERS SET LOGOUT_TIME = SYSDATE WHERE USERID = ?");
	        int i = 0;
	        pstmt.setString(1, userId);
	        i = pstmt.executeUpdate();		  
	        DBConnection.getInstance().returnConnection(con, pstmt);
	        return i;
	  }
	  public Integer deleteUser(String userId) throws Exception
	  {
		    Connection con = DBConnection.getInstance().getConnection();
	        PreparedStatement pstmt = con.prepareStatement("UPDATE USERS SET STATUS = 'D' WHERE USERID = ?");
	        int i = 0;
	        pstmt.setString(1, userId);
	        i = pstmt.executeUpdate();		  
	        DBConnection.getInstance().returnConnection(con, pstmt);
	        return i;
	  }
	  
	  
	  public User saveUser(UserVO vo) throws Exception
	  {
		  System.out.println("inside save user"+vo.getNewUserId());
		  Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query =session.createSQLQuery("SELECT USERID FROM USERS WHERE USERID =:USERID ");
			query.setString("USERID",vo.getNewUserId());
			Object obj=query.uniqueResult();
			if(obj!=null) return null;
			User u=getUser(vo);
			u.setUserId(vo.getNewUserId());
			//u.setPropUserRel(getUserRoles(vo.getRelation(),session));
			 Integer i=(Integer) session.save(u);
			 session.getTransaction().commit();
			 u.setId(i);
			 return u;
	  }
	  
	  public User updateUserData(UserVO vo) throws Exception
	  {
		  System.out.println("inside update user ");
		  Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			User u=getUser(vo);
			u.setId(vo.getId());
			//u.setPropUserRel(getUserRoles(vo.getRelation(),session));
			session.saveOrUpdate(u);
			session.getTransaction().commit();
			return u;
	  }
	  private User getUser(UserVO vo){
		  User u=new User();
		  u.setAddress1(vo.getAddress1());
			u.setAddress2(vo.getAddress2());
			u.setCity(vo.getCity());
			u.setContNo(vo.getContNo());
			u.setCountry(vo.getCountry());
			u.setEmail(vo.getEmail());
			u.setFirstName(vo.getFirstName());
			u.setLastName(vo.getLastName());
			u.setPassword(PasswordEncryption.getObject().getEncriptedData(vo.getPassword()));
			u.setState(vo.getState());
			u.setStatus("A");
			u.setUserId(vo.getUserId());
			u.setZip(vo.getZip());
			//u.setPropUserRel(getUserRoles(vo.getRelation()));
			return u;
	  }
	  private UserVO getUser(User vo){
		  UserVO u=new UserVO();
		  u.setAddress1(vo.getAddress1());
			u.setAddress2(vo.getAddress2());
			u.setCity(vo.getCity());
			u.setContNo(vo.getContNo());
			u.setCountry(vo.getCountry());
			u.setEmail(vo.getEmail());
			u.setFirstName(vo.getFirstName());
			u.setLastName(vo.getLastName());
			//u.setPassword(PasswordEncryption.getObject().getEncriptedData(vo.getPassword()));
			u.setState(vo.getState());
			u.setStatus("A");
			u.setUserId(vo.getUserId());
			u.setZip(vo.getZip());
			//u.setPropUserRel(getUserRoles(vo.getRelation()));
			return u;
	  }
	  
	  @SuppressWarnings("unchecked")
		public List<PropUserRel> getUserRoles()  throws Exception
			{
		  List<PropUserRel> userRoles=null;
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				Query query =session.createQuery("from PropUserRel");
				userRoles=query.list();
				session.getTransaction().commit();
				return userRoles;
			}
	  @SuppressWarnings("unchecked")
		public PropUserRel getUserRoles(String id,Session session)  throws Exception
			{
		  PropUserRel userRel=null;
				//Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				//session.beginTransaction();
				Query query =session.createQuery("from PropUserRel where id=:id");
				query.setString("id",id);
				userRel=(PropUserRel)query.uniqueResult();
				//session.getTransaction().commit();
				return userRel;
			}
	  @SuppressWarnings("unchecked")
		public UserVO findUser(String id)  throws Exception
			{
		  System.out.println("in find user "+id);
		  User user=null;
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				Query query =session.createQuery("from User where USERID=:id");
				query.setString("id",id);
				user=(User)query.uniqueResult();
				session.getTransaction().commit();
				UserVO userVo=	getUser(user);
				userVo.setId(user.getId());
				
				//userVo.setRelation(String.valueOf(user.getPropUserRel().getId()));
				return userVo;
			}
}
