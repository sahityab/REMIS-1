package com.prop.mnt.common;
import javax.servlet.*;
import javax.servlet.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;

public class SessionListener implements HttpSessionBindingListener
{
	  private static final Logger log = LoggerFactory.getLogger(SessionListener.class);
	  private String userid;
	  ServletContext scontext;
	  public SessionListener(ServletContext scontext, String userid)
	  {
	    this.scontext = scontext;
	    this.userid = userid;
	  }
	  public void valueBound(HttpSessionBindingEvent event){ }  
	  public void valueUnbound(HttpSessionBindingEvent event)
      {
		try{
				Connection con = DBConnection.getInstance().getConnection();
				PreparedStatement pstmt = con.prepareStatement("UPDATE USERS SET LOGOUT_TIME = now() WHERE USERID = ?");
				pstmt.setString(1, userid);
				pstmt.executeUpdate();
				DBConnection.getInstance().returnConnection(con, pstmt);
			}
			catch(Exception e)
	    	{
				log.error("Exception in valueUnbound:"+e);
			}
	  }
}
