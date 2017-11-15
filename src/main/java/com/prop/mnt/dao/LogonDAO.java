package com.prop.mnt.dao;
import java.sql.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prop.mnt.common.DBConnection;
import com.prop.mnt.common.PasswordEncryption;
import com.prop.mnt.logon.action.LoginAction;
public class LogonDAO 
{
	private static final Logger log = LoggerFactory.getLogger(LogonDAO.class);
  private String query,sUserid,sPassword,sFName,sLName,sPassDate,sEmail,sLoginTime,sLogoutTime;
  
  public LogonDAO(){ }
  public String getUserid()
  {
    return sUserid;
  }
  public String getPassword()
  {
    return sPassword;
  }
  public String getFName()
  {
    return sFName;
  }
  public String getLName()
  {
    return sLName;
  }
  public String getPassDate()
  {
    return sPassDate;
  }
  public String getEmail()
  {
    return sEmail;
  }
  public String getLoginTime()
  {
    return sLoginTime;
  }
  public String getLogoutTime()
  {
    return sLogoutTime;
  }
  public String checkLogon(String userid, String pw)throws Exception
  {
    sPassword = null;
    sUserid = null;
    sFName = null;
    sLName = null;
    sPassDate = null;
    sEmail = null;
    sLoginTime = null;
    sLogoutTime = null;
    pw = PasswordEncryption.getObject().getEncriptedData(pw);
    log.info("userid:"+userid+":"+ pw);
/*    query = "SELECT PASSWORD,FIRST_NAME,LAST_NAME,TO_CHAR(PASSWORD_DATE,'MM/DD/YYYY'),EMAIL,date_format(LOGIN_TIME,'MM/DD/YYYY HH:MI:SS PM'),"
           +"date_format(LOGOUT_TIME,'MM/DD/YYYY HH:MI:SS PM') FROM REMIS.USERS WHERE USERID = ? AND STATUS <> 'D'";*/
    
    query = " SELECT PASSWORD,FIRST_NAME,LAST_NAME,DATE_FORMAT(PASSWORD_DATE,'%m-%d-%Y'),"
    		 +" EMAIL,date_format(LOGIN_TIME,'%m-%d-%Y %r'),"
    		 +" date_format(LOGOUT_TIME,'%m-%d-%Y %r') FROM REMIS.USERS WHERE USERID = ? AND STATUS <> 'D' ";
    
    
    Connection con = DBConnection.getInstance().getConnection();
    PreparedStatement pstmt = con.prepareStatement(query);
    log.info("userid:"+userid+":");
    pstmt.setString(1, userid);
    ResultSet rs = pstmt.executeQuery();
    if(rs.next())
    {
      sPassword = new String(rs.getBytes(1));
      if(!pw.equalsIgnoreCase(sPassword))
        return "Invalid password.";
      sUserid = userid;
      sFName = rs.getString(2);
      sLName = rs.getString(3);
      sPassDate = rs.getString(4);
      sEmail = rs.getString(5);
      sLoginTime = rs.getString(6);
      sLogoutTime = rs.getString(7);
      if(sLogoutTime == null)
        return "The user with this id already logged in.";
      DBConnection.closeConnection(con, pstmt, rs);
      return null;
    }else
    {
    	DBConnection.getInstance().returnConnection(con, pstmt);
        return "Invalid userid.";
    }
  }
  public void updateLogin(String userid)throws Exception
  {
    query = "UPDATE USERS SET LOGIN_TIME = now(),LOGOUT_TIME = null WHERE USERID= ?";
    Connection con = DBConnection.getInstance().getConnection();
    PreparedStatement pstmt = con.prepareStatement(query);
    pstmt.setString(1, userid);
    pstmt.executeUpdate();
    DBConnection.getInstance().returnConnection(con, pstmt);
  }
  public String changePassword(String userid, String pw)throws Exception
  {
	    Connection con = DBConnection.getInstance().getConnection();
	    PreparedStatement pstmt = con.prepareStatement("UPDATE USERS SET PASSWORD = ? WHERE USERID = ?");
	    pstmt.setBytes(1,PasswordEncryption.getObject().getEncriptedData(pw).getBytes());
	    pstmt.setString(2, userid);
	    pstmt.executeUpdate();
	    DBConnection.getInstance().returnConnection(con, pstmt);
	    return "Password changed successfully.";
  }  
  public void updateLogout(Object userid)throws Exception
  {
	    Connection con = DBConnection.getInstance().getConnection();
	    PreparedStatement pstmt = con.prepareStatement("UPDATE USERS SET LOGOUT_TIME = now() WHERE USERID= ?");
	    pstmt.setString(1, userid!=null?userid.toString():"");
	    pstmt.executeUpdate();
	   System.out.println("LOGOUT_TIME set status= "+ pstmt.toString());
	    DBConnection.getInstance().returnConnection(con, pstmt);
	    sUserid = null;
  }  
}