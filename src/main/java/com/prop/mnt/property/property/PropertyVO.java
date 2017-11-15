package com.prop.mnt.property.property;

import java.util.Date;


//@Entity
//@Table(name="prop_user")
//@Access(value=AccessType.FIELD)
public class PropertyVO {

	//@Id 
	//@GeneratedValue
	//@Column(name = "PROP_Code")
	 private Integer propCode;
	 
	//@Column(name = "PROP_Name")
	 private String propName;
	//@Column(name = "PROP_TYPE_CODE")
	 private String propTypeCode;
	//@Column(name = "LAND_STATUS_CODE")
	 private String landStatusCode;
	//@Column(name = "PROP_CAT_CODE")
	 private String propCatCode;
	 private String propOwnCode;
	 private String relationCode;
	//@Column(name = "No_of_Div")
	 private Integer noOfDiv;
	//@Column(name = "PROP_Deed_No")
	 private String propDeedNo;
	//@Column(name = "FILE_REFER")
	 private String fileRefer;
	//@Column(name = "ADDR1")
	 private String addr1;	 
	//@Column(name = "ADDR2")
	 private String addr2;
	//@Column(name = "PROP_city")
	 private String propCity;
	//@Column(name = "ZIP")
	 private String zip;
	//@Column(name = "PROP_Country")
	 private String propCountry;
	//@Column(name = "PROP_Contactname")
	 private String propContactname;
	//@Column(name = "PROP_Contacttel")
	 private Long propContacttel;
	//@Column(name = "PROP_PUR_DATE")
	 private Date propPurDate;
	//@Column(name = "PROP_PUR_Value")
	 private Double propPurValue;
	//@Column(name = "PROP_MKT_Value")
	 private Double propMktValue;
	//@Column(name = "Currency_code")
	 private String currencyCode;
	//@Column(name = "VAL_DT")
	 private Date valDt;
	//@Column(name = "VAL_REMIND_DT")
	 private Date valRemindDt;
	//@Column(name = "VAL_AGENT")
	 private String valAgent;
	//@Column(name = "VAL_BASIS")
	 private String valBasis;
	//@Column(name = "PROP_Rmks")
	 private String propRmks;
	//@Column(name = "AREA")
	 private Long area;
	//@Column(name = "REM_LAND_AREA")
	 private Long remLandArea;
	//@Column(name = "UOM")
	 private String uom;
	//@Column(name = "PLOT_NO")
	 private String plotNo;
	//@Column(name = "SDJOB_NO")
	 private String sdJobNo;
	//@Column(name = "MAPSHEET")
	 private String mapsheet;	
	//@Column(name = "PERMIT_NO")
	 private String permitNo;
	//@Column(name = "PERMIT_DT")
	 private Date permitDt;
	//@Column(name = "STATUS")
	 private String status;
	 
	//@Column(name = "USERID")
	 private String userId;
	 private Integer userID;
	 
	 
	 PropertyVO (){}
	 
	public PropertyVO(Integer propCode,String propName){
		this.propCode=propCode;
		this.propName=propName;
		}
	 
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getPropCode() {
		return propCode;
	}
	public void setPropCode(Integer propCode) {
		this.propCode = propCode;
	}
	public String getPropName() {
		return propName;
	}
	public void setPropName(String propName) {
		this.propName = propName;
	}
	public String getPropTypeCode() {
		return propTypeCode;
	}
	public void setPropTypeCode(String propTypeCode) {
		this.propTypeCode = propTypeCode;
	}
	public String getLandStatusCode() {
		return landStatusCode;
	}
	public void setLandStatusCode(String landStatusCode) {
		this.landStatusCode = landStatusCode;
	}
	public String getPropCatCode() {
		return propCatCode;
	}
	public void setPropCatCode(String propCatCode) {
		this.propCatCode = propCatCode;
	}
	public String getPropOwnCode() {
		return propOwnCode;
	}
	public void setPropOwnCode(String propOwnCode) {
		this.propOwnCode = propOwnCode;
	}
	public Integer getNoOfDiv() {
		return noOfDiv;
	}
	public void setNoOfDiv(Integer noOfDiv) {
		this.noOfDiv = noOfDiv;
	}
	public String getPropDeedNo() {
		return propDeedNo;
	}
	public void setPropDeedNo(String propDeedNo) {
		this.propDeedNo = propDeedNo;
	}
	public String getFileRefer() {
		return fileRefer;
	}
	public void setFileRefer(String fileRefer) {
		this.fileRefer = fileRefer;
	}
	public String getPropCity() {
		return propCity;
	}
	public void setPropCity(String propCity) {
		this.propCity = propCity;
	}
	public String getPropCountry() {
		return propCountry;
	}
	public void setPropCountry(String propCountry) {
		this.propCountry = propCountry;
	}
public String getPropContactname() {
		return propContactname;
	}
	public void setPropContactname(String propContactname) {
		this.propContactname = propContactname;
	}
	public Long getPropContacttel() {
		return propContacttel;
	}
	public void setPropContacttel(Long propContacttel) {
		this.propContacttel = propContacttel;
	}
	public Double getPropPurValue() {
		return propPurValue;
	}
	public void setPropPurValue(Double propPurValue) {
		this.propPurValue = propPurValue;
	}
	public Double getPropMktValue() {
		return propMktValue;
	}
	public void setPropMktValue(Double propMktValue) {
		this.propMktValue = propMktValue;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getValAgent() {
		return valAgent;
	}
	public void setValAgent(String valAgent) {
		this.valAgent = valAgent;
	}
	public String getValBasis() {
		return valBasis;
	}
	public void setValBasis(String valBasis) {
		this.valBasis = valBasis;
	}
	public String getPropRmks() {
		return propRmks;
	}
	public void setPropRmks(String propRmks) {
		this.propRmks = propRmks;
	}
	public Long getArea() {
		return area;
	}
	public void setArea(Long area) {
		this.area = area;
	}
	public Long getRemLandArea() {
		return remLandArea;
	}
	public void setRemLandArea(Long remLandArea) {
		this.remLandArea = remLandArea;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getPlotNo() {
		return plotNo;
	}
	public void setPlotNo(String plotNo) {
		this.plotNo = plotNo;
	}
	public String getSdJobNo() {
		return sdJobNo;
	}
	public void setSdJobNo(String sdJobNo) {
		this.sdJobNo = sdJobNo;
	}
	public String getMapsheet() {
		return mapsheet;
	}
	public void setMapsheet(String mapsheet) {
		this.mapsheet = mapsheet;
	}
	public String getPermitNo() {
		return permitNo;
	}
	public void setPermitNo(String permitNo) {
		this.permitNo = permitNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public Date getPropPurDate() {
		return propPurDate;
	}
	public void setPropPurDate(Date propPurDate) {
		this.propPurDate = propPurDate;
	}
	public Date getValDt() {
		return valDt;
	}
	public void setValDt(Date valDt) {
		this.valDt = valDt;
	}
	public Date getValRemindDt() {
		return valRemindDt;
	}
	public void setValRemindDt(Date valRemindDt) {
		this.valRemindDt = valRemindDt;
	}
	public Date getPermitDt() {
		return permitDt;
	}
	public void setPermitDt(Date permitDt) {
		this.permitDt = permitDt;
	}
	public String getRelationCode() {
		return relationCode;
	}
	public void setRelationCode(String relationCode) {
		this.relationCode = relationCode;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PropertyVO [propCode=");
		builder.append(propCode);
		builder.append(", propName=");
		builder.append(propName);
		builder.append(", propTypeCode=");
		builder.append(propTypeCode);
		builder.append(", landStatusCode=");
		builder.append(landStatusCode);
		builder.append(", propCatCode=");
		builder.append(propCatCode);
		builder.append(", propOwnCode=");
		builder.append(propOwnCode);
		builder.append(", noOfDiv=");
		builder.append(noOfDiv);
		builder.append(", propDeedNo=");
		builder.append(propDeedNo);
		builder.append(", fileRefer=");
		builder.append(fileRefer);
		builder.append(", addr1=");
		builder.append(addr1);
		builder.append(", addr2=");
		builder.append(addr2);
		builder.append(", propCity=");
		builder.append(propCity);
		builder.append(", zip=");
		builder.append(zip);
		builder.append(", propCountry=");
		builder.append(propCountry);
		builder.append(", propContactname=");
		builder.append(propContactname);
		builder.append(", propContacttel=");
		builder.append(propContacttel);
		builder.append(", propPurDate=");
		builder.append(propPurDate);
		builder.append(", propPurValue=");
		builder.append(propPurValue);
		builder.append(", propMktValue=");
		builder.append(propMktValue);
		builder.append(", currencyCode=");
		builder.append(currencyCode);
		builder.append(", valDt=");
		builder.append(valDt);
		builder.append(", valRemindDt=");
		builder.append(valRemindDt);
		builder.append(", valAgent=");
		builder.append(valAgent);
		builder.append(", valBasis=");
		builder.append(valBasis);
		builder.append(", propRmks=");
		builder.append(propRmks);
		builder.append(", area=");
		builder.append(area);
		builder.append(", remLandArea=");
		builder.append(remLandArea);
		builder.append(", uom=");
		builder.append(uom);
		builder.append(", plotNo=");
		builder.append(plotNo);
		builder.append(", sdJobNo=");
		builder.append(sdJobNo);
		builder.append(", mapsheet=");
		builder.append(mapsheet);
		builder.append(", permitNo=");
		builder.append(permitNo);
		builder.append(", permitDt=");
		builder.append(permitDt);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}
}
