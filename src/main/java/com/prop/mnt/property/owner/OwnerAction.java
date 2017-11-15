/**
 * 
 */
package com.prop.mnt.property.owner;

/**
 * @author sahitya
 *
 */
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.Preparable;
import com.prop.mnt.common.BaseActionSupport;
import com.prop.mnt.common.StaticDataBean;

public class OwnerAction  extends BaseActionSupport implements Preparable {

	private static final long serialVersionUID = 5515593704788890868L;
	private static final Logger log = LoggerFactory.getLogger(OwnerAction.class);
	private OwnerDAO ownersBn = new OwnerDAO();
	private OwnerVO ownerVo;
	private List<OwnerVO> ownerList;
	private Map<String, String> stateList;
	private List<String> countryList;
	private Map<String, String> ownTypeList;
	
	public void prepare(){
		try{
			setDefaultData();
		}catch(Exception e){
			log.error("Exception in display:"+e);
		}
	}
	
	public String display(){
		try{
			ownerVo = new OwnerVO();
			setDefaultData();
			return "success";
		}catch(Exception e){
			log.error("Exception in display:"+e);
			return "error";
		}
	}
	public String showOwner(){
		try{
			ownerVo = ownersBn.getOwnerInfo(ownerVo.getOwnCode());
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
			if(ownerVo.getOwnCode() == 0){
				i = ownersBn.createOwner(ownerVo);
		        if( i > 0){
		        	addActionMessage("Owner created successfully.");
		        }
			}else{
				i = ownersBn.updateOwner(ownerVo);
		        if( i > 0){
		        	addActionMessage("Owner updated successfully.");
		        }
			}
	        ownerVo = new OwnerVO();
	        setDefaultData();
	        return "success";
		}catch(Exception e){
			log.error("Exception in execute:"+e);
			return "error";
		}        
	}
	public String deleteOwner(){
		try{
			int i = ownersBn.deleteOwner(ownerVo.getOwnCode());
	        if( i > 0){
	        	addActionMessage("Owner deleted successfully.");
	        }
	        ownerVo = new OwnerVO();
			setDefaultData();
	        return "success";
		}catch(Exception e){
			log.error("Exception in deleteOwner:"+e);
			return "error";
		}        
	}
	public void setDefaultData()throws Exception{
		stateList = StaticDataBean.getObject().getUSStates();
		countryList = StaticDataBean.getObject().getCountries();
		ownTypeList = StaticDataBean.getObject().getOwnerTypes();
		ownerList = ownersBn.getOwnerList();
	}

	public OwnerVO getOwnerVo() {
		return ownerVo;
	}

	public void setOwnerVo(OwnerVO ownerVo) {
		this.ownerVo = ownerVo;
	}

	public List<OwnerVO> getOwnerList() {
		return ownerList;
	}

	public void setOwnerList(List<OwnerVO> ownerList) {
		this.ownerList = ownerList;
	}

	public Map<String, String> getStateList() {
		return stateList;
	}

	public void setStateList(Map<String, String> stateList) {
		this.stateList = stateList;
	}

	public List<String> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<String> countryList) {
		this.countryList = countryList;
	}

	public Map<String, String> getOwnTypeList() {
		return ownTypeList;
	}

	public void setOwnTypeList(Map<String, String> ownTypeList) {
		this.ownTypeList = ownTypeList;
	}
	
}


