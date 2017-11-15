package com.prop.mnt.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prop.mnt.common.DBConnection;
import com.prop.mnt.model.Modules;

public class PermissionDAO {
	  private static final Logger log = LoggerFactory.getLogger(PermissionDAO.class);
	  private ResultSet rs;
	  private String sFirstModule, sFirstPage;
	  private ArrayList<String> arrUsers,arrPages,arrUserPerms;
	  private HashMap<String, HashMap<String, String>> hModules;
	  private HashMap<String, ArrayList<String>> hPages, hUserPerms;
	  private SortedMap<Integer, String> userModules;
	  private SortedMap<Integer, Map<String, String>> userPages;
	  
	
	  private List<Modules> moduleList;
	  
	  public List<Modules> getModuleList() {
		return moduleList;
	}
	public void setModuleList(List<Modules> moduleList) {
		this.moduleList = moduleList;
	}
	
	
	
	
	
	public PermissionDAO(){ }  
	  public ArrayList<String> getUsers()
	  {
	    return arrUsers;
	  }
	  public HashMap<String, HashMap<String, String>> getModules()
	  {
	    return hModules;
	  }
	  public HashMap<String, ArrayList<String>> getPages()
	  {
	    return hPages;
	  }
	  public HashMap<String, ArrayList<String>> getUserPerms()
	  {
	    return hUserPerms;
	  }
	  public String getFirstModule()
	  {
	    return sFirstModule;
	  }
	  public String getFirstPage()
	  {
	    return sFirstPage;
	  }
	  public void setUsers()throws Exception
	  {
		    String query = "SELECT USERID,FIRST_NAME,LAST_NAME FROM USERS WHERE STATUS != 'D' ORDER BY FIRST_NAME,LAST_NAME";
		    Connection con = DBConnection.getInstance().getConnection();
		    PreparedStatement pstmt = con.prepareStatement(query);		  
		    arrUsers = new ArrayList<String>();
		    rs = pstmt.executeQuery();
		    while(rs.next())
		    {
		    	arrUsers.add(rs.getString("USERID")+"#"+rs.getString("FIRST_NAME")+" "+rs.getString("LAST_NAME"));
		    }
		    DBConnection.getInstance().returnConnection(con, pstmt);
	  }
	  public HashMap<String, String> getUserList()throws Exception
	  {
		    String query = "SELECT USERID,FIRST_NAME,LAST_NAME FROM USERS WHERE STATUS != 'D' ORDER BY FIRST_NAME,LAST_NAME";
		    Connection con = DBConnection.getInstance().getConnection();
		    PreparedStatement pstmt = con.prepareStatement(query);		  
		    HashMap<String, String> hUsers = new HashMap<String, String>();
		    rs = pstmt.executeQuery();
		    while(rs.next())
		    {
		    	hUsers.put(rs.getString("USERID"), rs.getString("FIRST_NAME")+" "+rs.getString("LAST_NAME"));
		    }
		    DBConnection.getInstance().returnConnection(con, pstmt);
		    return hUsers;
	  }	  
	  public void setModules()throws Exception
	  {
	    hModules = new HashMap<String, HashMap<String, String>>();
	    String query = "SELECT MODULE_ID, MODULE_NAME,IMAGE,ALT FROM MODULES ORDER BY MODULE_ID";
	    Connection con = DBConnection.getInstance().getConnection();
	    PreparedStatement pstmt = con.prepareStatement(query);		  
	    rs = pstmt.executeQuery();
	    while(rs.next())
	    {
	      HashMap<String, String> hMap = new HashMap<String, String>();
	      String id = rs.getString("MODULE_ID");
	      hMap.put(id, id);
	      hMap.put("name",rs.getString("MODULE_NAME"));
	      hMap.put("img",rs.getString("IMAGE"));
	      hMap.put("alt",rs.getString("ALT"));
	      hModules.put(id,hMap);
	    }
	    DBConnection.getInstance().returnConnection(con, pstmt);
	  }
	  public void setPages()throws Exception
	  {
	    hPages = new HashMap<String, ArrayList<String>>();
	    Connection con = DBConnection.getInstance().getConnection();
	    PreparedStatement pstmt = con.prepareStatement("SELECT PAGE_ID, PAGE_NAME, PATH FROM PAGES WHERE MODULE_ID = ? ORDER BY PAGE_ID");
	    Set<String> s = hModules.keySet();
	    Iterator<String> iter = s.iterator();
	    while(iter.hasNext())
	    {
	      arrPages = new ArrayList<String>();
	      String mid = (String)iter.next();
	      pstmt.setString(1,mid);
	      rs = pstmt.executeQuery();
	      while(rs.next()){
	    	  arrPages.add(rs.getString("PAGE_ID")+"#"+rs.getString("PAGE_NAME")+"#"+rs.getString("PATH"));
	      }
	      hPages.put(mid, arrPages);
	      pstmt.clearParameters();
	    }
	    DBConnection.getInstance().returnConnection(con, pstmt);
	  }
	  public void setUserPerms(String userid)throws Exception
	  {
	    if(userid != null)
	    {    
	      hUserPerms = new HashMap<String, ArrayList<String>>();
	      boolean bFirst = true;
	      sFirstModule = null;
	      sFirstPage = null;
	      log.info("hModules :"+hModules);
	      String query = "SELECT PAGE_ID FROM PERMISSIONS WHERE USERID = ? AND MODULE_ID = ? ORDER BY PAGE_ID";
		  Connection con = DBConnection.getInstance().getConnection();
		  PreparedStatement pstmt = con.prepareStatement(query);		  	      
	      for(int i = 1; i <= 10; i++)
	      {
	        String mid = ""+i;
	        if(hModules.containsKey(mid))
	        {     
	          arrUserPerms = new ArrayList<String>();
	          boolean b = false;
	          pstmt.setString(1, userid);
	          pstmt.setString(2, mid);
	          rs = pstmt.executeQuery();
	          log.info("before while: "+userid);
	          log.info("before while: "+mid);
	          while(rs.next())
	          {
	            String id = rs.getString("PAGE_ID");
	            if(bFirst)
	            {
	              sFirstModule = mid;
	              sFirstPage = id;
	              bFirst = false;
	            }
	            arrUserPerms.add(id);
	            b = true;
	          }
	          if(b){
	            hUserPerms.put(mid,arrUserPerms);
	          }
	          pstmt.clearParameters();
	        }
	      }
	      DBConnection.getInstance().returnConnection(con, pstmt);
	    }
	  }
	  public void saveData(Map<String, Object> request, String userid)throws Exception
	  {
		  Connection con = DBConnection.getInstance().getConnection();
		  PreparedStatement pstmt = con.prepareStatement("DELETE FROM PERMISSIONS WHERE USERID = ?");	
		  pstmt.setString(1, userid);
		  pstmt.executeUpdate();
		 // DBConnection.getInstance().returnConnection(con, pstmt);
		  //con = DBConnection.getInstance().getConnection();
	      pstmt = con.prepareStatement("INSERT INTO PERMISSIONS(USERID,MODULE_ID,PAGE_ID)VALUES(?,?,?)");
	      for(int i = 1; i < 100; i++)
	      {
		      String[] sCheck = (String[])request.get(""+i);
		      if(sCheck != null && sCheck.length > 0)
		      {
			        pstmt.setString(1, userid);
			        pstmt.setString(2, sCheck[0]);
			        pstmt.setInt(3, i);
			        pstmt.execute();
			        pstmt.clearParameters();
		      }
	     }
	     DBConnection.getInstance().returnConnection(con, pstmt);
	  }
	  public String getPageName(String pageid)
	  {
	    if(pageid != null)
	    {
	      Set<String> s = hPages.keySet();
	      Iterator<String> iter = s.iterator();
	      while(iter.hasNext())
	      {
	        ArrayList<String> arrList = (ArrayList<String>)hPages.get(iter.next());
	        for(int i = 0; i < arrList.size(); i++)
	        {
	          String val = arrList.get(i);
	          if(pageid.equals(val.substring(0,val.indexOf('#'))))
	            return val.substring(val.indexOf('#')+1,val.lastIndexOf('#'));
	        }
	      }
	    }
	    return null;
	  }
	  public String getPagePath(String pageid)
	  {
	    if(pageid != null)
	    {
	      Set<String> s = hPages.keySet();
	      Iterator<String> iter = s.iterator();
	      while(iter.hasNext())
	      {
	        ArrayList<String> arrList = (ArrayList<String>)hPages.get(iter.next());
	        for(int i = 0; i < arrList.size(); i++)
	        {
	          String val = (String)arrList.get(i);
	          if(pageid.equals(val.substring(0,val.indexOf('#'))))
	            return val.substring(val.lastIndexOf('#')+1);
	        }
	      }
	    }
	    return null;
	  }
	  public void setUserModules(){
		  userModules = new TreeMap<Integer, String>();
		  Set<String> s = hUserPerms.keySet();
		  Iterator<String> iter = s.iterator();
		  while(iter.hasNext())
		  {
		    String m = (String)iter.next();
		    HashMap<String, String> hMap = (HashMap<String, String>)hModules.get(m);
		    userModules.put(Integer.valueOf(m), hMap.get("name"));
		  }
    }
	public void setUserPages() {
		  this.userPages = new TreeMap<Integer, Map<String, String>>();
		  Set s = hUserPerms.keySet();
		  Iterator iter = s.iterator();
		  while(iter.hasNext())
		  {
			    String m = (String)iter.next();
			    ArrayList<String> arrList = hUserPerms.get(m);
			    Map<String, String> map = new HashMap<String, String>();
		        for(int j = 0; j < arrList.size(); j++)
			    {
			        String pageid = (String)arrList.get(j);
			        map.put(getPageName(pageid), "/remis/"+getPagePath(pageid));
			    }
		        userPages.put(Integer.valueOf(m), map);
		  }
	}
	public SortedMap<Integer, String> getUserModules() {
		return userModules;
	}
	public SortedMap<Integer, Map<String, String>> getUserPages() {
		return userPages;
	}
}
