package com.prop.mnt.property.owner;
import java.sql.*;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prop.mnt.common.DBConnection;

public class OwnerDAO 
{
	private static final Logger log = LoggerFactory.getLogger(OwnerDAO.class);

	public OwnerDAO(){ }
  
  public List<OwnerVO> getOwnerList()throws Exception
  {
    String query = "SELECT OWN_Code,OWN_F_Name,OWN_M_Name,OWN_L_Name FROM OWNER"
          +" WHERE STATUS != 'D' ORDER BY OWN_F_Name,OWN_M_Name,OWN_L_Name";
    Connection con = DBConnection.getInstance().getConnection();
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery(query);
    List<OwnerVO> ownerList = new ArrayList<OwnerVO>();
    while(rs.next())
    {
    	OwnerVO owner = new OwnerVO();
    	owner.setOwnCode(rs.getInt("OWN_Code"));
    	owner.setOwnFName(rs.getString("OWN_F_Name"));
    	owner.setOwnMName(rs.getString("OWN_M_Name"));
    	owner.setOwnLName(rs.getString("OWN_L_Name"));
    	ownerList.add(owner);
    }
    DBConnection.getInstance().returnConnection(con, stmt);
    return ownerList;
  }  
  public OwnerVO getOwnerInfo(Integer id)throws Exception
  {
    String query = "SELECT OWN_F_Name,OWN_M_Name,OWN_L_Name,OWN_TYPE,OWN_SSN,ADDRESS1,ADDRESS2,"
          +"CITY,STATE_CD,COUNTRY,ZIP,"
          +"PHONE,FAX,EMAIL,STATUS FROM OWNER"
          +" WHERE OWN_Code = ?";
      Connection con = DBConnection.getInstance().getConnection();
	  PreparedStatement pstmt = con.prepareStatement(query);
	  pstmt.setInt(1, id.intValue());
      ResultSet rs = pstmt.executeQuery();
      OwnerVO owner=null;
	    if(rs.next())
	    {
	    	owner=new OwnerVO();
	    	owner.setOwnCode(id);
			 owner.setOwnFName(rs.getString("OWN_F_Name"));
			 owner.setOwnMName(rs.getString("OWN_M_Name"));
			 owner.setOwnLName(rs.getString("OWN_L_Name"));
			 owner.setOwnType(rs.getString("OWN_TYPE"));
			 owner.setOwnSsn(rs.getInt("OWN_SSN"));
			 owner.setAddress1(rs.getString("ADDRESS1"));
			 owner.setAddress2(rs.getString("ADDRESS2"));
			 owner.setCity(rs.getString("CITY"));
			 owner.setStateCd(rs.getString("STATE_CD"));
			 owner.setCountry(rs.getString("COUNTRY"));
			 owner.setZip(rs.getString("ZIP"));
			 owner.setPhone(rs.getLong("PHONE"));
			 owner.setFax(rs.getLong("FAX"));
			 owner.setEmail(rs.getString("EMAIL"));
			 owner.setStatus(rs.getString("STATUS"));
	    }
	    DBConnection.getInstance().returnConnection(con, pstmt);
	    return owner;
  }
  public Integer createOwner(OwnerVO vo) throws Exception
  {
	    Connection con = DBConnection.getInstance().getConnection();
	    Statement stmt = con.createStatement();
       // ResultSet rs = stmt.executeQuery("SELECT SEQ_OWN_CODE.NEXTVAL FROM DUAL");
        ResultSet rs = stmt.executeQuery("SELECT max(OWN_Code) FROM OWNER");
	    int id =1;
       if(rs.next())
         id = rs.getInt(1)+1;
        //log.info("New owner code:"+id);
        DBConnection.getInstance().returnConnection(con, stmt);
        con = DBConnection.getInstance().getConnection();
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO OWNER(OWN_Code,OWN_F_Name,OWN_M_Name,OWN_L_Name,OWN_TYPE,OWN_SSN,ADDRESS1,ADDRESS2,CITY,STATE_CD,COUNTRY,ZIP,PHONE,FAX,EMAIL,STATUS) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        pstmt.setInt(1, id);
        pstmt.setString(2, vo.getOwnFName());
        pstmt.setString(3, vo.getOwnMName());
        pstmt.setString(4, vo.getOwnLName());
        pstmt.setString(5, vo.getOwnType());
        pstmt.setInt(6, vo.getOwnSsn());
        pstmt.setString(7, vo.getAddress1());
        pstmt.setString(8, vo.getAddress2());
        pstmt.setString(9, vo.getCity());
        pstmt.setString(10, vo.getStateCd());
        pstmt.setString(11, vo.getCountry());
        pstmt.setString(12, vo.getZip());
        pstmt.setLong(13, vo.getPhone());
        pstmt.setLong(14, vo.getFax());
        pstmt.setString(15, vo.getEmail());
        pstmt.setString(16, "A");
        int i = pstmt.executeUpdate();
        DBConnection.getInstance().returnConnection(con, pstmt);
        return i;
  }
  
  public Integer updateOwner(OwnerVO vo) throws Exception
  {
	    String query = "UPDATE OWNER SET OWN_F_Name= ?, OWN_M_Name= ?, OWN_L_Name= ?,";
        query += "OWN_TYPE= ?,OWN_SSN = ?,ADDRESS1 = ?,ADDRESS2 = ?,CITY = ?,STATE_CD =?,COUNTRY = ?,ZIP=?,PHONE=?,FAX=?, EMAIL = ?,STATUS = ? WHERE OWN_Code = ?";
	    Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        int i = 0;
        pstmt.setString(++i, vo.getOwnFName());
        pstmt.setString(++i, vo.getOwnMName());
        pstmt.setString(++i, vo.getOwnLName());
        pstmt.setString(++i, vo.getOwnType());
        pstmt.setInt(++i, vo.getOwnSsn());
        pstmt.setString(++i, vo.getAddress1());
        pstmt.setString(++i, vo.getAddress2());
        pstmt.setString(++i, vo.getCity());
        pstmt.setString(++i, vo.getStateCd());
        pstmt.setString(++i, vo.getCountry());
        pstmt.setString(++i, vo.getZip());
        pstmt.setLong(++i, vo.getPhone());
        pstmt.setLong(++i, vo.getFax());
        pstmt.setString(++i, vo.getEmail());
        pstmt.setString(++i,"A");
        pstmt.setInt(++i, vo.getOwnCode());
        int j = pstmt.executeUpdate();
        DBConnection.getInstance().returnConnection(con, pstmt);
        return j;
  }
  
  public Integer deleteOwner(Integer ownerid) throws Exception
  {
	    Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement pstmt = con.prepareStatement("UPDATE OWNER SET STATUS = 'D' WHERE OWN_Code = ?");
        int i = 0;
        pstmt.setInt(1, ownerid);
        i = pstmt.executeUpdate();		  
        DBConnection.getInstance().returnConnection(con, pstmt);
        return i;
  }
}