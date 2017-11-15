package com.prop.mnt.logon.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.prop.mnt.service.ContractService;
import com.prop.mnt.service.ContractServiceImpl;
import com.prop.mnt.vo.ContractVo;

public class AjaxJsonAction extends ActionSupport{

private Map<Integer, String> divisionMap = new LinkedHashMap<Integer, String>();
private ContractService contractService;
private Integer propId;
private Integer notiId;

private InputStream inputStream;

public AjaxJsonAction(){
	contractService=new ContractServiceImpl();
}

public String execute() {
	System.out.println("In ajax execut method id="+propId);
	List<ContractVo> contarList=contractService.findPropContrctors(propId);
	
	for(ContractVo divi:contarList)
		divisionMap.put(divi.getContratorId(), divi.getContractorName());
	
	System.out.println(divisionMap.size());
        return SUCCESS;
}

	public String closenotification(){
		System.out.println("notiId="+notiId);
		 int i=contractService.updateNotification(notiId);
		 if(i>0) inputStream = new ByteArrayInputStream("success".getBytes(StandardCharsets.UTF_8));
		 else  inputStream = new ByteArrayInputStream("fail".getBytes(StandardCharsets.UTF_8));
		 
		 
	         return i>0 ?SUCCESS:ERROR;
	}


public Map<Integer, String> getDivisionMap() {
	return divisionMap;
}


public void setDivisionMap(Map<Integer, String> divisionMap) {
	this.divisionMap = divisionMap;
}



public Integer getPropId() {
	return propId;
}

public void setPropId(Integer propId) {
	this.propId = propId;
}

public Integer getNotiId() {
	return notiId;
}

public void setNotiId(Integer notiId) {
	this.notiId = notiId;
}



}