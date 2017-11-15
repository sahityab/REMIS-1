package com.prop.mnt.property.property;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;
import com.prop.mnt.common.BaseActionSupport;
import com.prop.mnt.property.owner.OwnerAction;
import com.prop.mnt.property.owner.OwnerDAO;
import com.prop.mnt.property.owner.OwnerVO;

public class PropertyAction  extends BaseActionSupport implements Preparable {
	private static final long serialVersionUID = 5515593704788890868L;
	private static final Logger log = LoggerFactory.getLogger(PropertyAction.class);
	private PropertyDAO propertyBn = new PropertyDAO();
	private PropertyVO propertyVo = new PropertyVO();
	private Map<String, String> propList;
	private Map<String, String> propTypes;
	private Map<String, String> propCatList;
	private Map<String, String> landStatus;
	private Map<String, String> currencyList;
	private Map<String, String> valusBasisList;
	private Map<String, String> unitOfMeasList;
	private Map<String, String> governList;
	
	public void prepare(){
		try{
			setDefaultData();
		}catch(Exception e){
			log.error("Exception in prepare:"+e);
		}
	}
	public String display(){
		try{
			propertyVo = new PropertyVO();
			setDefaultData();
			return "success";
		}catch(Exception e){
			log.error("Exception in display:"+e);
			return "error";
		}
	}
	public String showProperty(){
		try{
			propertyVo = propertyBn.getPropInfo(propertyVo.getPropCode());
			setDefaultData();
	        return "success";
		}catch(Exception e){
			log.error("Exception in display:"+e);
			return "error";
		}
	}
	public String execute(){
		try{
			int i = 0;
			if(propertyVo.getPropCode() == 0){
				//propertyVo.setPropOwnCode((String)ActionContext.getContext().getSession().get("userid")); 
				propertyVo.setUserID((Integer)ActionContext.getContext().getSession().get("userid"));
				i = propertyBn.createProperty(propertyVo);
		        if( i > 0){
		        	addActionMessage("Property created successfully.");
		        }
			}else{
				i = propertyBn.updateProperty(propertyVo);
		        if( i > 0){
		        	addActionMessage("Property updated successfully.");
		        }
			}
	        propertyVo = new PropertyVO();
	        setDefaultData();
	        return "success";
		}catch(Exception e){
			log.error("Exception in execute:"+e);
			return "error";
		}        
	}
	public String deleteProperty(){
		try{
			int i = propertyBn.deleteProperty(propertyVo.getPropCode());
	        if( i > 0){
	        	addActionMessage("Property deleted successfully.");
	        }
	        propertyVo = new PropertyVO();
			setDefaultData();
	        return "success";
		}catch(Exception e){
			log.error("Exception in deleteProperty:"+e);
			return "error";
		}        
	}
	public void setDefaultData()throws Exception{
		
		Map<String, Map<String,String>> dataMap=propertyBn.getDataMap((Integer)ActionContext.getContext().getSession().get("userid"));
		propList=dataMap.get("propList");
		propTypes=dataMap.get("propTypes");
		propCatList=dataMap.get("propCatList");
		landStatus=dataMap.get("landStatus");
		currencyList=dataMap.get("currencyList");
		valusBasisList=dataMap.get("valusBasisList");
		unitOfMeasList=dataMap.get("unitOfMeasList");
		
		//propList = propertyBn.getPropList((String)ActionContext.getContext().getSession().get("userid"));
		System.out.println("propList size="+propList.size());
		//propTypes=  propertyBn.getPropTypes();
		//propCatList =  propertyBn.getPropCatList();
		//landStatus =  propertyBn.getLandStat();
		//currencyList =  propertyBn.getCurrencyList();
		//valusBasisList =  propertyBn.getValueBasisList();
		//unitOfMeasList = propertyBn.getUnitofMeasList();
	}

	public PropertyVO getPropertyVo() {
		return propertyVo;
	}
	public void setPropertyVo(PropertyVO propertyVo) {
		this.propertyVo = propertyVo;
	}

	public Map<String, String> getPropList() {
		return propList;
	}
	public void setPropList( Map<String, String> propList) {
		this.propList = propList;
	}
	public Map<String, String> getPropTypes() {
		return propTypes;
	}
	public void setPropTypes(Map<String, String> propTypes) {
		this.propTypes = propTypes;
	}
	public Map<String, String> getPropCatList() {
		return propCatList;
	}
	public void setPropCatList(Map<String, String> propCatList) {
		this.propCatList = propCatList;
	}
	public Map<String, String> getLandStatus() {
		return landStatus;
	}
	public void setLandStatus(Map<String, String> landStatus) {
		this.landStatus = landStatus;
	}
	public Map<String, String> getCurrencyList() {
		return currencyList;
	}
	public void setCurrencyList(Map<String, String> currencyList) {
		this.currencyList = currencyList;
	}
	public Map<String, String> getValusBasisList() {
		return valusBasisList;
	}
	public void setValusBasisList(Map<String, String> valusBasisList) {
		this.valusBasisList = valusBasisList;
	}
	public Map<String, String> getUnitOfMeasList() {
		return unitOfMeasList;
	}
	public void setUnitOfMeasList(Map<String, String> unitOfMeasList) {
		this.unitOfMeasList = unitOfMeasList;
	}
	public Map<String, String> getGovernList() {
		return governList;
	}
	public void setGovernList(Map<String, String> governList) {
		this.governList = governList;
	}
}
