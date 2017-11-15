package com.prop.mnt.property.property;
import java.sql.*;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prop.mnt.property.property.PropertyVO;
import com.prop.mnt.admin.user.UserVO;
import com.prop.mnt.common.DBConnection;
import com.prop.mnt.common.PasswordEncryption;
import com.prop.mnt.property.owner.OwnerDAO;
import com.prop.mnt.property.owner.OwnerVO;


public class PropertyDAO {

	private static final Logger log = LoggerFactory.getLogger(PropertyDAO.class);
	
	public PropertyDAO(){ }
	
	public  Map<String, Map<String,String>> getDataMap(int userid)throws Exception{
		Map<String,  Map<String,String>> resultMap = new HashMap<String,  Map<String,String>>();
		Connection con = DBConnection.getInstance().getConnection();
		resultMap.put("propList", getPropList(userid,con));
		resultMap.put("propTypes", getPropTypes(con));
		resultMap.put("propCatList", getPropCatList(con));
		resultMap.put("landStatus", getLandStat(con));
		resultMap.put("currencyList", getCurrencyList(con));
		resultMap.put("valusBasisList", getValueBasisList(con));
		resultMap.put("unitOfMeasList", getUnitofMeasList(con));
		
		DBConnection.getInstance().returnConnection(con,null);
		return resultMap;
	}
	
	public   Map<String, String> getPropList(int userid,Connection con) throws Exception{
		String query = "SELECT P.PROP_CODE, P.PROP_NAME FROM PROPERTY P WHERE P.userid = '"+userid+"' AND P.STATUS != 'D' ORDER BY PROP_NAME";
		System.out.println("Property query="+query);
	if(con==null) con = DBConnection.getInstance().getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		 Map<String, String> propList = new HashMap<String, String>();
		while(rs.next())
		{
			propList.put(rs.getString(1), rs.getString(2));
		}
	   // DBConnection.getInstance().returnConnection(con, stmt);
        return propList;
	}

	private Map<String, String> getPropTypes(Connection con) throws Exception{
		String query = "SELECT PRO_TYPE_CODE, PRO_TYPE_DES FROM PROP_TYPE WHERE STATUS != 'D' ORDER BY PRO_TYPE_DES";
		if(con==null) con =  DBConnection.getInstance().getConnection();
		    Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		HashMap<String, String> propType = new HashMap<String, String>();
		while(rs.next())
		{
			propType.put(rs.getString(1), rs.getString(2));
		}
	    //DBConnection.getInstance().returnConnection(con, stmt);
        return propType;
	}
	
	private Map<String, String> getPropCatList(Connection con) throws Exception{
		String query = "SELECT PRO_CAT_Code, PRO_CAT_Des FROM PROP_CAT WHERE STATUS != 'D' ORDER BY PRO_CAT_Des";
		if(con==null) con =  DBConnection.getInstance().getConnection();
		    Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		HashMap<String, String> propCatList = new HashMap<String, String>();
		while(rs.next())
		{
			propCatList.put(rs.getString(1), rs.getString(2));
		}
		//DBConnection.getInstance().returnConnection(con, stmt);
        return propCatList;
	}

	private Map<String, String> getLandStat(Connection con) throws Exception{
		String query = "SELECT LAND_STATUS_CODE,LAND_STATUS_DESC FROM LAND_STATUS WHERE STATUS <> 'D' ORDER BY LAND_STATUS_DESC";
		if(con==null) con =  DBConnection.getInstance().getConnection();
		    Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		HashMap<String, String> landStat = new HashMap<String, String>();
		while(rs.next())
		{
			landStat.put(rs.getString(1), rs.getString(2));
		}
		//DBConnection.getInstance().returnConnection(con, stmt);
        return landStat;
	}
	
	private Map<String, String> getCurrencyList(Connection con) throws Exception{
		String query = "SELECT CURR_NO,CURR_DESC FROM CURRENCY WHERE STATUS != 'D' ORDER BY CURR_DESC";
		if(con==null) con = DBConnection.getInstance().getConnection();
		    Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		HashMap<String, String> currList = new HashMap<String, String>();
		while(rs.next())
		{
			currList.put(rs.getString(1), rs.getString(2));
		}
		//DBConnection.getInstance().returnConnection(con, stmt);
        return currList;
	}
	
	private Map<String, String> getValueBasisList(Connection con) throws Exception{
		String query = "SELECT BASIS_NO,BASIS_DESC FROM VALUE_BASIS WHERE STATUS != 'D' ORDER BY BASIS_DESC";
		if(con==null) con = DBConnection.getInstance().getConnection();
		    Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		HashMap<String, String> valBasis = new HashMap<String, String>();
		while(rs.next())
		{
			valBasis.put(rs.getString(1), rs.getString(2));
		}
		//DBConnection.getInstance().returnConnection(con, stmt);
         return valBasis;
	}
	
	private Map<String, String> getUnitofMeasList(Connection con) throws Exception{
		String query = "SELECT UNIT_CODE, UNIT_DESC FROM UNITS WHERE STATUS != 'D' ORDER BY UNIT_DESC";
		if(con==null) con = DBConnection.getInstance().getConnection();
		    Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		HashMap<String, String> uom = new HashMap<String, String>();
		while(rs.next())
		{
			uom.put(rs.getString(1), rs.getString(2));
		}
		//DBConnection.getInstance().returnConnection(con, stmt);
       return uom;
	}
	
	  public PropertyVO getPropInfo(Integer id) throws Exception
	  {
		  String query = "SELECT PROP_Name,PROP_TYPE_CODE,LAND_STATUS_CODE,PROP_CAT_CODE,NO_OF_DIV,PROP_Deed_No,FILE_REFER,"
		           +"ADDR1,ADDR2,PROP_CITY,ZIP,PROP_Country,PROP_Contactname,PROP_Contacttel,PROP_PUR_DATE,"
		           +"PROP_PUR_Value,PROP_MKT_Value,CURRENCY_CODE,VAL_DT,VAL_REMIND_DT,VAL_AGENT,"
		           +"VAL_BASIS,PROP_Rmks,AREA,REM_LAND_AREA,UOM,PLOT_NO,SDJOB_NO,"
		           +"MAPSHEET,PERMIT_NO,PERMIT_DT,STATUS FROM PROPERTY WHERE PROP_Code = ? ";
		  Connection con = DBConnection.getInstance().getConnection();
		  PreparedStatement pstmt = con.prepareStatement(query);
		  pstmt.setInt(1, id);
		  ResultSet rs = pstmt.executeQuery();
		  PropertyVO prop = null;
		  if(rs.next())
		  {      
			  prop = new PropertyVO();
			  prop.setPropCode(id);
			  prop.setPropName(rs.getString("PROP_Name"));
			  prop.setPropTypeCode(rs.getString("PROP_TYPE_CODE"));
			  prop.setLandStatusCode(rs.getString("LAND_STATUS_CODE"));
			  prop.setPropCatCode(rs.getString("PROP_CAT_CODE"));
			  prop.setNoOfDiv(rs.getInt("NO_OF_DIV"));
			  prop.setPropDeedNo(rs.getString("PROP_Deed_No"));
			  prop.setFileRefer(rs.getString("FILE_REFER"));
			  prop.setAddr1(rs.getString("addr1"));
			  prop.setAddr2(rs.getString("addr2"));			  
			  prop.setPropCity(rs.getString("PROP_CITY"));
			  prop.setZip(rs.getString("ZIP"));
			  prop.setPropCountry(rs.getString("PROP_Country"));
			  prop.setPropContactname(rs.getString("PROP_Contactname"));
			  prop.setPropContacttel(rs.getLong("PROP_Contacttel"));
			  prop.setPropPurDate(rs.getDate("PROP_PUR_DATE"));
			  prop.setPropPurValue(rs.getDouble("PROP_PUR_Value"));
			  prop.setPropMktValue(rs.getDouble("PROP_MKT_Value"));
			  prop.setCurrencyCode(rs.getString("CURRENCY_CODE"));
			  prop.setValDt(rs.getDate("VAL_DT"));
			  prop.setValRemindDt(rs.getDate("VAL_REMIND_DT"));
			  prop.setValAgent(rs.getString("VAL_AGENT"));
			  prop.setValBasis(rs.getString("VAL_BASIS"));
			  prop.setPropRmks(rs.getString("PROP_Rmks"));
			  prop.setArea(rs.getLong("AREA"));
			  prop.setRemLandArea(rs.getLong("REM_LAND_AREA"));
			  prop.setUom(rs.getString("UOM"));
			  prop.setPlotNo(rs.getString("PLOT_NO"));
			  prop.setSdJobNo(rs.getString("SDJOB_NO"));
			  prop.setMapsheet(rs.getString("MAPSHEET"));		  
			  prop.setPermitNo(rs.getString("PERMIT_NO"));
			  prop.setPermitDt(rs.getDate("PERMIT_DT"));
			  prop.setStatus(rs.getString("STATUS"));
           }
		  DBConnection.getInstance().returnConnection(con, pstmt);
		  if(prop != null){
			  con = DBConnection.getInstance().getConnection();
			  Statement stmt = con.createStatement();
			  rs = stmt.executeQuery("SELECT USERID FROM PROP_USER WHERE PROP_Code = "+id+" AND RELATION_CODE = 'OWN'");
			  if(rs.next()){
				  prop.setPropOwnCode(rs.getString("USERID"));
			  }
			  DBConnection.getInstance().returnConnection(con, stmt);
		  }
		  return prop;
	  }
	  
	  public Integer createProperty(PropertyVO vo) throws Exception
	  {
		    Connection con = DBConnection.getInstance().getConnection();
		    Statement stmt = con.createStatement();
	       // ResultSet rs = stmt.executeQuery("SELECT SEQ_PROP_CODE.NEXTVAL FROM DUAL");
		    ResultSet rs = stmt.executeQuery("SELECT max(PROP_Code) FROM PROPERTY");
		    int id =1;
	       if(rs.next())
	         id = rs.getInt(1)+1;
	        log.info("New property code:"+id);
	        DBConnection.getInstance().returnConnection(con, stmt);
	        con = DBConnection.getInstance().getConnection();
	       PreparedStatement pstmt = con.prepareStatement("INSERT INTO PROPERTY(PROP_Code,PROP_Name,PROP_TYPE_CODE,LAND_STATUS_CODE,PROP_CAT_CODE,NO_OF_DIV,PROP_Deed_No,"       
	        										+"FILE_REFER,ADDR1,ADDR2,PROP_CITY,ZIP,PROP_Country,PROP_Contactname,PROP_Contacttel,PROP_PUR_DATE,"
	        										+"PROP_PUR_Value,PROP_MKT_Value,CURRENCY_CODE,VAL_DT,VAL_REMIND_DT,VAL_AGENT,VAL_BASIS,PROP_Rmks,AREA,REM_LAND_AREA,UOM,"
           											+"PLOT_NO,SDJOB_NO,MAPSHEET,PERMIT_NO,PERMIT_DT,STATUS,USERID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
	        pstmt.setInt(1, id);
	        pstmt.setString(2, vo.getPropName());
	        pstmt.setString(3, vo.getPropTypeCode());
	        pstmt.setString(4, vo.getLandStatusCode());
	        pstmt.setString(5, vo.getPropCatCode());
	        pstmt.setInt(6, vo.getNoOfDiv());
	        pstmt.setString(7, vo.getPropDeedNo());
	        pstmt.setString(8, vo.getFileRefer());
	        pstmt.setString(9, vo.getAddr1());
	        pstmt.setString(10, vo.getAddr2());
	        pstmt.setString(11, vo.getPropCity());
	        pstmt.setString(12, vo.getZip());
	        pstmt.setString(13, vo.getPropCountry());
	        pstmt.setString(14, vo.getPropContactname());
	        pstmt.setLong(15, vo.getPropContacttel());
	        if(vo.getPropPurDate() == null){
	        	pstmt.setDate(16, null);
	        }else{
	        	pstmt.setDate(16, new java.sql.Date(vo.getPropPurDate().getTime()));
	        }
	        pstmt.setDouble(17, vo.getPropPurValue());
	        pstmt.setDouble(18, vo.getPropMktValue());
	        pstmt.setString(19, vo.getCurrencyCode());
	        pstmt.setDate(20, (vo.getValDt() == null ? null : new java.sql.Date(vo.getValDt().getTime())));
	        pstmt.setDate(21, (vo.getValRemindDt() == null ? null : new java.sql.Date(vo.getValRemindDt().getTime())));
	        pstmt.setString(22, vo.getValAgent());
	        pstmt.setString(23, vo.getValBasis());
	        pstmt.setString(24, vo.getPropRmks());
	        pstmt.setLong(25, vo.getArea());
	        pstmt.setLong(26, vo.getRemLandArea());
	        pstmt.setString(27, vo.getUom());
	        pstmt.setString(28, vo.getPlotNo());
	        pstmt.setString(29, vo.getSdJobNo());
	        pstmt.setString(30, vo.getMapsheet());
	        pstmt.setString(31, vo.getPermitNo());
	        pstmt.setDate(32, (vo.getPermitDt() == null ? null : new java.sql.Date(vo.getPermitDt().getTime())));
	        pstmt.setString(33, "A");
	        pstmt.setString(34, vo.getPropOwnCode());
	        int i = pstmt.executeUpdate();
	        DBConnection.getInstance().returnConnection(con, pstmt);
	   /*     if(i > 0){
	        	con = DBConnection.getInstance().getConnection();
	        	pstmt = con.prepareStatement("INSERT INTO PROP_USER(PROP_Code, USERID, RELATION_CODE, STATUS) VALUES(?,?,?,?)");
	        	pstmt.setInt(1, id);
	        	pstmt.setString(2, vo.getPropOwnCode());
	        	pstmt.setString(3, "OWN");
	        	pstmt.setString(4, "A");
	        	pstmt.executeUpdate();
	        	DBConnection.getInstance().returnConnection(con, pstmt);
	        }*/
	        return i;
	  }
	  
	  public Integer updateProperty(PropertyVO vo) throws Exception
	  {
		    String query = "UPDATE PROPERTY SET PROP_Name = ?,PROP_TYPE_CODE = ?,";
	        query += "LAND_STATUS_CODE = ?,PROP_CAT_CODE = ?,NO_OF_DIV = ?,PROP_Deed_No = ?,FILE_REFER = ?,addr1 = ?,"
	        		+ "addr2 = ?,PROP_CITY = ?, ZIP = ?, PROP_Country = ?,PROP_Contactname = ?,PROP_Contacttel = ?,PROP_PUR_DATE = ?,"
	        		+ "PROP_PUR_Value = ?,PROP_MKT_Value = ?,CURRENCY_CODE = ?,VAL_DT = ?,VAL_REMIND_DT = ?,VAL_AGENT = ?,VAL_BASIS = ?,PROP_Rmks = ?,"
	        		+ "AREA = ?,REM_LAND_AREA = ?,UOM = ?,PLOT_NO = ?,SDJOB_NO = ?,MAPSHEET = ?, PERMIT_NO = ?,PERMIT_DT = ? "
	        		+ "WHERE STATUS = ? AND PROP_Code = ?";
		    Connection con = DBConnection.getInstance().getConnection();
	        PreparedStatement pstmt = con.prepareStatement(query);
	        int i = 0;
	        pstmt.setString(++i, vo.getPropName());
	        pstmt.setString(++i, vo.getPropTypeCode());
	        pstmt.setString(++i, vo.getLandStatusCode());
	        pstmt.setString(++i, vo.getPropCatCode());
	        pstmt.setInt(++i, vo.getNoOfDiv());
	        pstmt.setString(++i, vo.getPropDeedNo());
	        pstmt.setString(++i, vo.getFileRefer());
	        pstmt.setString(++i, vo.getAddr1());
	        pstmt.setString(++i, vo.getAddr2());
	        pstmt.setString(++i, vo.getPropCity());
	        pstmt.setString(++i, vo.getZip());
	        pstmt.setString(++i, vo.getPropCountry());
	        pstmt.setString(++i, vo.getPropContactname());
	        pstmt.setLong(++i, vo.getPropContacttel());
	        pstmt.setDate(++i, (vo.getPropPurDate() == null ? null : new java.sql.Date(vo.getPropPurDate().getTime())));
	        pstmt.setDouble(++i, vo.getPropPurValue());
	        pstmt.setDouble(++i, vo.getPropMktValue());
	        pstmt.setString(++i, vo.getCurrencyCode());
	        pstmt.setDate(++i, (vo.getValDt() == null ? null : new java.sql.Date(vo.getValDt().getTime())));
	        pstmt.setDate(++i, (vo.getValRemindDt() == null ? null : new java.sql.Date(vo.getValRemindDt().getTime())));
	        pstmt.setString(++i, vo.getValAgent());
	        pstmt.setString(++i, vo.getValBasis());
	        pstmt.setString(++i, vo.getPropRmks());
	        pstmt.setLong(++i, vo.getArea());
	        pstmt.setLong(++i, vo.getRemLandArea());
	        pstmt.setString(++i, vo.getUom());
	        pstmt.setString(++i, vo.getPlotNo());
	        pstmt.setString(++i, vo.getSdJobNo());
	        pstmt.setString(++i, vo.getMapsheet());
	        pstmt.setString(++i, vo.getPermitNo());
	        pstmt.setDate(++i, (vo.getPermitDt() == null ? null : new java.sql.Date(vo.getPermitDt().getTime())));
	        pstmt.setString(++i, "A");
	        pstmt.setInt(++i, vo.getPropCode());
	        int j = pstmt.executeUpdate();
	        DBConnection.getInstance().returnConnection(con, pstmt);
	        return j;
	  }
	  public Integer deleteProperty(Integer propertyid) throws Exception
	  {
		    Connection con = DBConnection.getInstance().getConnection();
	        PreparedStatement pstmt = con.prepareStatement("UPDATE PROPERTY SET STATUS = 'D' WHERE PROP_Code = ?");
	        int i = 0;
	        pstmt.setInt(1, propertyid);
	        i = pstmt.executeUpdate();		  
	        DBConnection.getInstance().returnConnection(con, pstmt);
	        return i;
	  }
}
	
	

